package com.example.interacao.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.interacao.MainViewModel
import com.example.interacao.R
import com.example.interacao.model.Tarefas

class TarefaAdapter (
    private val taskItemClickListener: TaskItemClickListener,
    private val mainViewModel: MainViewModel
        )
    : RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>() {

    private var listTarefas = emptyList<Tarefas>()

    class TarefaViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textNome = view.findViewById<TextView>(R.id.textNome)
        val textDesc = view.findViewById<TextView>(R.id.textDesc)
        val textDono = view.findViewById<TextView>(R.id.textDono)
        val textData = view.findViewById<TextView>(R.id.textData)
        val textStatus = view.findViewById<TextView>(R.id.textStatus)
        val buttonDeletar = view.findViewById<ImageButton>(R.id.buttonDeletar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        val layoutAdapter = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_list, parent, false)

        return TarefaViewHolder(layoutAdapter)
    }

    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {

        val tarefa = listTarefas[position]

        holder.textNome.text = tarefa.name
        holder.textDesc.text = tarefa.description
        holder.textDono.text = tarefa.assignetTo
        holder.textData.text = tarefa.dueDate
        holder.textStatus.text = tarefa.status

        holder.buttonDeletar.setOnClickListener {
            mainViewModel.deleteTarefa(tarefa)
        }

        holder.itemView.setOnClickListener {
            taskItemClickListener.onTaskClicked(tarefa)
        }

    }

    override fun getItemCount(): Int {
        return listTarefas.size
    }

    fun setData(tarefas: List<Tarefas>){
        this.listTarefas = tarefas
        notifyDataSetChanged()
    }

}