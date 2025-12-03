package fr.eni.ecole.enishop

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.Date

@Composable
fun ArticleDetails(name: String,
                   description: String,
                   price: Double,
                   urlImage: String,
                   date: Date,
                   modifier: Modifier
) {
    Column() {
        Text(name)
        //AsyncImage( model = urlimage, contentDescription = "", )
        Text(description)
        Spacer(modifier.padding(horizontal = 8.dp))
        Row() {
            Text(text = "Prix : $price €")
            Spacer(modifier = modifier.weight(3F))
            Text(text="Date de sortie : $date")
        }
    }
}