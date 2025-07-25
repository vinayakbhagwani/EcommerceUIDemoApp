package com.vinayak.ecommerceuicloneapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


data class Product(
    val name: String,
    val description: String,
    val originalPrice: String,
    val offerPrice: String,
    val rating: Float,
    val reviews: Int,
    val discountPercent: Int
)

@Composable
fun SearchBar() {
    TextField(
        value = "",
        onValueChange = {},
        placeholder = { Text("Search for \"iphone\"") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF2F2F2))
    )
}

@Composable
fun CartBadge(viewModel: CartViewModel) {
    val count = viewModel.getCartCount()
    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.Red, CircleShape)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text("Cart: $count", color = Color.White, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun CategoryTabs() {
    val tabs = listOf("All", "Rakhi Store", "Fashion", "Cafe", "Electronics")
    LazyRow(modifier = Modifier.padding(start = 8.dp)) {
        items(tabs) {
            Text(
                text = it,
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFFEDEDED))
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun SubCategorySelector() {
    val categories = listOf("Vegetables", "Fruits", "Leafy", "Exotics", "Melons")
    LazyRow(contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)) {
        items(categories) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(horizontal = 8.dp)) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFEEFCB))
                        .border(width = 1.dp, color = Color.Black)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.vegetables), // Replace with your image resource
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Text(it, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun ProductList(viewModel: CartViewModel) {
    val products = listOf(
        Product("Fresh Onion", "1 Pack / 900-1000 gm", "₹44", "₹25", 4.4f, 56000, 43),
        Product("Tomato Hybrid 1kg Combo", "500g x 2", "₹52", "₹44", 4.6f, 575, 15),
        Product("Cucumber", "500 g", "₹41", "₹35", 4.4f, 4400, 14),
        Product("Fresh Onion", "1 Pack / 900-1000 gm", "₹44", "₹25", 4.5f, 56000, 43),
        Product("Tomato Hybrid 1kg Combo", "500g x 2", "₹52", "₹44", 4.5f, 575, 15),
        Product("Cucumber", "500 g", "₹41", "₹35", 4.2f, 4400, 14)
    )

    LazyRow(contentPadding = PaddingValues(horizontal = 8.dp)) {
        items(products) { product ->
            ProductCard(product, viewModel)
        }
    }
}

@Composable
fun ProductCard(product: Product, viewModel: CartViewModel) {
    val isInCart = viewModel.isInCart(product)

    Card(
        modifier = Modifier
            .width(160.dp)
            .padding(end = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.onions), // Replace with your image resource
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Text(
                    text = "${product.discountPercent}% Off",
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(4.dp)
                        .background(Color(0xFF6A1B9A), shape = RoundedCornerShape(4.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp),
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
            Spacer(Modifier.height(4.dp))
            Text(product.name, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(product.description, fontSize = 12.sp, color = Color.Gray)
            Spacer(Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFFFC107), modifier = Modifier.size(14.dp))
                Text("${product.rating} (${product.reviews / 1000}k)", fontSize = 12.sp)
            }
            Spacer(Modifier.height(2.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(product.originalPrice, fontSize = 12.sp, textDecoration = TextDecoration.LineThrough, color = Color.Gray)
                Spacer(Modifier.width(4.dp))
                Text(product.offerPrice, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }
            Spacer(Modifier.height(4.dp))
            OutlinedButton(
                onClick = { if (!isInCart) viewModel.addToCart(product) else viewModel.removeFromCart(product) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp),
                shape = RoundedCornerShape(20),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (isInCart) Color.White else Color.White,
                    contentColor = if (isInCart) Color(0xFFE3036B) else Color(0xFFE3036B)
                ),
                border = BorderStroke(width = 1.dp, color = Color(0xFFE3036B))
            ) {
                Text(if (isInCart) "Added" else "Add to Cart", fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun FreeDeliveryBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE3FCEC))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.Lock, contentDescription = null, tint = Color(0xFF038008))
        Spacer(Modifier.width(8.dp))
        Text("Shop for ₹99 to unlock FREE delivery with ", fontSize = 14.sp)
        Text("daily", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold, color = Color(0xFF2E7D32))
    }
}