package edu.polytech.fridge.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import edu.polytech.fridge.databinding.FragmentNotificationsBinding;
import edu.polytech.fridge.notifications.vmc.NotificationsController;
import edu.polytech.fridge.notifications.vmc.NotificationsView;

public class NotificationsFragment extends Fragment {
    private NotificationsSender notificationsSender;
    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        NotificationsView notificationsView = new NotificationsView(this, binding.getRoot());
        NotificationsController notificationsController = new NotificationsController(notificationsView);
        notificationsView.setController(notificationsController);

        notificationsSender = new NotificationsSender(getContext());
        notificationsSender.newNotification(
                notificationsSender,
                "Leek expiration date warning",
                "https://www.onceuponachef.com/images/2011/11/potato-leek-soup-14.jpg"
        );

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}