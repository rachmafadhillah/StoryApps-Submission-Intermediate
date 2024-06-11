package com.dicoding.picodiploma.loginwithanimation.data

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.dicoding.picodiploma.loginwithanimation.R
import com.dicoding.picodiploma.loginwithanimation.util.IdlingResourceSingleton
import com.dicoding.picodiploma.loginwithanimation.view.main.MainActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.RootMatchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginLogoutTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(IdlingResourceSingleton.countingIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdlingResourceSingleton.countingIdlingResource)
    }

    @Test
    fun testLoginLogout() {
        onView(withId(R.id.loginButton)).perform(click())
        onView(withId(R.id.ed_login_email_layout)).perform(click())
        onView(withId(R.id.ed_login_email)).perform(
            typeText("rachmafadhillah28@gmail.com"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.ed_login_password_layout)).perform(click())
        onView(withId(R.id.ed_login_password)).perform(
            typeText("12345678"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.loginButton)).perform(click())

        Espresso.onIdle()

        onView(withId(R.id.progressIndicator)).check(matches(isDisplayed()))

        Espresso.onIdle()

        Thread.sleep(2000)

        onView(withText(R.string.yeah)).inRoot(RootMatchers.isDialog())
            .check(matches(isDisplayed()))
        onView(withText(R.string.berhasil)).check(matches(isDisplayed()))
        onView(withText(R.string.lanjut)).perform(click())

        Intents.init()

        onView(withId(R.id.logout_button)).perform(click())

        onView(withId(R.id.loginButton)).check(matches(isDisplayed()))
    }
}