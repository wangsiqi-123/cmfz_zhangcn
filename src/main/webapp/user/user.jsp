<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>
    $(function () {

        //初始化表单
        $("#utable").jqGrid({
            url: "${path}/user/queryByPage",
            datatype: "json",
            rowNum: 2,  //每页展示条数   page   rows
            rowList: [2, 3, 10, 20, 30],  //可选展示条数
            styleUI: "Bootstrap",
            pager: '#upage',  //分页工具栏
            viewrecords: true,  //是否显示总条数
            autowidth: true,
            height: "auto",
            colNames: ['Id', '头像', '名字', '法名', '密码', '盐值', '状态', '签名', '电话', '上传时间', '所属上师'],
            colModel: [
                {name: 'id', width: 55},
                {name: 'photo', editable: true, width: 90, align: "center"},
                {name: 'name', editable: true, width: 100, align: "center"},
                {name: 'nickName', width: 80, align: "center"},
                {name: 'password', width: 80, align: "center"},
                {name: 'salt', width: 80, align: "center"},
                {
                    name: 'status', width: 80, align: "center",
                    formatter: function (cellValue, option, row) {
                        if (cellValue == 1) {
                            //展示
                            return "<button class='btn btn-danger' onclick='updateUserStatus(\"" + row.id + "\",\"" + cellValue + "\")' >冻结</button>";
                        } else {
                            //不展示
                            return "<button class='btn btn-success' onclick='updateUserStatus(\"" + row.id + "\",\"" + cellValue + "\")' >解除冻结</button>";
                        }
                    }
                },
                {name: 'sign', width: 80, align: "center"},
                {name: 'phone', width: 80, align: "center"},
                {name: 'crea_date', width: 80, align: "center"},
                {name: 'guru_id', width: 80, align: "center"}
            ]
        });

        //处理增删改查操作
        $("#utable").jqGrid('navGrid', '#upage',
            {edit: false, add: false, del: false, search: false, deltext: "删除"}
        );

    });

    //修改状态
    function updateUserStatus(id, status) {

        if (status == 1) {
            $.ajax({
                url: "${path}/user/updateStatus",
                type: "post",
                dataType: "JSON",
                data: {"id": id, "status": "2"},
                success: function () {
                    //刷新页面
                    $("#utable").trigger("reloadGrid");
                }
            });
        } else {
            $.ajax({
                url: "${path}/user/updateStatus",
                type: "post",
                dataType: "json",
                data: {"id": id, "status": "1"},
                success: function () {
                    //刷新页面
                    $("#utable").trigger("reloadGrid");
                }
            });
        }
    }

    //点击导出按钮
    $("#btnExport").click(function () {

        $.post("${path}/user/export", function (data) {
        }, "json");

    });


    //点击发送短信验证码按钮
    $("#btnphone").click(function () {

        //获取手机号
        var phone = $("#phoneInput").val();
        //alert(phone);

        $.post("${path}/user/getPhone?phone=" + phone, function (data) {
        }, "json");

    });


</script>

<%--初始化面板--%>
<div class="panel panel-warning">

    <%--面板头--%>
    <div class="panel panel-heading">
        <h2>用户信息</h2>
    </div>

    <ul class="nav nav-tabs">
        <li class="active"><a href="#">用户信息</a></li>
    </ul>

    <div class="panel panel-body">
        <button id="btnExport" class="btn btn-info">导出用户数据</button>&emsp;
        <div align="right">
            <input id="phoneInput" name="phone" type="text"/>&emsp;
            <button id="btnphone" class="btn btn-info">发送验证码</button>
        </div>
    </div>

    <%--初始表单--%>
    <table id="utable"/>

    <%--分页工具栏--%>
    <div id="upage"/>

</div>