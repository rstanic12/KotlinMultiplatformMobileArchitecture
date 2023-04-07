package com.kotlin.multiplatform.features.login

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import com.kotlin.multiplatform.android.BaseActivity
import com.kotlin.multiplatform.core.presentation.ActionDispatcher

class LoginActivity : BaseActivity(), LoginViewContract {

    private val actionDispatcher = ActionDispatcher()
    private val loginPresenter = LoginPresenter()
    private val loginAnalyticsPresenter = LoginAnalyticsPresenter()
    private var viewModel = mutableStateOf(LoginViewModel())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginLayout(viewModel.value, actionDispatcher)
        }
        actionDispatcher.bind(loginPresenter, this)
        actionDispatcher.bind(loginAnalyticsPresenter, this)
    }

    override fun onDestroy() {
        actionDispatcher.unbind(loginPresenter)
        actionDispatcher.unbind(loginAnalyticsPresenter)
        super.onDestroy()
    }

    override fun render(viewModel: LoginViewModel) {
        this.viewModel.value = viewModel
    }

    override fun navigateToHomeScreen() {

    }
}
