package wordpress.baca.app.bacaartikel;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import wordpress.baca.app.bacaartikel.Adapter.KategoriAdapter;

/**
 * Created by isfaaghyth on 24/06/16.
 */
public class KategoriArtikel extends Fragment {

    public KategoriArtikel() {}
    private KategoriAdapter adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ItemObject.KategoriPost kategoriJson;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kategori_artikel, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.lst_kategori);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        GetJSON(PublicController.URL_CATEGORY);
        return view;
    }

    private void GetJSON(String url) {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {;
                    @Override
                    public void onResponse(String response) {
                        GsonBuilder builder = new GsonBuilder();
                        Gson mGson = builder.create();
                        kategoriJson = mGson.fromJson(response, ItemObject.KategoriPost.class);
                        adapter = new KategoriAdapter(getActivity(), kategoriJson.categories);
                        if (adapter.getItemCount() > 0) {
                            recyclerView.setAdapter(adapter);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Check your Internet Connection.",Toast.LENGTH_SHORT).show();
            }
        })  {
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String jsonString = new String(response.data);
                return Response.success(jsonString, NetworkCache.parseIgnoreCacheHeaders(response));
            }
        };
        queue.add(stringRequest);
    }
}
