package com.example.pedroimai.kotlinrx2.moviedetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.movie_detail_activity.*;

import com.example.pedroimai.kotlinrx2.R

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        val MOVIE_TITLE = "MovieDetailActivity:movietitle"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_detail_activity)
        movie_title.text = intent.getStringExtra(MOVIE_TITLE)
    }


}
