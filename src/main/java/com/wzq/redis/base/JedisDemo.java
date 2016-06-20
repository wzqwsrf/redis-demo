package com.wzq.redis.base;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Author: zhenqing.wang <wangzhenqing1008@163.com>
 * Date: 2016-06-20 15:43:27
 * Description: Jedis基本用法
 */
public class JedisDemo {

    private Jedis jedis;

    public void init() {
        jedis = new Jedis("127.0.0.1", 6379);
    }

    public void stringDemo() {
        //get and set
        String res = jedis.set("name", "wangzq"); //set值，成功返回ok
        System.out.println(res); //"OK"
        res = jedis.get("name"); //获取key为name的value值
        System.out.println(res); //"wangzq"

        res = jedis.getSet("name", "what's your name"); //将key赋为新值并返回旧值
        System.out.println(res); //"wangzq"
        res = jedis.get("name");
        System.out.println(res); //"what's your name"

        res = jedis.mset("name", "wangzq", "age", "28", "job", "engineer"); //set多个key值
        System.out.println(res); //"OK"
        List<String> resList = jedis.mget("name", "age");
        System.out.println(resList); //"[wangzq, 28]"

        res = jedis.set("current", "2"); //需要赋值为数字
        System.out.println(res); //"OK"
        long num = jedis.incr("current");
        System.out.println(num); //3
        System.out.println(jedis.get("current")); //3

        num = jedis.incrBy("current", 3);
        System.out.println(num); //6

        num = jedis.decr("current");
        System.out.println(num); //5
        num = jedis.decrBy("current", 3);
        System.out.println(num); //2

        jedis.append("name","zhenqing");
        System.out.println(jedis.get("name"));

        res = jedis.substr("name",0,5);
        System.out.println(res);

        jedis.del("name");
        System.out.println(jedis.get("name"));
        jedis.setnx("name","qingqing");
        System.out.println(jedis.get("name"));

        jedis.setex("name", 10, "qq");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(jedis.get("name"));
    }

    public static void main(String[] args) {
        JedisDemo jedisDemo = new JedisDemo();
        jedisDemo.init();
        jedisDemo.stringDemo();
        System.out.println();
    }

}
