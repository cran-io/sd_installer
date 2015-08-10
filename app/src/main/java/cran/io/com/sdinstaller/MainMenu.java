package cran.io.com.sdinstaller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Window;

import java.io.File;

//  This is the main menu class where we are going to show all the packages

public class MainMenu extends ActionBarActivity {

    public MainMenu(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        getSupportFragmentManager().beginTransaction().add(R.id.mainMenu, new MenuItem()).commit();
    }

//  With these method we delete the apk file after the instalation

    @Override
    protected void onResume() {

        super.onResume();

        String path;

        if(new File("/storage/extSdCard/games/").exists()) path="/storage/extSdCard/games/";
        else if(new File("/storage/sdcard1/games/").exists()) path="/storage/sdcard1/games/";
        else if(new File("/mnt/extsd/games/").exists()) path="/mnt/extsd/games/";

        File unzipDirectory = new File("/mnt/sdcard/Download");
        String unzipLocation = unzipDirectory.getAbsolutePath();

        File apkFile = new File(unzipLocation, "game.apk");

        if(apkFile.exists()) apkFile.delete();

    }

}
