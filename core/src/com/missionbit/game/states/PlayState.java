package com.missionbit.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.missionbit.game.InfiniteRunner;
import com.missionbit.game.sprites.Bird;
import com.missionbit.game.sprites.Cupcake;
import com.missionbit.game.sprites.Puddle;

import java.util.Random;


public class PlayState extends State {
    public static final int GAP_MIN = 100;
    public static final int GAP_MAX = 300;
    private static final int PUDDLE_COUNT = 4;
    public static final int BIRD_COUNT = 4;
    private Cupcake cupcake;
    private Texture bg;
    //private Bird bird;
    //TODO: fix puddle spacing
    private Array<Puddle> puddles;
    private Random rand;
    private Array<Bird> birds;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        cupcake = new Cupcake(50, 0);
        bg = new Texture("bg.png");
        birds = new Array<>();
        puddles = new Array<>();
        rand = new Random();

        for(int i = 1; i <= PUDDLE_COUNT; i++){
            if(i==1) {
                puddles.add(new Puddle(i * (100 + Puddle.PUDDLE_WIDTH)));

            } else {
                puddles.add(new Puddle(i * ((rand.nextInt(GAP_MAX) + GAP_MIN) + Puddle.PUDDLE_WIDTH)));

            }
        }

        for(int i = 1; i <= BIRD_COUNT; i++){
            birds.add(new Bird(i * ((rand.nextInt(GAP_MAX) + GAP_MIN) + Bird.BIRD_WIDTH), 100));
        }

    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())
            cupcake.jump();
    }

    @Override
    public void update(float dt) {
        handleInput();
        cupcake.update(dt);
        for (Bird b: birds)
        {
            b.update(dt);
        }

        cam.position.x = cupcake.getPosition().x + 80;

        for(int i = 0; i < puddles.size; i++){
            Puddle puddle = puddles.get(i);

            if(cam.position.x - (cam.viewportWidth / 2) > puddle.getPosPuddle().x + Puddle.PUDDLE_WIDTH){
                puddle.reposition(puddle.getPosPuddle().x + ((rand.nextInt(GAP_MAX) + GAP_MIN) + Puddle.PUDDLE_WIDTH) * PUDDLE_COUNT);
            }

            if(puddle.collides(cupcake.getBounds()))
                gsm.set(new RestartState(gsm));
        }

        for(int i = 0; i < birds.size; i++){
            Bird bird = birds.get(i);

            if(cam.position.x - (cam.viewportWidth / 2) > birds.get(i).getPosBird().x + bird.BIRD_WIDTH){
                birds.get(i).reposition(birds.get(i).getPosBird().x + ((rand.nextInt(GAP_MAX) + GAP_MIN) + bird.BIRD_WIDTH) * BIRD_COUNT);
            }

            if(birds.get(i).collides(cupcake.getBounds()))
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
        for(Puddle puddle : puddles) {
            sb.draw(puddle.getPuddle(), puddle.getPosPuddle().x, puddle.getPosPuddle().y);
        }

        for(Bird bird : birds) {
            sb.draw(bird.getBird(), bird.getPosBird().x, bird.getPosBird().y);
        }

        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        cupcake.dispose();

        for(Bird bird : birds) {
            bird.dispose();
        }


        for(Puddle puddle : puddles) {
            puddle.dispose();
        }

        System.out.println("Play State Disposed");

    }

}
