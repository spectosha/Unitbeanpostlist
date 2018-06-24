package postlist.unitbean.com.unitbeanpostlist.di.services.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import postlist.unitbean.com.unitbeanpostlist.ui.post.models.PostModel;

@Entity(tableName = "posts")
public class PostEntity {

    @Ignore
    public static final String TABLENAME = "posts";

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "user_id")
    private int user_id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "body")
    private String body;

    public PostEntity(int user_id, String title, String body) {
        this.user_id = user_id;
        this.title = title;
        this.body = body;
    }

    @Ignore
    public PostEntity(int id, int user_id, String title, String body) {
        this.id = id;
        this.user_id = user_id;
        this.title = title;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    @Override
    public String toString() {
        return "id: " + id + " " +
                "user_id: " + user_id + " " +
                "title: " + title + " " +
                "body: " + body;
    }

    public PostModel castToPostModel(){
        return new PostModel(id, user_id, title, body);
    }
}
