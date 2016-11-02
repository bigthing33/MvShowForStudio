package com.cyq.mvshow.utils;

import com.cyq.mvshow.base.BaseInterfaceListener;
import com.google.gson.Gson;

import java.util.Random;

/**
 * Created by win7 on 2016/10/28.
 */

public class DataUtils {
    /**
     * 随机生成一个四位long类型的随机数
     *
     * @return
     */
    public static long getRandomLong4() {
        Random rd = new Random();
        long l2 = 0;
        for (int i = 1; i < 100; i++) {
            long l1 = (int) (Math.random() * 9000 + 1000);
            l2 = rd.nextInt(9000) + 1000;
        }
        return l2;
    }

    /**
     * 随机生成一个四位long类型的随机数
     *
     * @return
     */
    public static long getRandomLong3() {
        Random rd = new Random();
        long l2 = 0;
        l2 = (int) (Math.random() * 300);
        return l2;
    }

    public static void stringConvertClass(String s, Class<?> exampleClass, BaseInterfaceListener listener) {
        Gson gson = new Gson();
        try {
            listener.success(gson.fromJson(s, exampleClass));
        } catch (Exception e) {
            listener.fail(e);
        }
    }
}
