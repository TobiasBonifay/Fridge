package edu.polytech.fridge.notifications;

import android.app.Notification;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import edu.polytech.fridge.R;
import edu.polytech.fridge.notifications.vmc.NotificationsController;
import edu.polytech.fridge.notifications.vmc.NotificationsView;

public class ViewAdapterNotification extends BaseAdapter {
    private final LayoutInflater inflater;
    private List<Notification> notificationList;
    private NotificationsController controller;
    private final NotificationsView view;

    public ViewAdapterNotification(NotificationsView view, List<Notification> notificationList) {
        inflater = LayoutInflater.from(view.getContext());
        this.view = view;
        this.notificationList = notificationList;
    }

    @Override
    public int getCount() {
        return notificationList.size();
    }

    @Override
    public Notification getItem(int position) {
        return notificationList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ConstraintLayout layout = (ConstraintLayout) (view == null ? inflater.inflate(R.layout.view_adapter_notification, viewGroup, false) : view);

        Notification notification = getItem(i);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            ((ImageView) layout.findViewById(R.id.image_notification)).setImageResource(notification.getSmallIcon().getResId());
        }
        ((TextView) layout.findViewById(R.id.text_message)).setText(notification.extras.getString(Notification.EXTRA_TEXT));
        ((TextView) layout.findViewById(R.id.text_date)).setText(getFormattedTime(notification));
        this.view.setGestureAdapters(this, layout, i);

        return layout;
    }

    public void updateModel(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }

    public void refresh(List<Notification> notificationList) {
        updateModel(notificationList);
        notifyDataSetChanged();
    }

    private String getFormattedTime(Notification notification) {
        long cmp = new Date().getTime() - notification.when;
        String time;
        if (TimeUnit.MILLISECONDS.toMinutes(cmp) < 1) {
            time = TimeUnit.MILLISECONDS.toSeconds(cmp) + " s";
        } else if (TimeUnit.MILLISECONDS.toHours(cmp) < 1) {
            time = TimeUnit.MILLISECONDS.toMinutes(cmp) + " min";
        } else if (TimeUnit.MILLISECONDS.toDays(cmp) < 1) {
            time = TimeUnit.MILLISECONDS.toHours(cmp) + " h";
        } else {
            time = TimeUnit.MILLISECONDS.toDays(cmp) + "j";
        }
        return time;
    }
}
