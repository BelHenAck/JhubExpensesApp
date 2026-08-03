package com.example.jhubexpensesapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jhubexpensesapp.repository.ExpenseRepository

class ViewModelFactory(private val repository: ExpenseRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ExpenseViewModel::class.java)){
            @Suppress("UNCHECKED CAST")
            return ExpenseViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }

}