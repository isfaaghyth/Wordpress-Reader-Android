package wordpress.baca.app.bacaartikel.Holder;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import wordpress.baca.app.bacaartikel.BacaLengkap;
import wordpress.baca.app.bacaartikel.R;

/**
 * Created by isfaaghyth on 24/06/16.
 */
public class MainHolder extends RecyclerView.ViewHolder {

    public ImageView img_header;
    public TextView txt_caption, txt_kategori;
    public CardView card_item;

    public MainHolder(View itemView) {
        super(itemView);
        img_header = (ImageView) itemView.findViewById(R.id.img_header);
        txt_caption = (TextView) itemView.findViewById(R.id.txt_caption);
        txt_kategori = (TextView) itemView.findViewById(R.id.txt_kategori);
        card_item = (CardView) itemView.findViewById(R.id.card_view);
    }
}
