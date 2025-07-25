package com.vinayak.ecommerceuicloneapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.vinayak.ecommerceuicloneapp.ui.theme.EcommerceUICloneAppTheme

/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class HomeFragment private constructor() : Fragment() {

    val viewModel by lazy {  CartViewModel.getInstance() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                EcommerceUICloneAppTheme {
                    Scaffold(

                    ) { innerpadding ->
                        ZeptoHomeScreen(viewModel)
                    }
                }
            }
        }
    }

    companion object {
        var INSTANCE: HomeFragment? = null

        fun getInstance() : HomeFragment {
            INSTANCE = if(INSTANCE==null) {
                HomeFragment()
            } else {
                INSTANCE
            }
            return INSTANCE ?: HomeFragment()
        }
    }

}

@Composable
fun ZeptoHomeScreen(viewModel: CartViewModel) {
    LazyColumn {
        item {
            Spacer(Modifier.height(22.dp))
        }

        item {
            SearchBar()
        }

        item {
            CartBadge(viewModel)
        }

        item {
            CategoryTabs()
        }

        item {
            SubCategorySelector()
        }

        item {
            ProductList(viewModel)
        }

        item {
            Spacer(Modifier.height(30.dp))
        }

        item {
            ProductList(viewModel)
        }

        item {
            ProductList(viewModel)
        }

        item {
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeFragmentPreview() {
    EcommerceUICloneAppTheme {
//        ZeptoHomeScreen()
    }
}