package com.yjy.redmine.issue

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.yjy.redmine.R
import com.yjy.redmine.databinding.ActivityMainBinding

class IssueActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    private val mFragment: Fragment
        get() = IssuesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        var fragment = supportFragmentManager.findFragmentById(R.id.container)

        if (fragment == null) {
            fragment = mFragment
            supportFragmentManager.beginTransaction()
                .add(R.id.container, fragment)
                .commit()
        }

    }
}
