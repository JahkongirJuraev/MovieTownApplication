package com.example.movieapp.ui.main.home.myAccountFragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.core.model.response.main.account.accountDetails.AccountDetailsResponse
import com.example.movieapp.databinding.FragmentMyAccountBinding
import com.example.movieapp.ui.base.BaseFragment
import com.example.movieapp.ui.login.LoggingActivity
import com.google.android.material.snackbar.Snackbar

class MyAccountFragment : BaseFragment(), MyAccountMVP.View {

    private lateinit var binding: FragmentMyAccountBinding
    private lateinit var presenter: MyAccountMVP.Presenter
    private lateinit var data: AccountDetailsResponse

    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyAccountBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreated(savedInstanceState: Bundle?) {
        presenter = MyAccountPresenter(this)
        presenter.loadMyAccountData()
        binding.watchlist.setOnClickListener {
            findNavController().navigate(R.id.favoriteFragmentt)
        }

        binding.info.setOnClickListener {
            findNavController().navigate(R.id.infoFragment)
        }

        binding.logOut.setOnClickListener {
            presenter.deleteSession()
            binding.logOut.isClickable = false
            binding.logOut.isFocusable=false
            Toast.makeText(context,"Please wait, logging out",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onViewDestroyed() {
        presenter.cancelRequest()
    }

    override fun setMyAccountData(myAccountDetailsResponse: AccountDetailsResponse) {
        data = myAccountDetailsResponse
        showData()
    }

    fun showData() {

        if (data.avatar.tmdb.avatar_path != null) {
            Glide.with(binding.root)
                .load("https://image.tmdb.org/t/p/w200" + data.avatar.tmdb.avatar_path)
                .into(binding.profilePicture)
        } else {
            Glide.with(binding.root)
                .load("https://secure.gravatar.com/avatar/" + data.avatar.tmdb.avatar_path + ".jpg?s=200")
                .into(binding.profilePicture)
        }


        binding.username.text = "Username: " + data.username
    }

    override fun onError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun isSessionDeleted(sessionDeleted: Boolean) {

        binding.logOut.isFocusable = true
        binding.logOut.isClickable=true

            if (sessionDeleted) {
                var intent = Intent(requireActivity(), LoggingActivity::class.java)
                startActivity(intent)
            } else {
                Snackbar.make(binding.root, "Could not log out", Snackbar.LENGTH_SHORT)
            }
    }


}
