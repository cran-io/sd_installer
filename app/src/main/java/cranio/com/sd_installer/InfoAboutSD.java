package cranio.com.sd_installer;

public class InfoAboutSD {

    public InfoAboutSD(){

    }

    public int returnN1(char[] data, int cantDeNums, int key, int n1, int n2){
        for(int i=0;i<data.length;i++){
            if(Character.isDigit(data[i])){
                String val;
                val = "" + data[i];
                if ((Integer.valueOf(val))!=0){
                    if(cantDeNums!=2){
                        cantDeNums++;
                        key = (Integer.valueOf(val)) + key;
                        if(n1==-1) n1 = (Integer.valueOf(val));
                        else n2 = (Integer.valueOf(val));
                    }
                }
            }
        }
        return n1;
    }

    public int returnKey(char[] data, int cantDeNums, int n1, int n2){
        int key=0;
        for(int i=0;i<data.length;i++){
            if(Character.isDigit(data[i])){
                String val;
                val = "" + data[i];
                if ((Integer.valueOf(val))!=0){
                    if(cantDeNums!=2){
                        cantDeNums++;
                        key = (Integer.valueOf(val)) + key;
                        if(n1==-1) n1 = (Integer.valueOf(val));
                        else n2 = (Integer.valueOf(val));
                    }
                }
            }
        }
        return key;
    }

}
