package com.mygdx.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class MyInputListener extends InputListener {
    @Override
    public boolean keyDown(InputEvent event, int keycode) {
        switch (keycode) {
            case Input.Keys.W : {
                System.out.println("W 键被按下！");
                break;
            }
            case Input.Keys.S: {
                System.out.println("S 键被按下!");
                break;
            }
            default: {
                System.out.println("其他按键被按下");
                break;
            }
        }
        return false;
    }
}
