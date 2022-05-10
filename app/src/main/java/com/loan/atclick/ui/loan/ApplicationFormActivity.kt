package com.loan.atclick.ui.loan

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.accountapp.accounts.utils.Prefences
import com.airbnb.lottie.utils.Utils
import com.loan.atclick.R
import com.loan.atclick.adapter.TypeAdapter
import com.loan.atclick.base.BaseActivity
import com.loan.atclick.data.model.request.ApplyLoan
import com.loan.atclick.login.LoanViewModel
import com.loan.atclick.utils.Utility
import kotlinx.android.synthetic.main.activity_app_form.*
import kotlinx.android.synthetic.main.activity_app_form.editName
import kotlinx.android.synthetic.main.activity_app_form.edtFatherName
import kotlinx.android.synthetic.main.activity_app_form.edtPhone
import kotlinx.android.synthetic.main.app_header_layout.*
import kotlinx.android.synthetic.main.dialog_type.*
import kotlinx.android.synthetic.main.lytform_eight.*
import kotlinx.android.synthetic.main.lytform_five.*
import kotlinx.android.synthetic.main.lytform_four.*
import kotlinx.android.synthetic.main.lytform_seven.*
import kotlinx.android.synthetic.main.lytform_six.*
import kotlinx.android.synthetic.main.lytform_three.*
import kotlinx.android.synthetic.main.lytform_two.*
import java.util.*
import kotlin.collections.ArrayList

class ApplicationFormActivity : BaseActivity() {

    lateinit var mViewModel: LoanViewModel
    var mCustomerCatList = ArrayList<String>()
    var mEducationList = ArrayList<String>()
    var mProperty1List = ArrayList<String>()
    var mConstitList = ArrayList<String>()
    var mStateList = ArrayList<String>()
    var mLoanTypeList = ArrayList<String>()
    var mPurposeList = ArrayList<String>()
    var mEMpTypeList = ArrayList<String>()
    var mGenderList = ArrayList<String>()
    var mmaterialStatusList = ArrayList<String>()

    var mCustCatValue = ""
    var mEducationValue = ""
    var mProperty1Value = ""
    var mProperty2Value = ""
    var mConstiValue = ""
    var mStateValue = ""
    var mStateValue2 = ""
    var mLoanTypeValue = ""
    var mPurposeValue = ""
    var mEmpTypeValue = ""
    var mGenderValue = ""
    var mMatStatusValue = ""

