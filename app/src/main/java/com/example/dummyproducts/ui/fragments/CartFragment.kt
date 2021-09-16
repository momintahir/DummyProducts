package com.example.dummyproducts.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dummyproducts.R
import com.example.dummyproducts.adapters.ProductsAdapter
import com.example.dummyproducts.ui.MainActivity
import com.example.dummyproducts.ui.ProductsViewModel
import kotlinx.android.synthetic.main.fragment_product_list.view.*
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
            productsAdapter.differ.submitList(products)
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