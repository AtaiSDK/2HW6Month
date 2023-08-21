package com.example.a2hw6month

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.a2hw6month.databinding.FragmentAddBinding
import java.util.*

class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private val viewModel: TaskViewModel by viewModels()
    private var editedTask: Task? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        if (args != null && args.containsKey("task")) {
            editedTask = args.getSerializable("task") as Task
            binding.taskEditText.setText(editedTask?.text)
        }

        binding.saveButton.setOnClickListener {
            val taskText = binding.taskEditText.text.toString()
            if (taskText.isNotEmpty()) {
                if (editedTask != null) {
                    editedTask?.text = taskText
                    viewModel.updateTask(editedTask!!)
                } else {
                    val newTask = Task(UUID.randomUUID().toString(), taskText)
                    viewModel.addTask(newTask)
                }

                requireActivity().onBackPressed()
            } else {
                Toast.makeText(requireContext(), "Введите текст задачи", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
