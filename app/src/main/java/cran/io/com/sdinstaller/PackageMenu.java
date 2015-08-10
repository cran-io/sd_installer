package cran.io.com.sdinstaller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;

import static cran.io.com.sdinstaller.R.drawable.game_menu;

//  This is the class that inflate the xml that shows all the games in the packaged that we open

public class PackageMenu extends Fragment {

    Package aPackage;
    Package [] allPackage;
    Fragment frag2;
    android.support.v4.app.FragmentTransaction fragTransaction2;

    Fragment fragment;
    Fragment fragment2;

    android.support.v4.app.FragmentTransaction fragmentTransaction;
    android.support.v4.app.FragmentTransaction fragmentTransaction2;

    public PackageMenu(Package aPackageReceive, Package[] allPackageReceive){
        aPackage = aPackageReceive;
        allPackage = allPackageReceive;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View packMenu = inflater.inflate(R.layout.package_menu, container, false);
        TextView title = (TextView) packMenu.findViewById(R.id.txtPackageName);
        title.setText(aPackage.getName());

        final ImageView leftButton = (ImageView) packMenu.findViewById(R.id.imgArrorLeft);

        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numberOfPackage = 0;
                for (int i = 0; i < allPackage.length; i++) {
                    if (aPackage.getName() == allPackage[i].getName()) numberOfPackage = i;
                }

                numberOfPackage = numberOfPackage - 1;

                if (numberOfPackage < 0) numberOfPackage = allPackage.length - 1;

                fragment = new PackageMenu(allPackage[numberOfPackage], allPackage);
                fragment2 = new GameDescription(allPackage[numberOfPackage].getGame(0));
                fragmentTransaction = getFragmentManager().beginTransaction().replace(R.id.mainMenu, fragment);
                fragmentTransaction2 = getFragmentManager().beginTransaction().replace(R.id.gameDescription, fragment2);
                fragmentTransaction.commit();
                fragmentTransaction2.commit();
            }
        });

        final ImageView rightButton = (ImageView) packMenu.findViewById(R.id.imgArrowRight);

        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numberOfPackage = 0;
                for(int i=0;i<allPackage.length;i++){
                    if(aPackage.getName()==allPackage[i].getName()) numberOfPackage = i;
                }

                numberOfPackage = numberOfPackage + 1;

                if (numberOfPackage==allPackage.length) numberOfPackage = 0;

                fragment = new PackageMenu(allPackage[numberOfPackage], allPackage);
                fragment2 = new GameDescription(allPackage[numberOfPackage].getGame(0));
                fragmentTransaction = getFragmentManager().beginTransaction().replace(R.id.mainMenu,fragment);
                fragmentTransaction2 = getFragmentManager().beginTransaction().replace(R.id.gameDescription,fragment2);
                fragmentTransaction.commit();
                fragmentTransaction2.commit();
            }
        });

        final TextView logo1 = (TextView) packMenu.findViewById(R.id.buttonGame1);
        final TextView logo2 = (TextView) packMenu.findViewById(R.id.buttonGame2);
        final TextView logo3 = (TextView) packMenu.findViewById(R.id.buttonGame3);
        final TextView logo4 = (TextView) packMenu.findViewById(R.id.buttonGame4);
        final TextView logo5 = (TextView) packMenu.findViewById(R.id.buttonGame5);
        final TextView logo6 = (TextView) packMenu.findViewById(R.id.buttonGame6);
        final TextView logo7 = (TextView) packMenu.findViewById(R.id.buttonGame7);
        final TextView logo8 = (TextView) packMenu.findViewById(R.id.buttonGame8);
        final TextView logo9 = (TextView) packMenu.findViewById(R.id.buttonGame9);
        final TextView logo10 = (TextView) packMenu.findViewById(R.id.buttonGame10);

        if(aPackage.gameExist(0)){
            logo1.setText(aPackage.getNameOfGame(0));
        }

        if(aPackage.gameExist(1)){
            logo2.setText(aPackage.getNameOfGame(1));
        }
        else{
            LinearLayout.LayoutParams Params1 = new LinearLayout.LayoutParams(0,0);
            logo2.setLayoutParams(Params1);
        }

        if(aPackage.gameExist(2)){
            logo3.setText(aPackage.getNameOfGame(2));
        }
        else{
            LinearLayout.LayoutParams Params1 = new LinearLayout.LayoutParams(0,0);
            logo3.setLayoutParams(Params1);
        }

        if(aPackage.gameExist(3)){
            logo4.setText(aPackage.getNameOfGame(3));
        }
        else{
            LinearLayout.LayoutParams Params1 = new LinearLayout.LayoutParams(0,0);
            logo4.setLayoutParams(Params1);
        }

        if(aPackage.gameExist(4)){
            logo5.setText(aPackage.getNameOfGame(4));
        }
        else{
            LinearLayout.LayoutParams Params1 = new LinearLayout.LayoutParams(0,0);
            logo5.setLayoutParams(Params1);
        }

        if(aPackage.gameExist(5)){
            logo6.setText(aPackage.getNameOfGame(5));
        }
        else{
            LinearLayout.LayoutParams Params1 = new LinearLayout.LayoutParams(0,0);
            logo6.setLayoutParams(Params1);
        }

        if(aPackage.gameExist(6)){
            logo7.setText(aPackage.getNameOfGame(6));
        }
        else{
            LinearLayout.LayoutParams Params1 = new LinearLayout.LayoutParams(0,0);
            logo7.setLayoutParams(Params1);
        }

        if(aPackage.gameExist(7)){
            logo8.setText(aPackage.getNameOfGame(7));
        }
        else{
            LinearLayout.LayoutParams Params1 = new LinearLayout.LayoutParams(0,0);
            logo8.setLayoutParams(Params1);
        }

        if(aPackage.gameExist(8)){
            logo9.setText(aPackage.getNameOfGame(8));
        }
        else{
            LinearLayout.LayoutParams Params1 = new LinearLayout.LayoutParams(0,0);
            logo9.setLayoutParams(Params1);
        }

        if(aPackage.gameExist(9)){
            logo10.setText(aPackage.getNameOfGame(9));
        }
        else{
            LinearLayout.LayoutParams Params1 = new LinearLayout.LayoutParams(0,0);
            logo10.setLayoutParams(Params1);
        }

        logo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logo1.setBackgroundResource(R.drawable.game_selected);

                frag2 = new GameDescription(aPackage.getGame(0));
                fragTransaction2 = getFragmentManager().beginTransaction().replace(R.id.gameDescription,frag2);
                fragTransaction2.commit();

                if(aPackage.gameExist(1)){
                    logo2.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(2)){
                    logo3.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(3)){
                    logo4.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(4)){
                    logo5.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(5)){
                    logo6.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(6)){
                    logo7.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(7)){
                    logo8.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(8)){
                    logo9.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(9)){
                    logo10.setBackgroundResource(R.drawable.game_menu);
                }
            }
        });

        logo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logo2.setBackgroundResource(R.drawable.game_selected);

                frag2 = new GameDescription(aPackage.getGame(1));
                fragTransaction2 = getFragmentManager().beginTransaction().replace(R.id.gameDescription,frag2);
                fragTransaction2.commit();

                if(aPackage.gameExist(0)){
                    logo1.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(2)){
                    logo3.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(3)){
                    logo4.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(4)){
                    logo5.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(5)){
                    logo6.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(6)){
                    logo7.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(7)){
                    logo8.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(8)){
                    logo9.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(9)){
                    logo10.setBackgroundResource(R.drawable.game_menu);
                }
            }
        });

        logo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logo3.setBackgroundResource(R.drawable.game_selected);

                frag2 = new GameDescription(aPackage.getGame(2));
                fragTransaction2 = getFragmentManager().beginTransaction().replace(R.id.gameDescription,frag2);
                fragTransaction2.commit();

                if(aPackage.gameExist(0)){
                    logo1.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(1)){
                    logo2.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(3)){
                    logo4.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(4)){
                    logo5.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(5)){
                    logo6.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(6)){
                    logo7.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(7)){
                    logo8.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(8)){
                    logo9.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(9)){
                    logo10.setBackgroundResource(R.drawable.game_menu);
                }
            }
        });

        logo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logo4.setBackgroundResource(R.drawable.game_selected);

                frag2 = new GameDescription(aPackage.getGame(3));
                fragTransaction2 = getFragmentManager().beginTransaction().replace(R.id.gameDescription,frag2);
                fragTransaction2.commit();

                if(aPackage.gameExist(0)){
                    logo1.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(2)){
                    logo3.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(1)){
                    logo2.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(4)){
                    logo5.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(5)){
                    logo6.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(6)){
                    logo7.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(7)){
                    logo8.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(8)){
                    logo9.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(9)){
                    logo10.setBackgroundResource(R.drawable.game_menu);
                }
            }
        });

        logo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logo5.setBackgroundResource(R.drawable.game_selected);

                frag2 = new GameDescription(aPackage.getGame(4));
                fragTransaction2 = getFragmentManager().beginTransaction().replace(R.id.gameDescription,frag2);
                fragTransaction2.commit();

                if(aPackage.gameExist(0)){
                    logo1.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(2)){
                    logo3.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(1)){
                    logo2.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(3)){
                    logo4.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(5)){
                    logo6.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(6)){
                    logo7.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(7)){
                    logo8.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(8)){
                    logo9.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(9)){
                    logo10.setBackgroundResource(R.drawable.game_menu);
                }
            }
        });

        logo6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logo6.setBackgroundResource(R.drawable.game_selected);

                frag2 = new GameDescription(aPackage.getGame(5));
                fragTransaction2 = getFragmentManager().beginTransaction().replace(R.id.gameDescription,frag2);
                fragTransaction2.commit();

                if(aPackage.gameExist(0)){
                    logo1.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(2)){
                    logo3.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(1)){
                    logo2.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(3)){
                    logo4.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(4)){
                    logo5.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(6)){
                    logo7.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(7)){
                    logo8.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(8)){
                    logo9.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(9)){
                    logo10.setBackgroundResource(R.drawable.game_menu);
                }
            }
        });

        logo7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logo7.setBackgroundResource(R.drawable.game_selected);

                frag2 = new GameDescription(aPackage.getGame(6));
                fragTransaction2 = getFragmentManager().beginTransaction().replace(R.id.gameDescription,frag2);
                fragTransaction2.commit();

                if(aPackage.gameExist(0)){
                    logo1.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(2)){
                    logo3.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(1)){
                    logo2.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(3)){
                    logo4.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(4)){
                    logo5.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(5)){
                    logo6.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(7)){
                    logo8.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(8)){
                    logo9.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(9)){
                    logo10.setBackgroundResource(R.drawable.game_menu);
                }
            }
        });

        logo8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logo8.setBackgroundResource(R.drawable.game_selected);

                frag2 = new GameDescription(aPackage.getGame(7));
                fragTransaction2 = getFragmentManager().beginTransaction().replace(R.id.gameDescription,frag2);
                fragTransaction2.commit();

                if(aPackage.gameExist(0)){
                    logo1.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(2)){
                    logo3.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(1)){
                    logo2.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(3)){
                    logo4.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(4)){
                    logo5.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(5)){
                    logo6.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(6)){
                    logo7.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(8)){
                    logo9.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(9)){
                    logo10.setBackgroundResource(R.drawable.game_menu);
                }
            }
        });

        logo9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logo9.setBackgroundResource(R.drawable.game_selected);

                frag2 = new GameDescription(aPackage.getGame(8));
                fragTransaction2 = getFragmentManager().beginTransaction().replace(R.id.gameDescription,frag2);
                fragTransaction2.commit();

                if(aPackage.gameExist(0)){
                    logo1.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(2)){
                    logo3.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(1)){
                    logo2.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(3)){
                    logo4.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(4)){
                    logo5.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(5)){
                    logo6.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(6)){
                    logo7.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(7)){
                    logo8.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(9)){
                    logo10.setBackgroundResource(R.drawable.game_menu);
                }
            }
        });

        logo10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logo10.setBackgroundResource(R.drawable.game_selected);

                frag2 = new GameDescription(aPackage.getGame(9));
                fragTransaction2 = getFragmentManager().beginTransaction().replace(R.id.gameDescription,frag2);
                fragTransaction2.commit();

                if(aPackage.gameExist(0)){
                    logo1.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(2)){
                    logo3.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(1)){
                    logo2.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(3)){
                    logo4.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(4)){
                    logo5.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(5)){
                    logo6.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(6)){
                    logo7.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(7)){
                    logo8.setBackgroundResource(R.drawable.game_menu);
                }

                if(aPackage.gameExist(8)){
                    logo9.setBackgroundResource(R.drawable.game_menu);
                }
            }
        });

        return packMenu;
    }

}
