package com.example.thelibrary.uitest.MoreBooks

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.thelibrary.activities.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.junit.Before
import org.junit.Test
import com.example.thelibrary.R
import com.example.thelibrary.uitest.Home.first
import kotlinx.android.synthetic.main.activity_more_ebooks.*

class MoreBookTest {
    private val activityRule = ActivityTestRule(MainActivity::class.java)
    lateinit var bottomNavigation: BottomNavigationView

    @Before
    fun setUp() {
        activityRule.launchActivity(Intent())
    }

    @Test
    fun moreBooksDataConfirm(){
        onView(first(withId(R.id.tvEbooksGenre)) ).perform(click())
        Thread.sleep(2000L)
        onView(withId(R.id.tvBookGenreMoreBooks)).check(matches( withText("Hardcover Fiction")))
        onView(first(withId(R.id.tvLibraryBookTitle))).check(matches(withText("DESERT STAR")))
        onView(withText("DESERT STAR")).check(matches(isDisplayed()))
        onView(withText("DESERT STAR")).perform(click())
        onView(withId(R.id.tvBookTitleDetail)).check(matches(withText("DESERT STAR")))
    }
}