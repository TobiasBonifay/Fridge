package edu.polytech.fridge.gps;

public interface IGPSActivity {
    int REQUEST_CODE = 400;
    void moveCamera(); //move camera (and zoom to center the map to the gps position)
}
