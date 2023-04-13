package com.kotlin.multiplatform.features.home

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.kotlin.multiplatform.core.presentation.ActionDispatcher
import com.kotlin.multiplatform.core.presentation.navigation.Navigation
import com.kotlin.multiplatform.core.presentation.viewcontract.ViewContract
import com.kotlin.multiplatform.features.login.LoginComponent

typealias TabNavigation = StackNavigation<DefaultHomeComponent.TabConfig>

interface HomeComponent : ViewContract<HomeViewModel> {
    val viewModel: Value<HomeViewModel>
    val actionDispatcher: ActionDispatcher

    val tabStack: Value<ChildStack<*, TabChild>>

    sealed class TabChild {
        class MyCarChild(val component: MyCarComponent) : TabChild()
        class DrivingActivitiesChild(val component: HomeComponent) : TabChild()
        class MapChild(val component: HomeComponent) : TabChild()
        class HvacChild(val component: HomeComponent) : TabChild()
        class BatteryChild(val component: HomeComponent) : TabChild()
    }
}

class DefaultHomeComponent(
    componentContext: ComponentContext,
    navigation: Navigation,
    override val actionDispatcher: ActionDispatcher,
    override val viewModel: MutableValue<HomeViewModel> = MutableValue(HomeViewModel())
) : HomeComponent, ComponentContext by componentContext {

    val tabNavigation = StackNavigation<TabConfig>()

    private val _tabStack = childStack(
        source = tabNavigation,
        initialConfiguration = TabConfig.MyCar,
        handleBackButton = false,
        childFactory = ::tabChild
    )
    override val tabStack: Value<ChildStack<*, HomeComponent.TabChild>> = _tabStack

    private val homePresenter = HomePresenter(navigation, tabNavigation)

    init {
        actionDispatcher.bind(homePresenter, this)
        instanceKeeper.put(homePresenter, homePresenter)
    }

    override fun render(viewModel: HomeViewModel) {
        this.viewModel.value = viewModel
    }

    private fun tabChild(config: TabConfig, componentContext: ComponentContext): HomeComponent.TabChild {
        return when (config) {
            TabConfig.MyCar -> HomeComponent.TabChild.MyCarChild(DefaultMyCarComponent())
            TabConfig.DrivingActivities -> HomeComponent.TabChild.DrivingActivitiesChild(this)
            TabConfig.Map -> HomeComponent.TabChild.MapChild(this)
            TabConfig.Hvac -> HomeComponent.TabChild.HvacChild(this)
            TabConfig.Battery -> HomeComponent.TabChild.BatteryChild(this)
        }
    }

    @Parcelize
    sealed interface TabConfig : Parcelable {
        object MyCar : TabConfig
        object DrivingActivities : TabConfig
        object Map : TabConfig
        object Hvac : TabConfig
        object Battery : TabConfig
    }
}

class PreviewHomeComponent(
    override val actionDispatcher: ActionDispatcher = ActionDispatcher(),
    override val viewModel: MutableValue<HomeViewModel> = MutableValue(HomeViewModel()),
) : HomeComponent {
    override val tabStack: Value<ChildStack<*, HomeComponent.TabChild>> = MutableValue(
        ChildStack(
            configuration = Unit,
            instance = HomeComponent.TabChild.MyCarChild(PreviewMyCarComponent())
        )
    )
}

fun homeComponent(componentContext: ComponentContext, navigation: Navigation): HomeComponent {
    return DefaultHomeComponent(
        componentContext,
        navigation,
        ActionDispatcher(),
    )
}
