package com.example.jhubexpensesapp.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jhubexpensesapp.viewModel.ExpenseViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.jhubexpensesapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: ExpenseViewModel, navController: NavController){

val totalCost by viewModel.getAllCosts.collectAsState(initial = 0.0)

    val expenses by viewModel.getAllExpenses.observeAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("jHub Expenses",
                    fontSize = 36.sp,
                    color = MaterialTheme.colorScheme.tertiary)
            },
                actions = {
                    Text("£%.2f".format(totalCost),
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.padding(end = 16.dp))
                })
        },

        floatingActionButton = {
            FabNewExpense(navController)
        }

    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(horizontal = 8.dp)
        ) {

            items(
                items = expenses,
                key = { expense -> expense.id }
            ) { expense ->

                val dismissState = rememberSwipeToDismissBoxState(
                    confirmValueChange = { value ->

                        if (value == SwipeToDismissBoxValue.EndToStart) {
                            viewModel.deleteExpense(expense)
                            true
                        } else {
                            false
                        }
                    }
                )

                SwipeToDismissBox(
                    state = dismissState,

                    backgroundContent = {

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    MaterialTheme.colorScheme.error
                                )
                                .padding(horizontal = 20.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Text(
                                "Delete",
                                color = MaterialTheme.colorScheme.onError
                            )
                        }
                    },

                    enableDismissFromStartToEnd = false,
                    enableDismissFromEndToStart = true

                ) {

                    ExpenseItemCard(
                        expense = expense,
                        navController = navController
                    )
                }

                Spacer(
                    modifier = Modifier.height(8.dp)
                )
            }
        }

        }

    }

@Composable
fun FabNewExpense(navController: NavController) {

    ExtendedFloatingActionButton(
        onClick = {
            navController.navigate("add_expense")
        },

        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.secondary,

        icon = {
            Icon(
                painter = painterResource(R.drawable.new_expense_icon),
                contentDescription = "New Expense"
            )
        },
        text = {
            Text("New Expense")
        }
    )
}

