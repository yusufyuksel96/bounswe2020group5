/*
* Created by Yasar Selcuk Caliskan
* Fragment class to let the user choose one of the existing cards or add a new one.
*/
package com.example.bupazar.page.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bupazar.R
import com.example.bupazar.model.*
import com.example.bupazar.service.RestApiService
import kotlinx.android.synthetic.main.fragment_pay_with_another_card.*

class PayWithAnotherCardFragment : Fragment() {

    private var userData: LoginResponse? = null
    private var authToken: String? = null
    private var creditCards: Array<CreditCard>? = null
    private var price: Float? = null
    private var chosenCreditCard: CreditCard? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userData = arguments?.getSerializable("USERDATA") as LoginResponse
            price = arguments?.getSerializable("price") as Float
        }
        authToken = userData?.authToken
        authToken = "Token " + authToken

        /*
        * Fetch the credit cards the user had previously added.
         */
        val apiService = RestApiService()
        apiService.getCreditCards(authToken!!){
            creditCards = it
            if (creditCards != null && creditCards!!.size > 0) {
                chosenCreditCard = creditCards?.get(creditCards!!.size - 1)
                val creditCardAdapter = this.context?.let { it1 -> creditCards?.let { it2 ->
                    CreditCardAdapter(it1, it2) }
                }
                /*
                Set the adapter to show credit cards in a recyclerview.
                 */
                credit_cards.adapter = creditCardAdapter
                credit_cards.layoutManager = LinearLayoutManager(this.context)
                /*
                * Set the on click listener for the recyclerview so that if a user chooses a card, it will be chosen as the new credit card.
                * User will be redirected back to the order page, where he/she can complete order with the chosen credit card.
                 */
                creditCardAdapter!!.onItemClick = { creditCard ->
                    chosenCreditCard = creditCard
                    val orderFragment = OrderFragment()
                    val bundle = Bundle()
                    bundle.putSerializable("USERDATA", userData)
                    bundle.putSerializable("price", price)
                    bundle.putSerializable("chosenCreditCard", chosenCreditCard)
                    orderFragment.arguments = bundle
                    requireActivity().supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fl_wrapper, orderFragment)
                        commit()
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pay_with_another_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiService = RestApiService()

        /*
        * Add new card on click listener implementation.
        * User needs to have had entered the required information to be able to add a new card.
         */
        add_new_card_button.setOnClickListener {
            val newCreditCard = AddCreditCardRequest(name = card_name_text.text.toString(), cardOwner = card_owner_text.text.toString(),
                cardNumber = card_number_text.text.toString(), expirationDate = expiration_text.text.toString(), cvc = cvc_text.text.toString())

            /*
            * Make the API request to add that new card.
            * User will be redirected back to the order page, where he/she can complete order with the newly added credit card.
             */
            apiService.addCreditCard(authToken!!, newCreditCard) {
                apiService.getCreditCards(authToken!!) {
                    chosenCreditCard = it?.get(it.size - 1) ?: CreditCard(-1, card_name_text.text.toString(), customer = -1, cardOwner = card_owner_text.text.toString(),
                        cardNumber = card_number_text.text.toString(), expirationDate = expiration_text.text.toString(), cvc = cvc_text.text.toString())

                    val orderFragment = OrderFragment()
                    val bundle = Bundle()
                    bundle.putSerializable("USERDATA", userData)
                    bundle.putSerializable("price", price)
                    bundle.putSerializable("chosenCreditCard", chosenCreditCard)
                    orderFragment.arguments = bundle

                    requireActivity().supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fl_wrapper, orderFragment)
                        commit()
                    }
                }
            }
        }

        /*
        * Same method used in onCreate above.
         */
        apiService.getCreditCards(authToken!!){
            val creditCards = it
            val creditCardAdapter = this.context?.let { it1 -> creditCards?.let { it2 ->
                CreditCardAdapter(it1,
                    it2
                )
            } }
            credit_cards.adapter = creditCardAdapter
            credit_cards.layoutManager = LinearLayoutManager(this.context)
            creditCardAdapter!!.onItemClick = { creditCard ->
                chosenCreditCard = creditCard
                val orderFragment = OrderFragment()
                val bundle = Bundle()
                bundle.putSerializable("USERDATA", userData)
                bundle.putSerializable("price", price)
                bundle.putSerializable("chosenCreditCard", chosenCreditCard)
                orderFragment.arguments = bundle

                requireActivity().supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fl_wrapper, orderFragment)
                    commit()
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PayWithAnotherCardFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}