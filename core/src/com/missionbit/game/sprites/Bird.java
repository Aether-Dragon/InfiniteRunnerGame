package com.missionbit.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bird {
    public static final int BIRD = 4;
    public static final int GAP_MIN = 100;
    public static final int GAP_MAX = 300;
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;
    public static final int BIRD_WIDTH = 100;
    private Vector2 posBird;
    private Vector2 position;
    private Vector2 velocity;
    private Texture bird;
    private Rectangle bounds;
    private Animation birdAnimation;

    public Bird(int x, int y) {
        bird = new Texture("seagull.png");
        birdAnimation = new Animation(new TextureRegion(bird), 6, 0.5f);
        position = new Vector2(x, y);
        bounds = new Rectangle(position.x + 6, position.y, bird.getWidth() - 6, birdAnimation.getFrame().getRegionHeight() - 12);

        posBird = new Vector2(x, y);
        // starting x-point = i
        // ending x-point = -i
        // starting/ending y-point = j
        velocity = new Vector2(0, -100);

    }


    public void update(float dt) {
        birdAnimation.update(dt);
        if (velocity.y < 0) {
            velocity.y += 1;
        } else {
            velocity.y += 1;
        }

        if (position.y < 0) {
            velocity.y = -velocity.y;
        }
        velocity.scl(dt);
        position.add(velocity);

        velocity.scl(1/dt);

        bounds.x = position.x + 6;
        bounds.y = position.y;
    }
//    public void reposition(float x) {
//        posBird.set(x, 5);
//        bounds.setPosition(posBird.x, posBird.y);
//    }
    public boolean collides(Rectangle player) {
        return player.overlaps(bounds);
    }
    public TextureRegion getBird() {
        return birdAnimation.getFrame();
    }

//    public Vector2 getPosBird() {
//        return posBird;
//    }

    public TextureRegion getTexture() {
        return birdAnimation.getFrame();
    }

    public Vector2 getPosition() {
        return position;
    }

    public void dispose() {
        bird.dispose();
    }

    public Rectangle getBounds() {
        return bounds;
    }


}
