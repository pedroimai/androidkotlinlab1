package com.example.dagger.kotlin

import android.app.Application
import com.example.pedroimai.kotlinrx2.AndroidModule
import com.example.pedroimai.kotlinrx2.data.MovieRestServiceApi
import com.example.pedroimai.kotlinrx2.data.MovieRestServiceModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = arrayOf(AndroidModule::class,MovieRestServiceModule::class)
)
interface ApplicationComponent {
    fun inject(application: Application)
    fun getRestService():MovieRestServiceApi
}