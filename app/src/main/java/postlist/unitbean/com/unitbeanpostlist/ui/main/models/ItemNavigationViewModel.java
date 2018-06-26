package postlist.unitbean.com.unitbeanpostlist.ui.main.models;

public class ItemNavigationViewModel implements NavigationEnum {

    private String title;
    private int image;

    public ItemNavigationViewModel(int image, String title){
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public int getType() {
        return NavigationEnum.ITEM;
    }
}
