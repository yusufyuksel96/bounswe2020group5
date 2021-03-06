/*
* Created by Sertay Akpinar
* Fragment class to let the user review his/her personal info.
*/
package com.example.bupazar.page.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.bupazar.R
import com.example.bupazar.User
import com.example.bupazar.model.LoginResponse
import kotlinx.android.synthetic.main.fragment_profile_page.*


/**
 * A simple [Fragment] subclass.
 * Use the [ProfilePageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfilePageFragment : Fragment() {
    var userData : LoginResponse? = null
    lateinit var NameTextView: TextView
    lateinit var surNameTextView: TextView
    lateinit var userNameTextView: TextView
    lateinit var mailTextView: TextView
    lateinit var addressTextView: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NameTextView = view.findViewById(R.id.name)
        NameTextView.text = User.firstName
        surNameTextView = view.findViewById(R.id.surname)
        surNameTextView.text = User.lastName
        userNameTextView = view.findViewById(R.id.username)
        userNameTextView.text = User.userName
        mailTextView = view.findViewById(R.id.email)
        mailTextView.text = User.userEmail
        addressTextView = view.findViewById(R.id.address)
        addressTextView.text = User.address

        buttonEdit.setOnClickListener(){
            val editProfileInfoFragment = EditProfileInfoFragment()
            val bundle = Bundle()
            editProfileInfoFragment.arguments = bundle
            if(User.isVendor) {
                requireActivity().supportFragmentManager.beginTransaction().apply {
                    replace(R.id.activity_vendor_1, editProfileInfoFragment)
                    commit()
                }
            }
            else {
                requireActivity().supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fl_wrapper, editProfileInfoFragment)
                    commit()
                }
            }
        }

        buttonChangePassword.setOnClickListener(){
            if(User.isVendor) {
                requireActivity().supportFragmentManager.beginTransaction().apply {
                    replace(R.id.activity_vendor_1, ChangePasswordFragment())
                    commit()
                }
            }
            else {
                requireActivity().supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fl_wrapper, ChangePasswordFragment())
                    commit()
                }
            }
        }
    }
}