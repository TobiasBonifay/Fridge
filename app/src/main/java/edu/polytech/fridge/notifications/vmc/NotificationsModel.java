package edu.polytech.fridge.notifications.vmc;


import android.app.Notification;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;

public final class NotificationsModel extends Observable {
    public static final String NOTIFICFATION_LIST = "notifcationList";
    public static final String PINNED_NOTIFICFATION_LIST = "pinnedNotifcationList";

    private static NotificationsModel instance;
    private final List<Notification> notificationList = new ArrayList<>();
    private final List<Notification> pinnedNotificationList = new ArrayList<>();
    private NotificationsController controller;


    public NotificationsModel(){}

    public static NotificationsModel getInstance(){
        if (instance == null)
            instance = new NotificationsModel();
        return instance;
    }

    public void addNotification(Notification notification) {
        notificationList.add(notification);
        updateData();
    }

    public Notification removeNotification(int index) {
        Notification notification = notificationList.remove(index);
        updateData();
        return notification;
    }

    public Notification removePinnedNotification(int index) {
        Notification notification = pinnedNotificationList.remove(index);
        updateData();
        return notification;
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
        updateData();
    }

    public void transferNotificationToUnpinned(int index) {
        notificationList.add(pinnedNotificationList.remove(index));
        updateData();
    }

    public void sortTimeIncrease() {
        Comparator<Notification> c = (n1, n2) -> (int) (n1.when - n2.when);
        notificationList.sort(c);
        pinnedNotificationList.sort(c);
        updateData();
    }
    public void sortTimeDecrease() {
        Comparator<Notification> c = (n1, n2) -> (int) (n2.when - n1.when);
        notificationList.sort(c);
        pinnedNotificationList.sort(c);
        updateData();
    }

    public List<Notification> getNotificationList() {
        return notificationList;
    }

    public List<Notification> getPinnedNotificationList() {
        return pinnedNotificationList;
    }

    private void updateData() {
        setChanged();
        notifyObservers();

        if (controller != null) {
            controller.update();
        }
    }

    public void setController(NotificationsController controller) {
        this.controller = controller;
    }
}
