package com.example.pedroimai.kotlinrx2.movie.detail

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pedroimai.kotlinrx2.R
import com.example.pedroimai.kotlinrx2.movie.Movie
import com.example.pedroimai.kotlinrx2.movie.MovieDetailContract
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.movie_detail_fragment.*
import javax.inject.Inject

class MovieDetailFragment : Fragment(), MovieDetailContract.View {
    @Inject lateinit var presenter: MovieDetailContract.Presenter

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.movie_detail_fragment, container, false)


    override fun onStart() {
        presenter.bind()
        super.onStart()
    }

    override fun showMovie(movie: Movie) {
        movie_title.text = movie.title
    }

    override fun onDetach() {
        presenter.unbind()
        super.onDetach()
    }

}
