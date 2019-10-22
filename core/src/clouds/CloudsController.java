package clouds;



import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Random;

import helpers.GameInfo;


public class CloudsController {

    private World world;

    private List<Cloud> clouds = new ArrayList<Cloud>();

    private final float DISTANCE_BETWEEN_CLOUDS =250f;

    private float minX, maxX;

    private Random random = new Random();


    public CloudsController(World world){
        this.world = world;
        minX = GameInfo.WIDTH /2f -110;
        maxX = GameInfo.WIDTH /2f +110;
        createClouds();
        positionClouds();
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

    public  void positionClouds(){

        while (clouds.get(0).getCloudName() == "Dark Cloud"){
            Collections.shuffle(clouds);
        }

        float positionY = GameInfo.HEIGHT/2f;

        int controlX = 0;

        for (Cloud c: clouds) {
            float tempX = 0;

            if (controlX == 0) {
                tempX = randomBetweenNumbers(maxX , maxX);
                controlX =1;

            }else if (controlX == 1){
                tempX = randomBetweenNumbers(minX , minX);
                controlX =0;

            }

            c.setSpritePosition(tempX, positionY);

            positionY -= DISTANCE_BETWEEN_CLOUDS;

        }

    }

    public void drawClouds (SpriteBatch batch){
        for (Cloud c : clouds) {

            batch.draw(c, c.getX() - c.getWidth() /2f, c.getY() - c.getHeight() /2f);
        }
    }

    private float randomBetweenNumbers(float min, float max){

        return random.nextFloat() * (max - min) +min;
    }
}