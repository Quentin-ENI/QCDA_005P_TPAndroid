package fr.eni.ecole.enishop.ui.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.eni.ecole.enishop.ui.theme.ENIShopTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = { ENIShopTitle() }
    )
}

@Composable
fun ENIShopTitle() {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Filled.ShoppingCart,
            contentDescription = "Shopping Cart",
            modifier = Modifier.size(40.dp)
        )
        Spacer(
            modifier = Modifier.width(8.dp)
        )
        Text(
            text = "ENI-SHOP",
            fontSize = 40.sp,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun TopBarPreview() {
//    ENIShopTheme {
//        ENIShopTitle()
//    }
//}