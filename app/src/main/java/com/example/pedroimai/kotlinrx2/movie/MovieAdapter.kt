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
class MovieAdapter() : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    var mItems: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        return ViewHolder(layoutInflater.inflate(R.layout.movie_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mItems[position]
        holder.title.setText(item.title)
        holder.genre.setText(item.genre)
        holder.year.setText(item.year.toString())
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val container = view.container
        val title = view.title
        val genre = view.genre
        val year = view.year
    }

    fun add(item: Movie) {
        mItems.add(item)
    }
}