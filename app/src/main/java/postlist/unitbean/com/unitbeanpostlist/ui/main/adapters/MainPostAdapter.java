package postlist.unitbean.com.unitbeanpostlist.ui.main.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import postlist.unitbean.com.unitbeanpostlist.R;
import postlist.unitbean.com.unitbeanpostlist.ui.main.activities.MainActivity;
import postlist.unitbean.com.unitbeanpostlist.ui.post.models.PostModel;

public class MainPostAdapter extends RecyclerView.Adapter<MainPostAdapter.PostHolder> {

    private List<PostModel> list;

     private Context parent_context;

    public MainPostAdapter(List<PostModel> list){
        this.list = list;
    }

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
        PostModel post = list.get(position);
        holder.title.setText(post.getTitle());
        holder.date.setText(post.getDate());
        holder.body.setText(post.getBody());
        holder.commentsAmmount.setText("2");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class PostHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener{

        public TextView title;
        public TextView date;
        public TextView body;
        public TextView commentsAmmount;

        public CardView cardView;

        public PostHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            title = (TextView) itemView.findViewById(R.id.post_title);
            date = (TextView) itemView.findViewById(R.id.post_date);
            body = (TextView) itemView.findViewById(R.id.post_body);
            commentsAmmount = (TextView) itemView.findViewById(R.id.post_comments);
        }

        @Override
        public void onClick(View v) {
            //TODO Мне кажется, что это не лучшее место для перехода к выбранному посту. Могу я это сделать в MainActivity? И если да, то как?
            ((MainActivity) parent_context).showPostItem(list.get(getAdapterPosition()));
        }
    }
}
