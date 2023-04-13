package com.kotlin.multiplatform.features.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.bringToFront
import com.kotlin.multiplatform.features.home.HomeComponent.TabChild.BatteryChild
import com.kotlin.multiplatform.features.home.HomeComponent.TabChild.DrivingActivitiesChild
import com.kotlin.multiplatform.features.home.HomeComponent.TabChild.HvacChild
import com.kotlin.multiplatform.features.home.HomeComponent.TabChild.MapChild
import com.kotlin.multiplatform.features.home.HomeComponent.TabChild.MyCarChild

@Composable
fun HomeLayout(component: HomeComponent, modifier: Modifier = Modifier) {
    Column {
        Children(
            stack = component.tabStack,
            modifier = modifier,
            animation = stackAnimation(fade() + scale())
        ) {
            when (val child = it.instance) {
                is MyCarChild -> Text("MyCar")
                is DrivingActivitiesChild -> Text("Driving Activities")
                is MapChild -> Text("Map")
                is HvacChild -> Text("Hvac")
                is BatteryChild -> Text("Battery")
            }
        }
        BottomNavigation {
            BottomNavigationItem(
                selected = component.tabStack.value.active.instance is MyCarChild,
                onClick = { (component as DefaultHomeComponent).tabNavigation.bringToFront(DefaultHomeComponent.TabConfig.MyCar)},
                icon = { Icon(Icons.Default.ShoppingCart, null) }
            )
            BottomNavigationItem(
                selected = component.tabStack.value.active.instance is DrivingActivitiesChild,
                onClick = { (component as DefaultHomeComponent).tabNavigation.bringToFront(DefaultHomeComponent.TabConfig.DrivingActivities)},
                icon = { Icon(Icons.Default.List, null) }
            )
            BottomNavigationItem(
                selected = component.tabStack.value.active.instance is MapChild,
                onClick = { /*TODO*/ },
                icon = { Icon(Icons.Default.MailOutline, null) }
            )
            BottomNavigationItem(
                selected = component.tabStack.value.active.instance is HvacChild,
                onClick = { /*TODO*/ },
                icon = { Icon(Icons.Default.List, null) }
            )
            BottomNavigationItem(
                selected = component.tabStack.value.active.instance is BatteryChild,
                onClick = { /*TODO*/ },
                icon = { Icon(Icons.Default.ShoppingCart, null) }
            )
        }
    }
}

@Preview
@Composable
private fun DefaultPreview() {
    HomeLayout(PreviewHomeComponent())
}