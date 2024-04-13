package com.example.hw1_template

import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

// TaskItemViewHolder: Holds the view for each task item in the list.
// VERY IMPORTANT: This class binds individual views in the RecyclerView to your data.
class TaskItemViewHolder(
    private val itemView: View,
    private val taskItemClickListener: TaskItemClickListener
) : RecyclerView.ViewHolder(itemView) {
    // TODO: Initialize your task item views, like TextView for the title, CheckBox for status, etc.
    val timeFormat = DateTimeFormatter.ofPattern("HH:mm")
    var tvTaskTitle: TextView
    var tvTime: TextView
    var ivCompleteTask: ImageView
    var ivDeleteTask: ImageView
    var itemCell: RelativeLayout


    init {
        tvTaskTitle = itemView.findViewById(R.id.tvTaskTitle)
        tvTime = itemView.findViewById(R.id.tvTaskTime)
        ivCompleteTask = itemView.findViewById(R.id.ivCompleteTask)
        ivDeleteTask = itemView.findViewById(R.id.ivDeleteTask)
        itemCell = itemView.findViewById(R.id.itemCell)
    }
    fun bindTaskItem(taskItem: TaskItem) {
        tvTaskTitle.text = taskItem.taskName

        ivCompleteTask.setImageResource(taskItem.imageResource())


        ivCompleteTask.setOnClickListener {
            taskItemClickListener.completeTask(taskItem.uuid)
        }

        itemCell.setOnClickListener {
            taskItemClickListener.onItemEdit(taskItem.uuid)
        }

        ivDeleteTask.setOnClickListener {
            taskItemClickListener.onItemDelete(taskItem.uuid)
        }

        if (taskItem.dueTime != null) {
            tvTime.text = timeFormat.format(taskItem.dueTime)
        } else {
            tvTime.text = ""
        }
    }
}
