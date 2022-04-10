package com.vladbstrv.mymvp.domain

interface LoginApi {

    fun login(login: String, password: String): Boolean

    fun register(login: String, password: String, repeatPassword: String, email: String): Boolean

    fun logout(): Boolean

    fun forgotPassword(login: String, email: String): Boolean
}