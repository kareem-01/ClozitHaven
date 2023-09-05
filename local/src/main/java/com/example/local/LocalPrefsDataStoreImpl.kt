package com.example.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.repository.dataSources.local.LocalDataStoreDataSource
import javax.inject.Inject
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalPrefsDataStoreImpl @Inject constructor(private val dataStore: DataStore<Preferences>) :
    LocalDataStoreDataSource {
    override suspend fun setApiKey(apiKey: String) {
        dataStore.setValue(APIKEY, apiKey)
    }

    override suspend fun getApiKey(): Flow<String> =
        dataStore.data.map { it[(APIKEY)] ?: "" }


    override suspend fun setUserName(name: String) {

    }

    override suspend fun setEmail(email: String) {
        
    }

    override suspend fun getUserName(): Flow<String> {
        TODO("Not yet implemented")
    }

    override suspend fun getEmail(): Flow<String> {
        TODO("Not yet implemented")
    }


    private suspend fun <T> DataStore<Preferences>.setValue(key: Preferences.Key<T>, value: T) {
        this.edit {
            it[key] = value
        }
    }

    companion object {
        private val APIKEY = stringPreferencesKey("API_KEY")
    }

}