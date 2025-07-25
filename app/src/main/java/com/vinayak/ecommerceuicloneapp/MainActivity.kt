package com.vinayak.ecommerceuicloneapp

import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager
import com.vinayak.ecommerceuicloneapp.ui.theme.EcommerceUICloneAppTheme

class MainActivity : AppCompatActivity() {

    val homeFragment by lazy { HomeFragment.getInstance() }
    val cartFragment by lazy { CartFragment.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EcommerceUICloneAppTheme {

                val supportFragmentManager = (LocalActivity.current as AppCompatActivity).supportFragmentManager
                var showFreeDeliveryBar by remember { mutableStateOf(true) }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        Column {
                            if(showFreeDeliveryBar) {
                                FreeDeliveryBar()
                            } else {
                                Button(
                                    onClick = {},
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF007F))
                                ) {
                                    Text("Click to Pay", color = Color.White)
                                }
                            }
                            BottomNavigationBar(
                                navigateToDest = { fragment ->
                                    navigateToFragment(supportFragmentManager, fragment)
                                },
                                showFreeDeliveryBar = { isVisible ->
                                    showFreeDeliveryBar = isVisible
                                }
                            )
                        }
                    }
                ) { innerPadding ->

                    AndroidView(
                        factory = { context ->
                            FragmentContainerView(context).apply {
                                id = R.id.fragment_container
                                layoutParams = FrameLayout.LayoutParams(
                                    FrameLayout.LayoutParams.MATCH_PARENT,
                                    FrameLayout.LayoutParams.MATCH_PARENT
                                )
                                // Load the initial fragment
                                post {
                                    if (supportFragmentManager.findFragmentById(id) == null) {
                                        Log.d("FRAGMENT","reached adding")
                                        supportFragmentManager.beginTransaction()
                                            .add(id, homeFragment)
                                            .commit()
                                    }
                                }
                            }
                        },
                        modifier = Modifier.fillMaxSize()
                            .padding(bottom = 105.dp)
                    )
                }
            }
        }
    }

    private fun navigateToFragment(supportfragManager: FragmentManager, fragment: Fragment) {
        supportfragManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack("Main")
            .commit()
    }

    @Composable
    fun BottomNavigationBar(
        navigateToDest: (fragment: Fragment) -> Unit,
        showFreeDeliveryBar: (Boolean) -> Unit
    ) {

        var isSelected by remember { mutableIntStateOf(NavigationItems.ZEPTO.position) }

        NavigationBar(Modifier.padding(horizontal = 10.dp)) {

            NavigationBarItem(
                selected = isSelected == 0,
                onClick = {
                    isSelected = 0
                    showFreeDeliveryBar(false)
                },
                icon = {
                    Icon(Icons.Default.ArrowBack, contentDescription = null)
                },
                label = { Text("Back") }
            )

            NavigationBarItem(
                selected = isSelected == 1,
                onClick = {
                    isSelected = 1
                    navigateToDest(homeFragment)
                    showFreeDeliveryBar(true)
                },
                icon = {
                    Icon(Icons.Default.Home, contentDescription = null)
                },
                label = { Text("Zepto") }
            )

            NavigationBarItem(
                selected = isSelected == 2,
                onClick = {
                    isSelected = 2
                    showFreeDeliveryBar(false)
                },
                icon = {
                    Icon(Icons.Default.LocationOn, contentDescription = null)
                },
                label = { Text("Categories") }
            )

            NavigationBarItem(
                selected = isSelected == 3,
                onClick = {
                    isSelected = 3
                    showFreeDeliveryBar(false)
                },
                icon = {
                    Icon(Icons.Default.AddCircle, contentDescription = null)
                },
                label = { Text("Cafe") }
            )

            NavigationBarItem(
                selected = isSelected == 4,
                onClick = {
                    isSelected = 4
                    navigateToDest(cartFragment)
                    showFreeDeliveryBar(false)
                          },
                icon = {
                    Icon(Icons.Default.ShoppingCart, contentDescription = null)
                },
                label = { Text("Buy Again") }
            )

        }
    }



}


enum class NavigationItems(val position: Int) {
    BACK(0),
    ZEPTO(1),
    CATEGORIES(2),
    CAFE(3),
    BUY_AGAIN(4)
}