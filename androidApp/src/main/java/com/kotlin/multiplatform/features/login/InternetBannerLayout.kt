package com.kotlin.multiplatform.features.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kotlin.multiplatform.features.login.internet.InternetBannerComponent
import com.kotlin.multiplatform.features.login.internet.PreviewInternetBannerComponent

@Composable
fun InternetBannerLayout(component: InternetBannerComponent) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Text("No Internet Connection!!!")
    }
}

@Preview
@Composable
private fun DefaultPreview() {
    InternetBannerLayout(PreviewInternetBannerComponent())
}