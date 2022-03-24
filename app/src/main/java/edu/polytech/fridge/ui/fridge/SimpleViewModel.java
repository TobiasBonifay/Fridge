package edu.polytech.fridge.ui.fridge;

import androidx.annotation.NonNull;

public class SimpleViewModel {
    private String simpleText;

    public SimpleViewModel(@NonNull final String simpleText) {
        setSimpleText(simpleText);
    }

    @NonNull
    public String getSimpleText() {
        return simpleText;
    }

    public void setSimpleText(@NonNull final String simpleText) {
        this.simpleText = simpleText;
    }
}