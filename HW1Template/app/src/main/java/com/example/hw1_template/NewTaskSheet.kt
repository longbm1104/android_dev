package com.example.hw1_template

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.example.hw1_template.databinding.FragmentNewTaskSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.time.LocalTime

// NewTaskSheet: This Activity/Fragment is responsible for adding new tasks.
// Neglecting the input handling here will break the core functionality of task addition.
class NewTaskSheet(var taskItem: TaskItem?) : BottomSheetDialogFragment() {

    // onCreate: Again, crucial for setting up the layout and functionality.
    private lateinit var etTaskTitle: EditText
    private lateinit var etDesc: EditText
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var btnSelectTime: Button
    private var dueTime: LocalTime? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = requireActivity()

        etTaskTitle = requireView().findViewById(R.id.etTaskTitle)
        etDesc = requireView().findViewById(R.id.etDesc)
        btnSelectTime = requireView().findViewById(R.id.btnSelectTime)

        if (taskItem != null) {
            view.findViewById<TextView>(R.id.taskTitle).text = "Update Task"
            etTaskTitle.setText(taskItem!!.taskName)
            etDesc.setText(taskItem!!.taskDesc)
            if (taskItem!!.dueTime != null) {
                dueTime = taskItem!!.dueTime
                setTimeText(dueTime)
            }
        }

        taskViewModel = ViewModelProvider(activity).get(TaskViewModel::class.java)
        requireView().findViewById<Button>(R.id.btnSaveTask).setOnClickListener{
            saveAction()
        }

        btnSelectTime.setOnClickListener{
            showTimePicker()
        }
    }

    private fun showTimePicker() {
        dueTime = dueTime ?: LocalTime.now()
        val listener = TimePickerDialog.OnTimeSetListener {
            _, pickHour, pickMinute -> dueTime = LocalTime.of(pickHour, pickMinute)
            setTimeText(dueTime)
        }

        val dialog = TimePickerDialog(activity, listener, dueTime!!.hour, dueTime!!.minute, true)
        dialog.setTitle("Pick Due Time")
        dialog.show()

    }

    private fun saveAction() {
        var name = etTaskTitle.text.toString()
        var description = etDesc.text.toString()

        if (taskItem == null) {
            val newTask = TaskItem(name, description, dueTime, false)
            taskViewModel.addTaskItem(newTask)
        } else {
            taskViewModel.update(taskItem!!.uuid, name, description, dueTime)
        }
        etTaskTitle.setText("")
        etDesc.setText("")
        dismiss()
    }

    private fun setTimeText(dueTime: LocalTime?) {
        btnSelectTime.text = String.format("%02d:%02d",dueTime!!.hour, dueTime.minute)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_task_sheet, container, false)
    }
}
