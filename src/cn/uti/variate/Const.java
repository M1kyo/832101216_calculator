package cn.uti.variate;

import java.awt.*;

public class Const {
    public static final int FRAME_W = 350;
    public static final int FRAME_H = 500;
    //get the length of screen
    public static final int SCREEN_W = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static final int SCREEN_H = Toolkit.getDefaultToolkit().getScreenSize().height;
    //set the window on the center of a screen
    public static final int FRAME_X = (SCREEN_W - FRAME_W) / 2;
    public static final int FRAME_Y = (SCREEN_H - FRAME_H) / 2;

    public static final String TITLE = "my calculator";
}
