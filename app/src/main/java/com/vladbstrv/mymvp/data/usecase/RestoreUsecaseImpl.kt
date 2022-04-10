package com.vladbstrv.mymvp.data.usecase

import android.os.Handler
import com.vladbstrv.mymvp.domain.LoginApi
import com.vladbstrv.mymvp.domain.usecase.RestoreUsecase

class RestoreUsecaseImpl(
    private val api: LoginApi,
    private val uiHandler: Handler
) : RestoreUsecase {

    override fun restorePassword(login: String, callback: (String) -> Unit) {
        Thread {
            val result = api.forgotPassword(login)
            uiHandler.post {
                callback(result)
            }
        }.start()
    }
}