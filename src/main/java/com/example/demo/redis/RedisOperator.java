package com.example.demo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Repository;
import java.util.concurrent.TimeUnit;

@Repository
public class RedisOperator {
	@Autowired
	private StringRedisTemplate template;
	private ValueOperations<String, String> getOps(){
		return template.opsForValue();
	}

	public void set(String key, String value, long expireSeconds) {
		this.getOps().set(key, value, expireSeconds, TimeUnit.SECONDS);
	}
	public String get(String key) {
		return this.getOps().get(key);
	}
	public void remove(String key) {
		template.delete(key);
	}
}
