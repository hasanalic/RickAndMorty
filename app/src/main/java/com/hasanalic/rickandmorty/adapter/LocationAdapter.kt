package com.hasanalic.rickandmorty.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hasanalic.rickandmorty.R
import com.hasanalic.rickandmorty.databinding.RowLocationBinding
import com.hasanalic.rickandmorty.model.Location

class LocationAdapter(): RecyclerView.Adapter<LocationAdapter.MyViewHolder>() {

    private val diffUtil = object: DiffUtil.ItemCallback<Location>() {
        override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this,diffUtil)

    var locations: List<Location>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    private var locationPosition: Int = 1

    inner class MyViewHolder(private val binding: RowLocationBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Location, position: Int) {
            binding.textViewLocationName.text = item.name
            if(locationPosition == position + 1) {
                binding.linearLayoutLocation.setBackgroundResource(R.drawable.custom_location_selected_bg)
            } else {
                binding.linearLayoutLocation.setBackgroundResource(R.drawable.custom_location_bg)
            }
            binding.linearLayoutLocation.setOnClickListener {
                onItemClickListener?.let {
                    it(item.id)
                }
            }
        }
    }

    fun changeLocation(locId: Int) {
        this.locationPosition = locId
        notifyDataSetChanged()
    }

    private var onItemClickListener : ((Int) -> Unit) ? = null

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = RowLocationBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return locations.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val location = locations[position]
        holder.bind(location,position)
    }
}