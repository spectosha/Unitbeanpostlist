package postlist.unitbean.com.unitbeanpostlist.ui.post.models

import com.google.gson.annotations.SerializedName

import java.text.SimpleDateFormat
import java.util.Date


class CommentModel : PostEnum {

    @SerializedName("postId")
    var postId: Int = 0

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("name")
    var name: String? = null

    @SerializedName("email")
    var email: String? = null

    @SerializedName("body")
    var body: String? = null

    val date: String
        get() {
            val date = Date()

            val dateFormat = SimpleDateFormat("dd MMM yyyy")
            val dateStr = dateFormat.format(date)
            return dateStr
        }

    fun copy(): CommentModel {
        val comment = CommentModel()
        comment.id = id
        comment.postId = postId
        comment.name = name
        comment.body = body
        comment.email = email
        return comment
    }

    override fun getType(): Int {
        return PostEnum.COMMENT
    }

    override fun toString(): String {
        return "id: " + id + " " +
                "body: " + body + " " +
                "name: " + name + " "
    }
}
