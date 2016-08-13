package wordpress.baca.app.bacaartikel.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import wordpress.baca.app.bacaartikel.R;

/**
 * Created by isfaaghyth on 26/06/16.
 */
public class KategoriHolder extends RecyclerView.ViewHolder {

    public TextView img_item, txt_judul, txt_deskripsi;

    public KategoriHolder(View itemView) {
        super(itemView);
        img_item = (TextView) itemView.findViewById(R.id.img_item);
        txt_judul = (TextView) itemView.findViewById(R.id.txt_judul);
        txt_deskripsi = (TextView) itemView.findViewById(R.id.txt_deskripsi);
    }
}
