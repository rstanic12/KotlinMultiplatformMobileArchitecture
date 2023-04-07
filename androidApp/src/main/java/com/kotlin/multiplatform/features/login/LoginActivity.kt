package com.kotlin.multiplatform.features.login

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.mutableStateOf
import com.kotlin.multiplatform.android.BaseActivity
import com.kotlin.multiplatform.core.presentation.ActionDispatcher
import com.kotlin.multiplatform.features.login.internet.InternetBannerPresenter

class LoginActivity : BaseActivity(), LoginViewContract {

    private val actionDispatcher = ActionDispatcher()
    private val loginPresenter = LoginPresenter()
    private val loginAnalyticsPresenter = LoginAnalyticsPresenter()
    private val internetBannerPresenter = InternetBannerPresenter()
    private var viewModel = mutableStateOf(LoginViewModel())
    private val internetBannerViewContractHolder = InternalBannerViewContactHolder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box {
                LoginLayout(viewModel.value, actionDispatcher)
                InternetBannerView(internetBannerViewContractHolder.viewModel.value)
            }
        }
        actionDispatcher.bind(loginPresenter, this)
        actionDispatcher.bind(loginAnalyticsPresenter)
        actionDispatcher.bind(internetBannerPresenter, internetBannerViewContractHolder)
    }

    override fun onDestroy() {
        actionDispatcher.unbind(loginPresenter)
        actionDispatcher.unbind(loginAnalyticsPresenter)
        super.onDestroy()
    }

    override fun render(viewModel: LoginViewModel) {
        this.viewModel.value = viewModel
    }
}
