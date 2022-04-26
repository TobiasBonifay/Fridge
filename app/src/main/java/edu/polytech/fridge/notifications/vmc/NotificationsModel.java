package edu.polytech.fridge.notifications.vmc;

import android.app.Notification;

import java.util.HashSet;

public class NotificationsModel {
    private static NotificationsModel instance;
    private static HashSet<Notification> notificationList;

    private NotificationsModel(HashSet<Notification> notificationList) {
        NotificationsModel.notificationList = notificationList;
    }

    public static NotificationsModel getInstance() {
        if (instance == null) {
            instance = new NotificationsModel(notificationList);
        }
        return instance;
    }

    public void removeNotifications(Notification notification) {
        notificationList.remove(notification);
    }

    public void createNotification(Notification notification) {
        notificationList.add(notification);
    }
}
