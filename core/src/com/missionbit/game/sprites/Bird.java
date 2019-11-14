package com.missionbit.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.missionbit.game.InfiniteRunner;

public class Bird {
    public static final int BIRD = 4;
    public static final int GAP_MIN = 100;
    public static final int GAP_MAX = 300;
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;
    private Vector2 start;
    private Vector2 position;
    private Vector2 velocity;
    private Texture texture;
    private Rectangle bounds;
    private Animation birdAnimation;

    public Bird(int x, int y) {
        texture = new Texture("seagull.png");
        birdAnimation = new Animation(new TextureRegion(texture), 6, 0.5f);
        bounds = new Rectangle(x, y, birdAnimation.getFrame().getRegionHeight(), texture.getHeight());
        position = new Vector2(x, y);
        start = new Vector2(x, y);
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
//        if (position.x != -start.x) {
//            position.rotateAround(new Vector2(start.x - InfiniteRunner.WIDTH / 2, InfiniteRunner.HEIGHT), -0.3f);
//        }
        velocity.scl(1/dt);
    }


    public TextureRegion getTexture() {
        return birdAnimation.getFrame();
    }

    public Vector2 getPosition() {
        return position;
    }


}
