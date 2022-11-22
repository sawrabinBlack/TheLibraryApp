package com.example.thelibrary.uitest.Home

import android.content.Intent
import android.view.View
import com.example.thelibrary.R
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.example.thelibrary.activities.MainActivity
import com.example.thelibrary.views.viewholder.EbooksViewHolder
import com.example.thelibrary.views.viewholder.EbooksWithGenreViewHolder
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

private const val MovieCategoryOne = "Hardcover Fiction"

private const val TEST_MOVIE_NAME_1 = "Desert star"

private const val TEST_MOVIE_NAME_2 = "The boys from biloxi"

@RunWith(AndroidJUnit4ClassRunner::class)
class HomeScreenUserTest {
    private val activityRule = ActivityTestRule(MainActivity::class.java)

    companion object {
        const val FIRST_ITEM_DESCRIPTION = "Return The First Matching Item"
    }

    @Before
    fun setUp() {
        activityRule.launchActivity(Intent())

    }

    @Test
    fun carouselViewEmpty() {
        onView(withId(R.id.carouselView)).check(matches(hasChildCount(0)))
        onView(ViewMatchers.withText(MovieCategoryOne)).check(matches(isDisplayed()))
        onView(first<View>(withText(TEST_MOVIE_NAME_1))).check(matches(isDisplayed()))
        onView(first<View>(withText(TEST_MOVIE_NAME_2))).check(matches(isDisplayed()))


        onView(first<View>(withId(R.id.rvEbooks))).perform(
            RecyclerViewActions.actionOnItemAtPosition<EbooksViewHolder>(
                0, click()
            )

        )

        onView(isRoot()).perform(ViewActions.pressBack());
        onView(withId(R.id.rvEbooksWithGenre)).perform(
            RecyclerViewActions.actionOnItemAtPosition<EbooksWithGenreViewHolder>(
                2, scrollTo()
            )
        )
        onView(second<View>(withId(R.id.rvEbooks),2)).perform(
            RecyclerViewActions.actionOnItemAtPosition<EbooksViewHolder>(
                1, click()
            )
        )

        onView(isRoot()).perform(ViewActions.pressBack());

        onView(withId(R.id.rvEbooksWithGenre)).perform(
            RecyclerViewActions.actionOnItemAtPosition<EbooksWithGenreViewHolder>(
                3, scrollTo()
            )
        )
        onView(second<View>(withId(R.id.rvEbooks),3)).perform(
            RecyclerViewActions.actionOnItemAtPosition<EbooksViewHolder>(
                1, click()
            )
        )
        onView(isRoot()).perform(ViewActions.pressBack());
        onView(withId(R.id.carouselView)).check(matches(hasChildCount(2)))
        onView(withId(R.id.action_library)).perform(click())
        onView(withId(R.id.rvLibraryEbooks)).check(matches(hasChildCount(3)))
    }






}
