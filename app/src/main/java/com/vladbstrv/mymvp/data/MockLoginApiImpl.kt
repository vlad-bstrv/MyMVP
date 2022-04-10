package com.vladbstrv.mymvp.data

import com.vladbstrv.mymvp.domain.LoginApi

class MockLoginApiImpl: LoginApi {

    override fun login(login: String, password: String): Boolean {
        Thread.sleep(1_000)
        return login =="login" && password == "password"
    }

    override fun register(login: String, password: String, repeatPassword: String, email: String): Boolean {
        Thread.sleep(1_000)
        return true
    }

    override fun logout(): Boolean {
        Thread.sleep(1_000)
        return true
    }

    override fun forgotPassword(login: String, email: String): Boolean {
        Thread.sleep(1_000)
        return true
    }
}