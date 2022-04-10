package com.vladbstrv.mymvp.domain.usecase

interface RegisterUsecase {
    fun register(
        login: String,
        password: String,
        repeatPassword: String,
        email: String,
        callback: (Boolean) -> Unit
    )
}