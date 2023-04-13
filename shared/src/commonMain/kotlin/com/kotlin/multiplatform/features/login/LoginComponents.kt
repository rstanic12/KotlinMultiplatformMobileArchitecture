package com.kotlin.multiplatform.features.login

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.kotlin.multiplatform.core.presentation.ActionDispatcher
import com.kotlin.multiplatform.core.presentation.navigation.Navigation
import com.kotlin.multiplatform.core.presentation.viewcontract.ViewContract

interface LoginComponent : ViewContract<LoginViewModel> {
    val viewModel: Value<LoginViewModel>
    val actionDispatcher: ActionDispatcher
}

class DefaultLoginComponent(
    componentContext: ComponentContext,
    navigation: Navigation,
    override val actionDispatcher: ActionDispatcher = ActionDispatcher(),
    override val viewModel: MutableValue<LoginViewModel> = MutableValue(LoginViewModel()),
) : LoginComponent, ComponentContext by componentContext {

    private val loginPresenter = LoginPresenter(navigation)

    init {
        actionDispatcher.bind(loginPresenter, this)
        instanceKeeper.put(loginPresenter, loginPresenter)
    }

    override fun render(viewModel: LoginViewModel) {
        this.viewModel.value = viewModel
    }
}

class PreviewLoginComponent(
    override val actionDispatcher: ActionDispatcher = ActionDispatcher(),
    override val viewModel: Value<LoginViewModel> = MutableValue(LoginViewModel())
) : LoginComponent

fun loginComponent(componentContext: ComponentContext, navigation: Navigation): LoginComponent =
    DefaultLoginComponent(componentContext, navigation)
