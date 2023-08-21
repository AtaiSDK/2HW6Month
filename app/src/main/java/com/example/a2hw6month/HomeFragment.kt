package com.example.a2hw6month

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.a2hw6month.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: TaskViewModel by viewModels()
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        taskAdapter = TaskAdapter()
        taskAdapter.setOnItemClickListener { task: Task ->
            val bundle = Bundle()
            bundle.putSerializable("task", task)
            navController.navigate(R.id.addFragment, bundle)
        }

        binding.taskRecyclerView.adapter = taskAdapter

        viewModel.tasks.observe(viewLifecycleOwner) { tasks ->
            taskAdapter.setTasks(tasks)
            taskAdapter.notifyDataSetChanged()
        }

        binding.addButton.setOnClickListener {
            navController.navigate(R.id.addFragment)
        }
    }
}
