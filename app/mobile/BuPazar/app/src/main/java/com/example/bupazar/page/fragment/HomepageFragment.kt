package com.example.bupazar.page.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bupazar.R
import com.example.bupazar.model.ProductAdapter
import com.example.bupazar.model.ProductDetails
import com.example.bupazar.service.RestApiService
import kotlinx.android.synthetic.main.fragment_homepage.*

class HomepageFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_homepage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val apiService = RestApiService()
        apiService.allProducts {
            if (it == null) {

            }
            else {
                val products: Array<ProductDetails> = it
                rvProducts.adapter = this.context?.let { ProductAdapter(it, products) }
                rvProducts.layoutManager = GridLayoutManager(this.context, 2)
            }

        }
    }

    companion object {
        @JvmStatic fun newInstance(param1: String, param2: String) =
                HomepageFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}