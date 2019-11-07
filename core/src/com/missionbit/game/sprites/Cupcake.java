package com.missionbit.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Cupcake {
    public final static int GRAVITY = -10;
    public final static int MOVEMENT = 50;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Animation cupcakeAnimation;
    private Texture texture;

    public Cupcake(int x, int y ) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        texture = new Texture("cupcakeholder.png");
        //cupcakeAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
        bounds = new Rectangle(x, y, texture.getWidth() / 3, texture.getHeight());

    }

    public void update(float dt) {
        //cupcakeAnimation.update(dt);

        if(position.y > 20) {
            velocity.add(0, GRAVITY, 0);
        }
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y, 0);
        if(position.y < 0) {
            position.y = 0;
        }

        velocity.scl(1/dt);

        bounds.setPosition(position.x, position.y);

    }

    public Vector3 getPosition() { return position; }

    public Texture getTexture() {
        //return cupcakeAnimation.getFrame();
        return texture;
    }

    public void jump() {
        if (position.y == 0) {
            velocity.y = 200;
        }
    }

    public Rectangle getBounds() { return bounds; }

    public void dispose() {
        texture.dispose();
    }

}
