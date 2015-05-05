package cranio.com.sd_installer;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends ActionBarActivity{

    File dirPackage = new File("storage/extSdCard/games/", "package.txt");

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView title = (TextView) findViewById(R.id.titlePackage);

//      I check the name of the package and the description of it with the package.txt file
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
        }

        title.setText(packageName);
        title.setGravity(Gravity.CENTER);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.menu, new MainMenu()).commit();
            getSupportFragmentManager().beginTransaction().add(R.id.mainDescription, new Description(packageDescription)).commit();
        }
    }
}
