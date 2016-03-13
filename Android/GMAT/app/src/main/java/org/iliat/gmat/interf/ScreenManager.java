package org.iliat.gmat.interf;

import android.app.DialogFragment;
import android.app.Fragment;

/**
 * Created by hungtran on 3/13/16.
 */
public interface ScreenManager {
    void openFragment(Fragment fragment, boolean addToBackStack);
    void showDialogFragment(DialogFragment dialogFragment, String tag);
    boolean back();
    void setTitleOfActionBar(String titles);
}
