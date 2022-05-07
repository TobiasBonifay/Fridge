package edu.polytech.fridge;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.Objects;

import edu.polytech.fridge.databinding.FragmentFridgeBinding;
import edu.polytech.fridge.databinding.FragmentNotificationsBinding;
import edu.polytech.fridge.fridge.data.Fridge;
import edu.polytech.fridge.notifications.NavigationBar;
import edu.polytech.fridge.notifications.NavigationBarInterface;
import edu.polytech.fridge.notifications.NavigationBarInterfaceImplementation;
import edu.polytech.fridge.notifications.vmc.NotificationsController;
import edu.polytech.fridge.notifications.vmc.NotificationsModel;
import edu.polytech.fridge.notifications.vmc.NotificationsView;

public class NotificationsFragment extends Fragment implements NavigationBarInterface {
    private FragmentNotificationsBinding binding;
    private NavigationBarInterfaceImplementation implementation;
    private Activity mActivity;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        implementation = new NavigationBarInterfaceImplementation(requireActivity());
        getParentFragmentManager().beginTransaction().replace(R.id.navigation_bar, new NotificationsFragment()).commit();
        NotificationsView view = new NotificationsView(getContext(), binding.getRoot().findViewById(R.id.notification_center));

        NotificationsController controller = new NotificationsController(view, mActivity);
        NotificationsModel.getInstance().addObserver(view);
        view.setController(controller);
        NotificationsModel.getInstance().setController(controller);

        return root;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Override
    public void onButtonPlanningClicked(View v) {
        implementation.onButtonPlanningClicked(v);
    }

    @Override
    public void onButtonProfilClicked(View v) {
        implementation.onButtonProfilClicked(v);
    }

    @Override
    public void onButtonMesCoursesClicked(View v) {
        implementation.onButtonMesCoursesClicked(v);
    }

    @Override
    public void onButtonRecettesClicked(View v) {
        implementation.onButtonRecettesClicked(v);
    }

}
