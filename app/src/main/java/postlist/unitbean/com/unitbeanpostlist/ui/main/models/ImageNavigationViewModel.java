package postlist.unitbean.com.unitbeanpostlist.ui.main.models;

import postlist.unitbean.com.unitbeanpostlist.ui.base.SelectableItem;

public class ImageNavigationViewModel extends SelectableItem implements NavigationEnum{

    private int imageId;

    public ImageNavigationViewModel(int imageId){
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @Override
    public int getType() {
        return NavigationEnum.HEADER;
    }
}
