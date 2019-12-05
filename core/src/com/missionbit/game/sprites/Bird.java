package com.missionbit.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bird extends Obstacle {
    private Vector2 velocity;

    public Bird(int x, int y) {
        texture = new Texture("seagull.png");
        animation = new Animation(new TextureRegion(texture), 6, 0.5f);
        position = new Vector2(x, y);
        bounds = new Rectangle(position.x + 6, position.y, texture.getWidth() - 6, animation.getFrame().getRegionHeight() - 12);

        // starting x-point = i
        // ending x-point = -i
        // starting/ending y-point = j
        velocity = new Vector2(0, -100);

    }


    public void update(float dt) {
        animation.update(dt);
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
}
