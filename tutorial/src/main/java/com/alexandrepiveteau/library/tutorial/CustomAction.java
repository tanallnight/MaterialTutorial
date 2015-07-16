package com.alexandrepiveteau.library.tutorial;

import android.net.Uri;

/**
 * Created by Alexandre on 15.07.2015.
 */
public interface CustomAction {
    Uri getCustomActionUri();
    int getCustomActionIcon();
    boolean hasCustomAction();
}
