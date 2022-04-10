package com.vladbstrv.mymvp

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import com.vladbstrv.mymvp.data.usecase.LoginUsecaseImpl
import com.vladbstrv.mymvp.data.MockLoginApiImpl
import com.vladbstrv.mymvp.data.usecase.RegisterUsecaseImpl
import com.vladbstrv.mymvp.data.usecase.RestoreUsecaseImpl
import com.vladbstrv.mymvp.domain.LoginApi
import com.vladbstrv.mymvp.domain.usecase.LoginUsecase
import com.vladbstrv.mymvp.domain.usecase.RegisterUsecase
import com.vladbstrv.mymvp.domain.usecase.RestoreUsecase

class App : Application() {

    private val loginApi: LoginApi by lazy {
        MockLoginApiImpl()
    }

    val loginUsecase: LoginUsecase by lazy {
        LoginUsecaseImpl(app.loginApi, Handler(Looper.getMainLooper()))
    }

    val registerUsecase: RegisterUsecase by lazy {
        RegisterUsecaseImpl(app.loginApi, Handler(Looper.getMainLooper()))
    }

    val restoreUsecase: RestoreUsecase by lazy {
        RestoreUsecaseImpl(app.loginApi, Handler(Looper.getMainLooper()))
    }


}

val Context.app: App
    get() {
        return applicationContext as App
    }