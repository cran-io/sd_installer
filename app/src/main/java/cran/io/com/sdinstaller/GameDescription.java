package cran.io.com.sdinstaller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

//  This is the class that we use to create the description of every game

public class GameDescription extends Fragment {

    public Game aGame;
    public AlertDialog alertDialog;
    public boolean accessControl = true;
    public boolean installOk = false;
    public String key = "";
    public String tabletMacAddr = "";

    public GameDescription(Game aGame) {
        this.aGame = aGame;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//      Here we get all the objects of the xml that we are going to fill

        View description = inflater.inflate(R.layout.game_description, container, false);

        final SharedPreferences.Editor editor;
        editor = getActivity().getApplicationContext().getSharedPreferences("MyPref", 0).edit();

        TextView title = (TextView) description.findViewById(R.id.txtGameName);
        title.setText(aGame.getName());

        TextView shortDescription = (TextView) description.findViewById(R.id.txtShortDescription);
        shortDescription.setText(aGame.getShortDescription());

        TextView descriptionGame = (TextView) description.findViewById(R.id.txtDescription);
        descriptionGame.setText(aGame.getDescription());

        TextView descriptionShortBottom = (TextView) description.findViewById(R.id.txtShortDescriptionBotton);
        descriptionShortBottom.setText(aGame.getShortDescriptionBottom());

        final TextView installarButton = (TextView) description.findViewById(R.id.InstallButton);
        if (!aGame.isUnlock()) installarButton.setVisibility(View.INVISIBLE);

        final WifiManager wifiManager = (WifiManager) this.getActivity().getSystemService(Context.WIFI_SERVICE);

//      Here we declare the function for the install button

        installarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String address = "";

                if (installarButton.getVisibility() == View.VISIBLE){
                    if(wifiManager.isWifiEnabled()) {
                        // WIFI ALREADY ENABLED. GRAB THE MAC ADDRESS HERE
                        WifiInfo info = wifiManager.getConnectionInfo();
                        address = info.getMacAddress();
                    } else {
                        // ENABLE THE WIFI FIRST
                        wifiManager.setWifiEnabled(true);

                        // WIFI IS NOW ENABLED. GRAB THE MAC ADDRESS HERE
                        WifiInfo info = wifiManager.getConnectionInfo();
                        address = info.getMacAddress();
                    }

                }

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());

//              Here we get the key of the key of the package

                int number = 0;
                File filesOfKeys = new File(aGame.getPathOfFiles() + "/key.txt");
                boolean controlF;

