package com.vladbstrv.mymvp.data.usecase

import android.os.Handler
import com.vladbstrv.mymvp.domain.LoginApi
import com.vladbstrv.mymvp.domain.usecase.RegisterUsecase

class RegisterUsecaseImpl(private val loginApi: LoginApi, private val uiHandler: Handler) : RegisterUsecase {

    override fun register(
        login: String,
        password: String,
        repeatPassword: String,
        email: String,
        callback: (Boolean) -> Unit
    ) {
        Thread {
            val result = loginApi.register(login, password, repeatPassword, email)
            uiHandler.post{
                callback(result)
            }
        }.start()
    }
}