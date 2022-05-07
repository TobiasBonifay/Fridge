package edu.polytech.fridge.notifications;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import androidx.fragment.app.FragmentActivity;

import edu.polytech.fridge.MainActivity;
import edu.polytech.fridge.NotificationsFragment;

public class NavigationBarInterfaceImplementation implements NavigationBarInterface {

    private final Activity activity;

    public NavigationBarInterfaceImplementation(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onButtonPlanningClicked(View v) {
        v.setOnClickListener(click -> activity.startActivity(new Intent(activity.getApplicationContext(), MainActivity.class)));
    }

    @Override
    public void onButtonProfilClicked(View v) {
        v.setOnClickListener(click -> activity.startActivity(new Intent(activity.getApplicationContext(), MainActivity.class)));
    }

    @Override
    public void onButtonMesCoursesClicked(View v) {
        v.setOnClickListener(click -> activity.startActivity(new Intent(activity.getApplicationContext(), MainActivity.class)));
    }

    @Override
    public void onButtonRecettesClicked(View v) {
        v.setOnClickListener(click -> activity.startActivity(new Intent(activity.getApplicationContext(), MainActivity.class)));
    }


}