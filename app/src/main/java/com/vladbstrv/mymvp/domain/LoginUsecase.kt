package com.vladbstrv.mymvp.domain

import androidx.annotation.MainThread

interface LoginUsecase {
    fun login(login: String, password: String, @MainThread callback: (Boolean) -> Unit)
}