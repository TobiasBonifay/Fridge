package edu.polytech.fridge.notifications.VMC;

public class NotificationsController {
    private final NotificationsView view;

    public NotificationsController(NotificationsView view) {
        this.view = view;
    }
    public void sortNotificationInIncreasingTime() {
        NotificationsModel.getInstance().sortTimeIncrease();
        updatesData();
    }

    public void sortNotificationInDecreasingTime() {
        NotificationsModel.getInstance().sortTimeDecrease();
        updatesData();
    }

    private void updatesData() {
        view.getAdapterBaseNotification().notifyDataSetChanged();
        view.getAdapterPinnedNotification().notifyDataSetChanged();
    }

    public void changeNotificationToPinned(int index) {
        NotificationsModel.getInstance().transferNotificationToPinned(index);
        updatesData();
    }

    public void changeNotificationToUnPinned(int index) {
        NotificationsModel.getInstance().transferNotificationToUnpinned(index);
        updatesData();
    }

    public void removeNotification(int index) {
        NotificationsModel.getInstance().removeNotification(index);
        updatesData();
    }
}
