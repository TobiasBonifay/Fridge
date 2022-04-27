package edu.polytech.fridge.notifications.vmc;

public class NotificationsModel {
    private static NotificationsModel instance;
    private String notificationText = "default value";
    private String notificationImage = "";

    NotificationsModel() {
    }

    public static NotificationsModel getInstance() {
        if (instance == null) {
            instance = new NotificationsModel();
        }
        return instance;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }

    public String getNotificationImage() {
        return notificationImage;
    }

    public void setNotificationImage(String notificationImage) {
        this.notificationImage = notificationImage;
    }

}
