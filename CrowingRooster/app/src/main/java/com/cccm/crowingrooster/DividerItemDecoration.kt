package com.cccm.crowingrooster

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView

class DividerItemDecoration(context: Context, drawableId: Int): RecyclerView.ItemDecoration() {

    var divider: Drawable? = ContextCompat.getDrawable(context, drawableId)

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        var left = parent.paddingLeft
        var right = parent.width - parent.paddingRight
        var childCount = parent.childCount

        for(x in 0 until childCount) run {
            var child: View = parent.getChildAt(x)

            var params: RecyclerView.LayoutParams = child.layoutParams as RecyclerView.LayoutParams

            var top = child.bottom + params.bottomMargin
            var bottom = top + (divider?.intrinsicHeight ?: 16)

            divider?.setBounds(left,top,right,bottom)
            divider?.draw(c)
        }
    }
}