package cranio.com.sd_installer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

//  This is the class that I use for each game
//  Basically it has some values and the regulars getters and setters

public class Game {

    String name="";
    String description="";
    String pathIcon="";
    String pathZip="";
    String nameOfApk="";
    List<String> imagePath = new ArrayList<String>();
    int countImagePath = 0;

    public Game(int numberOfGame){
        String path = null;

        if(new File("/storage/extSdCard/games/").exists()) path="/storage/extSdCard/games/game";
        else if(new File("/storage/sdcard1/games/").exists()) path="/storage/sdcard1/games/game";

        pathZip = path + Integer.toString(numberOfGame) + "/game.zip";
    }

    public void createName(String nameReceived) {
        name = nameReceived;
    }

    public void createDescription(String descriptionReceived) {
        description = descriptionReceived;
    }

    public void createPathIcon(String pathIconReceived) {
        pathIcon = pathIconReceived;
    }

    public void createApkName(String apkNameReceived) {
        nameOfApk=apkNameReceived;
    }

    public void createPathImage(String pathImageReceived) {
        imagePath.add(countImagePath, pathImageReceived);
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

    public String getPathZip(){
        return this.pathZip;
    }

    public String getNameOfApk(){
        return nameOfApk;
    }

}


