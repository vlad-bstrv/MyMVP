package com.vladbstrv.mymvp.domain.usecase

interface RestoreUsecase {

    fun restorePassword(login: String, callback:(String) -> Unit)
}