package edu.polytech.fridge.notifications.vmc;

import android.app.Notification;

import java.util.HashSet;

public class NotificationsModel {
    private static NotificationsModel instance;
    private HashSet<Notification> notificationList;

    private NotificationsModel() {
    }

    public static NotificationsModel getInstance() {
        if (instance == null) {
            instance = new NotificationsModel();
        }
        return instance;
    }

    public Notification removeNotifications() {
        return null;
    }

    public void createNotification(Notification notification) {
        notificationList.add(notification);
    }
}
