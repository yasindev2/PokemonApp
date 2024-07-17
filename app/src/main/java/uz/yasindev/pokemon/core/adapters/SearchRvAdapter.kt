package uz.yasindev.pokemon.core.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.yasindev.pokemon.core.models.CardModel
import uz.yasindev.pokemon.databinding.ItemAutoTvBinding

class SearchRvAdapter : RecyclerView.Adapter<SearchRvAdapter.MyVh>() {

    private val data = ArrayList<CardModel>()
    var onItemClickListener: ((cardmodel: CardModel) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: ArrayList<CardModel>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class MyVh(private val binding: ItemAutoTvBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(cardModel: CardModel) {
            binding.name.text = cardModel.name
            binding.imageViewAutoTv.setImageResource(cardModel.image)

            binding.root.setOnClickListener {
                onItemClickListener?.invoke(cardModel)
                Log.d("TAGmm", "bindData: CLIKCED")
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVh {
        return MyVh(
            ItemAutoTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyVh, position: Int) {
        holder.bindData(data[position])
    }
}