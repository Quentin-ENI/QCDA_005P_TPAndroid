package fr.eni.ecole.enishop

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import fr.eni.ecole.enishop.ui.screens.ArticleDetailsScreen
import org.junit.Rule
import org.junit.Test

class ArticleDetailsScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testArticleDetailScreenIsWorking(){
        composeTestRule.setContent {
            ArticleDetailsScreen(1)
        }

        composeTestRule
            .onNodeWithTag("artName")
            .assertExists("introuvable")

        composeTestRule
            .onNodeWithTag("artName")
            .assertHasClickAction()
            .performClick()
    }
}