package com.example.hw1_template

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.example.hw1_template.databinding.FragmentNewTaskSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

// NewTaskSheet: This Activity/Fragment is responsible for adding new tasks.
// Neglecting the input handling here will break the core functionality of task addition.
class NewTaskSheet(var taskItem: TaskItem?) : BottomSheetDialogFragment() {

    // onCreate: Again, crucial for setting up the layout and functionality.
    private lateinit var etTaskTitle: EditText
    private lateinit var etDesc: EditText
    private lateinit var binding: FragmentNewTaskSheetBinding
    private lateinit var taskViewModel: TaskViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = requireActivity()

//        binding.btnSave.setOnClickListener {
//            saveAction()
//        }
        etTaskTitle = requireView().findViewById(R.id.etTaskTitle)
        etDesc = requireView().findViewById(R.id.etDesc)

        if (taskItem != null) {
            view.findViewById<TextView>(R.id.taskTitle).text = "Update Task"

        }

        taskViewModel = ViewModelProvider(activity).get(TaskViewModel::class.java)
        requireView().findViewById<Button>(R.id.btnSaveTask).setOnClickListener{
            saveAction()
        }
    }

    private fun saveAction() {
        var name = etTaskTitle.text.toString()
        var description = etDesc.text.toString()

        if (taskItem == null) {
            val newTask = TaskItem(name, description, null)
            taskViewModel.addTaskItem(newTask)
        }
        etTaskTitle.setText("")
        etDesc.setText("")
        dismiss()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewTaskSheetBinding.inflate(inflater, container, false)
        return inflater.inflate(R.layout.fragment_new_task_sheet, container, false)
    }
}
