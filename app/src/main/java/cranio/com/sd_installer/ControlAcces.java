package cranio.com.sd_installer;


import android.content.Context;
import android.os.Build;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class ControlAcces {

    public ControlAcces(){

    }

    public boolean returnId(){
        String path = "";

        if(new File("/storage/extSdCard/").exists()) path="/storage/extSdCard/";
        else if(new File("/storage/sdcard1/").exists()) path="/storage/sdcard1/";

        File pathZip = new File(path,".tablet.key");

        String fullId2 = "";
        String strLineZip;
        try {
            BufferedReader brZip = new BufferedReader(new FileReader(pathZip));
            while ((strLineZip = brZip.readLine()) != null) {
                fullId2 = strLineZip;
            }
            brZip.close();
        }
        catch (IOException e) {
//          Here I am going to show a toast or a window error because the program cant find the file
//          Not sure if it necesary because we are going to develop the tool that create the info
        }

        InfoAboutSD infoSD = new InfoAboutSD();
        char[] data = Build.SERIAL.toCharArray();
        int cantDeNums = 0;
        int n1 = -1;
        int n2 = -1;
        int key = infoSD.returnKey(data,cantDeNums,n1,n2);
        n1 = infoSD.returnN1(data, cantDeNums,key,n1,n2);
        n2 = key - n1;

        Inf0 infoNum = new Inf0();

        char[] ar = fullId2.toCharArray();

        String idReturn="";

        idReturn = idReturn + ar[infoNum.getD1()];
        idReturn = idReturn + ar[infoNum.getD2()+n1];
        idReturn = idReturn + ar[infoNum.getD2()];
        idReturn = idReturn + ar[infoNum.getD1()-n2];

        idReturn = idReturn + ar[infoNum.getA1()];
        idReturn = idReturn + ar[infoNum.getA2()+n1];
        idReturn = idReturn + ar[infoNum.getA2()];
        idReturn = idReturn + ar[infoNum.getA1()-n2];

        idReturn = idReturn + ar[infoNum.getB1()];
        idReturn = idReturn + ar[infoNum.getB2()+n1];
        idReturn = idReturn + ar[infoNum.getB2()];
        idReturn = idReturn + ar[infoNum.getB1()-n2];

        idReturn = idReturn + ar[infoNum.getC1()];
        idReturn = idReturn + ar[infoNum.getC2()+n1];
        idReturn = idReturn + ar[infoNum.getC2()];
        idReturn = idReturn + ar[infoNum.getC1()-n2];

        return true;
    }

    public void setNew() {

        Inf0 infoNum = new Inf0();

        InfoAboutSD infoSD = new InfoAboutSD();

        char[] data = Build.SERIAL.toCharArray();
        int cantDeNums = 0;
        int n1 = -1;
        int n2 = -1;
        int key = infoSD.returnKey(data,cantDeNums,n1,n2);
        n1 = infoSD.returnN1(data, cantDeNums,key,n1,n2);
        n2 = key - n1;

        String path = "";

        if(new File("/storage/extSdCard/").exists()) path="/storage/extSdCard/";
        else if(new File("/storage/sdcard1/").exists()) path="/storage/sdcard1/";

        File pathZip = new File(path,".tablet.key");

        String idSerial = Build.SERIAL;

        char[] charIdSerial = idSerial.toCharArray();

        for(int i=0;i<16;i++){
            idSerial = idSerial + Build.SERIAL;
        }

        char[] charSerialId = idSerial.toCharArray();

        for(int i=0;i<100;i++){
            idSerial = shuffle(charSerialId);
        }

        charSerialId = idSerial.toCharArray();

        charSerialId[infoNum.getD1()] = charIdSerial[0];
        charSerialId[infoNum.getD2()+n1] = charIdSerial[1];
        charSerialId[infoNum.getD2()] = charIdSerial[2];
        charSerialId[infoNum.getD1()-n2] = charIdSerial[3];

        charSerialId[infoNum.getA1()] = charIdSerial[4];
        charSerialId[infoNum.getA2()+n1] = charIdSerial[5];
        charSerialId[infoNum.getA2()] = charIdSerial[6];
        charSerialId[infoNum.getA1()-n2] = charIdSerial[7];

        charSerialId[infoNum.getB1()] = charIdSerial[8];
        charSerialId[infoNum.getB2()+n1] = charIdSerial[9];
        charSerialId[infoNum.getB2()] = charIdSerial[10];
        charSerialId[infoNum.getB1()-n2] = charIdSerial[11];

        charSerialId[infoNum.getC1()] = charIdSerial[12];
        charSerialId[infoNum.getC2()+n1] = charIdSerial[13];
        charSerialId[infoNum.getC2()] = charIdSerial[14];
        charSerialId[infoNum.getC1()-n2] = charIdSerial[15];


        String string = charSerialId.toString();

        try {
            FileOutputStream f = null;
            f = new FileOutputStream(pathZip);
            PrintWriter pw = new PrintWriter(f);
            pw.println(string);
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String shuffle(char[] ar){
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            char a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
        return ar.toString();
    }
}
