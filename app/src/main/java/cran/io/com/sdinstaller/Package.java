package cran.io.com.sdinstaller;

import android.util.Log;

//  This is the class for every package that we have in the sd card with all the setters ang getters

public class Package {

    private String name;
    private String description;
    private Game games[];
    private int countGames = 0;
    private boolean unlock;

    public Package(int number)
    {
        games = new Game[number];
        unlock = false;
//        for(int i = 0; i<number-1;i++){
//            this.games[i].setNullName();
//        }
    }

    public Boolean getUnlock() {
        return unlock;
    }

    public void setUnlock(String unlock) {
        if(unlock.equals("1")) this.unlock = true;
        else this.unlock = false;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void addGame(String name, String description, String shortDescription, String shortDescriptionBottom, String path, boolean unlock, String path2) {
        games[countGames] = new Game();
        games[countGames].setName(name);
        games[countGames].setDescription(description);
        games[countGames].setShortDescription(shortDescription);
        games[countGames].setShortDescriptionBottom(shortDescriptionBottom);
        games[countGames].setImagesOfPreview(path);
        games[countGames].setPathOfFiles(path2);
        games[countGames].setUnlock(unlock);
        countGames++;
    }

    public void setAmountOfGames(int amountOfGames) {
        games = new Game[amountOfGames];
    }


    public boolean gameExist(int i) {
        if (i<games.length){
            if (games[i]!=null) return true;
        }
        return false;
    }

    public Game getGame(int number){
        return this.games[number];
    }

    public String getNameOfGame(int i) {
        return games[i].getName();
    }

    public String getShortDescription(int i) {
        return games[i].getShortDescription();
    }

    public String getShortDescriptionBottom(int i) {
        return games[i].getShortDescriptionBottom();
    }

    public String getDescription(int i) {
        return games[i].getDescription();
    }

}
