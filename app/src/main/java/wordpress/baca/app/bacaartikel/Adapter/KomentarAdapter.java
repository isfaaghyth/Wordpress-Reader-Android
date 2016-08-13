package wordpress.baca.app.bacaartikel.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import wordpress.baca.app.bacaartikel.Holder.KategoriHolder;
import wordpress.baca.app.bacaartikel.ItemObject;
import wordpress.baca.app.bacaartikel.R;

/**
 * Created by isfaaghyth on 28/06/16.
 */
public class KomentarAdapter extends RecyclerView.Adapter<KategoriHolder> {
    public List<ItemObject.KomentarPost.Komentar> itemList;
    public Context context;

    public KomentarAdapter(Context context, List<ItemObject.KomentarPost.Komentar> itemList) {
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
        holder.txt_judul.setText(itemList.get(position).name);
        holder.txt_deskripsi.setText(Html.fromHtml(itemList.get(position).content));
        char test = itemList.get(position).name.charAt(0);
        holder.img_item.setText(String.valueOf(test));
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
