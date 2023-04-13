package com.kotlin.multiplatform.features.login

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kotlin.multiplatform.features.login.internet.InternetBannerComponent
import com.kotlin.multiplatform.features.login.internet.PreviewInternetBannerComponent

@Composable
fun InternetBannerLayout(component: InternetBannerComponent) {
    Text("No Internet Connection!!!")
}

@Preview
@Composable
private fun DefaultPreview() {
    InternetBannerLayout(PreviewInternetBannerComponent())
}