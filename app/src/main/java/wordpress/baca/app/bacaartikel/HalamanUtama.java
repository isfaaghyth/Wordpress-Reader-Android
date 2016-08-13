package wordpress.baca.app.bacaartikel;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

import wordpress.baca.app.bacaartikel.Adapter.UtamaAdapter;

/**
 * Created by isfaaghyth on 24/06/16.
 */
public class HalamanUtama extends android.support.v4.app.Fragment {

    private UtamaAdapter adapter;
    private LinearLayoutManager layoutManager;
    private ItemObject.WordpressJson wordpressJson;
//    private MenuItem menu_refresh;
    private ProgressDialog pDialog;

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipe_refresh;

    public HalamanUtama() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.halaman_utama, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.lst_berita);
//        menu_refresh = (MenuItem) view.findViewById(R.id.mnRefresh);
        swipe_refresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        // Progress dialog
        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);
        pDialog.setMessage("Please wait...\nRetrieval of data from the server");

        //swipe
        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                GetJSON(PublicController.URL_MAIN);
            }
        });

        GetJSON(PublicController.URL_MAIN);

        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuInflater menuInflater = inflater;
        menuInflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnRefresh:
                GetJSON(PublicController.URL_MAIN);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void GetJSON(String url) {
        showDialog();
        try {
            RequestQueue queue = Volley.newRequestQueue(getActivity());
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {;
                @Override
                public void onResponse(String response) {
                    GsonBuilder builder = new GsonBuilder();
                    Gson mGson = builder.create();
                    wordpressJson = mGson.fromJson(response, ItemObject.WordpressJson.class);
                    swipe_refresh.setRefreshing(false);
                    adapter = new UtamaAdapter(getActivity(), wordpressJson.posts);
                    if (adapter.getItemCount() > 0) {
                        recyclerView.setAdapter(adapter);
                    }
                    hideDialog();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    hideDialog();
                    swipe_refresh.setRefreshing(false);
                    Toast.makeText(getContext(),
                            "Check your Internet Connection,\nSwipe or click Refresh button.",
                            Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String jsonString = new String(response.data);
                    return Response.success(jsonString, NetworkCache.parseIgnoreCacheHeaders(response));
                }
            };
            queue.add(stringRequest);
        } finally {

        }
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
