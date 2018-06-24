package postlist.unitbean.com.unitbeanpostlist.ui.post.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class CommentModel {

    @SerializedName("postId")
    private int postId;

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("body")
    private String body;


    public CommentModel(){

    }


    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate(){
        Date date = new Date();

        String day = String.format("%1$td", date) + " ";
        String monch = String.format("%1$tb", date) + " ";
        monch.replace(".", ",");
        String year = String.format("%1$tY", date);

        return day + monch + year;
    }

}
