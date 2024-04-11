package com.example.umesh_yadav_25327

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(
    private val movieList: MutableList<Movie>,
    private val clickListener: MovieClickListener
): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    interface MovieClickListener {
        fun onMovieClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(holder.moviePreview.context)
            .load(movieList[position].image)
            .into(holder.moviePreview)


        holder.movieTitle.text = movieList[position].name
        holder.starringList.text = movieList[position].starring.toString()
        holder.runningTime.text = "${movieList[position].runnning_time_mins / 60}hrs ${movieList[position].runnning_time_mins % 60}mins"

        if (movieList[position].seats_selected > 0) {
            holder.seatsRemaining.text = "${movieList[position].seats_selected} seats selected"
            holder.seatsRemaining.setTextColor(Color.GREEN)
            holder.seatIcon.imageTintList = null
            holder.seatIcon.imageTintList = ColorStateList.valueOf(Color.GREEN)
        }
        else {
            holder.seatsRemaining.text = "${movieList[position].seats_remaining} seats remaining"
            holder.seatsRemaining.setTextColor(Color.WHITE)
            holder.seatIcon.imageTintList = null
            holder.seatIcon.imageTintList = ColorStateList.valueOf(Color.WHITE)
        }

        when (movieList[position].certification) {
            "PG" -> holder.rating.setImageResource(R.drawable.pg)
            "16" -> holder.rating.setImageResource(R.drawable._6)
            "12A" -> holder.rating.setImageResource(R.drawable._2a)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val moviePreview: ImageView
        val movieTitle: TextView
        val starringList: TextView
        val runningTime: TextView
        val seatsRemaining: TextView
        val seatIcon: ImageView
        val rating: ImageView

        init {
            moviePreview = itemView.findViewById(R.id.movie_img)
            movieTitle = itemView.findViewById(R.id.movie_title)
            starringList = itemView.findViewById(R.id.starring_list)
            runningTime = itemView.findViewById(R.id.movie_running_time)
            seatsRemaining = itemView.findViewById(R.id.seats_remaining)
            seatIcon = itemView.findViewById(R.id.seat_icon)
            rating = itemView.findViewById(R.id.rating)

            itemView.setOnClickListener {
                clickListener.onMovieClick(adapterPosition)
            }
        }
    }
}