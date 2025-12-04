package fr.eni.ecole.enishop.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleAddScreen() {

    val context = LocalContext.current

    var title by rememberSaveable() { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }

    var priceText by rememberSaveable { mutableStateOf("") }
    var price: Double? = priceText.toDoubleOrNull()

    val categories = listOf(
        "electronics",
        "jewelery",
        "men's clothing",
        "women's clothing"
    )

    var expanded by rememberSaveable { mutableStateOf(false) }
    var selectedCategory by rememberSaveable { mutableStateOf(categories[0]) }
    var rounded =  RoundedCornerShape(16.dp)
    var modifierCard =  Modifier
        .fillMaxWidth()
        .border(
            width = 2.dp,
            color = Color.Blue,
            shape = rounded
        )
    var shapeCard = rounded
    var elevationCard = CardDefaults.cardElevation(
        defaultElevation = 4.dp
    )
    var colorCard = CardDefaults.cardColors(
        containerColor = Color.Gray
    )
    Scaffold { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            Card(
                modifier = modifierCard,
                shape = shapeCard,
                elevation = elevationCard,
                colors = colorCard
            ){
                Text(text = "Titre",
                    modifier = Modifier.background(Color.White)
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 16.dp,bottom = 16.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold


                )
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }


            Card(
                modifier = modifierCard,
                shape = shapeCard,
                elevation = elevationCard,
                colors = colorCard
            ){
                Text(text = "Description",
                    modifier = Modifier.background(Color.White)
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 16.dp,bottom = 16.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold

                )
                TextField(
                    value = description,
                    onValueChange = { description = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }



            Card(
                modifier = modifierCard,
                shape = shapeCard,
                elevation = elevationCard,
                colors = colorCard
            ){
                Text(text = "Prix",
                    modifier = Modifier.background(Color.White)
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 16.dp,bottom = 16.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold

                )
                TextField(
                    value = priceText,
                    onValueChange = { priceText = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }


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
                                    selectedCategory = cat
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }



            Button(
                onClick = {
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ajouter")
            }
        }
    }
}
