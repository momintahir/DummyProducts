package com.example.dummyproducts.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dummyproducts.R
import com.example.dummyproducts.adapters.ProductsAdapter
import kotlinx.android.synthetic.main.fragment_product_list.view.*


class ProductListFragment : Fragment() {

    private lateinit var productsAdapter: ProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_product_list, container, false)
        setupRecyclerView(view)

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