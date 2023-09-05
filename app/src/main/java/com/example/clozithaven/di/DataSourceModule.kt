package com.example.clozithaven.di

import com.example.local.LocalPrefsDataStoreImpl
import com.example.remote.dataSource.RemoteDataSourceImpl
import com.example.repository.dataSources.local.LocalDataStoreDataSource
import com.example.repository.dataSources.remote.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Singleton
    @Binds
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource
    @Singleton
    @Binds
    abstract fun bindLocalDataStoreDataSource(localPrefsDataStore: LocalPrefsDataStoreImpl):LocalDataStoreDataSource
}