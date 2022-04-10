package com.vladbstrv.mymvp.domain

interface LoginUsecase {
    fun login(login: String, password: String, callback: (Boolean) -> Unit)
}