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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.jhubexpensesapp.database.ExpenseDatabase
import com.example.jhubexpensesapp.repository.ExpenseRepository
import com.example.jhubexpensesapp.screens.AddExpenseScreen
import com.example.jhubexpensesapp.screens.HomeScreen
import com.example.jhubexpensesapp.screens.UpdateExpenseScreen
import com.example.jhubexpensesapp.ui.theme.JhubExpensesAppTheme
import com.example.jhubexpensesapp.viewModel.ExpenseViewModel
import com.example.jhubexpensesapp.viewModel.ViewModelFactory

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val database = ExpenseDatabase.getInstance(applicationContext)
            val repository = ExpenseRepository(database.expenseDao)
            val viewModelFactory = ViewModelFactory(repository)

            val viewModel = ViewModelProvider(
                this, viewModelFactory)[ExpenseViewModel::class.java]

            JhubExpensesAppTheme {

                AddExpenseScreen(viewModel)

            }

        }
    }
}



@Preview(showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_7)
@Composable
fun GreetingPreview() {
    JhubExpensesAppTheme{

    }
}