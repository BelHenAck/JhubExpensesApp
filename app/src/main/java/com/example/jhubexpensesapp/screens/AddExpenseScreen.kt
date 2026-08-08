package com.example.jhubexpensesapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.jhubexpensesapp.R


@Composable
fun AddExpenseScreen(){

    Column(modifier = Modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.primary)) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondary)
                .wrapContentHeight()
                .padding(start = 16.dp)
        ) {

            Text(
                "Add Expense",
                fontWeight = FontWeight.Bold
            )

        }

        Image(
            painter = painterResource(R.drawable.blank_receipt),
            contentDescription = "Blank receipt image",
            modifier = Modifier
                .size(300.dp)
                .align(Alignment.CenterHorizontally)
                .padding(top = 100.dp),
        )

    }

}