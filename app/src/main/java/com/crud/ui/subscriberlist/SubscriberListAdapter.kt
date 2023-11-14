package com.crud.ui.subscriberlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.crud.R
import com.crud.data.db.entity.SubscriberEntity

class SubscriberListAdapter(
    private val subscriber: List<SubscriberEntity>
) : RecyclerView.Adapter<SubscriberListAdapter.SubscriberListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriberListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.subscriber_item, parent, false)

        return SubscriberListViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubscriberListViewHolder, position: Int) {
        holder.bindView(subscriber[position])
    }

    override fun getItemCount() = subscriber.size

    class SubscriberListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textName: TextView = itemView.findViewById(R.id.text_name)
        private val textBirth: TextView = itemView.findViewById(R.id.text_data)

        fun bindView(subscriber: SubscriberEntity) {
            textName.text = subscriber.name
            textBirth.text = subscriber.birth
        }

    }
}