package wordpress.baca.app.bacaartikel;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import wordpress.baca.app.bacaartikel.Adapter.KomentarAdapter;

/**
 * Created by isfaaghyth on 25/06/16.
 */

public class BacaLengkap extends AppCompatActivity implements View.OnClickListener {


    private TextView txt_caption, txt_author, txt_tanggal;
    private WebView wv_content;
    private ImageView img_header;
    private Button btn_komentar;
    private RecyclerView lst_komentar;
    private LinearLayoutManager layoutManager;
    private KomentarAdapter adapter;
    private ItemObject.KomentarPost komentarPost;
    private AlertDialog.Builder dialog;
    private View dialogView;
    private LayoutInflater inflater;
    private PublicController komentar;
    private String str_url, str_caption, str_cover, str_category,
                   str_date, str_content, str_position, str_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baca_lengkap);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        CastingObject();

        str_url = this.getIntent().getStringExtra("url");
        str_caption = this.getIntent().getStringExtra("caption");
        str_cover = this.getIntent().getStringExtra("cover");
        str_category = this.getIntent().getStringExtra("category");
        str_date = this.getIntent().getStringExtra("date");
        str_content = this.getIntent().getStringExtra("content");
        str_position = this.getIntent().getStringExtra("position");
        str_id = this.getIntent().getStringExtra("id");

        Picasso.with(this).load(str_cover).placeholder(R.drawable.bg_tdk_ada_image).into(img_header);
        layoutManager = new LinearLayoutManager(this);
        lst_komentar.setLayoutManager(layoutManager);
        actionBar.setTitle(R.string.app_name);
        actionBar.setSubtitle(str_category);

        txt_author.setText("Admin"); //males parsing nested array wkwkw
        txt_tanggal.setText(str_date);
        txt_caption.setText(str_caption);
        wv_content.setScrollContainer(false);
        wv_content.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wv_content.loadData(getHtmlData("<div>" + str_content + "</div>"), "text/html; charset=utf-8", "utf-8");

        wv_content.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        GetJSON(PublicController.URL_GET_POST + str_id);
    }

    private void CastingObject() {
        txt_caption = (TextView) findViewById(R.id.txt_caption);
        txt_author = (TextView) findViewById(R.id.txt_author);
        txt_tanggal = (TextView) findViewById(R.id.txt_tanggal);
        wv_content = (WebView) findViewById(R.id.wv_content);
        img_header = (ImageView) findViewById(R.id.img_header);
        lst_komentar = (RecyclerView)  findViewById(R.id.lst_komentar);
        btn_komentar = (Button) findViewById(R.id.btn_komentar);
        btn_komentar.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mn_bagikan, menu);
        MenuItem shareItem = menu.findItem(R.id.mnBagikan);
        shareItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                PublicController.SharedContent(str_caption, str_url, getApplicationContext());
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void GetJSON(String url) {
        try {
            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {;
                @Override
                public void onResponse(String response) {
                    JsonParser parser = new JsonParser();
                    JsonObject rootObejct = parser.parse(response).getAsJsonObject();
                    JsonElement projectElement = rootObejct.get("post");
                    GsonBuilder builder = new GsonBuilder();
                    Gson mGson = builder.create();

                    komentarPost = mGson.fromJson(projectElement, ItemObject.KomentarPost.class);
                    adapter = new KomentarAdapter(getApplicationContext(), komentarPost.comments);
                    lst_komentar.setAdapter(adapter);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),
                            "Check your Internet Connection,\nSwipe or click Refresh button.",
                            Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(stringRequest);
        } finally {}
    }

    private String getHtmlData(String bodyHTML) {
        String head = "<head>" +
                "<style>" +
                "img{" +
                "max-width: 100%; " +
                "width:auto; height: " +
                "auto;}" +
                "body{" +
                "font-size: 14px;" +
                "line-height:150%;" +
                "overflow: auto;" +
                "overflow-y: hidden;}" +
                "</style>" +
                "</head>";
        return "<html>" + head + "<body text=\"#595959\">" + bodyHTML + "</body></html>";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_komentar:
                DialogForm();
                break;
        }
    }

    private void DialogForm() {
        final TextView txtPesan;
        dialog = new AlertDialog.Builder(this);
        inflater = this.getLayoutInflater();
        dialogView = inflater.inflate(R.layout.dialog_komentar, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Komentar");

        txtPesan = (EditText) dialogView.findViewById(R.id.txtPesan);
        txtPesan.setText(null);

        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String BASE_URL = "http://isfa.cf/?json=submit_comment&post_id=";
                String POST_ID = str_id;
                String NAME = "Guest"; //saya buat statik saja
                String EMAIL = "geust@gmail.com"; //begitu juga dengan emailnya
                String CONTENT = txtPesan.getText().toString();
                String URL = BASE_URL + POST_ID + "&name=" + NAME + "&email=" + EMAIL + "&content=" + CONTENT;
                komentar = new PublicController();
                komentar.URLAccess(URL, getApplication());
                dialog.dismiss();
            }
        });

        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
