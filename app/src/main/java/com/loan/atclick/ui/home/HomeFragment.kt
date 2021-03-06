package com.accountapp.accounts.ui.home


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.accountapp.accounts.base.BaseFragment
import com.loan.atclick.R
import com.loan.atclick.adapter.DashboardBannerAdapter
import com.loan.atclick.databinding.FragmentHomeBinding
import com.loan.atclick.login.LoanViewModel
import com.loan.atclick.ui.home.ImagePreviewActivity
import com.loan.atclick.ui.loan.ApplicationFormActivity
import com.loan.atclick.utils.Utility
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment() {
    lateinit var mViewModel: LoanViewModel


    companion object {
        val TAG = "FragmentHome"

        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()
            return fragment
        }
    }

    lateinit var binding: FragmentHomeBinding
    val mContext by lazy { context }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        mViewModel = ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
            .create(LoanViewModel::class.java)
        mViewModel.init(requireActivity())

        getDashboardApi()

        binding.btnonClickApply.setOnClickListener {
            Intent(requireActivity(), ApplicationFormActivity::class.java).apply {
            }.let {
                Utility.startActivityWithLeftToRightAnimation(requireActivity(),it)
            }
        }


        return binding.root;

    }

    private fun getDashboardApi() {

        mViewModel.initGetDashboard(
            true
        )!!.observe(this, Observer {
            if (it.success) {
                initAdapter(it.images)
            }
        })

    }

    private fun initAdapter(mList: List<String>) {
        rvBaners.layoutManager = GridLayoutManager(requireActivity(),1)
        val mAdapter = DashboardBannerAdapter(mList) {
            val mListImg = ArrayList<String>()
            for (i in mList) {
                mListImg.add(i)
            }
            Intent(requireActivity(), ImagePreviewActivity::class.java).apply {
                putStringArrayListExtra("imageArray", mListImg)
                startActivity(this)
            }
        }
        rvBaners.adapter = mAdapter
    }


}
