package com.example.jhubexpensesapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Insert
    suspend fun insert(expense: Expense)

    @Update
    suspend fun update(expense: Expense)

    @Delete
    suspend fun delete(expense: Expense)

    // Getting all expenses within the database
    @Query("SELECT * FROM expense_table ORDER BY id ASC")
    fun allExpenses() : LiveData<List<Expense>>

    // Getting the costs within the database
    @Query("SELECT cost FROM expense_table")
    fun getCosts() : Flow<Double?>

    @Query("SELECT * FROM expense_table WHERE id = :id")
    fun getExpenseById(id: Int): LiveData<Expense?>

}