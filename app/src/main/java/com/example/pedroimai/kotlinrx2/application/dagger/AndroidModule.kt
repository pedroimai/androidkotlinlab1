package com.example.pedroimai.kotlinrx2

import android.content.Context
import com.example.pedroimai.kotlinrx2.data.MovieRestServiceApi
import com.example.pedroimai.kotlinrx2.data.MovieRestServiceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Pedro Imai on 29/05/2017.
 */
@Module class AndroidModule(private val context: Context) {

    @Provides @Singleton fun provideApplicationContext(): Context = context

}