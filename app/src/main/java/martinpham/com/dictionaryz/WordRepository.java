package martinpham.com.dictionaryz;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WordRepository {
    public interface Runnable {
        public void run(ArrayList<Word> words);
        public void run(String result);
    }

    static class RunVi implements Runnable {
        public void run(ArrayList<Word> words)
        {

        }
        public void run(String result)
        {

        }
    }

    static class RunEn implements Runnable {
        public void run(ArrayList<Word> words)
        {

        }
        public void run(String result)
        {

        }
    }

    static class RunIt implements Runnable {
        public void run(ArrayList<Word> words)
        {

        }
        public void run(String result)
        {

        }
    }

    private static void query(Context context, String language, String text, final Runnable runnable) {
        try {
            RequestQueue queue = Volley.newRequestQueue(context);

            JsonArrayRequest request = new JsonArrayRequest(
                    Request.Method.GET,
                    "https://translate.googleapis.com/translate_a/single?client=gtx&sl=auto&tl=" + language + "&dt=t&q=" +  URLEncoder.encode(text, "UTF-8"),
                    null,
                    new Response.Listener<JSONArray>() {

                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                String text = response.getJSONArray(0).getJSONArray(0).getString(0);

                                runnable.run(text);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("xxx error", error.toString());
                        }
                    })
            {

                /**
                 * Passing some request headers
                 */
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    //headers.put("Content-Type", "application/json");
                    headers.put("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36cc");
                    return headers;
                }
            };

            queue.add(request);
        } catch (Exception e)
        {
            Log.w("xxx", e.toString());
        }

    }

    public static void findByName(final Context context, final String name, final Runnable runnable) {
        if(!name.isEmpty())
        {

            final ArrayList<Word> words = new ArrayList<Word>();

            query(
                    context,
                    "vi",
                    name,
                    new Runnable() {
                        @Override
                        public void run(ArrayList<Word> words) {

                        }

                        @Override
                        public void run(String result) {
                            Word word = new Word();
                            word.setName(result);
                            word.setDescription("Tiếng Việt");
                            word.setContent("Tiếng Việt: " + name + " = " + result);
                            word.setCardImageUrl("");
                            word.setBackgroundImageUrl("");

                            words.add(word);

                            query(
                                    context,
                                    "it",
                                    name,
                                    new Runnable() {
                                        @Override
                                        public void run(ArrayList<Word> words) {

                                        }

                                        @Override
                                        public void run(String result) {
                                            Word word = new Word();
                                            word.setName(result);
                                            word.setDescription("Italiano");
                                            word.setContent("Italiano: " + name + " = " + result);
                                            word.setCardImageUrl("");
                                            word.setBackgroundImageUrl("");

                                            words.add(word);

                                            query(
                                                    context,
                                                    "en",
                                                    name,
                                                    new Runnable() {
                                                        @Override
                                                        public void run(ArrayList<Word> words) {

                                                        }

                                                        @Override
                                                        public void run(String result) {
                                                            Word word = new Word();
                                                            word.setName(result);
                                                            word.setDescription("English");
                                                            word.setContent("English: " + name + " = " + result);
                                                            word.setCardImageUrl("");
                                                            word.setBackgroundImageUrl("");

                                                            words.add(word);

                                                            runnable.run(words);
                                                        }
                                                    }
                                            );
                                        }
                                    }
                            );
                        }
                    }
            );



        }




    }

    public static ArrayList<Word> relatedList(String name)
    {
        ArrayList<Word> words = new ArrayList<Word>();



        return words;
    }

    public static ArrayList<Word> featuredList()
    {
        ArrayList<Word> words = new ArrayList<Word>();



        return words;
    }

    public static ArrayList<Word> searchedList()
    {
        ArrayList<Word> words = new ArrayList<Word>();

        return words;
    }
}
