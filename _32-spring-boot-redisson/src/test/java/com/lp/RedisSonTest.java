package com.lp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


/**
 * Spring Data Redis测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=App.class)
public class RedisSonTest {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	@Resource
	private RedissonClient redissonClient;

	@Test
	public void test() throws InterruptedException {
		//创建锁
		RLock helloLock = redissonClient.getLock("hello");
		System.out.println("完成创建锁");
		//加锁
		helloLock.lock();
		try {
			System.out.println("进入锁");
			Thread.sleep(1000 * 40);
		} finally {
			//释放锁
			helloLock.unlock();
			System.out.println("退出锁");
		}

	}

	/**
	 * 添加一个字符串
	 */
	@Test
	public void testSet() {
		this.redisTemplate.opsForValue().set("springBootKey", "测试是否保存到redis集群");
	}

	/**
	 * 获取一个字符串
	 */
	@Test
	public void testGet(){
		String value = (String)this.redisTemplate.opsForValue().get("springBootKey");
		System.out.println(value);
		String value2 = (String)this.redisTemplate.opsForValue().get("a");
		System.out.println(value2);
	}


}
