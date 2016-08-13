package wordpress.baca.app.bacaartikel.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import wordpress.baca.app.bacaartikel.BacaLengkap;
import wordpress.baca.app.bacaartikel.Holder.MainHolder;
import wordpress.baca.app.bacaartikel.ItemObject;
import wordpress.baca.app.bacaartikel.R;

/**
 * Created by isfaaghyth on 24/06/16.
 */
public class UtamaAdapter extends RecyclerView.Adapter<MainHolder> {
    public List<ItemObject.WordpressJson.Hasil> itemList;
    public Context context;

    public UtamaAdapter(Context context, List<ItemObject.WordpressJson.Hasil> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.carditem, null);
        MainHolder rcv = new MainHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(MainHolder holder, final int position) {
        Picasso.with(context).load(itemList.get(position).thumbnail)
                .placeholder(R.drawable.bg_tdk_ada_image)
                .into(holder.img_header);

        holder.txt_caption.setText(Html.fromHtml(itemList.get(position).title));
        holder.txt_kategori.setText(itemList.get(position).categories.get(0).title);

        holder.card_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, BacaLengkap.class);
                i.putExtra("caption", itemList.get(position).title);
                i.putExtra("content",  itemList.get(position).content);
                i.putExtra("cover", itemList.get(position).thumbnail);
                i.putExtra("category", itemList.get(position).categories.get(0).title);
                i.putExtra("date", itemList.get(position).date);
                i.putExtra("url", itemList.get(position).url);
                i.putExtra("position", String.valueOf(position));
                i.putExtra("id", itemList.get(position).id);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
