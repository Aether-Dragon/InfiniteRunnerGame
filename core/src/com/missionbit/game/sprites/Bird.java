package com.missionbit.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {
    public static final int BIRD = 4;
    public static final int GAP_MIN = 100;
    public static final int GAP_MAX = 300;
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;
    private Vector3 start;
    private Vector3 position;
    private Vector3 velocity;
    private Texture texture;
    private Rectangle bounds;
    private Animation birdAnimation;

    public Bird(int x, int y) {
        texture = new Texture("birdAnimation.png");
        birdAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
        bounds = new Rectangle(x, y, birdAnimation.getFrame().getRegionHeight(), texture.getHeight());
        position = new Vector3(x, y, 0);
        start = new Vector3(x, y, 0);
        // starting x-point = i
        // ending x-point = -i
        // starting/ending y-point = j

        velocity = new Vector3(-5, 0, 0);
    }


    public void update(float dt) {
        birdAnimation.update(dt);
        velocity.scl(dt);
        position.add(velocity);
        if (position.x != -start.x) {
            position.y = (float) Math.pow(position.x, 2);
        }
        velocity.scl(1/dt);
    }


    public TextureRegion getTexture() {
        return birdAnimation.getFrame();
    }


}
