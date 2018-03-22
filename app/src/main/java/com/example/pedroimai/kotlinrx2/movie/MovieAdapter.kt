package com.example.pedroimai.kotlinrx2.movie

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pedroimai.kotlinrx2.R
import kotlinx.android.synthetic.main.movie_list_item.view.*

class MovieAdapter(private val itemClick: (Movie) -> Unit) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private var viewModels = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.movie_list_item, parent, false),itemClick)
    }

    override fun getItemCount(): Int {
        return viewModels.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindMovie(viewModels[position])
    }

    class ViewHolder(view: View, val itemClick: (Movie) -> Unit) : RecyclerView.ViewHolder(view) {

        fun bindMovie(movie: Movie) {
            with(itemView) {
                title.text = movie.title
                episode_number.text = movie.episodeNumber.toString()
                release_date.text = movie.releaseDate
                setOnClickListener { itemClick(movie) }
            }
        }
    }

    fun addAll(movies:List<Movie>) {
        viewModels.addAll(movies)
        notifyDataSetChanged()
    }
}