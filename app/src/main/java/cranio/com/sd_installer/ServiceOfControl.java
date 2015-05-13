package cranio.com.sd_installer;

//  With this service I am going to check wich is the app that is running on top so
//  I can control what to do when the package installer is close

import android.app.ActivityManager;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import java.io.File;
import java.util.Iterator;
import java.util.List;

public class ServiceOfControl extends IntentService{

    String nameOfTheGameToDelete;
    String unzipLocation = "/storage/sdcard0/Download/gamesToInstall";

    public ServiceOfControl() {
        super("Service Of Control");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        boolean installFinish;
        nameOfTheGameToDelete = intent.getStringExtra("sendInfo");
        File fileToDelete = new File(unzipLocation, nameOfTheGameToDelete);


        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> recentTasks = activityManager.getRunningTasks(Integer.MAX_VALUE);
        do{
            activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
            recentTasks = activityManager.getRunningTasks(Integer.MAX_VALUE);
            installFinish = false;
            for (int i = 0; i < recentTasks.size(); i++)
            {
                if(recentTasks.get(i).topActivity.getClassName().equals("com.android.packageinstaller.PackageInstallerActivity")) installFinish = true;
            }
        }while(installFinish);
        Log.d("juego","borrado");
        fileToDelete.delete();
    }

}

