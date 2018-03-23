package com.example.pedroimai.kotlinrx2.movie.listing

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pedroimai.kotlinrx2.R
import com.example.pedroimai.kotlinrx2.movie.Movie
import com.example.pedroimai.kotlinrx2.movie.MovieListingContract
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.movie_list_fragment.*
import javax.inject.Inject

class MovieListFragment : Fragment(),MovieListingContract.View {
    override fun showMovieDetail(movie: Movie) {
        //nao tem utilidade, está sendo mantido pq o MoviePresenter utiliza
    }

    @Inject lateinit var presenter: MovieListingContract.Presenter
    private lateinit var listAdapter: MovieAdapter

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.movie_list_fragment, container, false)

    override fun onStart() {
        super.onStart()
        initMovieList()
        presenter.bind()
    }

    override fun showMovies(movies: List<Movie>) {
        listAdapter.addAll(movies)
    }

    private fun initMovieList() {
        listAdapter = MovieAdapter {
            presenter.openMovieDetail(it)
        }
        movie_list.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = listAdapter
        }
    }
    override fun onDetach() {
        presenter.unbind()
        super.onDetach()
    }




}