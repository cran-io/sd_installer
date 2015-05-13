package cranio.com.sd_installer;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.IOException;

public class InstallGame {

    static Game gameToInstall;
    //    static String unzipLocation = "storage/sdcard0/Download/";
    static String unzipLocation = null;

    public InstallGame(Game game) {
        gameToInstall = game;

        String path = null;

        if(new File("/storage/extSdCard/").exists()) path="/storage/extSdCard/";
        else if(new File("/storage/sdcard1/").exists()) path="/storage/sdcard1/";

//        unzipLocation = path + "gamesToInstall";

        unzipLocation = "/storage/sdcard0/Download/gamesToInstall";
    }

    public static Intent getIntent() {

//      Here I have all the data that I need of the game
        File dirZip = new File(gameToInstall.getPathZip());
        String zipFile = dirZip.toString();

//      Here I create the directory in the SD where I am going to unzip the apks

        File unzipDirectory = new File(unzipLocation);
        if(!unzipDirectory.exists()) unzipDirectory.mkdir();

//      With this I descompress the zip file that is protected with a password
//      Here is the pass that I need to get with the server in order to control the security of the apks

        Decompress d = new Decompress(zipFile, unzipLocation, "cacaca");
        Boolean lecture = false;

        File apkFile = new File(unzipLocation, gameToInstall.getNameOfApk());

//      Here I get the result if i can handle with the unzip of the file successfully

        try
        {
            lecture = d.unzip();
        }
        catch (IOException e)
        {
            lecture = false;
        }

//      If i get a true result then I can install the game
        Intent intent = null;
        if (lecture)
        {
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
            intent.setClassName("com.android.packageinstaller", "com.android.packageinstaller.PackageInstallerActivity");
        }
        return intent;
    }

    public String getGameDirectory(){
        return unzipLocation + gameToInstall.nameOfApk;
    }

    public void deleteGameApk(){
        File apkFile = new File(unzipLocation, "juego1.apk");
        apkFile.delete();
    }

}
