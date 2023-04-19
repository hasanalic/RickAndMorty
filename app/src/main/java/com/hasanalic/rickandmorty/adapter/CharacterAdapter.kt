package com.hasanalic.rickandmorty.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hasanalic.rickandmorty.R
import com.hasanalic.rickandmorty.databinding.RowCharacterBinding
import com.hasanalic.rickandmorty.model.Character
import com.hasanalic.rickandmorty.util.downloadFromUrl
import com.hasanalic.rickandmorty.util.placeHolderProgressBar

class CharacterAdapter: RecyclerView.Adapter<CharacterAdapter.MyViewHolder>() {

    private val diffUtil = object: DiffUtil.ItemCallback<com.hasanalic.rickandmorty.model.Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this,diffUtil)

    var characters: List<com.hasanalic.rickandmorty.model.Character>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    inner class MyViewHolder(val binding: RowCharacterBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(character: com.hasanalic.rickandmorty.model.Character, pos: Int) {
            binding.textViewCharacterName.text = character.name
            binding.imageViewRowCharacter.downloadFromUrl(character.image,
                placeHolderProgressBar(binding.root.context)
            )
            when(character.gender) {
                "Male" -> {
                    binding.imageViewGender.setImageResource(R.drawable.ic_male)
                }
                "Female" -> {
                    binding.imageViewGender.setImageResource(R.drawable.ic_female)
                }
                "Genderless" -> {
                    binding.imageViewGender.setImageDrawable(AppCompatResources.getDrawable(binding.root.context,R.drawable.genderless))
                }
                "Unknown" -> {
                    binding.imageViewGender.setImageDrawable(AppCompatResources.getDrawable(binding.root.context,R.drawable.unknown))
                }
            }
            binding.linearLayoutCharacterBar.setOnClickListener {
                onItemClickListener?.let {
                    it(character.id)
                }
            }
        }
    }

    private var onItemClickListener: ((Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = RowCharacterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val character = characters[position]
        holder.bind(character,position)
    }
}