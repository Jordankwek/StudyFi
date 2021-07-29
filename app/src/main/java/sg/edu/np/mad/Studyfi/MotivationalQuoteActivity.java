package sg.edu.np.mad.Studyfi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import java.util.List;
import java.util.Random;

public class MotivationalQuoteActivity extends Service {
    private final static String TAG = "MotivationalQuoteActivity";

    @Override
    public void onCreate() {
        super.onCreate();
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_motivational_quote);

        // Code for retrieving random quote from API
        QuoteService quoteService = new QuoteService();
        List<Quote> quoteList = quoteService.GetQuotes();
        int random = new Random().nextInt(quoteList.size() + 1);
        Quote quote = quoteList.get(random);

        String quoteText = quote.text;
        String quoteAuthor = quote.author;

        Intent mainIntent = new Intent(this, MainActivity.class);

        NotificationManager notificationManager
                = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = new NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setContentIntent(PendingIntent.getActivity(this, 0, mainIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT))
                .setContentTitle("Motivational Quote")
                .setContentText("\"quoteText\"" + " - " + quoteAuthor)
                .setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setTicker("ticker message")
                .setWhen(System.currentTimeMillis())
                .build();

        notificationManager.notify(0, notification);

        //Log.i(TAG, "Notification created");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TO DO Auto-generated method stub
        return null;
    }
}