package com.missionbit.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Health {

    private Vector3 position;
    private Texture health;

    public Health(int x, int y) {
        position = new Vector3(x, y, 0);
        health = new Texture("whole_life.png");
    }


    public Vector3 getPosition() {return position; }


    public Texture getHealth() {
        return  health;
    }

    public void update(float dt) {

    }
    public void dispose()
    {
        health.dispose();
    }
}
