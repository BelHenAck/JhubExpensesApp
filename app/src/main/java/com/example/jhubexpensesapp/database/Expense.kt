package com.example.jhubexpensesapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expense_table")
data class Expense (

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val expenseTitle: String,
    val cost: Double,
    val metaDataDate: Long,
    val isPaid: Boolean = false,
    val imageAsString: String


)