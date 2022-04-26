package edu.polytech.fridge.notifications.VMC;

import android.app.Notification;
import android.util.Log;

public class NotificationsListener {
    private static final String TAG = "Notification";


    public static void onNotificationPosted(Notification notification) {
        Log.d(TAG, "onNotificationPosted");
        NotificationsModel.getInstance().addNotification(notification);
    }
}
