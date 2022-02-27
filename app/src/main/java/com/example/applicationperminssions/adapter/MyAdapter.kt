package com.example.applicationperminssions.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationperminssions.R
import com.example.applicationperminssions.databinding.ItemRomBinding
import com.example.applicationperminssions.model.NoteModelItem

class MyAdapter(private var list: List<NoteModelItem>) : RecyclerView.Adapter<MyAdapter.MYViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MYViewHolder = MYViewHolder.create(parent)

    override fun onBindViewHolder(holder: MYViewHolder, position: Int) = holder.bindData(position, list)

    override fun getItemCount(): Int = list.size

    class MYViewHolder(private val binding: ItemRomBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(position: Int, list: List<NoteModelItem>) {
            binding.executePendingBindings()
            binding.n = (list[position])
        }

        companion object {
            fun create(parent: ViewGroup): MYViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rom, parent, false)
                return MYViewHolder(binding = ItemRomBinding.bind(view))
            }
        }
    }
}