package com.ferhatt.wordapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.ferhatt.wordapp.R
import com.ferhatt.wordapp.roomdb.Word
import javax.inject.Inject

class WordRecyclerAdapter @Inject constructor(val glide: RequestManager) : RecyclerView.Adapter<WordRecyclerAdapter.WordViewHolder>() {

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val diffUtil = object : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem == newItem
        }

    }

    private val recyclerListDiff = AsyncListDiffer(this,diffUtil)

    var words : List<Word>
        get() = recyclerListDiff.currentList
        set(value) = recyclerListDiff.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.word_row,parent,false)
        return WordViewHolder(view)
    }

    override fun getItemCount(): Int {

        return words.size

    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {

        val imageView = holder.itemView.findViewById<ImageView>(R.id.wordRowImageView)
        val nameText = holder.itemView.findViewById<TextView>(R.id.wordRowText)
        val artistNameText = holder.itemView.findViewById<TextView>(R.id.wordRowDescription)


        val word = words[position]
        holder.itemView.apply {
            nameText.text = "${word.wordName}"
            artistNameText.text = "${word.wordDescription}"
            glide.load(word.imageUrl).into(imageView)

        }

    }
}