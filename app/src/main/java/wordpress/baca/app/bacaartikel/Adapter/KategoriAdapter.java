package wordpress.baca.app.bacaartikel.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import wordpress.baca.app.bacaartikel.ItemObject;
import wordpress.baca.app.bacaartikel.Holder.KategoriHolder;
import wordpress.baca.app.bacaartikel.R;

/**
 * Created by isfaaghyth on 26/06/16.
 */

public class KategoriAdapter extends RecyclerView.Adapter<KategoriHolder> {
    public List<ItemObject.KategoriPost.Hasil> itemList;
    public Context context;

    public KategoriAdapter(Context context, List<ItemObject.KategoriPost.Hasil> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public KategoriHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.carditem_vertikal, null);
        KategoriHolder rcv = new KategoriHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(KategoriHolder holder, int position) {
        holder.txt_judul.setText(itemList.get(position).title);
        holder.txt_deskripsi.setText("Ada " + itemList.get(position).post_count + " artikel");
        char test = itemList.get(position).title.charAt(0);
        holder.img_item.setText(String.valueOf(test));
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
