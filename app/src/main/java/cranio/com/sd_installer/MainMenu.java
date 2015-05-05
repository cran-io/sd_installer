package cranio.com.sd_installer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
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

    int countGames = 0;
    File dirInfo = new File("storage/extSdCard/games/", "game"+ Integer.toString(countGames) +".txt");

    public MainMenu(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//      I supposed that the package has at least ONE game. So here I check the quantity of games in the package
        int countGames = -1;

        do{
            countGames++;
            dirInfo = new File("storage/extSdCard/games/", "game"+ Integer.toString(countGames) +".txt");
        }while(dirInfo.exists());

//      Here I create the games with their properties
        int field = 0;
        Game[] games = new Game[countGames];
        for(int i=0;i<games.length;i++) games[i] = new Game();
//      Here I re initialize the countGames variable to zero in order to create the new objects for each file
        countGames = 0;
        dirInfo = new File("storage/extSdCard/games/", "game"+ Integer.toString(countGames) +".txt");
        do{
            try {
                BufferedReader brGames = new BufferedReader(new FileReader(dirInfo));
                String strLine;
                games[countGames] = new Game();
                while ((strLine = brGames.readLine()) != null) {
                    if(field==0) games[countGames].createName(strLine);
                    if(field==1) games[countGames].createDescription(strLine);
                    if(field==2) games[countGames].createPathIcon(strLine);
                    else games[countGames].createPathImage(strLine);
                    field++;
                }
                brGames.close();
                field = 0;
                countGames++;
                dirInfo = new File("storage/extSdCard/games/", "game"+ Integer.toString(countGames) +".txt");
            }
            catch (IOException e) {
//              Here I am going to show a toast or a window error because the program cant find the file
            }
        }while(dirInfo.exists());


//        super.onCreate(savedInstanceState);

        View mainMenu = inflater.inflate(R.layout.menu_fragment,container,false);

        LinearLayout menu = (LinearLayout) mainMenu.findViewById(R.id.mainMenuButtons);

        Button[] button = new Button[countGames];

        for(int i=0;i<games.length;i++){
            button[i] = new Button(mainMenu.getContext());
            button[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            button[i].setText(games[i].getName());
            button[i].setId(i);
            menu.addView(button[i]);
        }

        return mainMenu;
    }
}

