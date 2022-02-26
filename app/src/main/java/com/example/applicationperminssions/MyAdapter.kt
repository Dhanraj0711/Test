package com.example.applicationperminssions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationperminssions.databinding.ItemRomBinding
import com.example.applicationperminssions.model.NoteModelItem

class MyAdapter(private var list: List<NoteModelItem>) :
    RecyclerView.Adapter<MyAdapter.MYViewHolder>() {
    private lateinit var binding: ItemRomBinding
    var ItemClickListener: ((position: Int, name: String) -> Unit)? = null

    inner class MYViewHolder : RecyclerView.ViewHolder(binding.root) {

        private val diiff = object : DiffUtil.ItemCallback<NoteModelItem>() {
            override fun areItemsTheSame(oldItem: NoteModelItem, newItem: NoteModelItem): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: NoteModelItem, newItem: NoteModelItem): Boolean {
                return oldItem == newItem
            }
        }

        val differ = AsyncListDiffer(this@MyAdapter, diiff)

        fun bindItems(position: Int) {
            with(binding) {
                textView.text = list[position].title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MYViewHolder {
        binding = ItemRomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MYViewHolder()
    }

    override fun onBindViewHolder(holder: MYViewHolder, position: Int) {
        holder.bindItems(position)

    }


    override fun getItemCount(): Int = list.size

    fun updateList(newGalaxies: List<NoteModelItem>) {

        val diffCallback = SleepNightDiffCallback(list, newGalaxies)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)

        list = newGalaxies
    }
}

class SleepNightDiffCallback(var list: List<NoteModelItem>, var newGalaxies: List<NoteModelItem>) : DiffUtil.Callback() {


    override fun getOldListSize(): Int = list.size


    override fun getNewListSize(): Int = newGalaxies.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return list[oldItemPosition] == newGalaxies[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return list[oldItemPosition] == newGalaxies[newItemPosition]
    }
}