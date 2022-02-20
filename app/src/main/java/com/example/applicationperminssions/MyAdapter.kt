package com.example.applicationperminssions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationperminssions.databinding.ItemRomBinding
import com.example.applicationperminssions.model.NoteModel
import com.example.applicationperminssions.model.NoteModelItem

class MyAdapter(private var list: NoteModel) : RecyclerView.Adapter<MyAdapter.MYViewHolder>() {
    private lateinit var binding: ItemRomBinding

    inner class MYViewHolder : RecyclerView.ViewHolder(binding.root) {
        fun bindItems(noteModelItem: NoteModelItem) {
            with(binding) {
                textView.text = noteModelItem.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MYViewHolder {
        binding = ItemRomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MYViewHolder()
    }

    override fun onBindViewHolder(holder: MYViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    override fun getItemCount(): Int = list.size
}