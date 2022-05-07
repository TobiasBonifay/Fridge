package edu.polytech.fridge.notifications;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import edu.polytech.fridge.R;

public class NavigationBar extends Fragment {
    private NavigationBarInterface callback;
    public NavigationBar() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.navigation_bar, container, false);

        //Listener de Planning
        callback.onButtonPlanningClicked(layout.findViewById(R.id.planning));

        //Listener de Recettes
        callback.onButtonRecettesClicked(layout.findViewById(R.id.recettes));

        //Listener de Mes Courses
        callback.onButtonMesCoursesClicked(layout.findViewById(R.id.mes_courses));

        //Listener de Profil
        callback.onButtonProfilClicked(layout.findViewById(R.id.profil));

        return layout;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.createCallBackToParentActivity();
    }

    private void createCallBackToParentActivity() {
        try {
            callback = (NavigationBarInterface) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(e
                    + " must implement NavigationBarInterface");
        }

    }
}