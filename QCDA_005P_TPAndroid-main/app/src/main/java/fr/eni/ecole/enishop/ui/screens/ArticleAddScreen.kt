package fr.eni.ecole.enishop.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import fr.eni.ecole.enishop.ui.shared.BaseTextField



@Composable
fun ArticleAddScreen(navController: NavHostController) {

    val context = LocalContext.current

    var title by rememberSaveable() { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }

    var price by rememberSaveable { mutableStateOf("") }

    var selectedCategory by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        BaseTextField(
            value = title,
            onValueChange = {
                title = it
            },
            label = "Titre"
        )
        BaseTextField(
            value = description,
            onValueChange = {
                description = it
            },
            label = "Description"
        )
        BaseTextField(
            value = price,
            onValueChange = {
                price = it
            },
            label = "Prix"
        )
        DropDownCategories(
            selectedCategory = selectedCategory,
            onCategoryChange = {
                selectedCategory = it
            }
        )


        Button(
            onClick = {
                Toast.makeText(
                    context,
                    "L'article $title a bien été ajouté",
                    Toast.LENGTH_LONG
                ).show()
            },
        ) {
            Text("Enregistrer")
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownCategories(
    selectedCategory: String,
    onCategoryChange: (String) -> Unit
) {
    val categories = listOf(
        "electronics",
        "jewelery",
        "men's clothing",
        "women's clothing"
    )

    val rounded =  RoundedCornerShape(16.dp)

    var expanded by rememberSaveable { mutableStateOf(false) }

    val modifierCard =  Modifier
        .fillMaxWidth()
        .border(
            width = 2.dp,
            color = MaterialTheme.colorScheme.primary,
            shape = rounded
        )
    val shapeCard = rounded
    val elevationCard = CardDefaults.cardElevation(
        defaultElevation = 4.dp
    )
    val colorCard = CardDefaults.cardColors(
        containerColor = Color.Gray
    )

    Card(
        modifier = modifierCard,
        shape = shapeCard,
        elevation = elevationCard,
        colors = colorCard
    ){
        Text(text = "Catégorie",
            modifier = Modifier.background(Color.White)
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp,bottom = 16.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold

        )
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = selectedCategory,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                categories.forEach { cat ->
                    DropdownMenuItem(
                        text = { Text(cat) },
                        onClick = {
                            onCategoryChange(cat)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}