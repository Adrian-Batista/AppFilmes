package br.com.recyclerviewapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.recyclerviewapp.databinding.ItemBinding

class ItemAdapter(
    private val itemList: List<Item>,
    private val listener: OnItemClickListener
    ) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(private val itemBinding: ItemBinding) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener{
        init {
            itemView.setOnClickListener(this)
        }

        fun bind(item: Item){
            itemBinding.imageView.setImageResource(item.imageResource)
            itemBinding.textView1.text = item.text1
            itemBinding.textView2.text = item.text2
            itemBinding.textView3.text = item.text3.toString()

        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemBinding = ItemBinding.inflate(
            LayoutInflater.from(parent.context),parent, false)
        return ItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount() = itemList.size


}

