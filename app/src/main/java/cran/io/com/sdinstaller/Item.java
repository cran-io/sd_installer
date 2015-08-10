package cran.io.com.sdinstaller;

//  This class is used in order to set all the items that we are going to show to the user
//  the items are the packages that are included in the sd card

public class Item {

    private String description;
    private String title;
    private Boolean unlock;

    public Item(String title, String description, Boolean unlock)
    {
        this.setTitle(title);
        this.setDescription(description);
        this.setUnlock(unlock);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getUnlock() {
        return unlock;
    }

    public void setUnlock(Boolean unlock) {
        this.unlock = unlock;
    }

}
