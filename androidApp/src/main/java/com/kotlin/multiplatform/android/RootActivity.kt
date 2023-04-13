package com.kotlin.multiplatform.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import com.kotlin.multiplatform.core.presentation.navigation.DefaultRootComponent

class RootActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val component = DefaultRootComponent(
            defaultComponentContext()
        )

        setContent { RootLayout(component) }
    }
}
