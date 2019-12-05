package com.missionbit.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Queue;
import com.missionbit.game.InfiniteRunner;
import com.missionbit.game.sprites.Bird;
import com.missionbit.game.sprites.Cupcake;
import com.missionbit.game.sprites.Health;
import com.missionbit.game.sprites.Obstacle;
import com.missionbit.game.sprites.Puddle;

import java.util.Random;


public class PlayState extends State {
    private Cupcake cupcake;
    private Texture bg;
    private Random rand;
    private float spawn;
    private int life;
    private int type;
    private Health healthbar;
    private Queue<Obstacle> obstacles;
    private float spawnTimer;
    private float invincibilityTimer;
    private boolean isInvincible;
    private float secondTimer;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        cupcake = new Cupcake(50, 0);
        bg = new Texture("city.png");
        spawn = 0;
        rand = new Random();
        life = 3;
        healthbar = new Health(50, 0);
        spawn = 4.5f;
        spawnTimer = 0;
        rand = new Random();
        life = 3;
        obstacles = new Queue<>();
        invincibilityTimer = 0f;
        isInvincible = true;
        secondTimer = 0;
        RestartState.scoreTime = 0;
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched())
            cupcake.jump();
    }

    @Override
    public void update(float dt) {
        handleInput();
        cupcake.update(dt);
        healthbar.update(dt);

        spawnTimer = rand.nextInt(3) + 2.5f;
        spawnObstacles(dt);

        updateObstacles(dt);

        if (isInvincible) {
            invincibilityTimer -= dt;
            if (invincibilityTimer <= 0) {
                invincibilityTimer = 0;
                isInvincible = false;
            }
        }

        updateHealth();

        secondTimer += dt;
        if(secondTimer >= 1f) {
            RestartState.scoreTime += 1;
            secondTimer = 0;
        }

        cam.position.x = cupcake.getPosition().x + 80;

        if (life == 0) {
            gsm.set(new RestartState(gsm));

        }
        cam.update();

    }

    private void updateObstacles(float dt) {
        for (Obstacle o : obstacles) {
            o.update(dt);
            if (o.getPosition().x < cam.position.x - cam.viewportWidth / 2 - o.getTexture().getRegionWidth()) {
                obstacles.removeFirst().dispose();
            }
            if (o.collides(cupcake.getBounds())) {
                if (!isInvincible) {
                    setInvincible();
                    if (life > 0) {
                        life = life - 1;

                    }
                }
            }
        }
    }

    private void spawnObstacles(float dt) {
        if (spawn > spawnTimer) {
            spawn = 0;
            type = rand.nextInt(3);
            switch (type) {
                case 0:
                    obstacles.addLast(new Bird((int) (cam.position.x + cam.viewportWidth / 2), 100));
                    break;
                case 1:
                    obstacles.addLast(new Puddle((int) (cam.position.x + cam.viewportWidth / 2)));
                    break;
                case 2:
                    obstacles.addLast(new Bird((int) (cam.position.x + cam.viewportWidth / 2), 100));
                    obstacles.addLast(new Puddle((int) (cam.position.x + cam.viewportWidth / 2)));
                    break;
            }
        } else {
            spawn += dt;
        }
    }
  
    private void updateHealth() {
        switch (life) {
            case 3:
                healthbar.setTexture("life_4.png");
                break;
            case 2:
                healthbar.setTexture("life_3.png");
                break;
            case 1:
                healthbar.setTexture("life_2.png");
                break;
            case 0:
                healthbar.setTexture("life_1.png");
                break;
        }
    }

    private void setInvincible() {
            isInvincible = true;
            invincibilityTimer = 2.5f;
    }


    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);

        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0, 265, 175);


        sb.draw(cupcake.getTexture(), cupcake.getPosition().x, cupcake.getPosition().y);
        sb.draw(healthbar.getHealth(), cam.position.x - (cam.viewportWidth / 2), 143);

        for (Obstacle o : obstacles) {
            sb.draw(o.getTexture(), o.getPosition().x, o.getPosition().y);
        }

        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        cupcake.dispose();
        healthbar.dispose();

        for (Obstacle o : obstacles) {
            o.dispose();
        }

        System.out.println("Play State Disposed");

    }

}
