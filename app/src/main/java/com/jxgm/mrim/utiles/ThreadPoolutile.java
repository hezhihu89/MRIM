package com.jxgm.mrim.utiles;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hezhihu89 on 16-1-24.
 */
public class ThreadPoolutile {

    private static ExecutorService threadPool = Executors.newFixedThreadPool(5);

    public static void task(Runnable task) {
        threadPool.execute(task);
    }

}
