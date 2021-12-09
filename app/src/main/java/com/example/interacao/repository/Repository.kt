package com.example.interacao.repository

import com.example.interacao.api.RetrofitInstance
import com.example.interacao.data.TarefaDao
import com.example.interacao.model.Tarefas
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class Repository (private val tarefaDao: TarefaDao) {


    suspend fun listTarefas(): Response<List<Tarefas>>{
        return RetrofitInstance.api.listTarefas()
    }

    suspend fun addTarefa(tarefas: Tarefas): Response<Tarefas>{
        return RetrofitInstance.api.addTarefa(tarefas)
    }

    suspend fun updateTarefa(tarefas: Tarefas): Response<Tarefas>{
        return RetrofitInstance.api.updateTarefa(tarefas)
    }

    suspend fun deleteTarefa(valor: Int): Response<Tarefas>{
        return RetrofitInstance.api.deleteTarefa(valor)
    }


    fun queryAllTarefas(): Flow<List<Tarefas>> {
        return tarefaDao.queryAllTarefas()
    }


    suspend fun insertTarefa(tarefas: Tarefas){
        tarefaDao.insertTarefa(tarefas)
    }

    fun queryById(id: Int): Flow<Tarefas?> {
        return tarefaDao.queryById(id)
    }

    suspend fun updateRoom(tarefas: Tarefas){
        tarefaDao.updateRoom(tarefas)
    }

    suspend fun deleteTarefaRoom(tarefas: Tarefas){
        return tarefaDao.deleteTarefaRoom(tarefas)
    }

}