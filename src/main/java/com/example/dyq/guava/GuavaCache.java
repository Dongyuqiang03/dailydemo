package com.example.dyq.guava;

import com.google.common.cache.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: dongyuqiang
 * @Date: 2019/9/16 10:23
 * @Description: guava 缓存
 */
public class GuavaCache {

    public static void main(String[] args) {
        try {
//        ArrayList<String> list=new ArrayList<>();
//        list.add("a");
//        list.add("b");
//        list.add("c");
//        initGuavaCache(list);
            initGuavaLoadCache();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void initGuavaCache(ArrayList<String> list){

        // 通过CacheBuilder构建一个缓存实例
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(100) // 设置缓存的最大容量
                .expireAfterWrite(1, TimeUnit.MINUTES) // 设置缓存在写入一分钟后失效
                .concurrencyLevel(10) // 设置并发级别为10
                .recordStats() // 开启缓存统计
                .build();
        // 放入缓存
        cache.put("key", list.toString());
        // 获取缓存
        String value = cache.getIfPresent("key");

    }


    public static void initGuavaLoadCache() throws Exception{

        final LoadingCache<String, User> userCache = CacheBuilder.newBuilder()
                // 设置缓存在写入10分钟后，通过CacheLoader的load方法进行刷新
//                .refreshAfterWrite(10, TimeUnit.SECONDS)
                // jdk8以后可以使用 Duration
                .refreshAfterWrite(Duration.ofMinutes(10))
                //设置并发级别为8，并发级别是指可以同时写缓存的线程数
                .concurrencyLevel(8)
                //设置写缓存后8秒钟过期
                .expireAfterWrite(8, TimeUnit.SECONDS)
                //设置写缓存后1秒钟刷新
                .refreshAfterWrite(1, TimeUnit.SECONDS)
                //设置缓存容器的初始容量为5
                .initialCapacity(5)
                //设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项
                .maximumSize(100)
                //设置要统计缓存的命中率
                .recordStats()
                //设置缓存的移除通知
                .removalListener((RemovalNotification<Object, Object> notification)-> System.out.println(notification.getKey() + " 被移除了，原因： " + notification.getCause()))
                .build(new CacheLoader<String, User>() {
                    @Override
                    public User load(String key) {
                        // 缓存加载逻辑
                        System.out.println("缓存不存在，重新加载");
                        return new User("tony" + key, key);
                    }
                });

        // 第一次读取
        for (int i = 0; i < 10; i++) {
            User user = userCache.get("uid" + i);
            System.out.println(user);
        }

        // 第二次读取
        for (int i = 0; i < 10; i++) {
            User user = userCache.get("uid" + i);
            System.out.println(user);
        }
        System.out.println("cache stats:");
        //最后打印缓存的命中率等 情况
        System.out.println(userCache.stats().toString());
    }

}
