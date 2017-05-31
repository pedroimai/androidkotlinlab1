package com.example.pedroimai.kotlinrx2.movie.dagger

import com.example.pedroimai.kotlinrx2.ScreenScope
import com.example.pedroimai.kotlinrx2.data.MovieRestServiceApi
import com.example.pedroimai.kotlinrx2.data.MovieRestServiceImpl
import com.example.pedroimai.kotlinrx2.data.MovieRestServiceModule
import com.example.pedroimai.kotlinrx2.movie.MovieContract
import com.example.pedroimai.kotlinrx2.movie.MoviePresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Pedro Imai on 30/05/2017.
 */

@Module
class MovieModule(var view:MovieContract.View) {

    @Provides @ScreenScope
    internal fun provideView(): MovieContract.View {
        return view
    }

//    @Provides fun provideRestService():MovieRestServiceApi{
//        return MovieRestServiceImpl()
//    }

    @Provides @ScreenScope fun providePresenter(restService: MovieRestServiceApi, view: MovieContract.View): MovieContract.UserActions {
        return MoviePresenter(restService,view)
    }


}
