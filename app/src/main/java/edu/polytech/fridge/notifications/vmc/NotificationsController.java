package edu.polytech.fridge.notifications.vmc;

import android.app.Notification;

public class NotificationsController {
    private final NotificationsView notificationsView;

    public NotificationsController(NotificationsView notificationsView) {
        this.notificationsView = notificationsView;
    }


    public void newNotification() {
        Notification notification = new Notification();
        // NotificationsModel.getInstance().createNotification(notification);
    }
}