    lateinit var mDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_form)

        mViewModel = ViewModelProvider.AndroidViewModelFactory(application)
            .create(LoanViewModel::class.java)
        mViewModel.init(this)


        imgBack.setOnClickListener { finish() }
        txtPageTitle.text = "Application Form"


        editName.setText(""+Prefences.getName(this))
        edtPhone.setText(""+Prefences.getUserMobile(this))

        getApplicationField()

        edtClickCustCat.setOnClickListener {
            settPopupLoanType(mCustomerCatList, "1")
        }
        edtClickEdu.setOnClickListener {
            settPopupLoanType(mEducationList, "2")
        }
        edtClickProperty1.setOnClickListener {
            settPopupLoanType(mProperty1List, "3")
        }
        edtClickProperty2.setOnClickListener {
            settPopupLoanType(mProperty1List, "4")
        }
        edtClickConsti.setOnClickListener {
            settPopupLoanType(mConstitList, "5")
        }
        edtClickState.setOnClickListener {
            settPopupLoanType(mStateList, "6")
        }
        edtClickLoanType.setOnClickListener {
            settPopupLoanType(mLoanTypeList, "7")
        }
        edtClickLoanPurpose.setOnClickListener {
            settPopupLoanType(mPurposeList, "8")
        }
        edtEmpType.setOnClickListener {
            settPopupLoanType(mEMpTypeList, "9")
        }
        edtState.setOnClickListener {
            settPopupLoanType(mStateList, "10")
        }
        edtGender.setOnClickListener {
            settPopupLoanType(mGenderList, "11")
        }
        edtMeterialStatus.setOnClickListener {
            settPopupLoanType(mmaterialStatusList, "12")
        }


        edtDOB.setOnClickListener {
            showDateCalender("1")
        }
        edtSupposeDOB.setOnClickListener {
            showDateCalender("2")
        }

    }

    private fun showDateCalender(mType: String) {

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                c.set(Calendar.YEAR, year)
                c.set(Calendar.MONTH, monthOfYear)
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                if (mType == "1")
                    edtDOB.setText(Utility.date_in_yyyy_MM_dd(c.time))
                else if (mType == "2")
                    edtSupposeDOB.setText(Utility.date_in_yyyy_MM_dd(c.time))


            }, year, month, day
        )
        dpd.datePicker.maxDate = c.timeInMillis
        dpd.show()
    }

    private fun settPopupLoanType(mList: java.util.ArrayList<String>, mType: String) {


        mDialog = Utility.MyCustomDialog(this@ApplicationFormActivity, R.layout.dialog_type)
        mDialog.txtSelect.text = "Select"

        mDialog.imgClose.setOnClickListener { mDialog.dismiss() }
        mDialog.rvTypeList.layoutManager = LinearLayoutManager(this)
        val mAdapter = TypeAdapter(mList) {
            mDialog.dismiss()
            if (mType == "1") {
                mCustCatValue = mList[it]
                edtClickCustCat.setText("" + mCustCatValue)
            } else if (mType == "2") {
                mEducationValue = mList[it]
                edtClickEdu.setText("" + mEducationValue)
            } else if (mType == "3") {
                mProperty1Value = mList[it]
                edtClickProperty1.setText("" + mProperty1Value)
            } else if (mType == "4") {
                mProperty2Value = mList[it]
                edtClickProperty2.setText("" + mProperty2Value)
            } else if (mType == "5") {
                mConstiValue = mList[it]
                edtClickConsti.setText("" + mConstiValue)
            } else if (mType == "6") {
                mStateValue = mList[it]
                edtClickState.setText("" + mStateValue)
            } else if (mType == "7") {
                mLoanTypeValue = mList[it]
                edtClickLoanType.setText("" + mLoanTypeValue)
            } else if (mType == "8") {
                mPurposeValue = mList[it]
                edtClickLoanPurpose.setText("" + mPurposeValue)
            } else if (mType == "9") {
                mEmpTypeValue = mList[it]
                edtEmpType.setText("" + mEmpTypeValue)
            } else if (mType == "10") {
                mStateValue2 = mList[it]
                edtState.setText("" + mStateValue2)
            } else if (mType == "11") {
                mGenderValue = mList[it]
                edtGender.setText("" + mGenderValue)
            } else if (mType == "12") {
                mMatStatusValue = mList[it]
                edtMeterialStatus.setText("" + mMatStatusValue)
            }

        }
        mDialog.rvTypeList.adapter = mAdapter


        mDialog.show()


    }

    private fun getApplicationField() {

        mViewModel.initAllFields(true)!!.observe(this, Observer {
            if (it.success) {

                it.variable_fields.apply {
                    customer_category.forEach {
                        mCustomerCatList.add(it)
                    }
                    education_qualification.forEach {
                        mEducationList.add(it)
                    }
                    property_status.forEach {
                        mProperty1List.add(it)
                    }
                    contitution_of_company.forEach {
                        mConstitList.add(it)
                    }
                    states.forEach {
                        mStateList.add(it.state_title)
                    }
                    loan_type.forEach {
                        mLoanTypeList.add(it)
                    }
                    purpose_of_loan.forEach {
                        mPurposeList.add(it)
                    }
                    employment_type.forEach {
                        mEMpTypeList.add(it)
                    }
                    marital_status.forEach {
                        mmaterialStatusList.add(it)
                    }
                    var mStr = ArrayList<String>()
                    mStr.add(gender.m)
                    mStr.add(gender.f)
                    mStr.add(gender.o)
                    mStr.forEach {
                        mGenderList.add(it)
                    }


                }


            } else
                showToast(it.message)

        })

    }

    fun submit(view: View) {

        if (chekDescl.isChecked){
            callApplyLoan()
        }else if(editLoanAmt.text.toString().isEmpty())
            showAlert("Enter loan Amount.")
        else
            showAlert("Please check to Disclaimer")
    }

    private fun callApplyLoan() {
        ApplyLoan(""+mGenderValue,""+edtDOB.text.toString(),""+edtReligion.text.toString(),
        ""+edtFatherName.text.toString(),""+edtMotherName.text.toString(),
            ""+edtSupposeDOB.text.toString(),""+edtSupposeDOB.text.toString(),
            ""+mCustCatValue,""+mMatStatusValue,
            ""+mEducationValue,""+edtPanCard.text.toString(),
            ""+edtAadharCard.text.toString(),""+edtBankName.text.toString(),
            ""+edtBankNumber.text.toString(),""+editResiAddress.text.toString(),
            ""+mProperty1Value,""+mStateValue,
            ""+edtCity.text.toString(),""+edtPinCode.text.toString(),
            ""+edtLandmark.text.toString(),""+mEmpTypeValue,
            ""+mConstiValue,"",
            ""+edtWorkExp.text.toString(),""+edtEmpName.text.toString(),
            ""+edtDesiganation.text.toString(),""+edtWorkEmailId.text.toString(),
            ""+edtPersonalEmailId.text.toString(),""+edtCurrWorkExp.text.toString(),
            ""+editOffcAddress.text.toString(),""+mStateValue2,
            ""+edtCity2.text.toString(),""+edtPincode2.text.toString(),
            "","",
            "","",
            "","",
            "","",
            ""+edtPreCOmNAme.text.toString(),""+edtPreWorkExp.text.toString(),
            ""+editRef1Name.text.toString(),""+editRef1Add.text.toString(),
            ""+editRef1Phone.text.toString(),""+editRef1Relation.text.toString(),
            ""+editRef2Name.text.toString(),""+editRef2Address.text.toString(),
            ""+editRef2Phone.text.toString(),""+editRef2Relation.text.toString(),
            "1",""+editLoanAmt.text.toString(),
            ""+mPurposeValue,""+mLoanTypeValue,
            ""+editSourcedBy.text.toString()).let {

            mViewModel.initApplyLoan(it,true)!!.observe(this, Observer {
                if (it.success) {
                    showToast(""+it.message)
                    Intent(this@ApplicationFormActivity, MyAppStatusActivity::class.java).apply {
                    }.let {
                        Utility.startActivityWithLeftToRightAnimation(this,it)
                    }
                } else
                    showToast(it.message)

            })

        }

    }

}