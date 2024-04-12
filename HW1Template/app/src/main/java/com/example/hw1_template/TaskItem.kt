package com.example.hw1_template

import java.util.UUID

// TaskItem: Represents the data structure for a task in your to-do list.
// IMPORTANT: This class must be well-defined; it's the blueprint for your task data.
data class TaskItem(
    var taskName: String = "",
    var taskDesc: String = "",
    var dueTime: String? = null,
    var isComplete: Boolean = false,
    var uuid: UUID = UUID.randomUUID()
) {
    fun imageResource(): Int = if (isComplete) R.drawable.check_24 else R.drawable.uncheck_24
}
