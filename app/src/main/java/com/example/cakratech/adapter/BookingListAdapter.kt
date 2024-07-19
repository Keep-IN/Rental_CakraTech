package com.example.cakratech.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.cakratech.R
import com.example.cakratech.databinding.ItemBookingListBinding
import com.example.core.data.room.entity.BookingEntity

class BookingListAdapter: RecyclerView.Adapter<BookingListAdapter.ViewHolder>() {
    private val data: MutableList<BookingEntity> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingListAdapter.ViewHolder {
        return ViewHolder(
            ItemBookingListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
    override fun onBindViewHolder(holder: BookingListAdapter.ViewHolder, position: Int) {
        holder.setData(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun submitList(list: List<BookingEntity>){
        val initSize = itemCount
        data.clear()
        notifyItemRangeRemoved(0, initSize)
        data.addAll(list)
        notifyItemRangeInserted(0, data.size)
    }

    inner class ViewHolder(private val binding: ItemBookingListBinding): RecyclerView.ViewHolder(binding.root) {
        fun setData(item: BookingEntity){
            binding.tvBookingId.text = item.codeBook
            binding.tvCehicleName.text = item.vehicleName
            binding.tvTimeStart.text = item.dateStart
            binding.tvTimeEnd.text = item.dateEnd
            Glide.with(itemView.context)
                .load(item.vehicleImage)
                .placeholder(R.drawable.img_brio)
                .into(binding.ivVehicle)
        }
    }

}