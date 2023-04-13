package com.kotlin.multiplatform.features.login

import com.arkivanov.decompose.router.stack.push
import com.kotlin.multiplatform.core.presentation.navigation.DefaultRootComponent
import com.kotlin.multiplatform.core.presentation.navigation.Navigation
import com.kotlin.multiplatform.core.presentation.presenter.RenderablePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginPresenter(private val navigation: Navigation) : RenderablePresenter<
    LoginViewState,
    LoginViewModel,
    LoginViewStore>(
    LoginViewStore(),
) {

    override suspend fun onBind() {
        collectActions(this::onAction)
        collectEffects(this::onEffect)
    }

    private suspend fun onAction(action: LoginAction) = when (action) {
        is LoginAction.EmailChanged -> render(LoginViewState.EmailChanged(action.email))
        is LoginAction.PasswordChanged -> render(LoginViewState.PasswordChanged(action.password))
        is LoginAction.LoginPressed -> withContext(Dispatchers.Main) { navigation.push(DefaultRootComponent.Config.Home) }
    }

    private suspend fun onEffect(effect: LoginEffect) = when (effect) {
        LoginEffect.LoginSuccessful -> {}
    }
}