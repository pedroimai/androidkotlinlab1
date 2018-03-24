package com.example.pedroimai.kotlinrx2.application.dagger

import android.content.Context
import com.example.pedroimai.kotlinrx2.application.MyApplication
import com.example.pedroimai.kotlinrx2.application.api.StarWarsApi
import com.example.pedroimai.kotlinrx2.movie.*
import com.example.pedroimai.kotlinrx2.movie.detail.MovieDetailFragment
import com.example.pedroimai.kotlinrx2.movie.detail.MovieDetailRepository
import com.example.pedroimai.kotlinrx2.movie.detail.MovieDetailRxPresenter
import com.example.pedroimai.kotlinrx2.movie.listing.MovieListFragment
import com.example.pedroimai.kotlinrx2.movie.listing.MoviePresenter
import com.example.pedroimai.kotlinrx2.movie.listing.MovieRepository
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


    @Provides @Singleton
    fun provideMovieBus(): MovieBus = MovieRxBus()

    @Provides @Singleton
    fun provideMovieCache(): MovieCache = InMemoryMovieCache()
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
    @ContributesAndroidInjector
    abstract fun movieActivity(): MovieActivity

    @ContributesAndroidInjector(modules = [MovieListingModule::class])
    abstract fun movieListingFragment(): MovieListFragment

    @ContributesAndroidInjector(modules = [MovieDetailModule::class])
    abstract fun movieDetailFragment(): MovieDetailFragment
}

@Module
class MovieListingModule {

    @Provides
    fun provideView(view: MovieListFragment): MovieListingContract.View = view

    @Provides
    fun providePresenter(view: MovieListingContract.View, source: MovieListingContract.Source): MovieListingContract.Presenter {
        //return MovieRxPresenter(view, source)
        return MoviePresenter(view, source)
    }

    @Provides
    fun provideRespository(api: StarWarsApi, bus: MovieBus, cache:MovieCache): MovieListingContract.Source {
        return MovieRepository(api, bus, cache)
    }

}

@Module
class MovieDetailModule {
    @Provides
    fun provideView(view: MovieDetailFragment): MovieDetailContract.View = view

    @Provides
    fun providePresenter(view: MovieDetailContract.View, source: MovieDetailContract.Source): MovieDetailContract.Presenter {
        return MovieDetailRxPresenter(view, source)
    }

    @Provides
    fun provideRespository(bus: MovieBus): MovieDetailContract.Source {
        return MovieDetailRepository(bus)
    }


}

