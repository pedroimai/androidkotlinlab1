package com.example.pedroimai.kotlinrx2.application.dagger

import com.example.pedroimai.kotlinrx2.application.MyApplication
import dagger.Component
import javax.inject.Singleton

@Singleton @Component( modules = [(CoreModule::class)])
interface CoreComponent {
    infix fun inject(application: MyApplication)
}
