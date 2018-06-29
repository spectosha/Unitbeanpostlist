package postlist.unitbean.com.unitbeanpostlist.ui.main.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import postlist.unitbean.com.unitbeanpostlist.BaseApplication;
import postlist.unitbean.com.unitbeanpostlist.R;
import postlist.unitbean.com.unitbeanpostlist.ui.base.SelectableItem;
import postlist.unitbean.com.unitbeanpostlist.ui.base.adapter.BaseAdapter;
import postlist.unitbean.com.unitbeanpostlist.ui.base.views.BaseView;
import postlist.unitbean.com.unitbeanpostlist.ui.main.models.ImageNavigationViewModel;
import postlist.unitbean.com.unitbeanpostlist.ui.main.models.ItemNavigationViewModel;
import postlist.unitbean.com.unitbeanpostlist.ui.main.models.NavigationEnum;
import postlist.unitbean.com.unitbeanpostlist.ui.main.presenters.MainPresenter;

public class NavigationAdapter extends BaseAdapter<RecyclerView.ViewHolder> {

    MainPresenter presenter;

    public NavigationAdapter(MainPresenter presenter){
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == NavigationEnum.HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_navigation_view, parent, false);
            return new HeaderHolder(v);
        } else if(viewType == NavigationEnum.ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_item_navigation_view, parent, false);
            return new ItemHolder(v);
        }else{
            throw new RuntimeException("Unfounded value of typeView");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderHolder){
            ImageNavigationViewModel item = (ImageNavigationViewModel) presenter.getList().get(position);
            ((HeaderHolder) holder).fullContent(item);
        }else if(holder instanceof ItemHolder){
            ItemNavigationViewModel item = (ItemNavigationViewModel) presenter.getList().get(position);
            ((ItemHolder) holder).fullContent(item);
        }
    }

    public void selectItem(int position) {
        for(int i = 0; i < getItemCount(); i++){
            if(presenter.getList().get(i).getType() == NavigationEnum.ITEM){
                ItemNavigationViewModel item = (ItemNavigationViewModel) presenter.getList().get(i);
                if(i == position) {
                    item.setSelected(true);
                }
                else {
                    item.setSelected(false);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return presenter.getList().get(position).getType();
    }

    @Override
    public int getItemCount() {
        return presenter.getList().size();
    }

    class HeaderHolder extends RecyclerView.ViewHolder{

        public HeaderHolder(View itemView) {
            super(itemView);
        }

        public void fullContent(ImageNavigationViewModel item){
        }
    }

    class ItemHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener {

        ImageView imageView;
        TextView textView;

        public ItemHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_navigation_image);
            imageView.setColorFilter(R.color.colorAccent);
            textView = itemView.findViewById(R.id.item_navigation_title);
            if(onItemClickListener != null)
                itemView.setOnClickListener(this);
        }

        public void fullContent(ItemNavigationViewModel item){
            imageView.setImageResource(item.getImage());
            textView.setText(item.getTitle());
            if (item.isSelected()){
                imageView.setColorFilter(BaseApplication.getAppComponent().getContext().getResources().getColor(R.color.colorPrimary));
                textView.setTextColor(BaseApplication.getAppComponent().getContext().getResources().getColor(R.color.colorPrimary));
            }else{
                imageView.setColorFilter(BaseApplication.getAppComponent().getContext().getResources().getColor(R.color.text_color_grey));
                textView.setTextColor(BaseApplication.getAppComponent().getContext().getResources().getColor(R.color.text_color_grey));
            }
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onClick(v, getAdapterPosition());
        }
    }
}
