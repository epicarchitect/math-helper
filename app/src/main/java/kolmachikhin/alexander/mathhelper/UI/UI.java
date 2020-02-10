package kolmachikhin.alexander.mathhelper.UI;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import kolmachikhin.alexander.mathhelper.R;

public class UI {

    private static AppCompatActivity activity;
    private static FragmentManager fragmentManager;

    public static void init(AppCompatActivity a) {
        activity = a;
        fragmentManager = a.getSupportFragmentManager();
    }

    public static void add(int container, Fragment f) {
        try {
            fragmentManager.beginTransaction()
                    .add(container, f)
                    .commit();
        } catch (Exception ignored) {}
    }

    public static void remove(Fragment f) {
        try {
            fragmentManager.beginTransaction()
                    .remove(f)
                    .commit();
        } catch (Exception ignored) {}

    }

    public static void replace(int container, Fragment f) {
        try {
            fragmentManager.beginTransaction()
                    .replace(container, f)
                    .commit();
        } catch (Exception ignored) {}

    }

    public static void hideKeyboard() {
        if (activity != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            View v = activity.getCurrentFocus();
            if (v == null) v = new View(activity);
            if (imm != null) imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    public static int dp(int dp){
        return (int) (activity.getResources().getDisplayMetrics().density * dp);
    }
}
