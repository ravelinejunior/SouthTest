package br.com.raveline.myapplication.presentation.adapter

import android.annotation.SuppressLint
import android.util.Patterns
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.raveline.myapplication.data.model.EventItemModel
import br.com.raveline.myapplication.databinding.ItemMainAdapterBinding
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

class EventsAdapter : RecyclerView.Adapter<EventsAdapter.MyViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<EventItemModel>() {
        override fun areItemsTheSame(oldItem: EventItemModel, newItem: EventItemModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: EventItemModel, newItem: EventItemModel): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)

    private var onItemClickListener: ((EventItemModel) -> Unit)? = null

    fun setOnItemClickListener(listener: (EventItemModel) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemMainAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val event = differ.currentList[position]
        holder.bind(event)
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class MyViewHolder(
        private val adapterBinding: ItemMainAdapterBinding
    ) : RecyclerView.ViewHolder(adapterBinding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(eventItem: EventItemModel) {
            adapterBinding.apply {
                tvTitleMainAdapter.text = eventItem.title
                if (verifyImageSource(eventItem.image!!)) {
                    Glide.with(ivMainAdapter.context).load(eventItem.image).into(ivMainAdapter)
                } else {
                    Glide.with(ivMainAdapter.context).load(noImageUrl).into(ivMainAdapter)
                }

                root.setOnClickListener {
                    onItemClickListener?.let { item ->
                        item(eventItem)
                    }
                }

            }
        }
    }


    fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return format.format(date)
    }

    fun verifyImageSource(url: String): Boolean {

        val urlRegex = Patterns.WEB_URL.matcher(url)
        return urlRegex.matches()
    }

    companion object {
        const val noImageUrl =
            "https://media.istockphoto.com/photos/no-image-available-picture-id531302789"
    }


}