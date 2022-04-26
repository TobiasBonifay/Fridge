package edu.polytech.fridge.notifications;

import android.app.Notification;

import edu.polytech.fridge.notifications.vmc.NotificationsModel;

public class NotificationsListener {

    public static void onNotificationPosted(Notification notification) {
        NotificationsModel.getInstance().createNotification(notification);
    }

}
