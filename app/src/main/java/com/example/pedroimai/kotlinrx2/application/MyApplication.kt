package com.example.pedroimai.kotlinrx2

/**
 * Created by Pedro Imai on 29/05/2017.
 */

import android.app.Application
import com.example.dagger.kotlin.ApplicationComponent
import com.example.dagger.kotlin.DaggerApplicationComponent

class MyApplication: Application() {
    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        val component = DaggerApplicationComponent.builder().androidModule(AndroidModule(this)).build()
        component.inject(this)
        this.component = component
    }

    fun getAppComponent():ApplicationComponent {
        return component
    }

}