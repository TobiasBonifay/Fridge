package edu.polytech.fridge.notifications.vmc;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;

import edu.polytech.fridge.notifications.NotificationsFragment;

public class NotificationsController {
    private final NotificationsView notificationsView;
    private final Context context;

    public NotificationsController(NotificationsFragment notificationsFragment, NotificationsView notificationsView) {
        this.notificationsView = notificationsView;
        this.context = notificationsFragment.getContext();

    }

    /**
     * Try to get the image as a Bitmap from a given link as parameter
     *
     * @param src link of the image to download
     * @return Bitmap image
     */
    public Bitmap fetchImage(final String src) {
        try {
            return BitmapFactory.decodeStream(new URL(src).openConnection().getInputStream());
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }


    public void newNotification() {
        NotificationsModel notificationsModel = NotificationsModel.getInstance();
        Observable e = new Observable();
        e.notifyObservers();
        notificationsModel.setNotificationText("Fridge");
        notificationsModel.setNotificationImage("https://www.onceuponachef.com/images/2011/11/potato-leek-soup-14.jpg");
        notificationsView.update(e, notificationsModel);

    }
}
