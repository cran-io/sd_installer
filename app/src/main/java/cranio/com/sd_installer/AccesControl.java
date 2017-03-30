package cranio.com.sd_installer;

import android.os.Build;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AccesControl {

    String id;

    public AccesControl(){

    }

    public boolean returnId(){

        String dataId = getSDCARDiD();
        char[] data = getSDCARDiD().toCharArray();
        String path = null;
        String fullId = "";
        ControlAcces controlMemoryAcces = new ControlAcces();

        if(new File("/storage/extSdCard/").exists()) path="/storage/extSdCard/";
        else if(new File("/storage/sdcard1/").exists()) path="/storage/sdcard1/";

        File pathZip = new File(path,".id.key");
        String strLine = "";

        try {
            BufferedReader brGames = new BufferedReader(new FileReader(pathZip));
            while ((strLine = brGames.readLine()) != null) {
                fullId = strLine;
            }
            brGames.close();
        }
        catch (IOException e) {
//              Here I am going to show a toast or a window error because the program cant find the file
//              Not sure if it necesary because we are going to develop the tool that create the info
        }

        char[] ar = fullId.toCharArray();

        InfoAboutSD infoSD = new InfoAboutSD();

        int cantDeNums = 0;
        int n1 = -1;
        int n2 = -1;
        int key = infoSD.returnKey(data,cantDeNums,n1,n2);
        n1 = infoSD.returnN1(data, cantDeNums,key,n1,n2);
        n2 = key - n1;

        Info infoNum = new Info();

        String idReturn="";

        idReturn = idReturn + ar[infoNum.getB1()];
        idReturn = idReturn + ar[infoNum.getB2()+n1];
        idReturn = idReturn + ar[infoNum.getB2()];
        idReturn = idReturn + ar[infoNum.getB1()-n2];

        idReturn = idReturn + ar[infoNum.getH1()];
        idReturn = idReturn + ar[infoNum.getH2()+n1];
        idReturn = idReturn + ar[infoNum.getH2()];
        idReturn = idReturn + ar[infoNum.getH1()-n2];

        idReturn = idReturn + ar[infoNum.getD1()];
        idReturn = idReturn + ar[infoNum.getD2()+n1];
        idReturn = idReturn + ar[infoNum.getD2()];
        idReturn = idReturn + ar[infoNum.getD1()-n2];

        idReturn = idReturn + ar[infoNum.getG1()];
        idReturn = idReturn + ar[infoNum.getG2()+n1];
        idReturn = idReturn + ar[infoNum.getG2()];
        idReturn = idReturn + ar[infoNum.getG1()-n2];

        idReturn = idReturn + ar[infoNum.getE1()];
        idReturn = idReturn + ar[infoNum.getE2()+n1];
        idReturn = idReturn + ar[infoNum.getE2()];
        idReturn = idReturn + ar[infoNum.getE1()-n2];

        idReturn = idReturn + ar[infoNum.getF1()];
        idReturn = idReturn + ar[infoNum.getF2()+n1];
        idReturn = idReturn + ar[infoNum.getF2()];
        idReturn = idReturn + ar[infoNum.getF1()-n2];

        idReturn = idReturn + ar[infoNum.getC1()];
        idReturn = idReturn + ar[infoNum.getC2()+n1];
        idReturn = idReturn + ar[infoNum.getC2()];
        idReturn = idReturn + ar[infoNum.getC1()-n2];

        idReturn = idReturn + ar[infoNum.getA1()];
        idReturn = idReturn + ar[infoNum.getA2()+n1];
        idReturn = idReturn + ar[infoNum.getA2()];
        idReturn = idReturn + ar[infoNum.getA1()-n2];

        if(idReturn.equals(dataId)){
            File pathTZip = new File(path,".tablet.key");
            String fullId2 = "";
            String strLineZip;
            try {
                BufferedReader brZip = new BufferedReader(new FileReader(pathTZip));
                while ((strLineZip = brZip.readLine()) != null) {
                    fullId2 = strLineZip;
                }
                brZip.close();
            }
            catch (IOException e) {
//              Here I am going to show a toast or a window error because the program cant find the file
//              Not sure if it necesary because we are going to develop the tool that create the info
            }

            if(fullId.equals(fullId2)){
                controlMemoryAcces.setNew();
            }

            return true;
        }
        return false;
    }

    public String getSDCARDiD()
    {
        String sd_cid = null;
        try {

            File file = new File("/sys/block/mmcblk1");
            String memBlk;
            if (file.exists() && file.isDirectory()) {

                memBlk = "mmcblk1";
            } else {
                memBlk = "mmcblk0";
            }

            Process cmd = Runtime.getRuntime().exec("cat /sys/block/" + memBlk + "/device/cid");
            BufferedReader br = new BufferedReader(new InputStreamReader(cmd.getInputStream()));
            sd_cid = br.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return sd_cid;
    }

    public String getTabletId(){
        return Build.SERIAL;
    }
}
