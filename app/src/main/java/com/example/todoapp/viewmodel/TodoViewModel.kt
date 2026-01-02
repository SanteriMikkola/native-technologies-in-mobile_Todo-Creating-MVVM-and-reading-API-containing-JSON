package com.example.todoapp.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.model.Todo
import com.example.todoapp.model.TodosApi
import kotlinx.coroutines.launch
class TodoViewModel: ViewModel() {
    val todos = mutableStateListOf<Todo>()
    init {
        getTodosList()
    }

    private fun getTodosList() {
        viewModelScope.launch {
            try {
                val api = TodosApi.getInstance()
                val result = api.getTodos()
                todos.clear()
                todos.addAll(result)
            } catch (e: Exception) {
                Log.d("TODOVIEWMODEL", e.message ?: "Unknown error", e)
            }
        }
    }
}
