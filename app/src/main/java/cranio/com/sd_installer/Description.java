package cranio.com.sd_installer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Description extends Fragment{

    String description;

    public Description(String descriptionRecive){
        description = descriptionRecive;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mainDescription = inflater.inflate(R.layout.description,container,false);

        TextView descriptionOfPackage = (TextView) mainDescription.findViewById(R.id.descriptionOfPackage);

        descriptionOfPackage.setText(description);

        return mainDescription;
    }
}
