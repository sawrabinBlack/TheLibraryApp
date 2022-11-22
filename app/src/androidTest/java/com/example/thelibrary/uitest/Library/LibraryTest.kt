package com.example.thelibrary.uitest.Library

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.example.thelibrary.activities.MainActivity
import org.junit.Before
import org.junit.Test
import com.example.thelibrary.R
import com.example.thelibrary.uitest.Home.first
import com.example.thelibrary.views.viewholder.CategoryViewHolder
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.junit.internal.runners.JUnit4ClassRunner
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class LibraryTest {

    private val activityRule = ActivityTestRule(MainActivity::class.java)
    lateinit var bottomNavigation: BottomNavigationView

    @Before
    fun setUp() {
        activityRule.launchActivity(Intent())
    }


    fun onTapLibrary() {
        onView(withId(R.id.action_library)).perform(click())


    }

    @Test
    fun changeList() {
        onTapLibrary()
        onView(withId(R.id.rvLibraryEbooks)).check(matches(hasChildCount(3)))

        onView(withId(R.id.tvViewType)).perform(click())
        onView(withId(R.id.rbLargeGrid)).perform(click())
        onView(withId(R.id.rvLibraryEbooksTwoColumn)).check(matches(isDisplayed()))
        onView(withId(R.id.tvViewType)).perform(click())
        onView(withId(R.id.rbSmallGrid)).perform(click())
        onView(withId(R.id.rvLibraryEbooksThreeColumn)).check(matches(isDisplayed()))
    }

    @Test
    fun sortByTest() {
        onView(withId(R.id.action_home)).perform(click())
        onView(withId(R.id.action_library)).perform(click())
        onView(withId(R.id.tvSortBy)).perform(click())
        onView(withId(R.id.rbAuthor)).perform(click())
        onView(first(withId(R.id.tvBooksTitleListLayout))).check(matches(withText(TEST_BOOK_NAME_1)))
        onView(withId(R.id.tvSortBy)).perform(click())
        onView(withId(R.id.rbTitle)).perform(click())
        onView(first(withId(R.id.tvBooksTitleListLayout))).check(matches(withText(TEST_BOOK_NAME_2)))
        onView(withId(R.id.tvSortBy)).perform(click())
        onView(withId(R.id.rbRecent)).perform(click())
        onView(first(withId(R.id.tvBooksTitleListLayout))).check(matches(withText(TEST_BOOK_NAME_3)))
    }

    @Test
    fun filterByCategory() {
        onTapLibrary()
        onView(withId(R.id.chipGroup)).perform(
            RecyclerViewActions.actionOnItemAtPosition<CategoryViewHolder>(
                0, click()
            )
        )
        onView(first(withId(R.id.tvBooksTitleListLayout))).check(matches(withText(TEST_BOOK_NAME_3)))
        onView(withId(R.id.btnClearFilter)).perform(click())
        onView(withId(R.id.chipGroup)).perform(
            RecyclerViewActions.actionOnItemAtPosition<CategoryViewHolder>(
                1, click()
            )
        )
        onView(first(withId(R.id.tvBooksTitleListLayout))).check(matches(withText(TEST_BOOK_NAME_1)))
        onView(withId(R.id.btnClearFilter)).perform(click())
        onView(withId(R.id.chipGroup)).perform(
            RecyclerViewActions.actionOnItemAtPosition<CategoryViewHolder>(
                2, click()
            )
        )
        onView(first(withId(R.id.tvBooksTitleListLayout))).check(matches(withText(TEST_BOOK_NAME_2)))

    }


}

const val TEST_BOOK_NAME_1 = "SURRENDER"
const val TEST_BOOK_NAME_2 = "BRAIDING SWEETGRASS"
const val TEST_BOOK_NAME_3 = "DESERT STAR"