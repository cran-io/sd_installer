package cran.io.com.sdinstaller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

//  This is the main activity that check all the resources that we need to start the app

public class MainActivity extends ActionBarActivity {

    boolean success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Here we have a little animation of the logo of the app

        ImageView logo = (ImageView) findViewById(R.id.logoOfTheGame);
        AlphaAnimation animation1 = new AlphaAnimation(0.2f, 1.0f);
        animation1.setDuration(5000);
        animation1.setFillAfter(true);
        logo.startAnimation(animation1);

//        With this code we can continue with the app when the animation has finish

        animation1.setAnimationListener(new Animation.AnimationListener() {



            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

//                first we are going to check the key of the SD

//                Then I'm going to check if the tablet is ready to install third party apps

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
                    new AlertDialog.Builder(MainActivity.this)
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
                else{
                    Intent packages = new Intent(MainActivity.this,MainMenu.class);
                    startActivity(packages);
                    finish();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//  Here we check for the third party apps permission is checked

    @Override
    protected void onResume() {
        super.onResume();
        if(!success)
        {
            int result = Settings.Secure.getInt(getContentResolver(),
                    Settings.Secure.INSTALL_NON_MARKET_APPS, 0);
            if (result == 0) {
                new AlertDialog.Builder(MainActivity.this)
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
            else{
                success = true;
            }
        }
    }

}
