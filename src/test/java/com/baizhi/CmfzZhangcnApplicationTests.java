package com.baizhi;

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmfzZhangcnApplicationTests {

    @Resource
    private BannerDao bannerDao;

    @Test
    public void contextLoads() {


        bannerDao.save(new Banner("1", "2", "3", "3", "3", new Date()));

    }

}
