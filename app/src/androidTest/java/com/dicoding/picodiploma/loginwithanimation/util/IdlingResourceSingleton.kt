package com.dicoding.picodiploma.loginwithanimation.util

import androidx.test.espresso.idling.CountingIdlingResource

object IdlingResourceSingleton {
    private const val RESOURCE = "GLOBAL"


    @JvmField
    val countingIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        countingIdlingResource.increment()
    }

    fun decrement() {
        if (!countingIdlingResource.isIdleNow) {
            countingIdlingResource.decrement()
        }
    }
}