package com.example.umesh_yadav_25327

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.umesh_yadav_25327.databinding.ActivityMovieBinding

class MovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieBinding

    private var itemPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieBinding.inflate(layoutInflater)

        setContentView(binding.root)

        itemPosition = intent.extras?.getInt("item_position") as Int

        Glide.with(this)
            .load(MainActivity.movieList[itemPosition].image)
            .into(binding.movieImg)

        binding.movieTitle.text = MainActivity.movieList[itemPosition].name
        binding.starringList.text = MainActivity.movieList[itemPosition].starring.toString()
        binding.movieRunningTime.text = "${MainActivity.movieList[itemPosition].runnning_time_mins / 60}hrs ${MainActivity.movieList[itemPosition].runnning_time_mins % 60}mins"
        binding.seatsRemaining.text = "${MainActivity.movieList[itemPosition].seats_remaining} seats remaining"
        binding.seatsSelected.text = MainActivity.movieList[itemPosition].seats_selected.toString()
        binding.description.text = MainActivity.movieList[itemPosition].description

        when (MainActivity.movieList[itemPosition].certification) {
            "PG" -> binding.rating.setImageResource(R.drawable.pg)
            "16" -> binding.rating.setImageResource(R.drawable._6)
            "12A" -> binding.rating.setImageResource(R.drawable._2a)
        }

        setButtonStates()
        initClickListeners()
    }

    private fun setButtonStates() {
        binding.addSeats.isEnabled = MainActivity.movieList[itemPosition].seats_remaining != 0
        binding.subSeats.isEnabled = MainActivity.movieList[itemPosition].seats_selected != 0
    }

    private fun initClickListeners() {
        binding.addSeats.setOnClickListener {
            if (MainActivity.movieList[itemPosition].seats_remaining > 0) {
                binding.seatsSelected.text = (++MainActivity.movieList[itemPosition].seats_selected).toString()
                binding.seatsRemaining.text = "${--MainActivity.movieList[itemPosition].seats_remaining} seats remaining"

                setButtonStates()
            }
        }

        binding.subSeats.setOnClickListener {
            if (MainActivity.movieList[itemPosition].seats_selected > 0) {
                binding.seatsSelected.text = (--MainActivity.movieList[itemPosition].seats_selected).toString()
                binding.seatsRemaining.text = "${++MainActivity.movieList[itemPosition].seats_remaining} seats remaining"

                setButtonStates()
            }
        }
    }
}