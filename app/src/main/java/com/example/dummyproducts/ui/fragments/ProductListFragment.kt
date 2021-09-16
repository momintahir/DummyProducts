package com.example.dummyproducts.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dummyproducts.R
import com.example.dummyproducts.adapters.ProductsAdapter
import com.example.dummyproducts.models.ProductsResponseItem
import com.example.dummyproducts.ui.MainActivity
import com.example.dummyproducts.ui.ProductsViewModel
import com.example.dummyproducts.util.Resource
import kotlinx.android.synthetic.main.fragment_product_list.view.*
import kotlinx.coroutines.flow.collect


class ProductListFragment : Fragment() {

    private lateinit var productsAdapter: ProductsAdapter
    lateinit var viewModel: ProductsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_product_list, container, false)
        viewModel = (activity as MainActivity).viewModel

        setupRecyclerView(view)
        viewModel.getAllProducts()

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.products.collect { response ->
                when (response) {
                    is Resource.Success -> {
                        response.data?.let { productsResponse ->
                            productsAdapter.differ.submitList(productsResponse)
                        }
                    }
                    is Resource.Error -> {
                        response.message?.let { message ->
                            Toast.makeText(
                                activity,
                                "An error occured: $message",
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }
                    }
                    is Resource.Loading -> {
                    }
                }

            }
        }

        productsAdapter.setOnItemClickListener(object : ProductsAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, product: ProductsResponseItem) {
                val bundle = Bundle()
                bundle.putParcelable("product", product)
                Navigation.findNavController(view)
                    .navigate(R.id.action_productListFragment_to_productDetailsFragment, bundle)

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