                do{
                    BufferedReader brPackages = null;
                    try {
                        brPackages = new BufferedReader(new FileReader(filesOfKeys));
                        String strLine;
                        while ((strLine = brPackages.readLine()) != null)
                        {
                            key = strLine;
                        }
                            brPackages.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    tabletMacAddr = address;

                    Thread checker = new Thread(new Runnable() {
                        public void run(){
                            try
                            {

//                              Here we check that the status of the key with the mac adress is ok

                                HttpPost httpPost = new HttpPost("http://104.236.88.136:3000/api/v1/sdpackage/tablet");
                                httpPost.setEntity(new StringEntity("{\"key\": \""+key+"\", \"tablet\": \""+tabletMacAddr+"\"}"));
                                httpPost.setHeader("Accept", "application/json");
                                httpPost.setHeader("Content-type", "application/json");
                                HttpResponse resultado = new DefaultHttpClient().execute(httpPost);

                                BufferedReader reader = new BufferedReader(new InputStreamReader(resultado.getEntity().getContent(), "UTF-8"));
                                String json = reader.readLine();

                                String controlAcces = "{\"status\":\"ok\"}";


                                if (json.equals(controlAcces)){
                                    accessControl = true;
                                    installOk = true;
                                }
                                else{
                                    accessControl = false;
                                    installOk = false;
                                }
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            } catch (ClientProtocolException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            try {
                                this.finalize();
                            } catch (Throwable throwable) {
                                throwable.printStackTrace();
                            }
                        }
                    });

                    checker.start();

                    do{

                    }while(checker.isAlive());

                    number++;
                    filesOfKeys = new File(aGame.getPathOfFiles() + "/key"+number+".txt");
                    controlF = false;
                    if((filesOfKeys.exists())&&(!accessControl)) controlF = true;
                }while(controlF);

//              Here we check that the key is valid

                if(!installOk){
                    alertDialog.setTitle("Esta tablet no tiene permitido la instalacion de paquetes...");
                    alertDialog.setMessage("...la misma no se encunetra configurada dentro de nuestros servidores");
                    alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                    alertDialog.show();
                }
                else{
                    int countLetters = 0;
                    String idOfGameinString = "";
                    char[] pathOfTheGameToInstall = aGame.getDirectoryOfFiles().toCharArray();
                    for(int i = 0;i<pathOfTheGameToInstall.length;i++){
                        if((pathOfTheGameToInstall[i]=='g')&&(pathOfTheGameToInstall[i+1]=='a')&&(pathOfTheGameToInstall[i+2]=='m')&&(pathOfTheGameToInstall[i+3]=='e')) countLetters = i+3;
                    }
                    countLetters++;
                    for(int i = countLetters;i<pathOfTheGameToInstall.length;i++){
                        idOfGameinString = idOfGameinString + String.valueOf(pathOfTheGameToInstall[i]);
                    }
                    int idOfGame = Integer.parseInt(idOfGameinString);

                    final String finalIdOfGameinString = idOfGameinString;
                    Thread checker2 = new Thread(new Runnable() {
                        public void run(){
                            try
                            {
                                HttpPost httpPost = new HttpPost("http://104.236.88.136:3000/api/v1/games/"+ finalIdOfGameinString+"/");
                                httpPost.setEntity(new StringEntity("{\"tablet\": \"" + tabletMacAddr + "\", \"key\": \"" + key + "\"}"));
                                httpPost.setHeader("Accept", "application/json");
                                httpPost.setHeader("Content-type", "application/json");
                                HttpResponse resultOfApi = new DefaultHttpClient().execute(httpPost);

                                BufferedReader reader = new BufferedReader(new InputStreamReader(resultOfApi.getEntity().getContent(), "UTF-8"));
                                String json = reader.readLine();

                                JSONObject jObject = new JSONObject(json);
                                String aJsonString = jObject.getString("gamekey");

                                editor.putString("password", aJsonString);
                                editor.commit();

                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            } catch (ClientProtocolException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            try {
                                this.finalize();
                            } catch (Throwable throwable) {
                                throwable.printStackTrace();
                            }
                        }
                    });

                    checker2.start();

                    do{

                    }while(checker2.isAlive());

//                  Here we unzip the encrypted file

                    File dirZip = new File(aGame.getDirectoryOfFiles() + "/game.zip");
                    String zipFile = dirZip.toString();

                    File unzipDirectory = new File("/mnt/sdcard/Download");
                    String unzipLocation = unzipDirectory.getAbsolutePath();

                    getActivity().getApplicationContext().getSharedPreferences("MyPref", 0).edit();

                    SharedPreferences prfs = getActivity().getApplicationContext().getSharedPreferences("MyPref", 0);
                    String pass = prfs.getString("password", "");

                    Decompress d = new Decompress(zipFile, "/mnt/sdcard/Download", pass);

                    Boolean lecture = false;

                    try
                    {
                        lecture = d.unzip();
                    }
                    catch (IOException e)
                    {
                        lecture = false;
                    }

//                  To finally install it

                    File apkFile = new File(unzipLocation, "game.apk");

                    Intent intent = null;
                    if (lecture)
                    {
                        intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setClassName("com.android.packageinstaller", "com.android.packageinstaller.PackageInstallerActivity");
                    }
                    startActivity(intent);
                }
            }
        });

//      Here we inflate all the images of preview of the game

        ImageView imageLogo = (ImageView) description.findViewById(R.id.imgLogoOfGame);
        File pathOfLogo = new File(aGame.getDirectoryOfFiles() +"/logo.png");
        Bitmap bitmap = BitmapFactory.decodeFile(pathOfLogo.getAbsolutePath());
        BitmapDrawable backgroundDrawable = new BitmapDrawable(bitmap);
        imageLogo.setBackground(backgroundDrawable);

        LinearLayout.LayoutParams Params1 = new LinearLayout.LayoutParams(0,0);

        ImageView imgPreview0 = (ImageView) description.findViewById(R.id.imgPreview10);
        File pathOfPreview0 = new File(aGame.getDirectoryOfFiles() +"/image0.png");
        if (pathOfPreview0.exists()){
            Bitmap bitmap0 = BitmapFactory.decodeFile(pathOfPreview0.getAbsolutePath());
            BitmapDrawable backgroundDrawable0 = new BitmapDrawable(bitmap0);
            imgPreview0.setBackground(backgroundDrawable0);
        }
        else{
            imgPreview0.setLayoutParams(Params1);
        }

        ImageView imgPreview1 = (ImageView) description.findViewById(R.id.imgPreview1);
        File pathOfPreview = new File(aGame.getDirectoryOfFiles() + "/image1.png");
        if (pathOfPreview.exists()){
            Bitmap bitmap1 = BitmapFactory.decodeFile(pathOfPreview.getAbsolutePath());
            BitmapDrawable backgroundDrawable1 = new BitmapDrawable(bitmap1);
            imgPreview1.setBackground(backgroundDrawable1);
        }
        else{
            imgPreview1.setLayoutParams(Params1);
        }

