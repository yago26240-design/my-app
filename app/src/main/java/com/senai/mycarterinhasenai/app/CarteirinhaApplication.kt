package com.senai.mycarterinhasenai.app

import android.app.Application
import com.senai.mycarterinhasenai.app.di.AppContainer
import com.senai.mycarterinhasenai.app.di.DefaultAppContainer
import kotlin.getValue

class CarteirinhaApplication : Application() {
    val container: AppContainer by lazy {
        DefaultAppContainer()
    }
}