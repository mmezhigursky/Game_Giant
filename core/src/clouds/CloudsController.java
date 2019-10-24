package clouds;



import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Random;

import helpers.GameInfo;
import player.Player;


public class CloudsController {

    private World world;

    private List<Cloud> clouds = new ArrayList<Cloud>();

    private final float DISTANCE_BETWEEN_CLOUDS =250f;

    private float minX, maxX;
    private  float lastCloudPositionY;

    private float cameraY;

    private Random random = new Random();


    public CloudsController(World world){
        this.world = world;
        minX = GameInfo.WIDTH /2f -110;
        maxX = GameInfo.WIDTH /2f +110;
        createClouds();
        positionClouds(true);
    }

    void createClouds(){

        for(int i = 0; i<2; i++){
            clouds.add(new Cloud(world, "Dark Cloud"));
        }
        int index =1;
        for (int i = 0; i < 6 ; i++) {
            clouds.add(new Cloud(world, "Cloud " + index));
            index++;

            if ( index == 4) {
                index = 1;
            }

        }

        Collections.shuffle(clouds);



    }

    public  void positionClouds(boolean firstTimeArranging){

        while (clouds.get(0).getCloudName() == "Dark Cloud"){
            Collections.shuffle(clouds);

            if(clouds.get(clouds.indexOf("Dark Cloud")+1).getCloudName() == "Dark Cloud"){
                Collections.shuffle(clouds);
            }
        }

        float positionY = 0;

        if(firstTimeArranging){
            positionY = GameInfo.HEIGHT /2f;
        } else{
            positionY = lastCloudPositionY;
        }

        int controlX = 0;

        for (Cloud c: clouds) {

            if(c.getX() == 0 && c.getY() == 0){
                float tempX = 0;

                if (controlX == 0) {
                    tempX = randomBetweenNumbers(maxX - 40, maxX);
                    controlX =1;
                    c.setDrawLeft(false);

                }else if (controlX == 1){
                    tempX = randomBetweenNumbers(minX + 40, minX);
                    controlX =0;
                    c.setDrawLeft(true);

                }

                c.setSpritePosition(tempX, positionY);

                positionY -= DISTANCE_BETWEEN_CLOUDS;
                lastCloudPositionY =positionY;

            }


        }

    }

    public void drawClouds (SpriteBatch batch){
        for (Cloud c : clouds) {
            if ((c.getDrawLeft())){
                batch.draw(c, c.getX() - c.getWidth() /2f-10,
                        c.getY() - c.getHeight() /2f);
            }
                else{ batch.draw(c, c.getX() - c.getWidth() /2f+10,
                    c.getY() - c.getHeight() /2f);}
        }
    }

    public void createAndArranfNewClouds(){
        for (int i = 0; i <clouds.size(); i++) {
            if((clouds.get(i).getY() - GameInfo.HEIGHT / 2) > cameraY ){
                clouds.get(i).getTexture().dispose();
                clouds.remove(i);
            }

        }
        if(clouds.size() == 4){
            createClouds();


            positionClouds(false);
        }
    }

    public void setCameraY(float cameraY){
        this.cameraY = cameraY;
    }

    public Player positioThePlayer(Player player){
        player = new Player(world, clouds.get(0).getX(),
                clouds.get(0).getY() + 100 );
        return player;
    }

    private float randomBetweenNumbers(float min, float max){

        return random.nextFloat() * (max - min) +min;
    }
}
