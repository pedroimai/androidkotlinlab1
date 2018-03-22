package com.example.pedroimai.kotlinrx2.application.dagger

import android.content.Context
import com.example.pedroimai.kotlinrx2.application.MyApplication
import com.example.pedroimai.kotlinrx2.data.MovieApi
import com.example.pedroimai.kotlinrx2.data.MovieRestServiceModule
import com.example.pedroimai.kotlinrx2.movie.MovieActivity
import com.example.pedroimai.kotlinrx2.movie.MovieContract
import com.example.pedroimai.kotlinrx2.movie.MoviePresenter
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module(includes = [ActivitiesBuilder::class, AndroidInjectionModule::class, MovieRestServiceModule::class])
class CoreModule(private val app: MyApplication) {
    @Provides @Singleton
    fun provideContext(): Context = app

    @Provides @Singleton
    fun provideApplication(): MyApplication = app
}

@Module
abstract class ActivitiesBuilder {
    @ContributesAndroidInjector(modules = [MovieModule::class])
    abstract fun movieActivity(): MovieActivity
}


@Module
class MovieModule {
    @Provides
    fun provideView(view: MovieActivity): MovieContract.View = view

    @Provides
    fun providePresenter(restService: MovieApi, view: MovieContract.View): MovieContract.Presenter {
        return MoviePresenter(restService,view)
    }


}
