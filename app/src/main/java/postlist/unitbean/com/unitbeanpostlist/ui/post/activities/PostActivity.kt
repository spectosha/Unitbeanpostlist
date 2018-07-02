package postlist.unitbean.com.unitbeanpostlist.ui.post.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView

import com.arellomobile.mvp.presenter.InjectPresenter

import postlist.unitbean.com.unitbeanpostlist.R
import postlist.unitbean.com.unitbeanpostlist.ui.base.activities.BaseActivity
import postlist.unitbean.com.unitbeanpostlist.ui.post.adapters.CommentDiffUtilCallback
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.CommentModel
import postlist.unitbean.com.unitbeanpostlist.ui.post.adapters.PostAdapter
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.PostModel
import postlist.unitbean.com.unitbeanpostlist.ui.post.presenters.PostPresenter
import postlist.unitbean.com.unitbeanpostlist.ui.post.views.PostView

class PostActivity : BaseActivity(), PostView {

    private var toolbar: Toolbar? = null

    private var recyclerView: RecyclerView? = null
    private var postAdapter: PostAdapter? = null
    private var progressBar: ProgressBar? = null

    private var postModel: PostModel? = null

    @InjectPresenter
    lateinit var presenter: PostPresenter

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        toolbar = findViewById(R.id.toolbar)
        progressBar = findViewById(R.id.post_item_progress_bar)
        recyclerView = findViewById(R.id.post_item_comments)

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)

        val intent = intent
        val postId = intent.extras!!.getInt(POST_ID, 1)
        val title = intent.extras!!.getString(TITLE, "undefined")
        val body = intent.extras!!.getString(BODY, "undefined")
        postModel = PostModel()
        postModel!!.id = postId
        postModel!!.title = title
        postModel!!.body = body

        presenter.header = postModel

        postAdapter = PostAdapter()
        postAdapter!!.header = postModel
        recyclerView!!.adapter = postAdapter

        presenter.makeRequestToComments(postId)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showComments(newComments: List<CommentModel>) {
        //CommentDiffUtilCallback commentsDiffUtil = new CommentDiffUtilCallback(postAdapter.getComments(), newComments);
        //DiffUtil.DiffResult commentsDiffResult = DiffUtil.calculateDiff(commentsDiffUtil);
        postAdapter!!.comments = newComments
        postAdapter!!.notifyDataSetChanged()
        //commentsDiffResult.dispatchUpdatesTo(postAdapter);
        //recyclerView.scrollToPosition(0);
    }

    override fun showProgressBar() {
        progressBar!!.visibility = ProgressBar.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar!!.visibility = ProgressBar.INVISIBLE
    }

    companion object {

        val POST_ID = "post_id"
        val TITLE = "title"
        val BODY = "body"
        val DATE = "date"
    }
}
