package com.wzq.redis.base;

import redis.clients.jedis.Jedis;

/**
 * Author: zhenqing.wang <wangzhenqing1008@163.com>
 * Date: 2016-06-20 19:52:22
 * Description: List
 */
public class JedisListDemo {

    private Jedis jedis;

    public void init() {
        jedis = new Jedis("127.0.0.1", 6379);
    }

    public void push() {
        String key = "company";
        jedis.lpush(key, "baidu", "tencent", "taobao");
        System.out.println(jedis.llen(key));
        System.out.println(jedis.lrange(key, 0, -1));
    }

    public static void main(String[] args) {
        JedisListDemo jedisListDemo = new JedisListDemo();
        jedisListDemo.init();
        jedisListDemo.push();
    }
}
