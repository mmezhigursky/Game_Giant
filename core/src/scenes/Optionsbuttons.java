package scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameMain;

import helpers.GameInfo;

public class Optionsbuttons {

    private GameMain game;
    private Stage stage;
    private Viewport gameViewport;

    private ImageButton easy, medium, hard, backbtn;

    private Image sign;

    public Optionsbuttons(GameMain game) {
        this.game = game;

        gameViewport = new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT,
                new OrthographicCamera());

        stage = new Stage(gameViewport, game.getBatch());

        Gdx.input.setInputProcessor(stage);
        createAndPositionButtons();
        addAllListener();

        stage.addActor(easy);
        stage.addActor(medium);
        stage.addActor(hard);
        stage.addActor(backbtn);
        stage.addActor(sign);
    }

    void createAndPositionButtons() {

        easy = new ImageButton(new SpriteDrawable(new Sprite(
                new Texture("Buttons\\2 - Options Buttons\\Easy.png"))));
        medium = new ImageButton(new SpriteDrawable(new Sprite(
                new Texture("Buttons\\2 - Options Buttons\\Medium.png"))));
        hard = new ImageButton(new SpriteDrawable(new Sprite(
                new Texture("Buttons\\2 - Options Buttons\\Hard.png"))));
        backbtn = new ImageButton(new SpriteDrawable(new Sprite(
                new Texture("Buttons\\2 - Options Buttons\\Back.png"))));
        sign = new Image(new Texture("Buttons\\2 - Options Buttons\\Check Sign.png"));

        backbtn.setPosition(70, 25, Align.bottomRight);
        medium.setPosition(GameInfo.WIDTH / 2, GameInfo.HEIGHT / 2 + 40, Align.center);
        hard.setPosition(GameInfo.WIDTH / 2, GameInfo.HEIGHT / 2 - 40, Align.center);
        easy.setPosition(GameInfo.WIDTH / 2, GameInfo.HEIGHT / 2 + 120, Align.center);

        //remove this leter
        sign.setPosition(GameInfo.WIDTH / 2 + 100, medium.getY() + 25, Align.bottomLeft);
    }

    void addAllListener() {
        backbtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenu(game));
            }
        });
        medium.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                sign.setY(medium.getY() + 20);

            }
        });
        hard.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                sign.setY(hard.getY() + 20);
            }
        });
        easy.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                sign.setY(easy.getY() + 13);
            }
        });
    }

    public Stage getStage() {
        return this.stage;
    }
}
