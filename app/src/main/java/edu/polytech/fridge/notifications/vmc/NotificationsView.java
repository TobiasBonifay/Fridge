package edu.polytech.fridge.notifications.vmc;

import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;

import edu.polytech.fridge.R;
import edu.polytech.fridge.notifications.NotificationsFragment;

public class NotificationsView {
    private NotificationsController notificationsController;
    private final ConstraintLayout root;

    public NotificationsView(NotificationsFragment notificationsFragment, ConstraintLayout root) {
        this.root = root;
        this.setListeners();
    }

    private void setListeners() {
        final Button showNotificationButton = root.findViewById(R.id.show_notification_example);
        showNotificationButton.setOnClickListener(click -> notificationsController.newNotification());
    }

    public void setController(NotificationsController notificationsController) {
        this.notificationsController = notificationsController;
    }
}
