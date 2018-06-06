package com.yjy.redmine.issue

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yjy.redmine.R
import com.yjy.redmine.common.SimpleAdapter
import com.yjy.redmine.databinding.FragmentIssuesBinding
import com.yjy.redmine.databinding.ViewItemIssuesListBinding
import com.yjy.redmine.issue.data.Issue

class IssuesFragment : Fragment() {

    private lateinit var mBinding: FragmentIssuesBinding

    private lateinit var mViewModel: ViewModel

    private val issueAdapter = object : SimpleAdapter<Issue, ViewItemIssuesListBinding>() {
        override fun getLayoutId(position: Int): Int = R.layout.view_item_issues_list

        override fun onBindData(binding: ViewItemIssuesListBinding?, item: Issue?) {
            binding?.issue = item
            binding?.viewModel = mViewModel
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_issues, container, false
        )
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(ViewModel::class.java)
        initToolBar()
        initView()
        subscribe()

        mViewModel.fetch()
    }

    private fun initToolBar() {
        mBinding.toolbar.apply {
            val activity = activity
            if (activity is AppCompatActivity) {
                activity.setSupportActionBar(this)
            }
            title = getString(R.string.app_name)
        }
    }

    private fun initView() {
        mBinding.issuesList.adapter = issueAdapter
        mBinding.issuesList.addItemDecoration(ItemDecorate())
    }

    private fun subscribe() {
        mViewModel.issues.observe(this, Observer<List<Issue>> {
            issueAdapter.replace(it)
        })
    }
}
