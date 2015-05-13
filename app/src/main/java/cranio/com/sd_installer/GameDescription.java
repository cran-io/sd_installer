package cranio.com.sd_installer;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.List;

public class GameDescription extends Fragment{

    Game game;

    public GameDescription(Game gameReceive){
        game = gameReceive;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//      First I create the view that I want to add to my layout & also make the connection with the part of the layout
//      that I'm going to fill with the description of the game and the images of the preview of the game

        final View gameDescriptionView = inflater.inflate(R.layout.description_fragment,container,false);

        LinearLayout imagesOfTheGame = (LinearLayout) gameDescriptionView.findViewById(R.id.imagesOfPreview);

//      Here I create a simple TextView that is going to get the name of the game and make the connection with the
//      part of the layout where I want to be display

        TextView nameOfTheGame = (TextView) gameDescriptionView.findViewById(R.id.nameOfGame);
        nameOfTheGame.setText(game.getName());

//      Here I create a ImageView that is going to get the path of the logo of the game

        ImageView logoOfTheGame = (ImageView) gameDescriptionView.findViewById(R.id.logoOfTheGame);
        File dirLogo = new File(game.getPathIcon());
        Uri uriLogo = Uri.fromFile(dirLogo);
        logoOfTheGame.setImageURI(uriLogo);

//      I do the same thing with the description of the game

        TextView descriptionOfTheGame = (TextView) gameDescriptionView.findViewById(R.id.txtDescriptionOfTheGame);
        descriptionOfTheGame.setText(game.getDescription());

//      In my Game class I have a list of the previews images so here I create the images view with the size of the
//      quantity of images that I receive from the game

        ImageView[] imageOfDescription = new ImageView[game.getPathImage().size()];

//      So then I can add to the part of the layout that I want to be desplay

        for(int i=0;i<game.getPathImage().size();i++){
            imageOfDescription[i] = new ImageView(gameDescriptionView.getContext());
            File imgFile = new File(game.getPathImage().get(i));
            Uri uri = Uri.fromFile(imgFile);
            imageOfDescription[i].setImageURI(uri);
            imageOfDescription[i].setScrollContainer(true);
            imageOfDescription[i].setAdjustViewBounds(true);
            imagesOfTheGame.addView(imageOfDescription[i]);
        }

//      Here I set the button to install the game

        Button install = (Button) gameDescriptionView.findViewById(R.id.install_button);
        install.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final InstallGame gameToInstall = new InstallGame(game);
                final Intent install;
                install = gameToInstall.getIntent();
                if(install==null)
                {
                    new AlertDialog.Builder(gameDescriptionView.getContext())
                            .setTitle("TabiGames couldn't find the game...")
                            .setMessage("...that you want to install, check if the SD Card is insert or contact your provider").show();
                }
                else{
                    Intent msgIntent = new Intent(getActivity(), ServiceOfControl.class);
                    String fileToDelete = game.getNameOfApk();
                    msgIntent.putExtra("sendInfo", fileToDelete);
                    getActivity().startService(msgIntent);
                    startActivity(install);
                }
            }
        });
        
//      Finally I return the view with all the sets

        return gameDescriptionView;
    }

}
