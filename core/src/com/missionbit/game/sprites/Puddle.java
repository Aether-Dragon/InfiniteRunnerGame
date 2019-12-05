package com.missionbit.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class Puddle extends Obstacle {

    public Puddle(int x) {
        texture = new Texture("puddleAnimation.png");
        position = new Vector2(x, 0);
        animation = new Animation(new TextureRegion(texture), 6, 0.25f);
        bounds = new Rectangle(x + 6, position.y - 8, texture.getWidth() - 6, animation.getFrame().getRegionHeight());
    }


    public void update(float dt){
        animation.update(dt);
    }


}

