package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameMain;

import clouds.Cloud;
import clouds.CloudsController;
import helpers.GameInfo;
import huds.UIHud;
import player.Player;

public class GamePlay implements Screen {

    private GameMain game;

    private OrthographicCamera mainCamera;

    private Viewport gameViewport;

    private OrthographicCamera box2DCamera;

    private Box2DDebugRenderer debugRenderer;

    private World world;

    private Sprite [] bgs;

    private float lastYPosition;

    private UIHud hud;

    private CloudsController cloudsController;

    private Player player;






    public GamePlay(GameMain game){
        this.game = game;

        mainCamera = new OrthographicCamera(GameInfo.WIDTH, GameInfo.HEIGHT);
        mainCamera.position.set(GameInfo.WIDTH/2f, GameInfo.HEIGHT/2f,0);

        gameViewport = new StretchViewport(GameInfo.WIDTH, GameInfo.HEIGHT, mainCamera);

        box2DCamera = new OrthographicCamera();

        box2DCamera.setToOrtho(false, GameInfo.WIDTH/GameInfo.PPM,
                GameInfo.HEIGHT/GameInfo.PPM);

        box2DCamera.position.set(GameInfo.WIDTH/2f,
                GameInfo.HEIGHT/2f, 0);

        hud =new UIHud(game);

        debugRenderer = new Box2DDebugRenderer();

        world = new World(new Vector2(0,-9.8f),true);

        cloudsController = new CloudsController(world);

        player = cloudsController.positioThePlayer(player);




        createBackgrounds();


    }

    void hendalInput(float delt){
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            player.movePlayer(-2);
        } else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            player.movePlayer(2);
        }else{
            player.setWalking(false);
        }

    }

    void update(float delt){
        hendalInput(delt);
 //       moveCamera();
        checkBackgroundsOutOfBounds();
        cloudsController.setCameraY(mainCamera.position.y);
        cloudsController.createAndArranfNewClouds();


    }

    void  moveCamera(){
        mainCamera.position.y -= 1;
    }

    void createBackgrounds(){
        bgs = new Sprite[4];

        for (int i = 0; i<bgs.length; i++){

            bgs[i] = new Sprite(new Texture("Backgrounds\\Game BG.png"));
            bgs[i].setPosition(0,-(i*bgs[i].getHeight()));
            lastYPosition = Math.abs(bgs[i].getY());

        }
    }

    void drawBackgrounds(){
        for (int i = 0; i<bgs.length; i++){
            game.getBatch().draw(bgs[i], bgs[i].getX(), bgs[i].getY());
        }

    }

    void checkBackgroundsOutOfBounds(){
        for (int i = 0; i<bgs.length; i++){
            if((bgs[i].getY() - bgs[i].getHeight()/2f - 5)>mainCamera.position.y){
                System.out.println(bgs[i].getY() + "  "+ (bgs[i].getY() - bgs[i].getHeight()/2f - 5) + "  "+ mainCamera.position.y);
                float newPosition = bgs[i].getHeight() + lastYPosition;
                bgs[i].setPosition(0, -newPosition);
                lastYPosition = Math.abs(newPosition);

            }
        }

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        update(delta);

        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();

        drawBackgrounds();

        cloudsController.drawClouds(game.getBatch());

        player.drawPlayerIdle(game.getBatch());
        player.drawPlayerAnimation(game.getBatch());

        game.getBatch().end();

        debugRenderer.render(world, box2DCamera.combined);

        game.getBatch().setProjectionMatrix(mainCamera.combined);
        mainCamera.update();

        game.getBatch().setProjectionMatrix(hud.getStage().getCamera().combined);

        hud.getStage().draw();

        player.updatePlayer();

        world.step(Gdx.graphics.getDeltaTime(), 6, 2);

    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height);

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

    }
}
