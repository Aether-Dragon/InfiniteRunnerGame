package com.missionbit.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class Puddle {
    public static final int PUDDLE_WIDTH = 128;

    private Texture puddle;
    private Vector2 posPuddle;
    private Rectangle bounds;
    private Animation puddleAnimation;


    public Puddle(int x) {
        puddle = new Texture("puddleAnimation.png");
        posPuddle = new Vector2(x, 5);
        puddleAnimation = new Animation(new TextureRegion(puddle), 6, 0.25f);
        bounds = new Rectangle(x + 6, posPuddle.y - 8, puddle.getWidth() - 6, puddleAnimation.getFrame().getRegionHeight());
    }


    public void update(float dt){
        puddleAnimation.update(dt);
    }

    public void reposition(float x) {
        posPuddle.set(x, 5);
        bounds.setPosition(posPuddle.x, posPuddle.y);
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(bounds);
    }

    public TextureRegion getPuddle() {
        return puddleAnimation.getFrame();
    }

    public Vector2 getPosPuddle() {
        return posPuddle;
    }

    public void dispose() { puddle.dispose(); }
}

