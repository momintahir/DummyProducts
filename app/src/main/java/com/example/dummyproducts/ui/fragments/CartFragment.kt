package com.example.dummyproducts.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dummyproducts.R
import com.example.dummyproducts.adapters.ProductsAdapter
import com.example.dummyproducts.models.ProductsResponseItem
import com.example.dummyproducts.ui.MainActivity
import com.example.dummyproducts.ui.ProductsViewModel
import kotlinx.android.synthetic.main.fragment_cart.view.*
import kotlinx.android.synthetic.main.fragment_product_list.view.*
import kotlinx.android.synthetic.main.fragment_product_list.view.recyclerView
import kotlinx.coroutines.flow.observeOn


class CartFragment : Fragment() {
    lateinit var viewModel: ProductsViewModel
    lateinit var productsAdapter: ProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_cart, container, false)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView(view)

        viewModel.getProductsFromCart().observe(viewLifecycleOwner, Observer { products ->
            if (products.isEmpty()) {
                view.cart.visibility = View.VISIBLE
                view.tvNoItemsCart.visibility = View.VISIBLE
            } else {
                view.cart.visibility = View.GONE
                view.tvNoItemsCart.visibility = View.GONE
                productsAdapter.differ.submitList(products)

            }
        })

        productsAdapter.setOnItemClickListener(object : ProductsAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, product: ProductsResponseItem) {

            }
        })


        return view
    }


    private fun setupRecyclerView(view: View) {
        productsAdapter = ProductsAdapter()
        view.recyclerView.apply {
            adapter = productsAdapter
            layoutManager = GridLayoutManager(activity, 2)
        }
    }
}