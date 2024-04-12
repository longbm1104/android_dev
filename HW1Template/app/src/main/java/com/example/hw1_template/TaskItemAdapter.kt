package com.example.hw1_template

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

// TaskItemAdapter: Manages how task items are displayed and interacted with in a RecyclerView.
// CRITICAL: This adapter links your TaskItem data to the RecyclerView in the UI.
class TaskItemAdapter(private val taskItemsList: List<TaskItem>, private val taskItemClickListener: TaskItemClickListener) : RecyclerView.Adapter<TaskItemViewHolder>() {

    // TODO: Implement necessary methods like onCreateViewHolder, onBindViewHolder, and getItemCount
    // onCreateViewHolder: Create new views (invoked by the layout manager)
    // onBindViewHolder: Replace the contents of a view (invoked by the layout manager)
    // getItemCount: Return the size of your dataset (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
        val from = LayoutInflater.from(parent.context)
        val view = from.inflate(R.layout.task_item_cell, parent, false)
        return TaskItemViewHolder(view, taskItemClickListener)
    }

    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        holder.bindTaskItem(taskItemsList[position])
    }

    override fun getItemCount(): Int {
        return taskItemsList.size
    }
}
