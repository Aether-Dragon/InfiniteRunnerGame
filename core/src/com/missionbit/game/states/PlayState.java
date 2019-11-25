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
import com.missionbit.game.sprites.Obstacle;
import com.missionbit.game.sprites.Puddle;

import java.util.Random;


public class PlayState extends State {
    public static final int GAP_MIN = 1;
    public static final int GAP_MAX = 3;
    private static final int PUDDLE_COUNT = 4;
    public static final int BIRD_COUNT = 4;
    private Cupcake cupcake;
    private Texture bg;
    //private Bird bird;
    //TODO: fix puddle spacing
    private Queue<Bird> birds;
    private Queue<Puddle> puddles;
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
        birds = new Queue<>();
        puddles = new Queue<>();
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

//        for (Puddle puddle : puddles) {
//            sb.draw(puddle.getTexture(), puddle.getPosition().x, puddle.getPosition().y);
//        }
//
//        for (Bird bird : birds) {
//            sb.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
        // THIS IS ONLY FOR TESTING BOUNDARIES
//            sb.end();
//            ShapeRenderer r = new ShapeRenderer();
//            r.setProjectionMatrix(cam.combined);
//            r.setColor(Color.RED);
//            r.begin(ShapeRenderer.ShapeType.Line);
//            r.rect(bird.getBounds().x, bird.getBounds().y, bird.getBounds().width, bird.getBounds().height);
//            r.end();
//            sb.begin();
//        }


        for (Obstacle o : obstacles) {
            sb.draw(o.getTexture(), o.getPosition().x, o.getPosition().y);
        }
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        cupcake.dispose();

        for (Obstacle o : obstacles) {
            o.dispose();
        }

        System.out.println("Play State Disposed");

    }

}
