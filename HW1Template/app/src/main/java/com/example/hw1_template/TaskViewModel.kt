package com.example.hw1_template

import android.os.Build
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

// TaskViewModel: Handles the business logic of your task data, separating concerns from the UI.
// VITAL: This ViewModel should be the sole source of truth for your UI regarding task data.
class TaskViewModel : ViewModel() {
    // TODO: Implement the logic for managing task data, such as adding, deleting, and updating tasks.
    var taskItemsList = MutableLiveData<MutableList<TaskItem>>()

    init {
        taskItemsList.value = mutableListOf()
    }

    fun addTaskItem(newTask: TaskItem) {
        val list = taskItemsList.value
        list!!.add(newTask)
        taskItemsList.postValue(list)
    }


    fun update(
        uuid: UUID,
        name: String,
        description: String,
        dueTime: LocalTime?
    ) {
        val list = taskItemsList.value
        val task = list!!.find{
            it.uuid == uuid
        }!!
        task.taskName = name
        task.taskDesc = description
        task.dueTime = dueTime

        taskItemsList.postValue(list)
    }

    fun setCompleted(
        taskId: UUID
    ) {
        val list = taskItemsList.value
        val task = list!!.find{
            it.uuid == taskId
        }!!
        task.isComplete = true

        taskItemsList.postValue(list)
    }

    fun deleteItem(
        taskId: UUID
    ) : Boolean{
        val list = taskItemsList.value
        val task = list!!.find{
            it.uuid == taskId
        }!!

        var isRemoved = list.remove(task)

        taskItemsList.postValue(list)

        return isRemoved
    }
}
