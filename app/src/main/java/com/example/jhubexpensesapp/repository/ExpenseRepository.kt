package com.example.jhubexpensesapp.repository

import androidx.lifecycle.LiveData
import com.example.jhubexpensesapp.database.Expense
import com.example.jhubexpensesapp.database.ExpenseDao
import kotlinx.coroutines.flow.Flow

class ExpenseRepository(private val expenseDao: ExpenseDao) {

    suspend fun insertExpense(expense: Expense) = expenseDao.insert(expense)

    suspend fun deleteExpense(expense: Expense) = expenseDao.delete(expense)

    suspend fun updateExpense(expense: Expense) = expenseDao.update(expense)

    val getAllExpenses : LiveData<List<Expense>> = expenseDao.allExpenses()

    fun getAllCost() : Flow<Double?>{
        return expenseDao.getCosts()
    }

    fun getExpenseById(id: Int): LiveData<Expense?> {
        return expenseDao.getExpenseById(id)
    }

}