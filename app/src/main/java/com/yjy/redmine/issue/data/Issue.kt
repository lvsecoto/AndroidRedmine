package com.yjy.redmine.issue.data

import com.yjy.redmine.user.data.User
import java.util.*

data class Issue(
    var id: Long,
    var subject: String,
    var author: User,
    var assignedTo: User,
    var updatedOn : Date,
    var status: Status,
    var priority: Priority,
    var project: Project
)
