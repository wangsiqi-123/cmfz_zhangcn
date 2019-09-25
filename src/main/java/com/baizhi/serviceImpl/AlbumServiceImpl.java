package com.baizhi.serviceImpl;

import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

    @Resource
    private AlbumDao albumDao;

    @Override
    public HashMap<String, Object> queryByPage(Integer page, Integer rows) {

        HashMap<String, Object> map = new HashMap<>();

        //总条数
        Integer records = albumDao.queryRecords();
        map.put("records", records);

        //总页数
        Integer total = records % rows == 0 ? records / rows : records / rows + 1;
        map.put("total", total);

        //当前页
        map.put("page", page);

        //数据
        List<Album> albums = albumDao.queryByPage((page - 1) * rows, rows);
        map.put("rows", albums);

        return map;
    }
}
