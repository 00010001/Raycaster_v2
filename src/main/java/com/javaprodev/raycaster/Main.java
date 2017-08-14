package com.javaprodev.raycaster;

import org.newdawn.slick.*;

/**
 * Created by X on 8/12/2017.
 */
public class Main {
    public static void main(String[] args) {
        BasicGame game = new Raycaster("Title");

        AppGameContainer gameContainer = null;
        try {
            gameContainer = new AppGameContainer(game);
            //gameContainer.setDisplayMode(320,200,false);
            gameContainer.setDisplayMode(640,480,false);
            gameContainer.setTargetFrameRate(60);
            gameContainer.setShowFPS(true);
            gameContainer.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }

    }

}
