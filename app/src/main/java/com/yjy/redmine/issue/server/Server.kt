package com.yjy.redmine.issue.server

import retrofit2.Call
import retrofit2.http.GET

interface Server {
    @GET("issues.json")
    fun getIssues(): Call<IssueResponse>
}

