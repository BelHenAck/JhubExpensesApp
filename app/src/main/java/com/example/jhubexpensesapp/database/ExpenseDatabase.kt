package com.example.jhubexpensesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Specify that this will be an array of our entity
@Database(entities = [Expense::class], version = 1)
abstract class ExpenseDatabase: RoomDatabase() {

abstract val expenseDao: ExpenseDao

companion object {

    @Volatile // Ensure visibility on all threads
    private var INSTANCE: ExpenseDatabase? = null

    fun getInstance(context: Context): ExpenseDatabase{

        // Ensure only 1 thread can execute
        synchronized(this){

            var instance = INSTANCE

            if(instance == null){

                instance = Room.databaseBuilder(
                context = context.applicationContext,
                    ExpenseDatabase::class.java,
                    "expenses_database"
                ).build()

            }
            INSTANCE = instance

            return instance
        }


    }



}

}