package com.example.bupazar.page.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.bupazar.R
import com.example.bupazar.model.AddToCartRequest
import com.example.bupazar.service.RestApiService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_product.*


private const val ARG_PRODUCT_ID = "productId"
private const val ARG_AUTH_TOKEN = "authToken"

class ProductFragment : Fragment() {

    private var authToken: String? = null
    private var productId: Long = 0
    private var addedToCart: Boolean = false
    private var quantityAdded: Int = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            authToken = it.getString(ARG_AUTH_TOKEN)
            authToken = "Token " + authToken
            productId = it.getLong(ARG_PRODUCT_ID)
        }

        val apiService = RestApiService()
        apiService.productDetails(productId){
            if(it?.name == null){

            }
            else {
                product_name_text.text = it.name
                product_vendor_text.text = "Vendor: " + it.vendor
                product_brand_text.text = it.brand
                price_text.text = "$" + "%.2f".format(it.price)
                product_description_text.text = it.description
                Picasso.with(context)
                    .load(it.imageUrl)
                    .into(productImageView);
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val apiService = RestApiService()
        addtocart.setOnClickListener {
            val productData = AddToCartRequest(
                productId = this.productId,
                count = quantityAdded
            )
            authToken?.let { it1 ->
                apiService.addToCart(it1, productData) {
                    this.context?.let { it1 -> ContextCompat.getColor(it1, R.color.secondary_blue) }?.let { it2 ->
                        addtocart.setBackgroundColor(
                            it2
                        )
                    }
                    addtocart_text.setText("ADDED TO CART")
                    addedToCart = true
                }
            }
        }
        increase_quantity_button.setOnClickListener {
            quantityAdded += 1
            quantity_text.setText(quantityAdded.toString())
        }
        decrease_quantity_button.setOnClickListener {
            if (quantityAdded > 1) {
                quantityAdded -= 1
                quantity_text.setText(quantityAdded.toString())
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(authToken: String?, productId: Long) =
            ProductFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_AUTH_TOKEN, authToken)
                    putLong(ARG_PRODUCT_ID, productId)
                }
            }
    }
}