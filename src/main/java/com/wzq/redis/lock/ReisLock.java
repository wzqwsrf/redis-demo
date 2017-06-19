package com.wzq.redis.lock;

import redis.clients.jedis.Jedis;

/**
 * Author: zhenqing.wang <wangzhenqing1008@163.com>
 * Date: 2017-6-19 13:10:22
 * Description: redis分布式锁实现，主要是setnx
 */
public class ReisLock {
    private Jedis jedis;

    public void init() {
        jedis = new Jedis("127.0.0.1", 6379);
    }

    public boolean acquireLock(final String lock, final long expired) {

        // expired 单位为秒
        long value = System.currentTimeMillis() + expired * 1000 + 1;

        long acquired = jedis.setnx(lock, String.valueOf(value));
        if (acquired == 1) {
            return true;
        }
        long oldValue = Long.valueOf(jedis.get(lock));

        if (oldValue >= System.currentTimeMillis()) {
            return false;
        }

        String getValue = jedis.getSet(lock, String.valueOf(value));
        if (Long.valueOf(getValue) == oldValue) {
            return true;
        }
        return false;
    }

    public long releaseLock(final String lock) {
        long current = System.currentTimeMillis();
        if (current < Long.valueOf(jedis.get(lock))) {
            return jedis.del(lock);
        }
        return 0L;
    }

    public static void main(String[] args) {
        //TODO
    }
}
