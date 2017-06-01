package com.example.pedroimai.kotlinrx2.movie.dagger

import com.example.dagger.kotlin.ApplicationComponent
import com.example.pedroimai.kotlinrx2.ScreenScope
import com.example.pedroimai.kotlinrx2.movie.MovieActivity
import dagger.Component

/**
 * Created by Pedro Imai on 31/05/2017.
 */
@ScreenScope
@Component(dependencies = arrayOf(ApplicationComponent::class),modules = arrayOf(MovieModule::class))
interface MovieComponent {

    fun inject(target: MovieActivity)
}
