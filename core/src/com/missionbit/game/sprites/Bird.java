package com.missionbit.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Bird {
    public static final int PUDDLES = 4;
    public static final int GAP_MIN = 100;
    public static final int GAP_MAX = 300;
    private Texture texture;
    private Rectangle bounds;
    private Animation birdAnimation;

    public Bird(int x, int y) {
        texture = new Texture("birdAnimation.png");
        birdAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
        bounds = new Rectangle(x, y, birdAnimation.getFrame().getRegionHeight(), texture.getHeight());
    }

    public void update(float dt) {
        birdAnimation.update(dt);
    }


    public TextureRegion getTexture() {
        return birdAnimation.getFrame();
    }

}
