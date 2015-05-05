package cranio.com.sd_installer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class GameDescription extends Fragment{

    Game game;

    public GameDescription(Game gameReceive){
        game = gameReceive;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View gameDescriptionView = inflater.inflate(R.layout.description_fragment,container,false);

        TextView nameOfTheGame = (TextView) gameDescriptionView.findViewById(R.id.nameOfGame);
        nameOfTheGame.setText(game.getName());

        TextView descriptionOfTheGame = (TextView) gameDescriptionView.findViewById(R.id.txtDescriptionOfTheGame);
        descriptionOfTheGame.setText(game.getDescription());

        return gameDescriptionView;
    }
}
