package postlist.unitbean.com.unitbeanpostlist.ui.post.presenters

import com.arellomobile.mvp.InjectViewState

import java.util.ArrayList

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import postlist.unitbean.com.unitbeanpostlist.ui.base.presenters.BasePresenter
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.CommentModel
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.PostEnum
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.PostModel
import postlist.unitbean.com.unitbeanpostlist.ui.post.views.PostView

@InjectViewState
class PostPresenter : BasePresenter<PostView>() {

    var header: PostModel? = null
        set(header) {
            field = header
            setListOfTypes()
        }
    var list: List<CommentModel> = ArrayList<CommentModel>()
        private set
    private val listOfTypes = ArrayList<PostEnum>()

    fun makeRequestToComments(postId: Int) {
        val disposable = coreServices
                .apiService
                .api
                .getComments(postId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { r -> viewState.showProgressBar() }
                .doFinally { viewState.hideProgressBar() }
                .doOnError { e -> viewState.log("Error: " + e.message) }
                .subscribe({ result ->
                    list = result
                    setListOfTypes()
                    viewState.showComments(list)
                }) { throwable -> viewState.log("Нет соединения с интернетом!") }

        subscriptions.add(disposable)
    }

    private fun setListOfTypes() {
        listOfTypes.clear()
        listOfTypes.add(header!!)
        listOfTypes.addAll(list)
    }

}
