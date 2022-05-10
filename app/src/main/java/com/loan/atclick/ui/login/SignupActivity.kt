package com.loan.atclick.login


import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.loan.atclick.R
import com.loan.atclick.base.BaseActivity
import com.loan.atclick.databinding.ActivitySignUpBinding

class SignupActivity : BaseActivity() {

    lateinit var binding: ActivitySignUpBinding
    val mContext by lazy { this@SignupActivity }
    val mViewModel by lazy { ViewModelProviders.of(mContext).get(LoginViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


//        binding.btnSignin.setOnClickListener {
//            Utility.startActivityWithLeftToRightAnimation(
//                this,
//                Intent(this, LoginActivity::class.java)
//            )
//        }
    }

//    private fun isValidation(): Boolean {
//        if (TextUtils.isEmpty(binding.etUsername.text.toString())) {
//            showSnackBar(
//                binding.root,
//                getString(R.string.err_user_name)
//            )
//            return false
//        } else if (TextUtils.isEmpty(binding.etPassword.text.toString())) {
//            showSnackBar(
//                binding.root,
//                getString(R.string.err_password)
//            )
//            return false
//        }
//        return true
//    }



}