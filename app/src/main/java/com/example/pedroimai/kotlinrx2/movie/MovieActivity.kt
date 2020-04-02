package com.example.pedroimai.kotlinrx2.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pedroimai.kotlinrx2.R
import com.example.pedroimai.kotlinrx2.movie.detail.MovieDetailFragment
import com.example.pedroimai.kotlinrx2.movie.listing.MovieListFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MovieActivity : AppCompatActivity() {
  //  @Inject lateinit var fragmentDispatchingAndroidInjector: AndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.movie_container, MovieDetailFragment())
                    .add(R.id.movie_list_container, MovieListFragment())
                    .commit()

        }
    }

   // override fun androidInjector(): AndroidInjector<Any> = fragmentDispatchingAndroidInjector

}
