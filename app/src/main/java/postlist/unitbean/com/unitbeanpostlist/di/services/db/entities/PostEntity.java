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

    @ColumnInfo(name = "userId")
    private int userId;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "body")
    private String body;

    public PostEntity(int userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    @Ignore
    public PostEntity(int id, int userId, String title, String body) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
                "userId: " + userId + " " +
                "title: " + title + " " +
                "body: " + body;
    }

    public PostModel castToPostModel(){
        return new PostModel(id, userId, title, body);
    }
}
