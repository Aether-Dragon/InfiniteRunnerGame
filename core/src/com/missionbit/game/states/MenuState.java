package com.missionbit.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuState extends State {
    private Texture background;
    private Texture playBtn;
    private Texture playText;


    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("city.png");
        playBtn = new Texture("playBtn.png");
        playText = new Texture("play.png");




    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        cam.update();
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0,0, 265, 175);
        sb.draw(playBtn, (cam.position.x - playBtn.getWidth() / 2), cam.position.y);
        sb.draw(playText, (cam.position.x - playBtn.getWidth() / 2) - 3, (cam.position.y + playBtn.getHeight() / 2 + 25));
        sb.end();


    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        playText.dispose();
        System.out.println("Menu State Disposed");

    }
}
