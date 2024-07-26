package com.example.clozithaven.di

import com.example.repository.respository.AuthenticationRepositoryImpl
import com.example.repository.respository.CategoriesRepositoryImpl
import com.example.repository.respository.LocalPrefsRepositoryImpl
import com.example.repository.respository.ProductsRepositoryImpl
import com.example.usecase.repositoryInterfaces.AuthenticationRepository
import com.example.usecase.repositoryInterfaces.CategoriesRepository
import com.example.usecase.repositoryInterfaces.LocalPrefsRepository
import com.example.usecase.repositoryInterfaces.ProductsRepository
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

    @Binds
    @Singleton
    abstract fun provideAuthRepository(authenticationRepository: AuthenticationRepositoryImpl): AuthenticationRepository

    @Binds
    @Singleton
    abstract fun provideLocalPrefsRepository(repository: LocalPrefsRepositoryImpl): LocalPrefsRepository

    @Binds
    @Singleton
    abstract fun provideProductRepository(repository: ProductsRepositoryImpl): ProductsRepository

}