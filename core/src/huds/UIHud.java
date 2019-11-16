package huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameMain;

import helpers.GameInfo;
import scenes.MainMenu;

public class UIHud {

    private GameMain game;
    private Stage stage;
    private Viewport gameViewport;

    private Image coinImg, lifeImg, scoreImg,pausePanel;

    private Label coinLabel, lifeLabel, scoreLabel;

    private ImageButton  pausebtn, resumeBtn,quitBtn;

    public UIHud(GameMain game){
        this.game = game;

        gameViewport = new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT, new OrthographicCamera());

        stage = new Stage(gameViewport, game.getBatch());

        Gdx.input.setInputProcessor(stage);

        createLabel();
        creayeImages();
        createListener();

        Table lifeAndCoinTable = new Table();
        lifeAndCoinTable.top().left();
        lifeAndCoinTable.setFillParent(true);

        lifeAndCoinTable.add(lifeImg).padLeft(20).padTop(15);
        lifeAndCoinTable.add(lifeLabel).padLeft(5);
        lifeAndCoinTable.row();

        lifeAndCoinTable.add(coinImg).padLeft(20).padTop(15);
        lifeAndCoinTable.add(coinLabel).padLeft(5);

        Table scoreTable = new Table();
        scoreTable.top().right();
        scoreTable.setFillParent(true);

        scoreTable.add(scoreImg).padLeft(20).padTop(15);
        scoreTable.row();
        scoreTable.add(scoreLabel).padLeft(20).padTop(25);

        stage.addActor(lifeAndCoinTable);
        stage.addActor(scoreTable);
        stage.addActor(pausebtn);


    }
    void createLabel(){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(
                Gdx.files.internal("Fonts\\blow.ttf")
        );
        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;

        BitmapFont font = generator.generateFont(parameter);

        coinLabel = new Label("x0",
                new Label.LabelStyle(font, Color.WHITE));
        lifeLabel = new Label("x2",
                new Label.LabelStyle(font, Color.WHITE));

        scoreLabel =new Label("x100",
                new Label.LabelStyle(font, Color.WHITE));

    }

    void creayeImages(){
        coinImg = new Image(new Texture("Collectables\\Coin.png"));
        lifeImg = new Image(new Texture("Collectables\\Life.png"));
        scoreImg = new Image(new Texture("Buttons\\1 - Gameplay Buttons\\Score.png"));
    }

    void createListener(){
        pausebtn = new ImageButton(new SpriteDrawable(new Sprite(
                new Texture("Buttons\\1 - Gameplay Buttons\\Pause.png")
        )));
        pausebtn.setPosition(420, 50, Align.bottomLeft);

        pausebtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                CreatePausePanel();

            }
        });
    }

    void CreatePausePanel(){
        pausePanel = new Image(new Texture("Buttons\\Pause\\Pause Panel.png"));

        resumeBtn = new ImageButton(new SpriteDrawable(new Sprite(
                new Texture("Buttons\\Pause\\Resume.png"))));

        quitBtn = new ImageButton(new SpriteDrawable(new Sprite(
                new Texture("Buttons\\Pause\\Quit 2.png"))));
        pausePanel.setPosition(GameInfo.WIDTH/2f, GameInfo.HEIGHT/2f, Align.center);
        resumeBtn.setPosition(GameInfo.WIDTH/2f, GameInfo.HEIGHT/2f -50, Align.center);
        quitBtn.setPosition(GameInfo.WIDTH/2f, GameInfo.HEIGHT/2f +50, Align.center);

        resumeBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                removePauseBtn();

            }
        });

        quitBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenu(game));
            }
        });

        stage.addActor(pausePanel);
        stage.addActor(resumeBtn);
        stage.addActor(quitBtn);


    }
    void removePauseBtn(){
        pausePanel.remove();
        resumeBtn.remove();
        quitBtn.remove();
    }
    public Stage getStage(){
        return this.stage;
    }



}






























