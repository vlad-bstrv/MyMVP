package com.vladbstrv.mymvp

import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.vladbstrv.mymvp.domain.usecase.LoginUsecase
import com.vladbstrv.mymvp.ui.login.LoginContract
import com.vladbstrv.mymvp.ui.login.LoginPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LoginPresenterTest {

    private lateinit var presenter: LoginPresenter
    private val view: LoginContract.View = mock()
    private val useCase: LoginUsecase = mock()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = LoginPresenter(useCase)
        presenter.onAttach(view)
    }

    @Test
    fun onLogin_Test() {
        presenter.onLogin("", "")
        verify(useCase).login("","")
    }

    @Test
    fun onLogin_Test_UseCase_false() {
        Mockito.`when`(useCase.login(anyString(), anyString())).thenReturn(false)
        presenter.onLogin("", "")
        verify(view).setError()
    }

    @Test
    fun onLogin_Test_UseCase_true() {
        Mockito.`when`(useCase.login(anyString(), anyString())).thenReturn(true)
        presenter.onLogin("", "")
        verify(view).setSuccess()
    }
}