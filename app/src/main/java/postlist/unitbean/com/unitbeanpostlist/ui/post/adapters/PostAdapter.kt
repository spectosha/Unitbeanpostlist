package postlist.unitbean.com.unitbeanpostlist.ui.post.adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


import java.util.ArrayList
import java.util.Collections

import postlist.unitbean.com.unitbeanpostlist.R
import postlist.unitbean.com.unitbeanpostlist.ui.base.views.BaseView
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.CommentModel
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.PostModel

class PostAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var header: PostModel? = null
    var comments: List<CommentModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_HEADER) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post_header, parent, false)
            return PostHeaderHolder(view)
        } else if (viewType == TYPE_ITEM) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
            return CommentHolder(view)
        } else {
            throw RuntimeException("Unknown value of viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PostHeaderHolder) {
            holder.fillContent(header)
        } else if (holder is CommentHolder) {
            val comment = comments[position - 1]
            holder.fillContent(comment)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TYPE_HEADER else TYPE_ITEM
    }

    override fun getItemCount(): Int {
        val headerExist = if (header != null) 1 else 0
        return comments.size + headerExist
    }


    internal inner class PostHeaderHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title: TextView
        private val body: TextView
        private val date: TextView

        init {

            title = itemView.findViewById(R.id.post_item_title)
            body = itemView.findViewById(R.id.post_item_body)
            date = itemView.findViewById(R.id.post_item_date)
        }

        fun fillContent(post: PostModel?) {
            title.text = post!!.title
            body.text = post.body
            date.text = post.getDate()
        }
    }

    internal inner class CommentHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val name: TextView
        private val body: TextView
        private val date: TextView

        init {

            name = itemView.findViewById(R.id.comment_item_name)
            body = itemView.findViewById(R.id.comment_item_body)
            date = itemView.findViewById(R.id.comment_item_date)
        }

        fun fillContent(comment: CommentModel) {
            name.text = comment.name
            body.text = comment.body
            date.text = comment.date
        }
    }

    companion object {

        private val TYPE_HEADER = 0
        private val TYPE_ITEM = 1
    }
}
