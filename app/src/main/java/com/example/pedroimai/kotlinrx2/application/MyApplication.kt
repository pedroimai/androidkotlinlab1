package com.example.pedroimai.kotlinrx2.application

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.pedroimai.kotlinrx2.application.dagger.CoreComponent
import com.example.pedroimai.kotlinrx2.application.dagger.CoreModule
import com.example.pedroimai.kotlinrx2.application.dagger.DaggerCoreComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MyApplication : Application(), HasAndroidInjector {
    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    companion object {
        private lateinit var INSTANCE: MyApplication
        fun get(): MyApplication = INSTANCE
    }

    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Any>
    lateinit var coreComponent: CoreComponent


    override fun androidInjector(): AndroidInjector<Any>? = activityInjector

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        initDependencyInjection()
    }

    private fun initDependencyInjection() {
        coreComponent = DaggerCoreComponent.builder().coreModule(CoreModule(this)).build()
        coreComponent inject this
    }
}