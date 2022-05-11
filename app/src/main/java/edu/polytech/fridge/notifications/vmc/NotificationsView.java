package edu.polytech.fridge.notifications.vmc;

import android.content.Context;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Observable;
import java.util.Observer;

import edu.polytech.fridge.notifications.ViewAdapterNotification;

public class NotificationsView implements Observer {
    public static final String TAG = "notifView";

    private ViewAdapterNotification adapterBaseNotification;
    private ViewAdapterNotification adapterPinnedNotification;
    private NotificationsController controller;
    private final ConstraintLayout layout;
    private final Context context;

    public NotificationsView(Context context, ConstraintLayout layout) {
        this.layout = layout;
        this.context = context;
    }

    public void setAdapterBaseNotification(ViewAdapterNotification adapterBaseNotification) {
        this.adapterBaseNotification = adapterBaseNotification;
    }

    public void setAdapterPinnedNotification(ViewAdapterNotification adapterPinnedNotification) {
        this.adapterPinnedNotification = adapterPinnedNotification;
    }

    public void setController(NotificationsController controller) {
        this.controller = controller;
        this.controller.setListenersView();
    }

    public Context getContext() {
        return context;
    }

    @Override
    public void update(Observable o, Object arg) {
        NotificationsModel model = (NotificationsModel) o;
        adapterPinnedNotification.refresh(model.getPinnedNotificationList());
        adapterBaseNotification.refresh(model.getNotificationList());
    }

    public void setGestureAdapters(ViewAdapterNotification adapter, ConstraintLayout layout, int i) {
        controller.setGestAdaptGit(adapter, layout, i);
    }
}
