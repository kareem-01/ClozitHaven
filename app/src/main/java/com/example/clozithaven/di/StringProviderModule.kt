package com.example.clozithaven.di

import com.example.ui.utils.StringsProviderImpl
import com.example.viewmodel.StringsProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class StringProviderModule {
@Binds
@ViewModelScoped
abstract fun provideStringResource(stringsProvider: StringsProviderImpl):StringsProvider
}