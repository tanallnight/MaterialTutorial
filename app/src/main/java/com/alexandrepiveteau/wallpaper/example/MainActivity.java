package com.alexandrepiveteau.wallpaper.example;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.widget.Toast;

import com.alexandrepiveteau.library.tutorial.CustomAction;
import com.alexandrepiveteau.library.tutorial.TutorialActivity;
import com.alexandrepiveteau.library.tutorial.TutorialFragment;
import com.alexandrepiveteau.library.tutorial.widgets.LinePageIndicatorEngine;
import com.alexandrepiveteau.library.tutorial.widgets.PageIndicator;

/**
 * Created by Tanmay Parikh on 7/19/2015.
 */
public class MainActivity extends TutorialActivity {

    private static final int[] BACKGROUND_COLORS = {
            Color.parseColor("#F44336"),
            Color.parseColor("#e9a83b"),
            Color.parseColor("#5b9899"),
            Color.parseColor("#265963"),
            Color.parseColor("#cbb6c5"),
            Color.parseColor("#4FC3F7"),
            Color.parseColor("#3F51B5"),
            Color.parseColor("#F44336")};


    private static final int[] TOOLBAR_BACKGROUND_COLORS = {
            Color.parseColor("#265963"),
            Color.parseColor("#5b9899"),
            Color.parseColor("#4FC3F7"),
            Color.parseColor("#e9a83b"),
            Color.parseColor("#F44336"),
            Color.parseColor("#3F51B5"),
            Color.parseColor("#F44336"),
            Color.parseColor("#cbb6c5")};


    @Override
    public int getBottomBarColor(int position) {
        return TOOLBAR_BACKGROUND_COLORS[position];
    }

    @Override
    public void finishTutorial(FINISH_TYPE finish) {
        Toast.makeText(this, "Tutorial finished " + finish.toString(), Toast.LENGTH_SHORT).show();
        super.finishTutorial(finish);
    }

    @Override
    public String getIgnoreText() {
        return "SKIP";
    }

    /**
     * Requesting an IndicatorEngine to draw dots
     * Lib comes with 6 Engines built in:
     * DefaultPageIndicatorEngine (Material Design)
     * GoogleNowLauncherIndicatorEngine (Similar to the indicators in Google Now Launcher)
     * SimplePageIndicatorEngine (Simple dot color change on page change)
     * TyzenPageIndicatorEngine (Lines that rotate on page select)
     * RingPageIndicatorEngine (Rings that color up, two animations available)
     * LinePageIndicatorEngine (Line that stretches like progress or line can be constant size and move around)
     *
     * @return PageIndicatorEngine class which draws the indicator dots
     */
    @Override
    public PageIndicator.Engine getPageIndicatorEngine() {
        return new LinePageIndicatorEngine();
    }

    @Override
    public int getCount() {
        return BACKGROUND_COLORS.length;
    }

    @Override
    public int getBackgroundColor(int position) {
        return BACKGROUND_COLORS[position];
    }

    @Override
    public int getNavigationBarColor(int position) {
        return TOOLBAR_BACKGROUND_COLORS[position];
    }

    @Override
    public int getStatusBarColor(int position) {
        return BACKGROUND_COLORS[position];
    }

    @Override
    public Fragment getTutorialFragmentFor(int position) {
        switch (position) {
            case 3:
            case 4:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.github.com/cadialex/MaterialTutorial"));
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
                return new TutorialFragment.Builder()
                        .setTitle("Title")
                        .setDescription("Desc")
                        .setTitleGravity(Gravity.LEFT)
                        .setDescriptionGravity(Gravity.RIGHT)
                        .setDescriptionColor(Color.parseColor("#000000"))
                        .setImageResourceBackground(R.drawable.device)
                        .setImageResourceForeground(R.mipmap.ic_launcher)
                        .setCustomAction(
                                new CustomAction.Builder(pendingIntent)
                                        .setTitle("GitHub")
                                        //.setIcon(R.drawable.ic_open_in_browser)
                                        .build())
                        .build();
            default:
                return new TutorialFragment.Builder()
                        .setTitle("Title")
                        .setDescription("1) test<br>2) test<br>3) test<br>4) test")
                        .setImageResourceBackground(R.drawable.device)
                        .setTitleColor(Color.parseColor("#000000"))
                        .setImageResourceForeground(R.mipmap.ic_launcher)
                        .build();
        }
    }

    @Override
    public boolean isNavigationBarColored() {
        return true;
    }

    @Override
    public boolean isStatusBarColored() {
        return true;
    }

    @Override
    public ViewPager.PageTransformer getPageTransformer() {
        return TutorialFragment.getParallaxPageTransformer(1.25f);
    }
}
