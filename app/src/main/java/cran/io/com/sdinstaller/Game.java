package cran.io.com.sdinstaller;

//  This is the game class that we use to generate the games objects for the differences packages

public class Game {

//  Here we declare all the variables that we are going to use

    private String name;
    private String description;
    private String shortDescription;
    private String shortDescriptionBottom;
    private String pathOfGame;
    private boolean unlock;
    private String pathOfFiles;

    public Game(){

    }

//  Here we have all the getters and setters for the variables of the object

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setImagesOfPreview(String path) {
        pathOfGame = path;
    }

    public String getDirectoryOfFiles(){
        return pathOfGame;
    }

    public void setShortDescriptionBottom(String shortDescriptionBottom) {
        this.shortDescriptionBottom = shortDescriptionBottom;
    }

    public String getShortDescriptionBottom() {
        return this.shortDescriptionBottom;
    }

    public void setNullName() {
        this.name = "null";
    }


    public boolean isUnlock() {
        return unlock;
    }

    public void setUnlock(boolean unlock) {
        this.unlock = unlock;
    }

    public void setPathOfFiles(String pathOfFiles) {
        this.pathOfFiles = pathOfFiles;
    }

    public String getPathOfFiles() {
        return this.pathOfFiles;
    }
}
