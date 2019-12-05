package com.missionbit.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.missionbit.game.InfiniteRunner;

public class RestartState extends State {
    private Texture background;
    private Texture playBtn;
    private Texture gameOver;
    public static int scoreTime = 0;
    public static int highScore = 0;
    private GlyphLayout scoreLayout;
    private GlyphLayout highScoreLayout;
    private String finalScore;
    private String highScoreText;
    BitmapFont score;
    BitmapFont highScoreFont;
    private Sound sound;

    public RestartState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, InfiniteRunner.WIDTH / 2, InfiniteRunner.HEIGHT / 2);
        background = new Texture("city.png");
        playBtn = new Texture("playBtn.png");
        gameOver = new Texture("gameover.png");

        if (scoreTime > highScore) {
            highScore = scoreTime;
        }

        score = new BitmapFont();
        highScoreFont = new BitmapFont();
        scoreLayout = new GlyphLayout();
        highScoreLayout = new GlyphLayout();
        finalScore = "You survived for " + scoreTime + " seconds.";
        highScoreText = "High Score: " + highScore + " seconds";
        score.setColor(Color.WHITE);
        highScoreFont.setColor(Color.WHITE);
        scoreLayout.setText(score, finalScore);
        highScoreLayout.setText(highScoreFont, highScoreText);

        sound = Gdx.audio.newSound(Gdx.files.internal("gameover.ogg"));
        sound.play();
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
        score.draw(sb, scoreLayout, cam.position.x - scoreLayout.width / 2, cam.position.y + (playBtn.getHeight() / 2 + 55));
        highScoreFont.draw(sb, highScoreLayout, cam.position.x - highScoreLayout.width / 2, cam.position.y + (playBtn.getHeight() / 2 + 35));
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        gameOver.dispose();
        score.dispose();
        highScoreFont.dispose();
        sound.dispose();
    }
}