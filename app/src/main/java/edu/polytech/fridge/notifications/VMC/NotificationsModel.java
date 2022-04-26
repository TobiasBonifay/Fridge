package edu.polytech.fridge.notifications.VMC;

import android.app.Notification;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NotificationsModel {
    private static NotificationsModel instance;
    private final List<Notification> notificationList = new ArrayList<>();
    private final List<Notification> pinnedNotificationList = new ArrayList<>();


    private NotificationsModel(){}

    public static NotificationsModel getInstance(){
        if (instance == null)
            instance = new NotificationsModel();
        return instance;
    }

    public void addNotification(Notification notification) {
        notificationList.add(notification);
    }

    public Notification removeNotification(int index) {
        return notificationList.remove(index);
    }

    public Notification removePinnedNotification(int index) {
        return pinnedNotificationList.remove(index);
    }

    public int sizeNotification() {
        return notificationList.size();
    }

    public int sizePinnedNotification() {
        return pinnedNotificationList.size();
    }

    public Notification getNotification(int index) {
        return notificationList.get(index);
    }

    public Notification getPinnedNotification(int index) {
        return pinnedNotificationList.get(index);
    }

    public void transferNotificationToPinned(int index) {
        pinnedNotificationList.add(notificationList.remove(index));
    }

    public void transferNotificationToUnpinned(int index) {
        notificationList.add(pinnedNotificationList.remove(index));
    }

    public void sortTimeIncrease() {
        Comparator<Notification> c = (n1, n2) -> (int) (n1.when - n2.when);
        notificationList.sort(c);
        pinnedNotificationList.sort(c);
    }
    public void sortTimeDecrease() {
        Comparator<Notification> c = (n1, n2) -> (int) (n2.when - n1.when);
        notificationList.sort(c);
        pinnedNotificationList.sort(c);
    }
}
