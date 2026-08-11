package com.example.jhubexpensesapp.screens

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.R
import com.example.jhubexpensesapp.database.Expense


@Composable
fun ExpenseItemCard(){

    Spacer(
        modifier = Modifier.padding(top = 24.dp)
    )

    ElevatedCard(
        elevation = CardDefaults.cardElevation(6.dp),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(start = 8.dp, end = 8.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        )
    ){

        Row(
            modifier = Modifier.
            fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(com.example.jhubexpensesapp.R.drawable.blank_receipt),
                contentDescription = "Blank receipt image",
                modifier = Modifier.size(70.dp).clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(
                modifier = Modifier.padding(16.dp)
            )

            Column() {

                Text("Lossiemouth",
                    fontSize = 24.sp)

                Spacer(
                    modifier = Modifier.padding(2.dp)
                )

                Text("£175.65",
                    fontSize = 20.sp)

            }

        }

    }

}