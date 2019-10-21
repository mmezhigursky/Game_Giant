package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.GameMain;

import helpers.GameInfo;
import player.Player;


public class MainMenu implements Screen {

    private GameMain game;

    private Texture bg;

    private Player player;

    private World world;

    public MainMenu(GameMain game){
        this.game = game;

        world = new World(new Vector2(0,-9.8f), true);

        bg= new Texture("Game BG.png");

        player = new Player(world,"Player 1.png", GameInfo.WIDTH/2, GameInfo.HEIGHT/2);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        player.updatePlayer();


        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();

        game.getBatch().draw(bg,0,0);
        game.getBatch().draw(player, player.getX(), player.getY());
        game.getBatch().end();

        world.step(Gdx.graphics.getDeltaTime(),6,2);



    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        bg.dispose();
        player.getTexture().dispose();

    }
}
