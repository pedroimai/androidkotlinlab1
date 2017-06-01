package com.example.pedroimai.kotlinrx2.movie

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pedroimai.kotlinrx2.R
import com.example.pedroimai.kotlinrx2.data.Movie
import kotlinx.android.synthetic.main.movie_list_item.view.*

/**
 * Created by Pedro Imai on 29/05/2017.
 */
class MovieAdapter(val itemClick: (Movie) -> Unit) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    var mItems: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        return ViewHolder(layoutInflater.inflate(R.layout.movie_list_item, parent, false),itemClick)
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindMovie(mItems[position])
    }

    class ViewHolder(view: View, val itemClick: (Movie) -> Unit) : RecyclerView.ViewHolder(view) {

        fun bindMovie(movie: Movie) {
            with(movie) {
                itemView.title.text = movie.title
                itemView.genre.setText(movie.genre)
                itemView.year.setText(movie.year.toString())
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }

    fun add(item: Movie) {
        mItems.add(item)
    }
}