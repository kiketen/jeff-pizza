package com.jeff.pizza.base

import android.content.res.Resources
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

class RecyclerViewMatcher(private val recyclerViewId: Int) {

    companion object {
        const val LAST_POSITION = -1
    }

    fun atPosition(position: Int): Matcher<View> {
        return atPositionOnView(position, -1)
    }

    fun atPositionOnView(position: Int, targetViewId: Int): Matcher<View> {

        return object: TypeSafeMatcher<View>() {
            private var resources: Resources? = null
            private var childView: View? = null

            override fun describeTo(description: Description) {
                var idDescription = Integer.toString(recyclerViewId)
                if (this.resources != null) {
                    try {
                        idDescription = this.resources!!.getResourceName(recyclerViewId)
                    } catch (var4: Resources.NotFoundException) {
                        idDescription = String.format("%s (resource name not found)", recyclerViewId)
                    }

                }

                val positionDescription = when (position) {
                    LAST_POSITION -> "Last"
                    else -> position.toString()
                }

                description.appendText("RecyclerView with id: $idDescription at position: $positionDescription")
            }

            override fun matchesSafely(view: View): Boolean {

                this.resources = view.resources

                if (childView == null) {
                    val recyclerView: RecyclerView? = view.rootView.findViewById(recyclerViewId)
                    if (recyclerView != null && recyclerView.id == recyclerViewId) {

                        val positionToMatch = when (position) {
                            LAST_POSITION -> (recyclerView.adapter?.itemCount ?: 0) - 1
                            else -> position
                        }

                        val viewHolder = recyclerView.findViewHolderForAdapterPosition(positionToMatch)
                        if (viewHolder != null) {
                            childView = viewHolder.itemView
                        }

                    } else {
                        return false
                    }
                }

                if (targetViewId == -1) {
                    return view === childView
                } else {
                    val targetView: View = childView!!.findViewById(targetViewId)
                    return view === targetView
                }
            }
        }
    }
}

fun withRecyclerView(recyclerViewId: Int) = RecyclerViewMatcher(recyclerViewId)