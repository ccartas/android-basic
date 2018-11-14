package training.luxoft.ro.myapp.helpers;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import java.util.Random;

import training.luxoft.ro.myapp.R;

public class NotificationsHelper {

    private NotificationCompat.Builder notificationBuilder;
    private Context mContext;

    public NotificationsHelper(Context context){
        this.mContext = context;
        this.notificationBuilder = new NotificationCompat.Builder(context);
    }

    public void sendNotification(String title, String text){
        this.notificationBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
        this.notificationBuilder.setContentTitle(title);
        this.notificationBuilder.setContentText(text);
        Random r = new Random();
        NotificationManager manager =
                (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        manager.notify(r.nextInt(), this.notificationBuilder.build());
    }
}
