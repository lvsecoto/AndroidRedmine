package com.yjy.redmine.issue

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class ItemDecorate : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect?,
        view: View?,
        parent: RecyclerView?,
        state: RecyclerView.State?
    ) {
        val padding = (parent?.context?.resources?.displayMetrics?.density!! * 8).toInt()

        outRect?.apply {
            left = padding
            top = padding
            right = padding
            bottom = padding
        }

    }
}