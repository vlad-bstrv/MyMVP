package com.vladbstrv.mymvp.data

import androidx.annotation.WorkerThread
import com.vladbstrv.mymvp.domain.LoginApi

class MockLoginApiImpl : LoginApi {

    private val mapUser = mutableMapOf<String, String>()

    @WorkerThread
    override fun login(login: String, password: String): Boolean {
        Thread.sleep(1_000)
        return mapUser[login] == password
    }

    @WorkerThread
    override fun register(
        login: String,
        password: String,
        repeatPassword: String,
        email: String
    ): Boolean {
        Thread.sleep(1_000)
        if(password == repeatPassword) {
            mapUser[login] = password
            return true
        }
        return false
    }

    @WorkerThread
    override fun logout(): Boolean {
        Thread.sleep(1_000)
        return true
    }

    @WorkerThread
    override fun forgotPassword(login: String): String {
        Thread.sleep(1_000)
        return mapUser[login].toString()
    }
}