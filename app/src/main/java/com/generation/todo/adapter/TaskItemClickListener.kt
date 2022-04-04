package com.generation.todo.adapter

import com.generation.todo.model.Tarefa

interface TaskItemClickListener {
    fun onTaskClicked(tarefas: Tarefa)
}