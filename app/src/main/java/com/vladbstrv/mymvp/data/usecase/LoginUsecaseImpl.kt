package com.vladbstrv.mymvp.data.usecase

import android.os.Handler
import androidx.annotation.MainThread
import com.vladbstrv.mymvp.domain.LoginApi
import com.vladbstrv.mymvp.domain.usecase.LoginUsecase

class LoginUsecaseImpl(
    private val loginApi: LoginApi,
) : LoginUsecase {
    override fun login(login: String, password: String, callback: (Boolean) -> Unit) {
        Thread {
            val result = loginApi.login(login, password)
                callback(result)
        }.start()
    }
}