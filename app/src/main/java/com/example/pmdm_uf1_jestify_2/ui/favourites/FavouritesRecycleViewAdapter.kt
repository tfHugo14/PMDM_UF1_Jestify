package com.example.pmdm_uf1_jestify_2.ui.favourites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pmdm_uf1_jestify_2.R
import com.example.pmdm_uf1_jestify_2.jokeAPI.Joke

class FavouritesRecycleViewAdapter(
    private val jokes: MutableList<Joke>,
    private val onDelete: (Joke) -> Unit
) : RecyclerView.Adapter<FavouritesRecycleViewAdapter.JokeViewHolder>() {

    class JokeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textCategory: TextView = view.findViewById(R.id.textCategory)
        val textJoke: TextView = view.findViewById(R.id.textJoke)
        val textSetup: TextView = view.findViewById(R.id.textSetup)
        val textDelivery: TextView = view.findViewById(R.id.textDelivery)
        val textFlags: TextView = view.findViewById(R.id.textFlags)
        val deleteButton: ImageButton = itemView.findViewById(R.id.deleteJokeButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_joke_card, parent, false)
        return JokeViewHolder(view)
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        val joke = jokes[position]
        holder.textCategory.text = "Nº - "+joke.id+"\nCategory: "+joke.category
        holder.textJoke.text = joke.joke
        holder.textSetup.text = joke.setUp
        holder.textDelivery.text = joke.delivery
        holder.textFlags.text = "Flags: "+joke.flags?.filterValues { it }?.keys?.joinToString(", ")

        holder.deleteButton.setOnClickListener {
            onDelete(joke)
        }
    }

    override fun getItemCount() = jokes.size

    fun removeJoke(joke: Joke) {
        val position = jokes.indexOf(joke)
        if (position != -1) {
            jokes.removeAt(position)
            notifyItemRemoved(position)
        }
    }
}