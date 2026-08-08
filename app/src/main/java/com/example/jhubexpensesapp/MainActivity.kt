package com.example.jhubexpensesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jhubexpensesapp.screens.AddExpenseScreen
import com.example.jhubexpensesapp.screens.HomeScreen
import com.example.jhubexpensesapp.ui.theme.JhubExpensesAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            JhubExpensesAppTheme {

            }

        }
    }
}

@Composable
fun AddExpenseScreen(){

    Row(modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.primary)
        .wrapContentHeight()
        .padding(start = 8.dp),
        horizontalArrangement = Arrangement.Start) {

        Text("Add Expense",
            fontWeight = FontWeight.Bold
        )

    }

}

@Preview(showBackground = true,
    showSystemUi = true)
@Composable
fun GreetingPreview() {
    JhubExpensesAppTheme {
        AddExpenseScreen()
    }
}