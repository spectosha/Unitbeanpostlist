package postlist.unitbean.com.unitbeanpostlist.ui.post.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import postlist.unitbean.com.unitbeanpostlist.R;
import postlist.unitbean.com.unitbeanpostlist.ui.base.views.BaseView;
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.CommentModel;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentHolder> {

    List<CommentModel> comments;

    public CommentAdapter(List<CommentModel> comments) {
        this.comments = comments;
        Log.i(BaseView.LOG_TAG, String.valueOf(comments.size()));
    }

    @NonNull
    @Override
    public CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new CommentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentHolder holder, int position) {
        CommentModel comment = comments.get(position);
        holder.name.setText(comment.getName());
        holder.body.setText(comment.getBody());
        holder.date.setText(comment.getDate());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    class CommentHolder extends RecyclerView.ViewHolder{

        public ImageView profile_image;
        public TextView name;
        public TextView body;
        public TextView date;

        public CommentHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.comment_item_name);
            body = (TextView) itemView.findViewById(R.id.comment_item_body);
            date = (TextView) itemView.findViewById(R.id.comment_item_date);

        }
    }
}
