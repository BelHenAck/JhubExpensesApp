package com.example.jhubexpensesapp.screens

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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.jhubexpensesapp.database.Expense
import com.example.jhubexpensesapp.R


@Composable
fun ExpenseItemCard(expense: Expense, navController: NavController){


    Spacer(
        modifier = Modifier.padding(top = 24.dp)
    )

    ElevatedCard(
        onClick = {
            navController.navigate("update_expense/${expense.id}")
        },
        elevation = CardDefaults.cardElevation(12.dp),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ){

        Row(
            modifier = Modifier.
            fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            if (expense.imageUri != null) {
                AsyncImage(
                    model = expense.imageUri,
                    contentDescription = "Receipt",
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    painter = painterResource(R.drawable.blank_receipt),
                    contentDescription = "Blank receipt",
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(
                modifier = Modifier.padding(16.dp)
            )

            Column() {

                Text(text = expense.expenseTitle,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.secondary)

                Spacer(
                    modifier = Modifier.padding(2.dp)
                )

                Text(text = "£%.2f".format(expense.cost),
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.secondary)

            }

        }

    }

}