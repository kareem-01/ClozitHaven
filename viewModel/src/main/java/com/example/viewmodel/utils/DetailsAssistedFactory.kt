package com.example.viewmodel.utils

import com.example.viewmodel.details.DetailsViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory

@AssistedFactory
interface DetailsAssistedFactory {
    fun create(@Assisted itemId: String): DetailsViewModel
}