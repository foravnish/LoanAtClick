package com.loan.atclick.ui.home

import android.os.Bundle
import com.loan.atclick.R
import com.loan.atclick.adapter.GalleryPagerAdapter
import com.loan.atclick.base.BaseActivity
import kotlinx.android.synthetic.main.activity_image_preview.*

class ImagePreviewActivity : BaseActivity() {
    var currPos = 0
    lateinit var mAdapter: GalleryPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_preview)

        imgBack.setOnClickListener { finish() }

        val mImageList = intent.extras!!.getStringArrayList("imageArray")
        if (intent.hasExtra("mCurrentPage"))
            currPos = intent.extras!!.getString("mCurrentPage")!!.toInt()


        mAdapter = GalleryPagerAdapter(this, mImageList)
        pagerView.setAdapter(mAdapter)
        mAdapter.count
        pagerView.setCurrentItem(currPos, false)
    }
}
