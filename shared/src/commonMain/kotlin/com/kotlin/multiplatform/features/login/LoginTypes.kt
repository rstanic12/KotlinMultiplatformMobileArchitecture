package com.kotlin.multiplatform.features.login

import com.kotlin.multiplatform.core.presentation.BasePresenter
import com.kotlin.multiplatform.core.presentation.BaseViewStore

typealias LoginViewStoreType = BaseViewStore<LoginViewState, LoginViewEvent, LoginViewModel, LoginViewContract>
typealias LoginPresenterType = BasePresenter<LoginViewState, LoginViewEvent, LoginViewModel, LoginViewContract, LoginViewStore>
