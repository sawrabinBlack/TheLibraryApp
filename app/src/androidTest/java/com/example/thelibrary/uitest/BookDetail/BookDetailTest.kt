package com.example.thelibrary.uitest.BookDetail

import android.content.Intent
import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onIdle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.thelibrary.R
import com.example.thelibrary.activities.MainActivity
import com.example.thelibrary.uitest.Home.first
import com.example.thelibrary.views.viewholder.EbooksViewHolder
import org.junit.Before
import org.junit.Test

class BookDetailTest {
    private val activityRule = ActivityTestRule(MainActivity::class.java)


    @Before
    fun setUp() {
        activityRule.launchActivity(Intent())

    }

    @Test
    fun checkBookDetail() {
        onView(first<View>(withId(R.id.rvEbooks))).perform(
            RecyclerViewActions.actionOnItemAtPosition<EbooksViewHolder>(
                0, click()
            )

        )

        onView(withId(R.id.tvBookTitleDetail)).check(matches(withText("DESERT STAR")))
        onView(withId(R.id.tvAuthorDetail)).check(matches(withText("Michael Connelly")))
        onView(withId(R.id.tvBookDetailAbout)).check(matches(withText("Ballard and Bosch bury old resentments as they go after two killers.")))
        onView(withId(R.id.tvPublished)).check(matches(withText("Little, Brown")))
        onView(withId(R.id.tvRatingDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.tvReview)).check(matches(isDisplayed()))
        onView(withId(R.id.tvRatingName)).check(matches(isDisplayed()))
    }
}