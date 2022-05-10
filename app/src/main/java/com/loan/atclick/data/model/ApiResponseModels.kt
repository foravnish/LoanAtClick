package com.app.grofiesta.data.model

import java.io.Serializable

object ApiResponseModels {

    data class LoginResponse(
        val success: Boolean,
        val message: String,
        val user_id: String,
        val name: String,
        val email: String,
        val phone: String
    ) : Serializable

    data class RegistrationResponse(
    var enc_otp: String,
    var message: String,
    var success: Boolean
)
    data class Profile(
        val success: Boolean,
        val message: String,
        val user_data: Data
    ) : Serializable {
        data class Data(
            var id: String,
            var name: String,
            var email: String,
            var lastname: String,
            var address: String,
            var image: String,
            var phone: String

        )

    }

    data class ApplyLoanResponse(
        var loan_id: Int,
        var message: String,
        var success: Boolean
    )


    data class AllFieldResponse(
        var message: String,
        var success: Boolean,
        var variable_fields: VariableFields
    ) {
        data class VariableFields(
            var contitution_of_company: List<String>,
            var customer_category: List<String>,
            var education_qualification: List<String>,
            var employment_type: List<String>,
            var gender: Gender,
            var loan_type: List<String>,
            var marital_status: List<String>,
            var property_status: List<String>,
            var purpose_of_loan: List<String>,
            var states: List<State>
        ) {
            data class Gender(
                var f: String,
                var m: String,
                var o: String
            )

            data class State(
                var state_description: String,
                var state_id: String,
                var state_title: String,
                var status: String
            )
        }
    }

    data class LoanType(
        var loan_type: List<String>,
        var message: String,
        var success: Boolean
    )


    data class MyReferalResponse(
        var message: String,
        var refer_data: List<ReferData>,
        var success: Boolean
    ) {
        data class ReferData(
            var amount: String,
            var city: String,
            var create_date: String,
            var id: String,
            var loan_type: String,
            var name: String,
            var ph: String,
            var ref_code: String,
            var refer_by: String
        )
    }


    data class MyAppStatusResponse(
        var loan_data: List<LoanData>,
        var message: String,
        var success: Boolean
    ) {
        data class LoanData(
            var application_no: String,
            var apply_date: String,
            var id: String,
            var loan_amount: String,
            var status: String
        )
    }

    data class LoanTrackResponse(
        var message: String,
        var success: Boolean,
        var track_data: List<TrackData>
    ) {
        data class TrackData(
            var application_no: String,
            var bank_application_no: String,
            var bank_name: String,
            var create_date: String,
            var customer_care_no: String,
            var loan_amount: String,
            var remarks: String,
            var status: String
        )
    }


    data class LoanDetailResponse(
        var loan_status_data: LoanStatusData,
        var message: String,
        var success: Boolean
    ) {
        data class LoanStatusData(
            var bank_name: String,
            var customer_name: String,
            var debit_ac_no: String,
            var disbursal_date: String,
            var email: String,
            var emi_date: String,
            var loan_ac_no: String,
            var loan_amount: String,
            var location: String,
            var mobile_no: String,
            var processing_fee: String,
            var product: String,
            var roi: String,
            var tenure: String
        )
    }

}