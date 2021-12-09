package com.example.interacao.adapter

import com.example.interacao.model.Tarefas


interface TaskItemClickListener {

    fun onTaskClicked(tarefas: Tarefas)
}