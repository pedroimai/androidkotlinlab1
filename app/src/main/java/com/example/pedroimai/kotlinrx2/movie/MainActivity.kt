package com.example.pedroimai.kotlinrx2.movie

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.pedroimai.kotlinrx2.MyApplication
import com.example.pedroimai.kotlinrx2.R
import com.example.pedroimai.kotlinrx2.data.Movie
import com.example.pedroimai.kotlinrx2.movie.dagger.DaggerMovieComponent
import com.example.pedroimai.kotlinrx2.movie.dagger.MovieModule
import kotlinx.android.synthetic.main.main_activity.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MovieContract.View {
    @Inject
    lateinit var presenter: MovieContract.UserActions
    lateinit var listAdapter: MovieAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        DaggerMovieComponent.builder()
                .applicationComponent((application as MyApplication).getAppComponent())
                .movieModule(MovieModule(this))
                .build().inject(this)
        initMovieList()
        presenter.loadMovies()
    }

    override fun showMovie(movies: Movie) {
        listAdapter.add(movies)
        listAdapter.notifyDataSetChanged()
    }

    fun initMovieList() {
        with(movies_list) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(baseContext)
            listAdapter = MovieAdapter()
            adapter = listAdapter
        }
    }
}
