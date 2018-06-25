package postlist.unitbean.com.unitbeanpostlist.ui.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseAdapter<ViewHolder extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<ViewHolder> {

    protected OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onClick(View view, int position);
    }

}
