package com.example.interacao.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "tarefa_table")
data class Tarefas (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name: String,
    var description: String,
    var assignetTo: String,
    var dueDate: String,
    var status: String
    ) {
}