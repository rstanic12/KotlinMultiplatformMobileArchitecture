package com.kotlin.multiplatform.features.login.internet

import com.kotlin.multiplatform.core.presentation.BaseViewModel

data class InternetBannerViewModel(
    val isVisible: Boolean = false,
) : BaseViewModel