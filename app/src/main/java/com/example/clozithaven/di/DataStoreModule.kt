package com.example.clozithaven.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    private const val DataStoreDataBaseName = "dataStoreName"

    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext appContext: Context,
        @Named(DataStoreDataBaseName) dataStoreName: String
    ): DataStore<Preferences> = PreferenceDataStoreFactory.create(
        produceFile = { appContext.preferencesDataStoreFile(dataStoreName) }
    )

    @Named(DataStoreDataBaseName)
    @Provides
    @Singleton
    fun provideDataStoreName(): String = "ApplicationDataStore"


}