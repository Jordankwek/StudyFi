package sg.edu.np.mad.Studyfi;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class QuoteService {
    public List<Quote> GetQuotes() {
        String url = "https://type.fit/api/quotes";
        List<Quote> quoteList = new ArrayList<Quote>();

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println(response);
                try {
                    for (int i=0; i < response.length(); i++) {
                        JSONObject quoteData = response.getJSONObject(i);

                        String text = quoteData.getString("text");
                        String author = quoteData.getString("author");
                        Quote quote = new Quote(text, author);
                        quoteList.add(quote);
                    }
                }
                catch (JSONException e) {
                    // Error
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        System.out.println("testttttttttttttttttttttttt2");
        return quoteList;
    }
}
