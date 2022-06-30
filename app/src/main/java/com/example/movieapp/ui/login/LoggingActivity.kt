package com.example.movieapp.ui.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Toast
import com.example.movieapp.core.cache.AppCache
import com.example.movieapp.core.model.response.login.createRequestToken.CreateRequestTokenResponse
import com.example.movieapp.databinding.ActivityLoggingBinding
import com.example.movieapp.ui.base.BaseActivity
import com.example.movieapp.ui.start.StartActivity
import uz.fozilbekimomov.lesson44movieapp.core.models.response.login.acountDetail.AccountDetailResponse

class LoggingActivity : BaseActivity(), LoginMVP.View {

    private lateinit var binding: ActivityLoggingBinding

    private lateinit var presenter: LoginMVP.Presenter

    private lateinit var username: String
    private lateinit var password: String

    override fun getView(): View? {
        binding = ActivityLoggingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreated(savedInstanceState: Bundle?) {
        presenter = LoginPresenter(this)

        binding.passwordEditText.transformationMethod = PasswordTransformationMethod()

        binding.loginButton.setOnClickListener {
            binding.singUpBox.isClickable=false
            binding.loginButton.visibility = View.INVISIBLE
            binding.loginText.visibility = View.VISIBLE
            username = binding.usernameEditText.text.toString()
            password = binding.passwordEditText.text.toString()
            if (binding.usernameEditText.text.toString()
                    .isNotEmpty() && binding.passwordEditText.text.toString()
                    .isNotEmpty()
            ) {

                presenter.createRequestToken()
            } else {
                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT)
                    .show()
            }

            //presenter.createRequestToken()
        }

        binding.singUpBox.setOnClickListener {
            /*Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);*/
            val url = "https://www.themoviedb.org/signup"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }


    }

    override fun isLoading(isLoading: Boolean) {

    }

    override fun setData(any: Any) {
        TODO("Not yet implemented")
    }

    override fun createRequestToken(token: String) {
        /*binding.responseView.text=token*/
        presenter.createSessionWithLogin(username = username, password = password)
    }

    override fun createSession(sessionId: String) {
        /*binding.responseView.text=sessionId*/

        presenter.getAccountDetails()
    }

    override fun createSessionWithLogin(response: CreateRequestTokenResponse) {
        /*binding.responseView.text=response.toString()*/
        presenter.createSession()
    }

    override fun accountDetail(accountDetailResponse: AccountDetailResponse) {
        /* binding.responseView.text=accountDetailResponse.toString()*/
        intent = Intent(this, StartActivity::class.java)
        startActivity(intent)
        binding.singUpBox.isClickable=true
        binding.loginButton.visibility = View.VISIBLE
        binding.loginText.visibility = View.INVISIBLE
        finish()
        AppCache.appCache!!.setFirstOpen(false)
    }

    override fun onError(message: String) {
        binding.singUpBox.isClickable=true
        binding.loginButton.visibility=View.VISIBLE
        binding.loginText.visibility=View.INVISIBLE
        /* binding.errorView.text=message*/
    }
}