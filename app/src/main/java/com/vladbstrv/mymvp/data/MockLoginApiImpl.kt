package com.vladbstrv.mymvp.data

import androidx.annotation.WorkerThread
import com.vladbstrv.mymvp.domain.LoginApi
import com.vladbstrv.mymvp.domain.entity.User

class MockLoginApiImpl : LoginApi {

    private val listUser = mutableListOf<User>()

    @WorkerThread
    override fun login(login: String, password: String): Boolean {
        Thread.sleep(1_000)
        listUser.forEach {
            if (it.login == login && it.password == password) {
                return true
            }
        }
        return false
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
            listUser.add(User(login, password, email))
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
        listUser.forEach {
            if (it.login == login) return it.password
        }
        return "null"
    }
}