package com.example.thelibrary.uitest.Home

import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

fun <T> second(matcher: Matcher<T>, itemCount: Int): Matcher<T> {
    var isFirst: Boolean = true
    var n = 1
    return object : BaseMatcher<T>() {
        override fun describeTo(description: Description?) {
            description?.appendText(HomeScreenUserTest.FIRST_ITEM_DESCRIPTION)
        }

        override fun matches(item: Any?): Boolean {
            if (isFirst && matcher.matches(item)) {
                if (n == itemCount) {

                    isFirst = false
                    return true
                } else n++

            }
            return false
        }
    }
}