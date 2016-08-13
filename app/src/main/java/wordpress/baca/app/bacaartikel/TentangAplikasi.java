package wordpress.baca.app.bacaartikel;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by isfaaghyth on 24/06/16.
 */
public class TentangAplikasi extends Fragment {
    public TentangAplikasi() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tentang_aplikasi, container, false);
    }
}
