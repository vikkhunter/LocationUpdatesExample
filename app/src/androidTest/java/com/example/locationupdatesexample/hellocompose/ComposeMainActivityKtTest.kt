package com.example.locationupdatesexample.hellocompose

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ComposeMainActivityKtTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComposeMainActivity>()
    @Test
    fun greetingTest() {
        composeTestRule.activity.setContent {
            Greeting(name = "Vivek")
        }

        composeTestRule.onNodeWithText("Hello Vivek!").assertExists()
    }

}