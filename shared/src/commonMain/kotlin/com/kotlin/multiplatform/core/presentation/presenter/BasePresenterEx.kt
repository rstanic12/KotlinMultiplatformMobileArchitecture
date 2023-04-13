package com.kotlin.multiplatform.core.presentation.presenter

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun CoroutineScope.launchOnMain(block: suspend () -> Unit) {
    launch {
        withContext(Dispatchers.Main) {
            block()
        }
    }
}

fun CoroutineScope.launchOnBackground(block: suspend () -> Unit) {
    launch {
        withContext(Dispatchers.Default) {
            block()
        }
    }
}

suspend fun CoroutineScope.onMain(block: suspend () -> Unit) {
    withContext(Dispatchers.Main) {
        block()
    }
}

suspend fun CoroutineScope.onBackground(block: suspend () -> Unit) {
    withContext(Dispatchers.Default) {
        block()
    }
}