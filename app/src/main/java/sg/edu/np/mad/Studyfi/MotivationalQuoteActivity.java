package sg.edu.np.mad.Studyfi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;
import java.util.Random;

public class MotivationalQuoteActivity extends AppCompatActivity {
    // Instantiate the RequestQueue.
    RequestQueue queue = Volley.newRequestQueue(this);





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        QuoteService quoteService = new QuoteService();
        List<Quote> quoteList = quoteService.GetQuotes();
        int random = new Random().nextInt(quoteList.size() + 1);
        Quote quote = quoteList.get(random);

        String quoteText = quote.text;
        String quoteAuthor = quote.author;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motivational_quote);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Motivational Quote")
                .setContentText("\"quoteText\"" + " - " + quoteAuthor)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
    }
}