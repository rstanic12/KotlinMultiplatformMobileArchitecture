package com.kotlin.multiplatform.features.login

import com.kotlin.multiplatform.core.presentation.presenter.BasePresenter

class LoginAnalyticsPresenter : BasePresenter() {
    override suspend fun onBind() {
        collectActions(this::onAction)
    }

    private fun onAction(action: LoginAction) = when (action) {
        is LoginAction.EmailChanged -> println("Email changed notify analytics ${action.email}")
        is LoginAction.PasswordChanged -> println("Password changed notify analytics ${action.password}")
        is LoginAction.LoginPressed -> println("Login button pressed notify analytics")
    }
}