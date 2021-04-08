package com.fabiano.ioasys.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fabiano.ioasys.R
import com.fabiano.ioasys.data.remote.`interface`.ClickListener
import com.fabiano.ioasys.databinding.ListItemBinding
import com.fabiano.ioasys.model.EnterpriseData
import com.fabiano.ioasys.utils.Helpers

class EnterpriseListAdapter(
    private val clickListener: ClickListener,
    private val context: Context
) :
    ListAdapter<EnterpriseData, EnterpriseListAdapter.ViewHolder>(EnterpriseDataDiff()) {
    class ViewHolder(
        private val binding: ListItemBinding,
        private val clickListener: ClickListener,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: EnterpriseData, itemPosition: Int, context: Context) {
            Glide.with(context)
                .load(Helpers.createImageUrl(item.photo))
                .placeholder(R.drawable.ic_loading)
                .into(binding.enterpriseIcon)

            binding.enterpriseName.text = item.enterprise_name
            binding.enterpriseCountry.text = item.country
            binding.enterpriseField.text = item.enterprise_type.enterprise_type_name
            binding.cardViewLayout.setOnClickListener {
                clickListener.onClick(itemPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding = ListItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), position, context)
    }
}

class EnterpriseDataDiff : DiffUtil.ItemCallback<EnterpriseData>() {
    override fun areItemsTheSame(
        oldItem: EnterpriseData,
        newItem: EnterpriseData
    ): Boolean {
        return oldItem.enterprise_name == newItem.enterprise_name
    }

    override fun areContentsTheSame(
        oldItem: EnterpriseData,
        newItem: EnterpriseData
    ): Boolean = oldItem == newItem
}