package com.example.thelibrary.uitest.BookSearch

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.TypeTextAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.thelibrary.activities.MainActivity
import org.junit.Before
import org.junit.Test
import com.example.thelibrary.R

class GoogleBookTest {
    private val activityRule = ActivityTestRule(MainActivity::class.java)

    companion object {
        const val FIRST_ITEM_DESCRIPTION = "Return The First Matching Item"
    }

    @Before
    fun setUp() {
        activityRule.launchActivity(Intent())
    }

    @Test
    fun checkSearchBooks() {
        onView(withId(R.id.rlSearch)).perform(click())
        onView(withId(R.id.etSearch)).perform(TypeTextAction("Avenger"), closeSoftKeyboard())
        Thread.sleep(2000L)
        onView(withText("Star Trek")).check(matches(isDisplayed()))
        onView(withText("Star Trek")).perform(click())
        onView(withId(R.id.tvBookTitleDetail)).check(matches(withText("Star Trek")))
    }

}