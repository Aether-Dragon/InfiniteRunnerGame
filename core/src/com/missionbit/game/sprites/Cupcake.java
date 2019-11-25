package com.missionbit.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Cupcake {

    public final static int GRAVITY = -7;
    public final static int MOVEMENT = 100;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Animation cupcakeAnimation;
    private Texture texture;
    private int jumps;

    public Cupcake(int x, int y ) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        texture = new Texture("cupcake.png");
        cupcakeAnimation = new Animation(new TextureRegion(texture), 6, 0.5f);
        bounds = new Rectangle(x + 8, y, texture.getWidth() - 4, texture.getHeight() / 6);
        jumps = 0;

    }

    public void update(float dt) {
        cupcakeAnimation.update(dt);

        if(position.y > 20) {
            velocity.add(0, GRAVITY, 0);
        }
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y, 0);
        if(position.y < 0) {
            position.y = 0;
        }

        velocity.scl(1/dt);

        bounds.setPosition(position.x + 3, position.y);

        if (position.y == 0) {
            jumps = 0;
        }

    }

    public Vector3 getPosition() { return position; }

    public TextureRegion getTexture() {
        return cupcakeAnimation.getFrame();

    }

    public void jump() {
        if (jumps < 2) {
            velocity.y = 210;
            jumps += 1;
        }
    }

    public Rectangle getBounds() { return bounds; }

    public void dispose() {
        texture.dispose();
    }

}
