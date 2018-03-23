package com.example.pedroimai.kotlinrx2.application.dagger

import android.content.Context
import com.example.pedroimai.kotlinrx2.application.MyApplication
import com.example.pedroimai.kotlinrx2.application.api.StarWarsApi
import com.example.pedroimai.kotlinrx2.movie.*
import com.example.pedroimai.kotlinrx2.shared.uiScheduler
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ActivitiesBuilder::class, AndroidInjectionModule::class, MovieApiModule::class])
class CoreModule(private val app: MyApplication) {
    @Provides @Singleton
    fun provideContext(): Context = app

    @Provides @Singleton
    fun provideApplication(): MyApplication = app
}

@Module
class MovieApiModule {
    @Provides
    @Singleton
    fun provideApi(): StarWarsApi {
        return Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                    .build()
            )
            .baseUrl("https://swapi.co/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(StarWarsApi::class.java)
    }
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
    fun providePresenter(view: MovieContract.View, source: MovieContract.Source ): MovieContract.Presenter {
        //return MovieRxPresenter(view, source)
        return MoviePresenter(view, source)
    }

    @Provides
    fun provideRespository(api: StarWarsApi): MovieContract.Source {
        return MovieRepository(api)
    }


}
