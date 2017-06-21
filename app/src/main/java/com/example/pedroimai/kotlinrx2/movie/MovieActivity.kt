package com.example.pedroimai.kotlinrx2.movie

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.pedroimai.kotlinrx2.MyApplication
import com.example.pedroimai.kotlinrx2.R
import com.example.pedroimai.kotlinrx2.data.Movie
import com.example.pedroimai.kotlinrx2.movie.dagger.DaggerMovieComponent
import com.example.pedroimai.kotlinrx2.movie.dagger.MovieComponent
import com.example.pedroimai.kotlinrx2.movie.dagger.MovieModule
import com.example.pedroimai.kotlinrx2.moviedetail.MovieDetailActivity
import kotlinx.android.synthetic.main.main_activity.*
import javax.inject.Inject
import org.jetbrains.anko.startActivity

class MovieActivity : AppCompatActivity(), MovieContract.View {
    @Inject
    lateinit var presenter: MovieContract.UserActions
    lateinit var listAdapter: MovieAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        component.inject(this)
        initMovieList()
        presenter.loadMovies()
    }

    override fun showMovie(movies: Movie) {
        listAdapter.add(movies)
        listAdapter.notifyDataSetChanged()
    }

    override fun showMovieDetail(movie: Movie) {
        startActivity<MovieDetailActivity>(MovieDetailActivity.MOVIE_TITLE to movie.title)
    }

    fun initMovieList() {
        with(movies_list) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(baseContext)
            listAdapter = MovieAdapter{presenter.openMovieDetail(it)}
            adapter = listAdapter



        }
    }

    //teste 2

    private val component: MovieComponent
        get() = DaggerMovieComponent.builder()
                .applicationComponent((application as MyApplication).component)
                .movieModule(MovieModule(this))
                .build()






}
