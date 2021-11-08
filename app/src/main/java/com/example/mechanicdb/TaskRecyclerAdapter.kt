package com.example.mechanicdb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mechanicdb.models.Task

import kotlinx.android.synthetic.main.garage_list_item.view.*

class TaskRecyclerAdapter(private val onClickListener: OnClickListener) : ListAdapter<Task, TaskRecyclerAdapter.TaskViewHolder>(TaskComparator()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val current = getItem(position)
        when(holder) {
            is TaskViewHolder -> {
                holder.itemView.setOnClickListener{
                    onClickListener.onClick(current)
                }
                holder.bind(current)
            }
        }
    }


    class TaskViewHolder constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView) {
        val task_name: TextView = itemView.taskNameText
        val task_compby: TextView = itemView.taskCompByText
        val task_odo: TextView = itemView.taskOdoText
        val task_compdate: TextView = itemView.taskDateCompleteText

        fun bind(task: Task) {
            task_name.text = "Task: ".plus(task.name)
            task_compby.text = "Completed By: ".plus(task.compby)
            task_odo.text = "Odometer: ".plus(task.odo)
            task_compdate.text = "Completed: ".plus(task.datecomp)
        }

        companion object{
            fun create(parent: ViewGroup): TaskViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                        .inflate(R.layout.task_list_item, parent, false)
                return TaskViewHolder(view)
            }
        }
    }

    class OnClickListener(val clickListener: (task: Task) -> Unit) {
        fun onClick(task: Task) = clickListener(task)
    }

    class TaskComparator : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.tid == newItem.tid
        }

    }


}