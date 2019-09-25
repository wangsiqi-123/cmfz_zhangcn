package com.baizhi.dao;

import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterDao extends BaseDao<Chapter> {

    List<Chapter> queryByPage(@Param("albumId") String albumId, @Param("start") Integer start, @Param("rows") Integer rows);

}
