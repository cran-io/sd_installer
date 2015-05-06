package cranio.com.sd_installer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GameDescription extends Fragment{

    Game game;

    public GameDescription(Game gameReceive){
        game = gameReceive;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//      First I create the view that I want to add to my layout & also make the connection with the part of the layout
//      that I'm going to fill with the description of the game and the images of the preview of the game

        View gameDescriptionView = inflater.inflate(R.layout.description_fragment,container,false);

        LinearLayout imagesOfTheGame = (LinearLayout) gameDescriptionView.findViewById(R.id.imagesOfPreview);

//      Here i create a simple TextView that is going to get the name of the game and make the connection with the
//      part of the layout where I want to be display

        TextView nameOfTheGame = (TextView) gameDescriptionView.findViewById(R.id.nameOfGame);
        nameOfTheGame.setText(game.getName());

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
            imagesOfTheGame.addView(imageOfDescription[i]);
        }

//      Finally I return the view with all the sets

        return gameDescriptionView;
    }
}
