package com.example.pedroimai.kotlinrx2.data

/**
 * Created by Pedro Imai on 29/05/2017.
 */
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MovieRestServiceModule {

    @Singleton @Provides fun provideRestServiceModule():MovieRestServiceApi = MovieRestServiceImpl()

}