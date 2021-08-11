package sg.edu.np.mad.Studyfi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MotivationalQuoteActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        // Code for retrieving random quote from API
        //QuoteService quoteService = new QuoteService();
        //List<Quote> quoteList = quoteService.GetQuotes();

        List<Quote> quoteList = new ArrayList<>();
        quoteList.add(new Quote("Genius is one percent inspiration and ninety-nine percent perspiration.", "Thomas Edison"));
        quoteList.add(new Quote("You can observe a lot just by watching.", "Yogi Berra"));
        quoteList.add(new Quote("A house divided against itself cannot stand.", "Abraham Lincoln"));
        quoteList.add(new Quote("Difficulties increase the nearer we get to the goal.", "Johann Wolfgang von Goethe"));
        quoteList.add(new Quote("Fate is in your hands and no one elses", "Byron Pulsifer"));
        quoteList.add(new Quote("Be the chief but never the lord.", "Lao Tzu"));
        quoteList.add(new Quote("Nothing happens unless first we dream.", "Carl Sandburg"));
        quoteList.add(new Quote("Well begun is half done.", "Aristotle"));
        quoteList.add(new Quote("Life is a learning experience, only if you learn.", "Yogi Berra"));
        quoteList.add(new Quote("Self-complacency is fatal to progress.", "Margaret Sangster"));

        int random = new Random().nextInt(quoteList.size() + 1);
        Quote quote = quoteList.get(random);

        String quoteText = quote.text;
        String quoteAuthor = quote.author;

        Intent mainIntent = new Intent(this, MainActivity.class);

        NotificationCompat.Builder notification = new NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setContentIntent(PendingIntent.getActivity(this, 0, mainIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT))
                .setContentTitle("Motivational Quote")
                .setContentText("\"" + quoteText + "\" - " + quoteAuthor)
                .setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setTicker("ticker message");

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        //= (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notification.build());
    }

    /*@Override
    public IBinder onBind(Intent intent) {
        // TO DO Auto-generated method stub
        return null;
    }*/
}