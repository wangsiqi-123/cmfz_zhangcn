<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<script>
    $(function () {

        $("#abtable").jqGrid({
            url: '${path}/album/queryByPage',
            datatype: "json",
            autowidth: true,
            styleUI: "Bootstrap",
            height: "auto",
            rowNum: 8,
            rowList: [8, 10, 20, 30],
            pager: '#abpage',
            viewrecords: true,
            colNames: ['ID', '名称', '封面', '作者', '集数', '内容', '上传时间'],
            colModel: [
                {name: 'id', index: 'id', width: 55},
                {name: 'title', index: 'invdate', width: 90},
                {name: 'cover_img', index: 'name', width: 100},
                {name: 'author', index: 'amount', width: 80, align: "right"},
                {name: 'count', index: 'tax', width: 80, align: "right"},
                {name: 'brief', index: 'total', width: 80, align: "right"},
                {name: 'pub_date', index: 'note', width: 150, sortable: false}
            ],
            subGrid: true,  //是否开启子表格  // subgrid_id是在表数据中创建的div标签的id
            subGridRowExpanded: function (subgrid_id, row_id) {  //subgrid_id  子表格id  row_id 行id
                //开启子表格
                addSubGrid(subgrid_id, row_id);
            }
        });
        //处理增删改方法
        $("#abtable").jqGrid('navGrid', '#abpage',
            {add: false, edit: false, del: false}
        );
    });

    //子表格
    function addSubGrid(subgridId, rowId) {

        //tableId
        var subgridTableId = subgridId + "table";

        //工具栏Id
        var pagerId = subgridId + "page";

        //在子表格中创建一个表单table，创建一个工具栏div
        $("#" + subgridId).html("" +
            "<table id='" + subgridTableId + "'/>" +
            "<div id='" + pagerId + "' />"
        );

        //初始子表格
        $("#" + subgridTableId).jqGrid({
            url: "${path}/chapter/queryByPage?albumId=" + rowId,
            editurl: "${path}/chapter/edit?albumId=" + rowId,
            datatype: "json",
            rowNum: 20,
            pager: "#" + pagerId,
            autowidth: true,
            styleUI: "Bootstrap",
            height: "auto",
            colNames: ['Id', '名称', '大小', '时长', '上传时间', '专辑Id', '操作'],
            colModel: [
                {name: "id", index: "num", width: 80, key: true},
                {name: "url", index: "qty", width: 70, align: "right", edittype: "file", editable: true},
                {name: "size", index: "unit", width: 70, align: "right"},
                {name: "duration", index: "total", width: 70},
                {name: "up_date", index: "total", width: 70},
                {name: "album_id", index: "total", width: 70},
                {
                    name: "url", index: "total", width: 70,
                    formatter: function (value) {
                        return "<a href='#' onclick='play(\"" + value + "\")'><span class='glyphicon glyphicon-play-circle'/></a> &emsp; &nbsp; " +
                            "<a href='#' onclick='downloads(\"" + value + "\")'><span class='glyphicon glyphicon-download'/></a> ";
                    }
                }
            ]
        });

        //增删该方法
        $("#" + subgridTableId).jqGrid('navGrid', "#" + pagerId,
            {edit: true, add: true, del: true},
            {},
            {
                closeAfterAdd: true,
                afterSubmit: function (data) {
                    $.ajaxFileUpload({
                        url: "${path}/chapter/uploadChapter",
                        type: "post",
                        datatype: "json",
                        fileElementId: "url",
                        data: {id: data.responseText},
                        success: function () {
                            //刷新页面
                            $("#" + subgridTableId).trigger("reloadGrid");
                        }
                    })

                    return "hello";
                }
            }
        );
    }

    //在线播放
    function play(name) {
        //alert("播放"+name);
        //展示模态框
        $("#myModal").modal("show");
        $("#myAudio").attr("src", "${path}/upload/audio/" + name);

    }

    //下载
    function downloads(name) {
        //alert("下载"+name);

        //向后台发请求  文件下载
        location.href = "${path}/chapter/downloadChapter?fileName=" + name;
    }

</script>

<%--初始化面板--%>
<div class="panel panel-success">

    <%--面板头--%>
    <div class="panel panel-heading">
        <h2>专辑信息</h2>
    </div>

    <ul class="nav nav-tabs">
        <li class="active"><a href="#">专辑信息</a></li>
    </ul>

    <%--初始表单--%>
    <table id="abtable"/>

    <%--分页工具栏--%>
    <div id="abpage"/>

    <%--点击播放模态框--%>
    <div id="myModal" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <audio id="myAudio" controls/>
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

</div>