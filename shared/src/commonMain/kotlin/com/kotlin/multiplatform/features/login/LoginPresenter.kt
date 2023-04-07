package com.kotlin.multiplatform.features.login

import com.kotlin.multiplatform.core.presentation.presenter.RenderablePresenter

class LoginPresenter : RenderablePresenter<
    LoginViewState,
    LoginViewModel,
    LoginViewStore>(
    LoginViewStore(),
) {
    override suspend fun onBind() {
        collectActions(this::onAction)
        collectEffects(this::onEffect)
    }

    private fun onAction(action: LoginAction) = when (action) {
        is LoginAction.EmailChanged -> render(LoginViewState.EmailChanged(action.email))
        is LoginAction.PasswordChanged -> render(LoginViewState.PasswordChanged(action.password))
        is LoginAction.LoginPressed -> {}
    }

    private fun onEffect(effect: LoginEffect) = when (effect) {
        LoginEffect.LoginSuccessful -> {}
    }
}