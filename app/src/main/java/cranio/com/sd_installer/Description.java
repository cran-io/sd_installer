package cranio.com.sd_installer;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Description extends Fragment{

    public Description(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View windowDescription = inflater.inflate(R.layout.description_fragment,null);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
