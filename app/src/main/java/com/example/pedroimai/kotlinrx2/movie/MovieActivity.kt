package com.example.pedroimai.kotlinrx2.movie

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.pedroimai.kotlinrx2.R
import com.example.pedroimai.kotlinrx2.moviedetail.MovieDetailActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.main_activity.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import javax.inject.Inject

class MovieActivity : AppCompatActivity(), MovieContract.View {
    @Inject lateinit var presenter: MovieContract.Presenter
    private lateinit var listAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        initMovieList()
        presenter.bind()
    }

    override fun showMovies(movies: List<Movie>) {
        listAdapter.addAll(movies)
    }

    override fun showMovieDetail(movie: Movie) {
        toast("Abrir detalhes de ${movie.title}")
        //startActivity<MovieDetailActivity>(MovieDetailActivity.MOVIE_TITLE to movie.title)
    }

    private fun initMovieList() {
            listAdapter = MovieAdapter{presenter.openMovieDetail(it)}
            movies_list.run {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(baseContext)
                adapter = listAdapter
            }
    }

    override fun onDestroy() {
        presenter.unbind()
        super.onDestroy()
    }
}
