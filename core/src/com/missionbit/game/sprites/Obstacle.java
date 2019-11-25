package com.missionbit.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Obstacle {
    protected Vector2 position;
    protected Rectangle bounds;
    protected Texture texture;
    protected Animation animation;

    public abstract void update(float dt);

    public Rectangle getBounds() { return bounds; }

    public boolean collides(Rectangle player) {
        return player.overlaps(bounds);
    }

    public Vector2 getPosition() { return position; }

    public TextureRegion getTexture() {
        return animation.getFrame();
    }

    public void dispose() { texture.dispose(); }

}
