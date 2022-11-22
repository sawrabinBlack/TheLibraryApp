package com.example.thelibrary.uitest.shelf

import android.content.Intent
import android.view.View
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
import com.example.thelibrary.views.viewholder.ShelfViewHolder
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ShelfRenameTest {

    private val activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        activityRule.launchActivity(Intent())
    }


    @Test
    fun b_renameShelf() {
        onView(withId(R.id.action_library)).perform(ViewActions.click())
        onView(withText("Your Shelves")).perform(ViewActions.click())
        onView(withId(R.id.rvShelves)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ShelfViewHolder>(
                0, click()
            )
        )
        onView(withId(R.id.ivShelfAction)).perform(click())
        onView(withId(R.id.tvRenameShelf)).perform(click())
        onView(withId(R.id.etShelfNamesDetail)).perform(
            ViewActions.typeText(
                TEST_SHELF_NAME
            ), ViewActions.pressImeActionButton()
        )


        onView(withId(R.id.btnBackShelfDetail)).perform(click())
        onView(first<View>(withId(R.id.tvLibraryName))).check(matches(withText(TEST_SHELF_NAME)))


    }
    @Test
    fun a_createNewLibrary() {
        onView(withId(R.id.action_library)).perform(click())
        onView(ViewMatchers.withText("Your Shelves")).perform(click())
        onView(withId(R.id.btnCreateNewLibrary)).perform(click())
        onView(withId(R.id.etShelfName)).perform(
            ViewActions.typeText(
                TEST_USER_NAME_EMPTY
            ), ViewActions.pressImeActionButton()
        )

        onView(ViewMatchers.withText(TEST_USER_NAME_EMPTY)).check(matches(isDisplayed()))
    }
    @Test
    fun c_addToShelf(){
        onView(withId(R.id.action_library)).perform(click())
        onView(first<View>(withId(R.id.ivOptionsListLayout))).perform(click())
        onView(withId(R.id.tvAddToShelves)).perform(click())
        onView(first<View>(withId(R.id.cbAddToShelves))).perform(click())
        onView(withId(R.id.btnConfirmAddToShelves)).perform(click())
        onView(withText("Your Shelves")).perform(click())
        onView(first<View>(withId(R.id.tvNoOfBooksLibrary))).check(matches(withText("1 book")))

    }
    @Test
    fun d_deleteBookFromLibrary(){
        onView(withId(R.id.action_library)).perform(click())
        onView(ViewMatchers.withText("Your Shelves")).perform(click())
        onView(withId(R.id.rvShelves)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ShelfViewHolder>(
                0, click()
            )
        )
        onView(first(withId(R.id.ivOptionsListLayout))).perform(click())
        onView(withId(R.id.tvDelete)).perform(click())
        onView(withId(R.id.tvNoOfBooksDetail)).check(matches(withText("0 books")))
    }

    @Test
    fun e_deleteLibrary() {
        onView(withId(R.id.action_library)).perform(click())
        onView(ViewMatchers.withText("Your Shelves")).perform(click())
        onView(withId(R.id.rvShelves)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ShelfViewHolder>(
                0, click()
            )
        )
        onView(withId(R.id.ivShelfAction)).perform(click())
        onView(withId(R.id.tvDelete)).perform(click())
        onView(withId(R.id.tvNoShelves)).check(matches(isDisplayed()))
    }
}
const val TEST_SHELF_NAME="Book_test"
const val TEST_USER_NAME_EMPTY="books"