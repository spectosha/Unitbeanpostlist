package postlist.unitbean.com.unitbeanpostlist.ui.post.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import postlist.unitbean.com.unitbeanpostlist.R;
import postlist.unitbean.com.unitbeanpostlist.ui.base.views.BaseView;
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.CommentModel;
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.PostModel;

public class PostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private PostModel header;
    private List<CommentModel> comments = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_header, parent, false);
            return new PostHeaderHolder(view);
        } else if(viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
            return new CommentHolder(view);
        }else{
            throw new RuntimeException("Unknown value of viewType");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof PostHeaderHolder){
            ((PostHeaderHolder) holder).fillContent(header);
        }else if(holder instanceof CommentHolder){
            CommentModel comment = comments.get(position-1);
            ((CommentHolder) holder).fillContent(comment);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0)
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        int headerExist = (header != null) ? 1 : 0;
        return comments.size() + headerExist;
    }

    public List<CommentModel> getComments() {
        return comments;
    }

    public void setComments(List<CommentModel> newComments) {
        this.comments = newComments;
    }

    public PostModel getHeader() {
        return header;
    }

    public void setHeader(PostModel header) {
        this.header = header;
    }


    class PostHeaderHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private TextView body;
        private TextView date;

        public PostHeaderHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.post_item_title);
            body = itemView.findViewById(R.id.post_item_body);
            date = itemView.findViewById(R.id.post_item_date);
        }

        public void fillContent(PostModel post) {
            title.setText(post.getTitle());
            body.setText(post.getBody());
            date.setText(post.getDate());
        }
    }

    class CommentHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private TextView body;
        private TextView date;

        public CommentHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.comment_item_name);
            body = itemView.findViewById(R.id.comment_item_body);
            date = itemView.findViewById(R.id.comment_item_date);
        }

        public void fillContent(CommentModel comment) {
            name.setText(comment.getName());
            body.setText(comment.getBody());
            date.setText(comment.getDate());
        }
    }
}
