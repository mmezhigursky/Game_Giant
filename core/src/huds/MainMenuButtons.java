package huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameMain;

import javax.swing.ImageIcon;

import helpers.GameInfo;
import scenes.GamePlay;
import scenes.Highscore;
import scenes.Options;

public class MainMenuButtons {

    private GameMain game;
    private Stage stage;
    private Viewport gameViewport;

    private ImageButton playBtn;
    private ImageButton highscoreBtn;
    private ImageButton optionsBtn;
    private ImageButton quitBtn;
    private ImageButton musicBtn;

    public MainMenuButtons(GameMain game){
        this.game =game;

        gameViewport = new FillViewport(GameInfo.WIDTH, GameInfo.HEIGHT,
                new OrthographicCamera());

        stage = new Stage(gameViewport, game.getBatch());

        Gdx.input.setInputProcessor(stage);

        createAndPositionButons();

        addAllListener();



        stage.addActor(playBtn);

        stage.addActor(highscoreBtn);

        stage.addActor(optionsBtn);

        stage.addActor(quitBtn);

        stage.addActor(musicBtn);
    }

    void createAndPositionButons(){

        playBtn = new ImageButton(new SpriteDrawable(new Sprite(
                new Texture("Buttons\\3 - Main Menu Buttons\\Start Game.png")
        )));

        highscoreBtn = new ImageButton(new SpriteDrawable(new Sprite(
                new Texture("Buttons\\3 - Main Menu Buttons\\Highscore.png")
        )));

        optionsBtn = new ImageButton(new SpriteDrawable(new Sprite(
                new Texture("Buttons\\3 - Main Menu Buttons\\Options.png")
        )));

        quitBtn = new ImageButton(new SpriteDrawable(new Sprite(
                new Texture("Buttons\\3 - Main Menu Buttons\\Quit.png")
        )));

        musicBtn = new ImageButton(new SpriteDrawable(new Sprite(
                new Texture("Buttons\\3 - Main Menu Buttons\\Music On.png")
        )));

        playBtn.setPosition(GameInfo.WIDTH/2f - 80, GameInfo.HEIGHT/2f+50,
                Align.center);

        highscoreBtn.setPosition(GameInfo.WIDTH/2f - 80, GameInfo.HEIGHT/2f-20,
                Align.center);

        optionsBtn.setPosition(GameInfo.WIDTH/2f - 40, GameInfo.HEIGHT/2f-90,
                Align.center);

        quitBtn.setPosition(GameInfo.WIDTH/2f - 20, GameInfo.HEIGHT/2f-160,
                Align.center);

        musicBtn.setPosition(GameInfo.WIDTH/2f - 13, 13, Align.bottomRight);

    }

    void addAllListener(){
        playBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
               game.setScreen(new GamePlay(game));
            }
        });

        highscoreBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new Highscore(game));
            }
        });

        optionsBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new Options(game));
            }
        });

        quitBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });

        musicBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("The music was pressed");
            }
        });
    }

    public Stage getStage(){
        return this.stage;
    }
}
