package fr.eni.ecole.enishop.ui.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.eni.ecole.enishop.datastore.DataStoreManager
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EniShopTopBar(
    canBack: Boolean,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { ENIShopTitle() },
        navigationIcon = {
            if (canBack) {
                IconButton(
                    onClick = onNavigateBack
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "Back to previous page."
                    )
                }
            }
        },
        actions = {
            BurgerMenuSettings()
        }
    )
}

@Composable
fun BurgerMenuSettings(modifier: Modifier = Modifier) {
    var menuExpanded by remember { mutableStateOf(false) }
    var isDarkTheme by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current
    val coroutine = rememberCoroutineScope()


    LaunchedEffect(Unit) {
        DataStoreManager.isDarkThemeEnabled(context).collect {
            isDarkTheme = it
        }
    }

    IconButton(onClick = { menuExpanded = true }) {
        Icon(
            imageVector = Icons.Filled.Menu,
            contentDescription = "Menu"
        )
    }
    DropdownMenu(
        expanded = menuExpanded,
        onDismissRequest = { menuExpanded = false }
    ) {
        DropdownMenuItem(
            text = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Mode sombre")
                    Spacer(modifier = Modifier.width(8.dp))
                    Switch(
                        checked = isDarkTheme,
                        onCheckedChange = {
                            coroutine.launch {
                                DataStoreManager.setDarkThemeEnabled(context, it)
                            }
                            menuExpanded = false
                        }
                    )
                }
            },
            onClick = {}
        )
    }
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