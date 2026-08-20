package com.example.weathercodingassignment

import android.Manifest
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.filterToOne
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.GrantPermissionRule
import com.example.weathercodingassignment.utils.TestTag.CITY_NAME_TAG

import com.example.weathercodingassignment.utils.TestTag.PROGRESS_BAR_TAG
import com.example.weathercodingassignment.utils.TestTag.SEARCH_TAG
import com.example.weathercodingassignment.utils.TestTag.WEATHER_CARD_TAG
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchScreenTest {
    @get:Rule
    val permissionRule = GrantPermissionRule.grant(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()


    @Test
    fun testSearch() {

        val searchQuery = "Seattle"

        // 1: Check if Search BOX  displayed
        composeTestRule.onNodeWithTag(SEARCH_TAG).assertIsDisplayed()
        // 2: wait for weather card to display
        composeTestRule.waitUntil {
            composeTestRule.onAllNodesWithTag(WEATHER_CARD_TAG)
                .fetchSemanticsNodes().size == 1
        }
        // 3: Perform a search with query
        composeTestRule.onNodeWithTag(SEARCH_TAG).performTextInput(searchQuery)
        //4 : check  if search Query display on Search BOX
        composeTestRule.onNodeWithText(searchQuery).assertIsDisplayed()
        //5:  wait  till progress loader is showing
        composeTestRule.waitUntil {
            composeTestRule.onAllNodesWithTag(PROGRESS_BAR_TAG)
                .fetchSemanticsNodes().size == 1
        }
        //6:  wait  till progress loader is gone
        composeTestRule.waitUntil {
            composeTestRule.onAllNodesWithTag(PROGRESS_BAR_TAG)
                .fetchSemanticsNodes().isEmpty()
        }
        //7: check if Weather card has child name where city name is search query
        composeTestRule.onNodeWithTag(WEATHER_CARD_TAG)
            .onChildren()
            .filterToOne(hasTestTag(CITY_NAME_TAG))
            .assertTextEquals(searchQuery)


    }


}