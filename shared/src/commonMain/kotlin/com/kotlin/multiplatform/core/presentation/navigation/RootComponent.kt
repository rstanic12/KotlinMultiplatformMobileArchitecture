package com.kotlin.multiplatform.core.presentation.navigation

import com.arkivanov.decompose.Child
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.kotlin.multiplatform.core.presentation.navigation.RootComponent.Child.HomeChild
import com.kotlin.multiplatform.core.presentation.navigation.RootComponent.Child.LoginChild
import com.kotlin.multiplatform.core.presentation.navigation.RootComponent.SlotChild.InternetBannerChild
import com.kotlin.multiplatform.features.home.HomeComponent
import com.kotlin.multiplatform.features.home.homeComponent
import com.kotlin.multiplatform.features.login.LoginComponent
import com.kotlin.multiplatform.features.login.PreviewLoginComponent
import com.kotlin.multiplatform.features.login.internet.InternetBannerComponent
import com.kotlin.multiplatform.features.login.internet.PreviewInternetBannerComponent
import com.kotlin.multiplatform.features.login.internet.internetBannerComponent
import com.kotlin.multiplatform.features.login.loginComponent

typealias Navigation = StackNavigation<DefaultRootComponent.Config>
typealias SlotNavigation = SlotNavigation<DefaultRootComponent.SlotConfig>

interface RootComponent {

    val stack: Value<ChildStack<*, Child>>
    val slot: Value<ChildSlot<*, SlotChild>>

    sealed class Child {
        class LoginChild(val component: LoginComponent) : Child()
        class HomeChild(val component: HomeComponent) : Child()
    }

    sealed class SlotChild {
        class InternetBannerChild(val component: InternetBannerComponent) : SlotChild()
    }
}

class DefaultRootComponent(
    componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    private val _stack = childStack(
        source = navigation,
        initialConfiguration = Config.Login,
        handleBackButton = true,
        childFactory = ::child
    )

    override val stack: Value<ChildStack<*, RootComponent.Child>> = _stack

    private val slotNavigation = SlotNavigation<SlotConfig>()

    private val _slot = childSlot(
        source = slotNavigation,
        childFactory = ::slotChild
    )

    override val slot: Value<ChildSlot<*, RootComponent.SlotChild>> = _slot

    private val internetBannerComponent = internetBannerComponent(componentContext, slotNavigation)

    private fun child(config: Config, componentContext: ComponentContext): RootComponent.Child {
        return when (config) {
            is Config.Login -> LoginChild(loginComponent(componentContext, navigation))
            is Config.Home -> HomeChild(homeComponent(componentContext, navigation))
        }
    }

    private fun slotChild(slotConfig: SlotConfig, componentContext: ComponentContext): RootComponent.SlotChild {
        return when (slotConfig) {
            SlotConfig.InternetBanner -> InternetBannerChild(internetBannerComponent)
        }
    }

    @Parcelize
    sealed interface Config : Parcelable {
        object Login : Config
        object Home : Config
    }

    @Parcelize
    sealed interface SlotConfig : Parcelable {
        object InternetBanner : SlotConfig
    }
}

class PreviewRootComponent : RootComponent {
    override val stack: Value<ChildStack<*, RootComponent.Child>> = MutableValue(
        ChildStack(
            configuration = Unit,
            instance = LoginChild(PreviewLoginComponent())
        )
    )
    override val slot: Value<ChildSlot<*, RootComponent.SlotChild>> = MutableValue(
        ChildSlot(
            child = Child.Created(
                configuration = Unit,
                instance = InternetBannerChild(PreviewInternetBannerComponent()),
            ),
        )
    )
}
