package com.husaynhakeem.lifecycleawarecomponentsample

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class LocationListenerShould {

    private val lifecycleOwner = TestLifecycleOwner()
    private lateinit var locationListener: LocationListener

    @Before
    fun setUp() {
        locationListener = LocationListener(lifecycleOwner.lifecycle)
    }

    @Test
    fun observeLifecycleOnInitialisation() {
        assertEquals(1, lifecycleOwner.lifecycle.observerCount)
    }

    @Test
    fun stopObservingLifecycle_whenLifecycleOwnerIsDestroyed() {
        lifecycleOwner.onCreate()
        lifecycleOwner.onDestroy()

        assertEquals(0, lifecycleOwner.lifecycle.observerCount)
    }

    @Test
    fun notConnect_whenEnableIsCalledBeforeLifeCycleStateIsAtLeastStarted() {
        lifecycleOwner.onCreate()
        locationListener.enable()

        assertEquals("", locationListener.status)
    }

    @Test
    fun notConnect_whenLifecycleIsStarted_butEnabledIsFalse() {
        lifecycleOwner.onCreate()
        lifecycleOwner.onStart()

        assertEquals("", locationListener.status)
    }

    @Test
    fun connectThroughStart_whenLifecycleIsStarted_andEnableIsCalledBeforeLifeCycleStateIsStarted() {
        lifecycleOwner.onCreate()
        locationListener.enable()

        lifecycleOwner.onStart()

        assertEquals(LocationListener.CONNECTED, locationListener.status)
    }

    @Test
    fun connectThroughEnable_whenLifecycleIsStarted_andEnableIsCalledAfterLifeCycleStateIsStarted() {
        lifecycleOwner.onCreate()
        lifecycleOwner.onStart()

        locationListener.enable()

        assertEquals(LocationListener.CONNECTED, locationListener.status)
    }

    @Test
    fun notDisconnect_whenEnabledIsFalse() {
        with(lifecycleOwner) {
            onCreate()
            onStart()
            onStop()
        }

        assertEquals("", locationListener.status)
    }

    @Test
    fun disconnect_whenEnabledIsTrue() {
        lifecycleOwner.onCreate()
        lifecycleOwner.onStart()
        locationListener.enable()

        lifecycleOwner.onStop()

        assertEquals(LocationListener.DISCONNECTED, locationListener.status)
    }
}