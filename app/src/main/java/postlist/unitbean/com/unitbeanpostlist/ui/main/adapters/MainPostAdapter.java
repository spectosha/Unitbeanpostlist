package postlist.unitbean.com.unitbeanpostlist.ui.main.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import postlist.unitbean.com.unitbeanpostlist.R;
import postlist.unitbean.com.unitbeanpostlist.ui.base.adapter.BaseAdapter;
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.PostModel;

public class MainPostAdapter extends BaseAdapter<MainPostAdapter.PostHolder> {

    private List<PostModel> posts = new ArrayList<>();

    private Context parent_context;

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post, parent, false);

        this.parent_context = parent.getContext();

        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        PostModel post = posts.get(position);
        holder.fillContent(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public List<PostModel> getList() {
        return posts;
    }

    public void setList(List<PostModel> newPosts) {
        this.posts = new ArrayList<>();
        for (PostModel post : newPosts)
            this.posts.add(post.copy());
    }

    class PostHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener {

       private TextView title;
       private TextView date;
       private TextView body;
       private TextView commentsAmmount;

        public PostHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.post_title);
            date = itemView.findViewById(R.id.post_date);
            body = itemView.findViewById(R.id.post_body);
            commentsAmmount = itemView.findViewById(R.id.post_comments);

            if(onItemClickListener != null)
                itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onClick(v, getAdapterPosition());
        }

        private void fillContent(PostModel post) {
            title.setText(post.getTitle());
            date.setText(post.getDate());
            body.setText(post.getBody());
            commentsAmmount.setText("2");
        }
    }
}
