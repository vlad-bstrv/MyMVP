package com.vladbstrv.mymvp

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import com.vladbstrv.mymvp.data.LoginUsecaseImpl
import com.vladbstrv.mymvp.data.MockLoginApiImpl
import com.vladbstrv.mymvp.domain.LoginApi
import com.vladbstrv.mymvp.domain.LoginUsecase

class App : Application() {

    private val loginApi: LoginApi by lazy {
        MockLoginApiImpl()
    }

    val loginUsecase: LoginUsecase by lazy {
        LoginUsecaseImpl(app.loginApi, Handler(Looper.getMainLooper()))
    }

}

val Context.app: App
    get() {
        return applicationContext as App
    }