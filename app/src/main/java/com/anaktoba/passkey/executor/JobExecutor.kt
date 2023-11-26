package com.anaktoba.passkey.executor

import java.util.concurrent.Executor
import java.util.concurrent.SynchronousQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit


/**
 * @author Nicolas Manurung (nicolas.manurung@dana.id)
 * @version JobExecutor, v 0.1 26/11/23 18.17 by Nicolas Manurung
 */
object JobExecutor : Executor {

    private val threadFactory = object : ThreadFactory {

        private var counter = 0

        override fun newThread(runnable: Runnable): Thread {
            return Thread(runnable, "android_${counter++}")
        }

    }
    private val threadPoolExecutor = ThreadPoolExecutor(
        3,
        Integer.MAX_VALUE,
        10,
        TimeUnit.SECONDS,
        SynchronousQueue(),
        threadFactory
    )

    override fun execute(runnable: Runnable) {
        threadPoolExecutor.execute(runnable)
    }
}