package com.husaynhakeem.lifecycleawarecomponentsample

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class LocationListener(
        @get:VisibleForTesting(otherwise = VisibleForTesting.PRIVATE) val lifecycle: Lifecycle,
        private val callback: (String) -> Unit) : LifecycleObserver {

    private var enabled = false

    @get:VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    var status = ""

    init {
        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() {
        if (enabled) {
            status = CONNECTED
            callback.invoke("Somewhere in Los Angeles")
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() {
        if (enabled) {
            status = DISCONNECTED
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroy() {
        lifecycle.removeObserver(this)
    }

    fun enable() {
        enabled = true
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            start()
        }
    }

    companion object {
        const val CONNECTED = "Connected"
        const val DISCONNECTED = "Disconnected"
    }
}