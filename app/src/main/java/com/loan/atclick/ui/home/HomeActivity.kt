package com.loan.atclick.home

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.accountapp.accounts.ui.home.HomeFragment
import com.accountapp.accounts.utils.Prefences
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.loan.atclick.R
import com.loan.atclick.base.BaseActivity
import com.loan.atclick.databinding.ActivityHomeBinding
import com.loan.atclick.login.LoginActivity
import com.loan.atclick.ui.WebViewActivity
import com.loan.atclick.ui.loan.*
import com.loan.atclick.ui.login.ChangePinActivity
import com.loan.atclick.ui.profile.MyAccountActivity
import com.loan.atclick.utils.Utility
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.app_main_header_layout.*
import kotlinx.android.synthetic.main.nav_header_main.view.*
import kotlinx.android.synthetic.main.navigation_footer_layout.*


class HomeActivity : BaseActivity() {


    val mContext by lazy { this@HomeActivity }
    var mTag="home"

    var fragment: Fragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initBottomNav()

        setActionBarViewUtils()

        if (savedInstanceState == null) {
            mTag="home"
//            txtDashboardTitle.text = getString(R.string.app_name)
            fragment = HomeFragment()
            loadFragment(fragment)
            navigation.currentItem = 0
        }


    }

    private fun setActionBarViewUtils() {
        imgMenuNav.setOnClickListener { drawer_layout!!.openDrawer(nav_view!!) }
//        txtTopAppVersion.text = "v" + Utils.getAppVersion()
//        txtAppVersion.text = "v" + Utils.getAppVersion()
    }

    override fun onResume() {
        super.onResume()
        setNavFooterLayoutUtilities()
    }
    private fun setNavFooterLayoutUtilities() {
        navHome.setOnClickListener(View.OnClickListener {
            closeDrawer()
        })

        navProfile.setOnClickListener(View.OnClickListener {
            closeDrawer()
            Intent(this@HomeActivity, MyAccountActivity::class.java).apply {
            }.let {
                Utility.startActivityWithLeftToRightAnimation(this,it)
            }

        })
        navAppForm.setOnClickListener(View.OnClickListener {
            closeDrawer()
            Intent(this@HomeActivity, ApplicationFormActivity::class.java).apply {
            }.let {
                Utility.startActivityWithLeftToRightAnimation(this,it)
            }
        })

        navStatus.setOnClickListener(View.OnClickListener {
            closeDrawer()
            Intent(this@HomeActivity, MyAppStatusActivity::class.java).apply {
            }.let {
                Utility.startActivityWithLeftToRightAnimation(this,it)
            }
        })

        navEnq.setOnClickListener(View.OnClickListener {
            closeDrawer()
            Intent(this@HomeActivity, LoanEnqActivity::class.java).apply {
            }.let {
                Utility.startActivityWithLeftToRightAnimation(this,it)
            }
        })
        navFeedback.setOnClickListener(View.OnClickListener {
            closeDrawer()
            Intent(this@HomeActivity, ComplaintFeedbackActivity::class.java).apply {
            }.let {
                Utility.startActivityWithLeftToRightAnimation(this,it)
            }
        })

        navRefer.setOnClickListener(View.OnClickListener {
            closeDrawer()
            Intent(this@HomeActivity, ReferEarnActivity::class.java).apply {
            }.let {
                Utility.startActivityWithLeftToRightAnimation(this,it)
            }
        })

        namChangePin.setOnClickListener(View.OnClickListener {
            closeDrawer()
            Intent(this@HomeActivity, ChangePinActivity::class.java).apply {
            }.let {
                Utility.startActivityWithLeftToRightAnimation(this,it)
            }
        })

        navContactUs.setOnClickListener(View.OnClickListener {
            closeDrawer()
            Intent(this@HomeActivity, WebViewActivity::class.java).apply {
                putExtra("webUrl","https://loanatclick.com/contact")
                putExtra("webTitle","Contact Us")
            }.let {
                Utility.startActivityWithLeftToRightAnimation(this,it)
            }
        })

        nanLogout.setOnClickListener(View.OnClickListener {
            closeDrawer()

            logoutDialog()

        })



//        txtAppVersion.text = "v" + Utils.getAppVersion()

        val headerView = nav_view!!.getHeaderView(0)
        val navUsername = headerView.findViewById<View>(R.id.txtNavUserName) as TextView
        val txtNavUserEmail = headerView.findViewById<View>(R.id.txtNavUserEmail) as TextView
        val imgNavProfilePic = headerView.findViewById<View>(R.id.imgNavProfilePic) as CircleImageView
        var urlimage=Prefences.getUserImage(this@HomeActivity)

        Glide.with(this@HomeActivity).load(urlimage).into(imgNavProfilePic)
        navUsername.text=""+Prefences.getName(mContext)
        txtNavUserEmail.text=""+Prefences.getUserEmailId(mContext)

        headerView.view_container.setOnClickListener(View.OnClickListener {
            closeDrawer()
            Intent(this@HomeActivity, MyAccountActivity::class.java).apply {
            }.let {
                Utility.startActivityWithLeftToRightAnimation(this,it)
            }
        })
    }


    private fun logoutDialog() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Alert")
        alertDialog.setMessage("Are you sure want to Logout?")
        alertDialog.setPositiveButton("Yes") { dialog, which ->
            dialog.dismiss()
            try {

                Prefences.resetUserData(this)
                Intent(this@HomeActivity, LoginActivity::class.java).apply {
                }.let {
                    Utility.startActivityWithLeftToRightAnimation(this,it)
                }
                finishAffinity()

            } catch (e: Exception) {
            }
        }

        alertDialog.setNegativeButton("No") { dialog, which ->
            dialog.dismiss()
        }

        alertDialog.show()
    }

    private fun closeDrawer() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START))
            drawer_layout.closeDrawer(GravityCompat.START)
    }

    private fun initBottomNav() {
        navigation.enableAnimation(false)
        navigation.enableShiftingMode(false)
        navigation.enableItemShiftingMode(false)
        navigation.setIconSize(22f, 22f)
        navigation.setTextSize(11f)

        navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    if (mTag!="home") {
                        mTag="home"
//                        txtDashboardTitle.text = getString(R.string.app_name)
                        fragment = HomeFragment()
                        loadFragment(fragment)
                        return@setOnNavigationItemSelectedListener true
                    }
                }

                R.id.navigation_my_market -> {
                    mTag="call"
//                    startActivity(Intent(this@MainActivity, CallListingActivity::class.java), animBundleFIO())
                }
                R.id.navigation_profile -> {
                    mTag="report"
//                    txtDashboardTitle.text = "Reports"
                    fragment = HomeFragment()
                    loadFragment(fragment)
                    return@setOnNavigationItemSelectedListener true
                }


            }
            true
        }
    }

    fun loadFragment(fragment: Fragment?) {
        if (fragment != null) {
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.content_frame, fragment, "Home")
            ft.addToBackStack(null)
            ft.commit()
        }
    }

}