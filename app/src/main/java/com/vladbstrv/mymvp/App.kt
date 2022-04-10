package com.vladbstrv.mymvp

import android.app.Application
import android.content.Context
import com.vladbstrv.mymvp.data.MockLoginApiImpl
import com.vladbstrv.mymvp.domain.LoginApi

class App : Application() {

    val loginApi: LoginApi by lazy {
        MockLoginApiImpl()
    }
}

val Context.app: App
    get() {
        return applicationContext as App
    }