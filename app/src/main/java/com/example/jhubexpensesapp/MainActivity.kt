package com.example.jhubexpensesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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

            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "home_page") {

            composable("home_page"){
                HomeScreen(viewModel = viewModel,
                    navController = navController)
            }

            composable("add_expense"){
                AddExpenseScreen(
                    viewModel = viewModel,
                    navController = navController
                )

            }

                composable("update_expense/{expenseId}"){
                    backStackEntry ->

                    val expenseId = backStackEntry
                        .arguments
                        ?.getString("expenseId")
                        ?.toIntOrNull()

                    if(expenseId != null){
                        val expense by viewModel
                            .getExpenseById(expenseId)
                            .observeAsState()

                        expense?.let{
                            UpdateExpenseScreen(expense = it,
                                viewModel = viewModel,
                                navController = navController)
                        }

                    }

                }

            }

            JhubExpensesAppTheme {



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