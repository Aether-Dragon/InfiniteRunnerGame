package com.missionbit.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Puddle {
    public static final int PUDDLES = 4;
    public static final int GAP_MIN = 100;
    public static final int GAP_MAX = 300;
    private Texture texture;
    private Rectangle bounds ;
    private Animation puddleAnimation;

    public Puddle(int x, int y) {
        texture = new Texture("puddleAnimation.png");
        puddleAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
        bounds = new Rectangle(x, y, puddleAnimation.getFrame().getRegionHeight(), texture.getHeight());
    }


    public void update(float dt){
        puddleAnimation.update(dt);
    }


    public TextureRegion getTexture() {
        return puddleAnimation.getFrame();
    }


}

