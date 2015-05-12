package cranio.com.sd_installer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends ActionBarActivity{

    File dirPackage;
    boolean success;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      First I'm going to check what is the route of the external storage, I can't use the "Environment.getExternalStorageDirectory()"
//      command because in some tablets and smartphones its returns /emulated/ by default of the manufacture of the terminal

        String path = null;

        if(new File("/storage/extSdCard/games/").exists()) path="/storage/extSdCard/games/";
        else if(new File("/storage/sdcard1/games/").exists()) path="/storage/sdcard1/games/";

        dirPackage = new File(path, "package.txt");

//      Then I'm going to check if the tablet is ready to install third party apps

        int result = Settings.Secure.getInt(getContentResolver(),
                Settings.Secure.INSTALL_NON_MARKET_APPS, 0);
        if (result == 0) {
            success = false;
        }
        else{
            success = true;
        }

        if(!success)
        {
            new AlertDialog.Builder(this)
                    .setTitle("TabiGames needs your permission...")
                    .setMessage("...we need you to allow us to install third party apps, are you going to allow us?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intentSettings = new Intent();
                            intentSettings.setAction(Settings.ACTION_SECURITY_SETTINGS);
                            startActivity(intentSettings);
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            System.exit(0);
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

//      I check the name of the package and the description of it with the package.txt file

        TextView title = (TextView) findViewById(R.id.titlePackage);
        String packageName = "";
        boolean nameGet = true;
        String packageDescription = "";
        try {
            BufferedReader brPackage = new BufferedReader(new FileReader(dirPackage));
            String strLine;
            while ((strLine = brPackage.readLine()) != null){
                if(nameGet){
                    packageName = strLine;
                    nameGet = false;
                }
                else{
                    packageDescription = strLine;
                }
            }
            brPackage.close();
        }
        catch (IOException e) {
//          Here I am going to show a toast or a window error because the program cant find the file
//          Not sure if it necesary because we are going to develop the tool that create the info
        }

//      Here I set the position and the name of the package

        title.setText(packageName);
        title.setGravity(Gravity.CENTER);

//      Finally I add the fragments

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.menu, new MainMenu()).commit();
            getSupportFragmentManager().beginTransaction().add(R.id.mainDescription, new Description(packageDescription)).commit();
        }
    }

//  With this method I check when the app is back if the user set the third party apps on

    @Override
    protected void onResume() {
        super.onResume();
        if(!success)
        {
            int result = Settings.Secure.getInt(getContentResolver(),
                    Settings.Secure.INSTALL_NON_MARKET_APPS, 0);
            if (result == 0) {
                Intent intentSettings = new Intent();
                intentSettings.setAction(Settings.ACTION_SECURITY_SETTINGS);
                startActivity(intentSettings);
            }
            else{
                success = true;
            }
        }
    }
}
