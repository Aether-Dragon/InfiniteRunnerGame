package com.missionbit.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
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
    public static final int GAP_MIN = 100;
    public static final int GAP_MAX = 300;
    private static final int PUDDLE_COUNT = 4;
    public static final int BIRD_COUNT = 4;
    private Cupcake cupcake;
    private Texture bg;
    private Random rand;
    private float spawn;
    private int life;
    private Health healthbar;
    private Queue<Obstacle> obstacles;
    private Random rand;
    private float spawn;
    private float spawnTimer;
    private int type;
    private int life;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        cupcake = new Cupcake(50, 0);
        bg = new Texture("bg.png");
        spawn = 0;
        rand = new Random();
        life = 3;
        healthbar = new Health(50, 0);
        spawn = 4.5f;
        spawnTimer = 0;
        rand = new Random();
        life = 3;
        obstacles = new Queue<>();
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

        if (spawn > 10) {
            spawn = 0;
            birds.addLast(new Bird((int) (cam.position.x + cam.viewportWidth / 2), 100));
        } else {
            spawn += dt;
        }

        for (Bird b : birds) {
            b.update(dt);
            if (b.getPosition().x < cam.position.x - cam.viewportWidth / 2 - b.getTexture().getRegionWidth()) {
                birds.removeFirst().dispose();
            }
        }

        cam.position.x = cupcake.getPosition().x + 80;

        for (int i = 0; i < puddles.size; i++) {
            Puddle puddle = puddles.get(i);

//            if (cam.position.x - (cam.viewportWidth / 2) > puddle.getPosPuddle().x + Puddle.PUDDLE_WIDTH) {
//                puddle.reposition(puddle.getPosPuddle().x + ((rand.nextInt(GAP_MAX) + GAP_MIN) + Puddle.PUDDLE_WIDTH) * PUDDLE_COUNT);
//            }

            if (puddle.collides(cupcake.getBounds()))
                gsm.set(new RestartState(gsm));
        }

        spawnTimer = rand.nextInt(3) + 2.5f;
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

        for (Obstacle o : obstacles) {
            o.update(dt);
            if (o.getPosition().x < cam.position.x - cam.viewportWidth / 2 - o.getTexture().getRegionWidth()) {
                obstacles.removeFirst().dispose();
            }
            if (o.collides(cupcake.getBounds())) {
                if (life > 0) {
                    life = life - 1;
                }
            }
        }

        cam.position.x = cupcake.getPosition().x + 80;

        if (life == 0) {
            gsm.set(new RestartState(gsm));

        }
        cam.update();

    }


    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);

        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);

        sb.draw(cupcake.getTexture(), cupcake.getPosition().x, cupcake.getPosition().y);
        sb.draw(healthbar.getHealth(), cam.position.x - (cam.viewportWidth / 2) , 143);
        
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
