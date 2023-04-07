package com.kotlin.multiplatform.features.login

class LoginAnalyticsPresenter : LoginPresenterType(LoginViewStore()) {
    override suspend fun onBind() {
        collect<LoginAction> { onAction(it) }
    }

    private fun onAction(action: LoginAction) = when (action) {
        is LoginAction.EmailChanged -> println("Email changed notify analytics ${action.email}")
        is LoginAction.PasswordChanged -> println("Password changed notify analytics ${action.password}")
        is LoginAction.LoginPressed -> println("Login button pressed notify analytics")
    }
}