        ImageView imgPreview2 = (ImageView) description.findViewById(R.id.imgPreview2);
        File pathOfPreview2 = new File(aGame.getDirectoryOfFiles() +"/image2.png");
        if (pathOfPreview2.exists()){
            Bitmap bitmap2 = BitmapFactory.decodeFile(pathOfPreview2.getAbsolutePath());
            BitmapDrawable backgroundDrawable2 = new BitmapDrawable(bitmap2);
            imgPreview2.setBackground(backgroundDrawable2);
        }
        else{
            imgPreview2.setLayoutParams(Params1);
        }

        ImageView imgPreview3 = (ImageView) description.findViewById(R.id.imgPreview3);
        File pathOfPreview3 = new File(aGame.getDirectoryOfFiles() +"/image3.png");
        if (pathOfPreview3.exists()){
            Bitmap bitmap3 = BitmapFactory.decodeFile(pathOfPreview3.getAbsolutePath());
            BitmapDrawable backgroundDrawable3 = new BitmapDrawable(bitmap3);
            imgPreview3.setBackground(backgroundDrawable3);
        }
        else{
            imgPreview3.setLayoutParams(Params1);
        }

        ImageView imgPreview4 = (ImageView) description.findViewById(R.id.imgPreview4);
        File pathOfPreview4 = new File(aGame.getDirectoryOfFiles() +"/image4.png");
        if (pathOfPreview4.exists()){
            Bitmap bitmap4 = BitmapFactory.decodeFile(pathOfPreview4.getAbsolutePath());
            BitmapDrawable backgroundDrawable4 = new BitmapDrawable(bitmap4);
            imgPreview4.setBackground(backgroundDrawable4);
        }
        else{
            imgPreview4.setLayoutParams(Params1);
        }

        ImageView imgPreview5 = (ImageView) description.findViewById(R.id.imgPreview5);
        File pathOfPreview5 = new File(aGame.getDirectoryOfFiles() +"/image5.png");
        if (pathOfPreview5.exists()){
            Bitmap bitmap5 = BitmapFactory.decodeFile(pathOfPreview5.getAbsolutePath());
            BitmapDrawable backgroundDrawable5 = new BitmapDrawable(bitmap5);
            imgPreview5.setBackground(backgroundDrawable5);
        }
        else{
            imgPreview5.setLayoutParams(Params1);
        }

        ImageView imgPreview6 = (ImageView) description.findViewById(R.id.imgPreview6);
        File pathOfPreview6 = new File(aGame.getDirectoryOfFiles() +"/image6.png");
        if (pathOfPreview6.exists()){
            Bitmap bitmap6 = BitmapFactory.decodeFile(pathOfPreview6.getAbsolutePath());
            BitmapDrawable backgroundDrawable6 = new BitmapDrawable(bitmap6);
            imgPreview6.setBackground(backgroundDrawable6);
        }
        else{
            imgPreview6.setLayoutParams(Params1);
        }

        ImageView imgPreview7 = (ImageView) description.findViewById(R.id.imgPreview7);
        File pathOfPreview7 = new File(aGame.getDirectoryOfFiles() +"/image7.png");
        if (pathOfPreview7.exists()){
            Bitmap bitmap7 = BitmapFactory.decodeFile(pathOfPreview7.getAbsolutePath());
            BitmapDrawable backgroundDrawable7 = new BitmapDrawable(bitmap7);
            imgPreview7.setBackground(backgroundDrawable7);
        }
        else{
            imgPreview7.setLayoutParams(Params1);
        }

        ImageView imgPreview8 = (ImageView) description.findViewById(R.id.imgPreview8);
        File pathOfPreview8 = new File(aGame.getDirectoryOfFiles() +"/image8.png");
        if (pathOfPreview8.exists()){
            Bitmap bitmap8 = BitmapFactory.decodeFile(pathOfPreview8.getAbsolutePath());
            BitmapDrawable backgroundDrawable8 = new BitmapDrawable(bitmap8);
            imgPreview8.setBackground(backgroundDrawable8);
        }
        else{
            imgPreview8.setLayoutParams(Params1);
        }

        ImageView imgPreview9 = (ImageView) description.findViewById(R.id.imgPreview9);
        File pathOfPreview9 = new File(aGame.getDirectoryOfFiles() +"/image9.png");
        if (pathOfPreview9.exists()){
            Bitmap bitmap9 = BitmapFactory.decodeFile(pathOfPreview9.getAbsolutePath());
            BitmapDrawable backgroundDrawable9 = new BitmapDrawable(bitmap9);
            imgPreview9.setBackground(backgroundDrawable9);
        }
        else{
            imgPreview9.setLayoutParams(Params1);
        }

        return description;
    }


}
