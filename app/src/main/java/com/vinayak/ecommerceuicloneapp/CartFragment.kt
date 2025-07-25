package com.vinayak.ecommerceuicloneapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.vinayak.ecommerceuicloneapp.databinding.FragmentCartBinding
import com.vinayak.ecommerceuicloneapp.ui.theme.EcommerceUICloneAppTheme

/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class CartFragment : Fragment() {

    val viewModel by lazy {  CartViewModel.getInstance() }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                EcommerceUICloneAppTheme {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(
                                        "Your Cart",
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(top = 28.dp)
                                    )
                                },
                                actions = {
                                    Column {
                                        Spacer(Modifier.height(26.dp))
                                        Text(
                                            "SAVED ₹33",
                                            color = Color(0xFF1BA672),
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier
                                                .background(Color(0xFFDFF5EC), shape = RoundedCornerShape(4.dp))
                                                .padding(horizontal = 8.dp, vertical = 2.dp),
                                            fontSize = 12.sp
                                        )
                                    }
                                }
                            )
                        }
                    ) { innerpadding ->
                        CartScreen(viewModel)
                    }
                }
            }
        }
    }

    companion object {
        var INSTANCE: CartFragment? = null

        fun getInstance() : CartFragment {
            INSTANCE = if(INSTANCE==null) {
                CartFragment()
            } else {
                INSTANCE
            }
            return INSTANCE ?: CartFragment()
        }
    }
}