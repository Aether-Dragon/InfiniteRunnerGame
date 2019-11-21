package com.missionbit.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.missionbit.game.InfiniteRunner;

public class RestartState extends State {
    private Texture background;
    private Texture playBtn;
    private Texture gameOver;

    public RestartState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, InfiniteRunner.WIDTH / 2, InfiniteRunner.HEIGHT / 2);
        background = new Texture("city.png");
        playBtn = new Texture("playBtn.png");
        gameOver = new Texture("gameover.png");
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0,0, 265, 175);
        sb.draw(playBtn, cam.position.x - playBtn.getWidth() / 2, cam.position.y);
        sb.draw(gameOver, cam.position.x - gameOver.getWidth() / 2, cam.position.y - (playBtn.getHeight() / 2 + 50) );
        sb.end();
    }

    @Override
    public void dispose() {

    }
}