package wordpress.baca.app.bacaartikel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by isfaaghyth on 28/06/16.
 */
public class PublicController extends Application {

    public static String URL_MAIN = "http://youresite.com/?json=1";
    public static String URL_CATEGORY = "http://youresite.com/api/get_category_index/";
    public static String URL_GET_POST = "http://youresite.com/api/get_post/?id=";

    public void URLAccess(String url, Application application) {
        RequestQueue queue = Volley.newRequestQueue(application.getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {;
                    @Override
                    public void onResponse(String response) {
                        Log.d("URLAccess", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("URLAccess", "error");
            }
        });
        queue.add(stringRequest);
    }

    public static void SharedContent(String subject, String content, Context context) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        shareIntent.putExtra(Intent.EXTRA_TEXT, content);
        Intent new_intent = Intent.createChooser(shareIntent, "Share it");
        new_intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(new_intent);
    }
}
