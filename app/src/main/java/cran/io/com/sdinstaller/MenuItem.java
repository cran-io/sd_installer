package cran.io.com.sdinstaller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//  With these class we are going to fill all the xml for the packages

public class MenuItem extends Fragment{

    int countPackages;

    Fragment frag;
    Fragment frag2;

    android.support.v4.app.FragmentTransaction fragTransaction;
    android.support.v4.app.FragmentTransaction fragTransaction2;

    public  MenuItem(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {

        String path = null;

        countPackages = 0;

        if(new File("/storage/extSdCard/games/").exists()) path="/storage/extSdCard/games/";
        else if(new File("/storage/sdcard1/games/").exists()) path="/storage/sdcard1/games/";
        else if(new File("/mnt/extsd/games/").exists()) path="/mnt/extsd/games/";

        File f = new File(path);
        File[] files = f.listFiles();
        for (File inFile : files) {
            if (inFile.isDirectory()) {
                countPackages++;
            }
        }

        int field = 0;
        final Package[] packages = new Package[countPackages];

        countPackages = 0;

        for (File inFile : files) {
            if (inFile.isDirectory()) {
                File folderOfPackages = new File(inFile.getAbsolutePath());
                File[] folderOfPackage = folderOfPackages.listFiles();
                try {
                    packages[countPackages] = new Package(folderOfPackages.listFiles().length);
                    BufferedReader brPackages = new BufferedReader(new FileReader(inFile.getAbsolutePath()+"/package.txt"));
                    String strLine;
                    while ((strLine = brPackages.readLine()) != null)
                    {
                        if(field==0) packages[countPackages].setName(strLine);
                        if(field==1) packages[countPackages].setDescription(strLine);
                        if(field==2) packages[countPackages].setUnlock(strLine);
                        field++;
                    }
                    brPackages.close();
                    int games = 0;
                    for (File inFile2 : folderOfPackage) {
                        if (inFile2.isDirectory()) {
                            int fieldGames = 0;
                            String name = null;
                            String description = null;
                            String shortDescription = null;
                            String shortDescriptionBottom = null;
                            try (BufferedReader brGames = new BufferedReader(new FileReader(inFile2.getAbsolutePath() + "/info.txt")))
                            {
                                String strLine2;
                                while ((strLine2 = brGames.readLine()) != null)
                                {
                                    if (fieldGames == 0) name = strLine2;
                                    if (fieldGames == 1) description = strLine2;
                                    if (fieldGames == 2) shortDescription = strLine2;
                                    if (fieldGames == 3) shortDescriptionBottom = strLine2;
                                    fieldGames++;
                                }
                                brGames.close();
                            }
                            packages[countPackages].addGame(name, description, shortDescription, shortDescriptionBottom, inFile2.getAbsolutePath(), packages[countPackages].getUnlock(),inFile.getAbsolutePath());
                            games++;
                        }
                    }
                    field = 0;
                    countPackages++;
                }
                catch (IOException e)
                {
//                  Here I am going to show a toast or a window error because the program cant find the file
//                  Not sure if it necesary because we are going to develop the tool that create the info
                }
            }
        }

        int numbOfPackage = 0;

        final View mainMenu = inflater.inflate(R.layout.main_menu,container,false);

        final LinearLayout menu = (LinearLayout) mainMenu.findViewById(R.id.mainMenu);

        Item[] menuItem = new Item[packages.length];

        int i = 0;
        for (File inFile : files) {

            File folderOfPackages = new File(inFile.getAbsolutePath());
            File[] folderOfPackage = folderOfPackages.listFiles();

            menuItem[i] = new Item(packages[i].getName(),packages[i].getDescription(), packages[i].getUnlock());
            View to_add = getLayoutInflater(savedInstanceState).inflate(R.layout.menu_item,null);

            TextView title = (TextView) to_add.findViewById(R.id.txtTitle);
            TextView description = (TextView) to_add.findViewById(R.id.txtDescription);
            title.setText(menuItem[i].getTitle());
            description.setText(menuItem[i].getDescription());

            ImageView imageLogo = (ImageView) to_add.findViewById(R.id.imgLogo);
            File pathOfLogo = new File(inFile.getAbsolutePath() + "/logo.png");
            Bitmap bitmap = BitmapFactory.decodeFile(pathOfLogo.getAbsolutePath());
            BitmapDrawable backgroundDrawable = new BitmapDrawable(bitmap);
            imageLogo.setBackground(backgroundDrawable);

            ImageView miniLogo1 = (ImageView) to_add.findViewById(R.id.imgLogo1);
            ImageView miniLogo2 = (ImageView) to_add.findViewById(R.id.imgLogo2);
            ImageView miniLogo3 = (ImageView) to_add.findViewById(R.id.imgLogo3);
            ImageView miniLogo4 = (ImageView) to_add.findViewById(R.id.imgLogo4);
            ImageView miniLogo5 = (ImageView) to_add.findViewById(R.id.imgLogo5);
            ImageView miniLogo6 = (ImageView) to_add.findViewById(R.id.imgLogo6);
            ImageView miniLogo7 = (ImageView) to_add.findViewById(R.id.imgLogo7);
            ImageView miniLogo8 = (ImageView) to_add.findViewById(R.id.imgLogo8);
            ImageView miniLogo9 = (ImageView) to_add.findViewById(R.id.imgLogo9);
            ImageView miniLogo10 = (ImageView) to_add.findViewById(R.id.imgLogo10);

            int miniLogo = 0;

            for (File inFile2 : folderOfPackage)
            {
                if (inFile2.isDirectory())
                {
                    File pathOfMiniLogo = new File(inFile2.getAbsolutePath() + "/logo.png");
                    if (pathOfMiniLogo.exists())
                    {
                        Bitmap bitmap2 = BitmapFactory.decodeFile(pathOfMiniLogo.getAbsolutePath());
                        BitmapDrawable backgroundDrawable2 = new BitmapDrawable(bitmap2);
                        if(miniLogo==0) miniLogo1.setBackground(backgroundDrawable2);
                        else if(miniLogo==1) miniLogo2.setBackground(backgroundDrawable2);
                        else if(miniLogo==2) miniLogo3.setBackground(backgroundDrawable2);
                        else if(miniLogo==3) miniLogo4.setBackground(backgroundDrawable2);
                        else if(miniLogo==4) miniLogo5.setBackground(backgroundDrawable2);
                        else if(miniLogo==5) miniLogo6.setBackground(backgroundDrawable2);
                        else if(miniLogo==6) miniLogo7.setBackground(backgroundDrawable2);
                        else if(miniLogo==7) miniLogo8.setBackground(backgroundDrawable2);
                        else if(miniLogo==8) miniLogo9.setBackground(backgroundDrawable2);
                        else if(miniLogo==9) miniLogo10.setBackground(backgroundDrawable2);
                    }
                    miniLogo++;
                }
            }

            ImageView imageBar = (ImageView) to_add.findViewById(R.id.imageViewSeparator);
            if(menuItem[i].getUnlock()){
                imageLogo.setImageResource(R.drawable.unlock);
                imageBar.setImageResource(R.drawable.separatorunlock);
                title.setTextColor(Color.parseColor("#00943e"));
            }
            else{
                imageLogo.setImageResource(R.drawable.lock);
                imageBar.setImageResource(R.drawable.separatorlock);
            }
            if(i == 0){
                ImageView separator = (ImageView) to_add.findViewById(R.id.sepatatorBar);
                int width = 0;
                int height = 0;
                LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(width,height);
                separator.setLayoutParams(parms);
            }

            ImageView openPackage = (ImageView) to_add.findViewById(R.id.imgOpenPackage);
            final int finalI = numbOfPackage;
            openPackage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    frag = new PackageMenu(packages[finalI], packages);
                    frag2 = new GameDescription(packages[finalI].getGame(0));
                    fragTransaction = getFragmentManager().beginTransaction().replace(R.id.mainMenu,frag);
                    fragTransaction2 = getFragmentManager().beginTransaction().replace(R.id.gameDescription,frag2);
                    fragTransaction.commit();
                    fragTransaction2.commit();
                }
            });

            menu.addView(to_add);
            i++;
            numbOfPackage++;
        }
        return mainMenu;
    }
}
