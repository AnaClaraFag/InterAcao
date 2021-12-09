package com.example.interacao.di

import android.content.Context
import com.example.interacao.data.TarefaDao
import com.example.interacao.data.TarefaDataBase
import com.example.interacao.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideDao(@ApplicationContext context: Context): TarefaDao{
        return TarefaDataBase.getDatabase(context).TarefaDao()
    }

    @Provides
    @Singleton
    fun provideTaskService(tarefaDao: TarefaDao): Repository {
        return Repository(tarefaDao)
    }
}