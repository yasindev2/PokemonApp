package uz.yasindev.pokemon.core.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.yasindev.pokemon.core.models.CardModel
import uz.yasindev.pokemon.core.models.MyItemTouchHelper
import uz.yasindev.pokemon.databinding.ItemRvBinding
import java.util.Collections

class RvAdapter : RecyclerView.Adapter<RvAdapter.ViewHolder>(), MyItemTouchHelper {

    private val data = ArrayList<CardModel>()

    var onItemClickListener: ((CardModel) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: ArrayList<CardModel>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }


    inner class ViewHolder(private val binding: ItemRvBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(model: CardModel) {
            binding.imageView.setImageResource(model.image)
            binding.name.text = model.name
        }

        fun itemClickListener(position: Int) {
            binding.root.setOnClickListener {
                onItemClickListener?.invoke(data[position])
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
        holder.itemClickListener(position)
    }

    // Drag in drop

    override fun itemMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition..<toPosition) {
                Collections.swap(data, i, i + 1)
            }
        } else {
            for (i in toPosition..fromPosition) {
                Collections.swap(data, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun itemDismiss(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }

}