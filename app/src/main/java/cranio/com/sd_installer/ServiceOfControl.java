package cranio.com.sd_installer;

//  With this service I am going to check wich is the app that is running on top so
//  I can control what to do when the package installer is close

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class ServiceOfControl extends IntentService{

    String fileToDelete;

    public ServiceOfControl() {
        super("Service Of Control");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        fileToDelete = intent.getStringExtra("envio");
        for (int i = 1; i <= 10; i++) {
            tareaLarga();
            //Comunicamos el progreso
            Log.d("esto lo hizimos", "estas veces " + Integer.toString(i) + " con estos datos " + fileToDelete);
        }
    }

    private void tareaLarga()
    {
        try {
            Thread.sleep(5000);
        } catch(InterruptedException e) {}
    }

}

