package postlist.unitbean.com.unitbeanpostlist.ui.main.models;

public class ImageNavigationViewModel implements NavigationEnum{

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
