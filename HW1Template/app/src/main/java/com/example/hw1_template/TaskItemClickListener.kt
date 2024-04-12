package com.example.hw1_template

import java.util.UUID


// TaskItemClickListener: An interface for handling clicks on items in your list.
// ESSENTIAL: You must implement this interface in your ViewHolder or Activity to respond to user interactions.
interface TaskItemClickListener {
    fun onItemClick(taskId: UUID)
    fun completeTask(taskId: UUID)
    // Add more methods as needed, like onItemDelete, onItemEdit, etc.
}
