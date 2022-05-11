package edu.polytech.fridge.notifications.vmc;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;

import edu.polytech.fridge.R;
import edu.polytech.fridge.notifications.OnSwipeTouchListener;
import edu.polytech.fridge.notifications.ViewAdapterNotification;

public class NotificationsController {
    public static final String TAG = "NotificationController";
    private final ViewAdapterNotification adapBaseNotif;
    private final ViewAdapterNotification adapPinNotif;
    // private final NotificationsCenterActivity activity;
    private final ConstraintLayout layout;
    private final Activity activity;
    private final NotificationsView notificationsView;
    private boolean sortModelNaturalOrder = true;
    private boolean controllerActOnModel = false;

    public NotificationsController(NotificationsView view, Activity activity) {
        this.notificationsView = view;
        this.activity = activity;
        this.layout = activity.findViewById(R.id.notification_center);
        adapBaseNotif = new ViewAdapterNotification(view, NotificationsModel.getInstance().getNotificationList());
        adapPinNotif = new ViewAdapterNotification(view, NotificationsModel.getInstance().getPinnedNotificationList());

        view.setAdapterBaseNotification(adapBaseNotif);
        view.setAdapterPinnedNotification(adapPinNotif);
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
        // notificationsModel.setNotificationText("Fridge");
        // notificationsModel.setNotificationImage("https://www.onceuponachef.com/images/2011/11/potato-leek-soup-14.jpg");
        // notificationsView.update(e, notificationsModel);
    }

    private void sortAcc() {
        controllerActOnModel = true;
        NotificationsModel.getInstance().sortTimeIncrease();
        sortModelNaturalOrder = true;
    }

    private void sortDec() {
        controllerActOnModel = true;
        NotificationsModel.getInstance().sortTimeDecrease();
        sortModelNaturalOrder = false;
    }

    public void setGestAdaptGit(ViewAdapterNotification adp, ConstraintLayout layout, int i) {
        OnSwipeTouchListener swipeTouchListener;

        if (adp == adapPinNotif) {
            swipeTouchListener = new OnSwipeTouchListener(layout.getContext()) {
                @Override
                public void onSwipeLeft() {
                    super.onSwipeLeft();
                    changeNotificationToUnPinned(i);
                    createToast("La notification a été désépinglée");
                }
            };
            layout.setOnTouchListener(swipeTouchListener);
        } else if (adp == adapBaseNotif) {
            swipeTouchListener = new OnSwipeTouchListener(layout.getContext()) {
                @Override
                public void onSwipeLeft() {
                    super.onSwipeLeft();
                    new AlertDialog.Builder(activity)
                            .setTitle("Confirmation")
                            .setMessage("Êtes-vous sûr de vouloir supprimer cette notification ?")
                            .setPositiveButton("Valider", (dialog, which) -> {
                                removeNotification(i);
                                createToast("La notification a bien été supprimée");
                            })
                            .setNegativeButton("Annuler", null)
                            .create()
                            .show();
                }

                @Override
                public void onSwipeRight() {
                    super.onSwipeRight();
                    changeNotificationToPinned(i);
                    createToast("La notification a été épinglée");
                }
            };
            layout.setOnTouchListener(swipeTouchListener);
        }

    }

    public void changeNotificationToPinned(int index) {
        NotificationsModel.getInstance().transferNotificationToPinned(index);
    }

    public void changeNotificationToUnPinned(int index) {
        NotificationsModel.getInstance().transferNotificationToUnpinned(index);
    }

    public void removeNotification(int index) {
        NotificationsModel.getInstance().removeNotification(index);
    }

    public void update() {
        Log.d(TAG, "Les données du modèle ont changé" );

        if (!controllerActOnModel) {
            if (sortModelNaturalOrder) {
                sortAcc();
            } else {
                sortDec();
            }
        } else {
            controllerActOnModel = false;
        }
    }

    private void createToast(String text) {
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
    }

    public void setListenersView() {
        LinearLayout layoutListView =  layout.findViewById(R.id.layout_list_view);
        ((ListView) layoutListView.findViewById(R.id.list_notifications)).setAdapter(adapBaseNotif);
        ((ListView) layoutListView.findViewById(R.id.list_notifications_pinned)).setAdapter(adapPinNotif);

        LinearLayout layout_buttons = layout.findViewById(R.id.buttons_sort);
        layout_buttons.findViewById(R.id.increase_time_button).setOnClickListener(view -> sortAcc());
        layout_buttons.findViewById(R.id.decrease_time_button).setOnClickListener(view -> sortDec());
    }
}
