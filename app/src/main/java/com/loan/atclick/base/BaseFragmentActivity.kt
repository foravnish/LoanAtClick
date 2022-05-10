package com.loan.atclick.base

import com.loan.atclick.callback.OnFragmentInteractionListener

abstract open class BaseFragmentActivity : BaseActivity() , OnFragmentInteractionListener {

    protected abstract fun getFragmentContainer(): Int

//    fun loadFragments(frag: BaseFragment, addToBackStack: Boolean, replace: Boolean, fragmentName: String) {
//        if (supportFragmentManager != null && !isFinishing) {
//            if (addToBackStack) {
//                if (replace) {
//                    supportFragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(getFragmentContainer(), frag, frag.getFragmentTag()).commit()
//                } else {
//                    supportFragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).add(getFragmentContainer(), frag, frag.getFragmentTag()).commit()
//                }
//
//            } else {
//                supportFragmentManager.beginTransaction().replace(getFragmentContainer(), frag, frag.getFragmentTag()).commitAllowingStateLoss()
//            }
//            supportFragmentManager.executePendingTransactions()
//        }
//    }
}
