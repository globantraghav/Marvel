package com.example.marvel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@Throws(InterruptedException::class)
fun <T> getValue(liveData: LiveData<T>):T{
    val data = arrayOfNulls<Any>(1)
    val latch = CountDownLatch(1)
    liveData.observeForever {
        data[0] = it
        latch.countDown()
    }
    latch.await(2, TimeUnit.SECONDS)
    @Suppress("UNCHECKED_CAST")
    return data[0] as T
}

fun <T> LiveData<T>.getOrAwaitValue(
    time:Long=2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve:()->Unit={}
):T{
    var data:T?=null
    val latch= CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(t: T) {
            data = t
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)
    afterObserve.invoke()

    if (!latch.await(time,timeUnit)){
        this.removeObserver(observer)
        throw TimeoutException("LiveData value was never set.")
    }
    @Suppress("UNCHECKED_CAST")
    return data as T
}
