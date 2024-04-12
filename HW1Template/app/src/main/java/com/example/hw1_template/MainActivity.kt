package com.example.hw1_template

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//import com.example.hw1_template.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.UUID

// MainActivity: The heart of your application's UI.
// This class should coordinate the main user interactions and screen transitions.
class MainActivity : AppCompatActivity(), TaskItemClickListener {

    // onCreate: Critical for initializing the activity and setting up the UI components.

    private lateinit var recyclerView: RecyclerView
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        // RecyclerView setup: Essential for displaying a list of items.
        // You MUST properly initialize and configure your RecyclerView and its adapter.
        recyclerView = findViewById(R.id.rvTasks)
        // TaskItemAdapter is your custom adapter for the RecyclerView. You need to create this file.
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        findViewById<ExtendedFloatingActionButton>(R.id.newTaskBtn).setOnClickListener{
            NewTaskSheet(null).show(supportFragmentManager, "taskSheet")
        }
        taskViewModel.taskItemsList.observe(this) {
            recyclerView.adapter = TaskItemAdapter(it, this)
        }
    }

    override fun onItemClick(taskId: UUID) {
        var taskItem = taskViewModel.taskItemsList.value!!.find{
            it.uuid == taskId
        }
        NewTaskSheet(taskItem).show(supportFragmentManager, "taskSheet")
    }

    override fun completeTask(taskId: UUID) {
        taskViewModel.setCompleted(taskId)
    }

    override fun onItemDelete(taskId: UUID) {
        if (taskViewModel.deleteItem(taskId)) {
            Toast.makeText(this, "Item Deleted Successfully", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Item Deleted unsuccessfully", Toast.LENGTH_SHORT).show()
        }
    }
}

    // Instructions:
    // 1. Create NewTaskSheet.kt for handling the creation of new tasks.
    // 2. Implement TaskItemAdapter.kt to manage how task data is bound to the RecyclerView.
    // 3. Understand how TaskItem.kt represents individual task data.
    // 4. TaskViewModel.kt should handle all your data logic, like adding and retrieving tasks.
    // 5. TaskItemClickListener.kt and TaskItemViewHolder.kt are crucial for handling item interactions in your RecyclerView.

    // To add new Kotlin files for your classes, like NewTaskSheet or TaskItemAdapter,
// right-click on the package directory in the 'src/main/java' (or 'src/main/kotlin') folder in the Project view,
// then choose 'New' > 'Kotlin File/Class', name your file/class


