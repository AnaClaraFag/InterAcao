package com.example.interacao.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.interacao.model.Tarefas

@Database(entities = [Tarefas::class], version = 1, exportSchema = false)
abstract class TarefaDataBase: RoomDatabase() {

    abstract fun TarefaDao(): TarefaDao

    companion object {
        @Volatile
        private var INSTANCE: TarefaDataBase? = null

        fun getDatabase(context: Context): TarefaDataBase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TarefaDataBase::class.java,
                    "tarefas_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}