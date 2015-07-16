package com.alexandrepiveteau.library.tutorial;

/**
 * Created by Alexandre on 24.04.2015.
 */
public class ColorMixer {

    private int mFirstColor;
    private int mSecondColor;

    /**
     * A convenience method to get a mixed color.
     * @param transitionProgression The progression of the mix.
     * @return The mixed color.
     */
    public int getMixedColor(float transitionProgression) {
        if (transitionProgression > 1f) {
            transitionProgression = 1f;
        } else if (transitionProgression < 0f) {
            transitionProgression = 0f;
        }
        float iRatio = 1.0f - transitionProgression;

        int aA = (mFirstColor >> 24 & 0xff);
        int aR = ((mFirstColor & 0xff0000) >> 16);
        int aG = ((mFirstColor & 0xff00) >> 8);
        int aB = (mFirstColor & 0xff);

        int bA = (mSecondColor >> 24 & 0xff);
        int bR = ((mSecondColor & 0xff0000) >> 16);
        int bG = ((mSecondColor & 0xff00) >> 8);
        int bB = (mSecondColor & 0xff);

        int A = (int) ((aA * iRatio) + (bA * transitionProgression));
        int R = (int) ((aR * iRatio) + (bR * transitionProgression));
        int G = (int) ((aG * iRatio) + (bG * transitionProgression));
        int B = (int) ((aB * iRatio) + (bB * transitionProgression));

        return A << 24 | R << 16 | G << 8 | B;
    }

    public void setFirstColor(int firstColor) {
        mFirstColor = firstColor;
    }

    public void setSecondColor(int secondColor) {
        mSecondColor = secondColor;
    }
}
