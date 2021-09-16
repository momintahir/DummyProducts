package com.example.dummyproducts.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dummyproducts.R
import com.example.dummyproducts.models.ProductsResponseItem
import kotlinx.android.synthetic.main.item_product.view.*

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {
    private lateinit var onItemClickListener: OnItemClickListener


    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallBack = object : DiffUtil.ItemCallback<ProductsResponseItem>() {
        override fun areItemsTheSame(
            oldItem: ProductsResponseItem,
            newItem: ProductsResponseItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ProductsResponseItem,
            newItem: ProductsResponseItem
        ): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            (LayoutInflater.from(parent.context).inflate(
                R.layout.item_product, parent, false
            ))
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(product.image).into(ivProduct)
            tvPrice.text = product.price.toString()
            tvName.text = product.title
            tvCategory.text = product.category
        }

        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(position, product)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, article: ProductsResponseItem)
    }

}