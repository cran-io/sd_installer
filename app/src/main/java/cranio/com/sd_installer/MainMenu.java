package cranio.com.sd_installer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainMenu extends Fragment{

    Fragment frag;
    android.support.v4.app.FragmentTransaction fragTransaction;

    int countGames = 0;
    File dirInfo;
    public MainMenu(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//      First I check for the ubication of the sd card

        String path = null;

        if(new File("/storage/extSdCard/games/").exists()) path="/storage/extSdCard/games/";
        else if(new File("/storage/sdcard1/games/").exists()) path="/storage/sdcard1/games/";

        dirInfo = new File(path, "game"+ Integer.toString(countGames) +"/info.txt");

//      I supposed that the package has at least ONE game. So here I check the quantity of games in the package
        int countGames = -1;

//      With this i get the quatity of games in the SDCard

        do{
            countGames++;
            dirInfo = new File(path, "game"+ Integer.toString(countGames) +"/info.txt");
        }while(dirInfo.exists());

//      Here I create the objects that I need for the quantity of games that I got

        int field = 0;
        final Game[] games = new Game[countGames];

//      Here I re initialize the countGames variable to zero in order to create the new objects for each file

        countGames = 0;

//      Here I check all the properties for each game that is in the SDCard

//        dirInfo = new File("storage/extSdCard/games/", "game"+ Integer.toString(countGames) +"/info.txt");
        dirInfo = new File(path, "game"+ Integer.toString(countGames) +"/info.txt");
        do{
            try {
                BufferedReader brGames = new BufferedReader(new FileReader(dirInfo));
                String strLine;
                games[countGames] = new Game(countGames);
                while ((strLine = brGames.readLine()) != null) {
                    if(field==0) games[countGames].createName(strLine);
                    if(field==1) games[countGames].createDescription(strLine);
                    if(field==2) games[countGames].createPathIcon(strLine);
                    if(field==3) games[countGames].createApkName(strLine);
                    else if(field>3) games[countGames].createPathImage(strLine);
                    field++;
                }
                brGames.close();
                field = 0;
                countGames++;
                dirInfo = new File(path, "game"+ Integer.toString(countGames) +"/info.txt");
            }
            catch (IOException e) {
//              Here I am going to show a toast or a window error because the program cant find the file
//              Not sure if it necesary because we are going to develop the tool that create the info
            }
        }while(dirInfo.exists());


//      Here I add the buttons to the menu for each game

        View mainMenu = inflater.inflate(R.layout.menu_fragment,container,false);

        LinearLayout menu = (LinearLayout) mainMenu.findViewById(R.id.mainMenuButtons);

        Button[] button = new Button[countGames];

        for(int i=0;i<games.length;i++){
            button[i] = new Button(mainMenu.getContext());
            button[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            button[i].setText(games[i].getName());
            button[i].setId(i);
            menu.addView(button[i]);
        }

//      Here I add the handling of the activitys

        for(int i=0;i<games.length;i++){
            final int finalI = i;
            button[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    frag = new GameDescription(games[finalI]);
                    fragTransaction = getFragmentManager().beginTransaction().replace(R.id.mainDescription,frag);
                    fragTransaction.commit();
                }
            });
        }

//      Finally I return the main menu with all the buttons

        return mainMenu;
    }
}