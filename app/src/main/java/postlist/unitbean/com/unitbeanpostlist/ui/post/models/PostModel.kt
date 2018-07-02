package postlist.unitbean.com.unitbeanpostlist.ui.post.models

import com.google.gson.annotations.SerializedName

import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Date

import postlist.unitbean.com.unitbeanpostlist.di.services.db.entities.PostEntity

class PostModel : PostEnum {

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("userId")
    var userId: Int = 0

    private var date: Date? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("body")
    var body: String? = null

    constructor() {
        this.date = Date()
    }

    constructor(id: Int, userId: Int, title: String, body: String) {
        this.id = id
        this.userId = userId
        this.title = title
        this.body = body

        this.date = Date()
    }

    fun getDate(): String {
        val dateFormat = SimpleDateFormat("dd MMM yyyy")
        val dateStr = dateFormat.format(date)
        return dateStr
    }

    override fun toString(): String {
        return "id: " + id + " " +
                "user_id: " + userId + " " +
                "title: " + title + " " +
                "body: " + body
    }

    fun copy(): PostModel {
        val post = PostModel()
        post.id = id
        post.body = body
        post.title = title
        post.userId = userId
        return post
    }

    fun castToPostEntity(): PostEntity {
        return PostEntity(id, userId, title, body)
    }

    override fun getType(): Int {
        return PostEnum.HEADER
    }
}
