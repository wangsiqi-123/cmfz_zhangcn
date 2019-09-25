package com.baizhi;

import com.alibaba.fastjson.JSONObject;
import io.goeasy.GoEasy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestGoEasy {

    @Test
    public void goEasy() {

        //配置必要参数    参数： restHost,自己的appkey
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-df0a45499f274b2bae29ae50a6a12dc9");

        //发布消息   参数：channel管道名称 ,发送的内容
        goEasy.publish("cmfz-162", "佛挡杀佛爽肤水");
    }

    @Test
    public void goEasyEcharts() {

        for (int i = 0; i < 10; i++) {

            System.out.println(i);

            //创建随机数
            Random random = new Random();

            Integer[] boysRandoms = {random.nextInt(100), random.nextInt(800), random.nextInt(900),
                    random.nextInt(200), random.nextInt(300), random.nextInt(700)
            };
            Integer[] girlsRandoms = {random.nextInt(100), random.nextInt(800), random.nextInt(900),
                    random.nextInt(200), random.nextInt(300), random.nextInt(700)
            };

            //创建一个json对象
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("month", Arrays.asList("1月", "2月", "3月", "4月", "5月", "6月"));
            jsonObject.put("boys", boysRandoms);
            jsonObject.put("girls", girlsRandoms);

            //将json对象转化为json字符串
            String content = jsonObject.toJSONString();

            //配置必要参数    参数： restHost,自己的appkey
            GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-df0a45499f274b2bae29ae50a6a12dc9");

            //发布消息   参数：channel管道名称 ,发送的内容
            goEasy.publish("cmfz-162", content);

            try {
                //线程休息
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
