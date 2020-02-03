package com.lp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=App.class)
public class AnyTimeTest {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testGet(){
        String value = (String)this.redisTemplate.opsForValue().get("7f1c8841-fac2-4e14-bfc1-82cfcece3cbb");
        System.out.println(value);
    }

}
