package com.example.clozithaven.di

import com.example.repository.respository.CategoriesRepositoryImpl
import com.example.usecase.repositoryInterfaces.CategoriesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideCategoriesRepository(categoriesRepositoryImpl: CategoriesRepositoryImpl): CategoriesRepository
}