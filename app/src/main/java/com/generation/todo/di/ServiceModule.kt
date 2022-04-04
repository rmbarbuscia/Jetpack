package com.generation.todo.di

import com.generation.todo.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//O objeto que armazenará as instâncias dos objetos que criaremos

@Module //Indica que o objeto armazenará os módulos
@InstallIn(SingletonComponent::class) //Indica que a classe criará objetos singleton
object ServiceModule {

    @Provides //indicar que o método que criaremos retornará uma instância de um objeto
    @Singleton
    fun providesRepository(): Repository{
        return Repository()
    }

}