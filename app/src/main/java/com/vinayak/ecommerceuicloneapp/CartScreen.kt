package com.vinayak.ecommerceuicloneapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CartScreen(viewModel: CartViewModel) {
    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        item {
            Spacer(Modifier.height(22.dp))
        }

        // Delivery info
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.Home,
                    contentDescription = null,
                    tint = Color(0xFF1BA672)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Delivery in 6 mins", fontWeight = FontWeight.SemiBold)
            }
        }


        // Cart items
        items(viewModel.cartItems) { item ->
            CartItem(
                name = item.name,
                quantity = item.description,
                price = item.offerPrice,
                originalPrice = item.originalPrice
            )
        }

        // Add more items button
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Missed something?", fontWeight = FontWeight.Medium)
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                ) {
                    Text("+ Add More Items", color = Color.White)
                }
            }
        }

        // Suggestions
        item {
            Text(
                "You might also like",
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }

        item {
            LazyRow(contentPadding = PaddingValues(horizontal = 16.dp)) {
                item { SuggestionItem("Parle Krack Jack", "176.4 g", 39, 40, "2% Off") }
                item { SuggestionItem("Milky Mist Paneer", "500 g", 278, 320, "13% Off") }
                item { SuggestionItem("Bingo Tedhe Medhe", "75 g", 18, 20, "10% Off") }
                item { SuggestionItem("Parle Krack Jack", "176.4 g", 39, 41, "2% Off") }
                item { SuggestionItem("Milky Mist Paneer", "500 g", 278, 310, "13% Off") }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Delivery address
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Place, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Delivering to Work", fontWeight = FontWeight.Medium)
                Spacer(Modifier.weight(1f))
                Text("9.3 Kms Away", color = Color.Red, fontSize = 12.sp)
            }
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            var isChecked by rememberSaveable { mutableStateOf(false) }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = isChecked, onCheckedChange = { isChecked = !isChecked})
                Spacer(Modifier.width(4.dp))
                Text("I don’t need a paper bag 🌱")
            }
        }

        item {
            Spacer(modifier = Modifier.height(65.dp))
        }

    }
}

@Composable
fun CartItem(
    name: String,
    itemImage: Int = R.drawable.onions,
    quantity: String,
    price: String,
    originalPrice: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                .clip(RoundedCornerShape(8.dp))
        ) {
            Image(
                painter = painterResource(itemImage),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(name, fontWeight = FontWeight.SemiBold)
            Text(quantity, fontSize = 12.sp, color = Color.Gray)
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = {}) {
                Icon(Icons.Default.Delete, contentDescription = null)
            }
            Text("1", fontWeight = FontWeight.Medium)
            IconButton(onClick = {}) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        Column(horizontalAlignment = Alignment.End) {
            Text("$price", fontWeight = FontWeight.Bold)
            Text(
                text = "$originalPrice",
                style = TextStyle(textDecoration = TextDecoration.LineThrough, color = Color.Gray, fontSize = 12.sp)
            )
        }
    }
}

@Composable
fun SuggestionItem(name: String, quantity: String, price: Int, originalPrice: Int, offer: String) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(end = 12.dp)
            .width(140.dp)
            .wrapContentHeight()
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Box(
                modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth()
                    .background(Color.LightGray)
            ) {
                Text(
                    text = offer,
                    color = Color.White,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .background(Color.Red, shape = RoundedCornerShape(bottomEnd = 4.dp))
                        .padding(horizontal = 4.dp, vertical = 2.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(name, fontSize = 14.sp, fontWeight = FontWeight.Medium, maxLines = 2)
            Text(quantity, fontSize = 12.sp, color = Color.Gray)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("₹$price", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "₹$originalPrice",
                    style = TextStyle(textDecoration = TextDecoration.LineThrough, color = Color.Gray, fontSize = 10.sp)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.End)
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartScreenPreview() {
//    CartScreen()
}
