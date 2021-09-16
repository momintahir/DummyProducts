package com.example.dummyproducts.ui.fragments

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.dummyproducts.R
import com.example.dummyproducts.models.ProductsResponseItem
import com.example.dummyproducts.ui.MainActivity
import com.example.dummyproducts.ui.ProductsViewModel
import kotlinx.android.synthetic.main.fragment_product_details.view.*
import kotlinx.android.synthetic.main.item_product.view.*
import kotlinx.android.synthetic.main.item_product.view.ivProduct
import kotlinx.android.synthetic.main.item_product.view.tvName
import kotlinx.android.synthetic.main.item_product.view.tvPrice


class ProductDetailsFragment : Fragment() {

    lateinit var viewModel: ProductsViewModel
    lateinit var product: ProductsResponseItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_product_details, container, false)
        viewModel = (activity as MainActivity).viewModel
        val bundle = this.arguments
        if (bundle != null) {
            product = bundle.getParcelable<Parcelable>("product") as ProductsResponseItem
        }
        Glide.with(this).load(product.image).into(view.ivProduct)
        view.tvPrice.text= product.price.toString()
        view.tvName.text= product.title
        view.tvDescription.text= product.description

        view.btnAddToCart.setOnClickListener {
            viewModel.saveProduct(product)
        }

        return view
    }


}