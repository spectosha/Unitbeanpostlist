package postlist.unitbean.com.unitbeanpostlist.ui.post.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import postlist.unitbean.com.unitbeanpostlist.di.services.db.entities.PostEntity;
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.CommentModel;

public class PostModel {

    @SerializedName("id")
    private int id;

    @SerializedName("userId")
    private int userId;

    private Date date;

    @SerializedName("title")
    private String title;

    @SerializedName("body")
    private String body;

    private List<CommentModel> comments = new ArrayList<>();

    public PostModel() {
        date = new Date();
    }

    public PostModel(int id, int userId, String title, String body) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.body = body;

        date = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return userId;
    }

    public void setUser_id(int user_id) {
        this.userId = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate(){

        String day = String.format("%1$td", date) + " ";
        String monch = String.format("%1$tb", date) + " ";
        monch = monch.replace(".", ",");
        String year = String.format("%1$tY", date);

        return day + monch + year;
    }

    @Override
    public String toString() {
        return "id: " + id + " " +
                "user_id: " + userId + " " +
                "title: " + title + " " +
                "body: " + body;
    }

    public PostEntity castToPostEntity(){
        return new PostEntity(id, userId, title, body);
    }
}
