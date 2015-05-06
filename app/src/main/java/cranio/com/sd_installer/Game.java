package cranio.com.sd_installer;

import java.util.ArrayList;
import java.util.List;

//  This is the class that I use for each game
//  Basically it has some values and the regulars getters and setters

public class Game {

    String name="";
    String description="";
    String pathIcon="";
    List<String> imagePath = new ArrayList<String>();
    int countImagePath = 0;

    public void Game(){

    }

    public void createName(String nameRecive) {
        name = nameRecive;
    }


    public void createDescription(String descriptionRecive) {
        description = descriptionRecive;
    }


    public void createPathIcon(String pathIconRecive) {
        pathIcon = pathIconRecive;
    }


    public void createPathImage(String pathImageRecive) {
        imagePath.add(countImagePath, pathImageRecive);
        countImagePath ++;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getPathIcon() {
        return this.pathIcon;
    }

    public List<String> getPathImage() {
        return this.imagePath;
    }

}


