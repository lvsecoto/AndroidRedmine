package com.yjy.redmine.issue

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.text.format.DateUtils
import com.yjy.redmine.Retrofits
import com.yjy.redmine.issue.data.Issue
import com.yjy.redmine.issue.server.IssueResponse
import com.yjy.redmine.issue.server.Server
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class ViewModel(application: Application) : AndroidViewModel(application) {

    val issues = MutableLiveData<List<Issue>>()
    private val server: Server = Retrofits.INSTANCE.retrofit.create(Server::class.java)

    fun fetch() {
        server.getIssues().enqueue(object : Callback<IssueResponse> {
            override fun onFailure(call: Call<IssueResponse>?, t: Throwable?) {

            }

            override fun onResponse(
                call: Call<IssueResponse>?,
                response: Response<IssueResponse>?
            ) {
                issues.postValue(response?.body()?.issues)
            }

        })
    }

    fun getDistance(issue: Issue): CharSequence {
        return DateUtils.getRelativeTimeSpanString(
            issue.updatedOn.time,
            System.currentTimeMillis(),
            DateUtils.FORMAT_ABBREV_TIME.toLong()
        )
    }

    private val dataFormat = SimpleDateFormat("yy-mm-dd", Locale.getDefault())

    fun getDate(issue: Issue): CharSequence {
        return dataFormat.format(issue.updatedOn)
    }
}