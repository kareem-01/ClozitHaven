package com.example.usecase.useCases.products

import javax.inject.Inject

class AddProductToCartUseCase @Inject constructor() {
    suspend operator fun invoke(productId: String, quantity: Int) {

    }
}