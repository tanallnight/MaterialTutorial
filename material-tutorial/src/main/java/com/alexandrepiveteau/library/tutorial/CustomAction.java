package com.alexandrepiveteau.library.tutorial;

import android.app.PendingIntent;
import android.support.annotation.DrawableRes;

/**
 * Created by Alexandre on 15.07.2015.
 */
public interface CustomAction {

    PendingIntent getCustomActionPendingIntent();

    int getCustomActionIcon();

    String getCustomActionTitle();

    boolean isEnabled();

    boolean hasCustomIcon();

    /*
     * Since Android doesn't support static methods in interfaces for the moment, we use an inner class.
     */
    class Utils {
        static boolean areCustomActionsDrawingEqual(CustomAction customAction1, CustomAction customAction2) {
            if (customAction1.hasCustomIcon() == customAction2.hasCustomIcon()) {
                if (customAction1.getCustomActionIcon() == customAction2.getCustomActionIcon()) {
                    return true;
                }
            } else {
                if (customAction1.getCustomActionTitle().equals(customAction2.getCustomActionTitle())) {
                    return true;
                }
            }

            return false;
        }
    }

    class Builder {

        private static final int NO_ICON = 0;

        private int mIcon;
        private String mTitle;
        private PendingIntent mPendingIntent;

        public Builder(PendingIntent pendingIntent) {
            mPendingIntent = pendingIntent;
            mIcon = NO_ICON;
            mTitle = "Custom Action";
        }

        public Builder setIcon(@DrawableRes int iconResource) {
            mIcon = iconResource;
            return this;
        }

        public Builder setTitle(String title) {
            mTitle = title;
            return this;
        }

        public CustomAction build() {
            return new CustomAction() {
                @Override
                public PendingIntent getCustomActionPendingIntent() {
                    return mPendingIntent;
                }

                @Override
                public int getCustomActionIcon() {
                    return mIcon;
                }

                @Override
                public String getCustomActionTitle() {
                    return mTitle;
                }

                @Override
                public boolean isEnabled() {
                    return mPendingIntent != null;
                }

                @Override
                public boolean hasCustomIcon() {
                    return mIcon != NO_ICON;
                }
            };
        }
    }
}
