package com.example.pedroimai.kotlinrx2

/**
 * Created by Pedro Imai on 29/05/2017.
 */

import android.app.Application
import com.example.dagger.kotlin.ApplicationComponent
import com.example.dagger.kotlin.DaggerApplicationComponent

class MyApplication : Application() {
    val component: ApplicationComponent
        get() = DaggerApplicationComponent.builder()
                .androidModule(AndroidModule(this))
                .build()

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }

    fun getAppComponent(): ApplicationComponent {
        return component
    }

}