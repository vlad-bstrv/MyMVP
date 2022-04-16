package com.vladbstrv.mymvp.utils

import android.os.Handler


private class Subscriber<T>(
    private val handler: Handler,
    private val callback: (T?) -> Unit
) {
    fun invoke(value: T?) {
        handler.post {
            callback.invoke(value)
        }
    }
}

class Publisher<T>(private val isSingle: Boolean = false) {

    private var subscribers: MutableSet<Subscriber<T?>> = mutableSetOf()
    var value: T? = null
        private set
    private var hasFirstValue = false

    fun subscribe(handler: Handler, callback: (T?) -> Unit) {
        val subscriber = Subscriber(handler, callback)
        subscribers.add(subscriber)
        if (hasFirstValue) {
            subscriber.invoke(value)
        }

    }

    fun unsubscribe() {
        subscribers.clear()
    }

    fun post(value: T) {
        if(!isSingle) {
            hasFirstValue = true
            this.value = value
        }

        subscribers.forEach {
            it.invoke(value)
        }
    }
}

//1.08
//(T) -> Unit
//interface Subscriber<T> {
//    fun post(value: T?)
//}