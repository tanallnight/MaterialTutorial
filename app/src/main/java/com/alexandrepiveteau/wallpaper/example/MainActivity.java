package com.alexandrepiveteau.wallpaper.example;

import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.alexandrepiveteau.library.tutorial.CustomAction;
import com.alexandrepiveteau.library.tutorial.TutorialActivity;
import com.alexandrepiveteau.library.tutorial.TutorialFragment;
import com.alexandrepiveteau.library.tutorial.widgets.DefaultPageIndicatorEngine;
import com.alexandrepiveteau.library.tutorial.widgets.PageIndicator;
import com.alexandrepiveteau.library.tutorial.widgets.SimplePageIndicatorEngine;
import com.alexandrepiveteau.library.tutorial.widgets.TyzenPageIndicatorEngine;


public class MainActivity extends TutorialActivity {

    @Override
    public String getIgnoreText() {
        return "Ignore";
    }

    @Override
    public PageIndicator.Engine getPageIndicatorEngine() {
        return new TyzenPageIndicatorEngine();
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public int getBackgroundColor(int position) {
        return Color.BLUE;
    }

    @Override
    public int getNavigationBarColor(int position) {
        return Color.BLUE;
    }

    @Override
    public int getStatusBarColor(int position) {
        return Color.BLUE;
    }

    @Override
    public Fragment getTutorialFragmentFor(int position) {
        switch (position) {
            case 3:
                return new TutorialFragment.Builder()
                        .setTitle("Title")
                        .setDescription("Desc")
                        .setImageResource(R.mipmap.ic_launcher)
                        .setCustomAction(
                                new CustomAction.Builder(Uri.parse("http://www.google.com"))
                                        .setTitle("Google")
                                        .setIcon(R.mipmap.ic_launcher)
                                        .build())
                        .build();
            default:
                return new TutorialFragment.Builder()
                        .setTitle("Title")
                        .setDescription("Desc")
                        .setImageResource(R.mipmap.ic_launcher)
                        .build();
        }
    }

    @Override
    public boolean isNavigationBarColored() {
        return false;
    }

    @Override
    public boolean isStatusBarColored() {
        return false;
    }

    @Override
    public ViewPager.PageTransformer getPageTransformer() {
        return TutorialFragment.getParallaxPageTransformer();
    }
}
