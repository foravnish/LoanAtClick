package com.loan.atclick.ui.profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.accountapp.accounts.utils.Prefences
import com.bumptech.glide.Glide
import com.github.dhaval2404.imagepicker.ImagePicker
import com.loan.atclick.R
import com.loan.atclick.base.BaseActivity
import com.loan.atclick.login.LoginViewModel
import com.loan.atclick.utils.Utility
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_my_account.*
import kotlinx.android.synthetic.main.app_header_layout.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class EditProfileActivity : BaseActivity() {

    val mContext by lazy { this@EditProfileActivity }
    lateinit var mViewModel: LoginViewModel
    var FileImageSingle: MultipartBody.Part? = null
    var selectedImage1: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        mViewModel = ViewModelProvider.AndroidViewModelFactory(application)
            .create(LoginViewModel::class.java)
        mViewModel.init(this@EditProfileActivity)

        imgBack.setOnClickListener { finish() }
        txtPageTitle.text = "Edit Profile"

        editName.setText(""+Prefences.getName(mContext))
        editMobile.setText(""+Prefences.getUserMobile(mContext))

        Glide.with(this).load(Prefences.getUserImage(mContext)).error(R.drawable.ic_user_dummy).into(profileImage!!)

        imgCapture.setOnClickListener {
            callCamera()
        }
    }

    fun onClickUpdate(view: View){

        val name = RequestBody.create(
            "multipart/form-data".toMediaType(),
            "" + editName.text.toString().trim()
        )
        val phone = RequestBody.create(
            "multipart/form-data".toMediaType(),
            "" + editMobile.text.toString().trim()
        )

        if (selectedImage1!=null) {
            val file1 = File(Utility.getRealPathFromURI(selectedImage1!!, this))
            val requestFile1 = RequestBody.create("multipart/form-data".toMediaType(), file1)
            FileImageSingle =
                MultipartBody.Part!!.createFormData("image", file1.name!!, requestFile1!!)
        }else{

        }

        mViewModel.initUpdateProfile(name,
            phone, FileImageSingle,true)!!.observe(this, Observer {
            hideKeyboard()
            if (it.success) {

                setResult(Activity.RESULT_OK)
                finish()
                showToast("Profile updated successfully!")


            } else
                showToast(it.message)

        })

    }


    private fun callCamera() {
        ImagePicker.Companion.with(this)
//            .crop() //Crop image(Optional), Check Customization for more option
            .compress(512) //Final image size will be less than 1 MB(Optional)
            .maxResultSize(
                1080,
                1080
            ) //Final image resolution will be less than 1080 x 1080(Optional)
            .start()

    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            selectedImage1 = data!!.data
            profileImage.setImageURI(selectedImage1)
        }
    }


}
