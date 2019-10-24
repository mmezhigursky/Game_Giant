package clouds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import helpers.GameInfo;

public class Cloud extends Sprite {

    private World world;
    private Body body;
    private  String cloudName;


    private boolean drawLeft;


    public Cloud(World world, String cloudName){
        super(new Texture("Clouds\\"+ cloudName + ".png"));
        this.world =world;
        this.cloudName = cloudName;
       // setPosition(GameInfo.WIDTH/2f, GameInfo.HEIGHT/2f -130);
      // createBody();
    }

     void createBody(){
        BodyDef bodyDef = new BodyDef();

        // a static body is not affected by gravity or other forces
        // a kinematic body is not affected by gravity but affected by other forces
        // a dynamic body is  affected by gravity but affected by other forces

        bodyDef.type = BodyDef.BodyType.StaticBody;

        bodyDef.position.set((getX()) / GameInfo.PPM,
                (getY())/ GameInfo.PPM);

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(((getWidth()/2)+20)/ GameInfo.PPM,
                (getHeight()/2)/ GameInfo.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;


        Fixture fixture = body.createFixture(fixtureDef);
      //  fixture.setUserData("Cloud");
       // fixture.setSensor(true);

        shape.dispose();

    }

    public void setSpritePosition(float x, float y){
        setPosition(x, y);
        createBody();
    }

    public String getCloudName(){

        return this.cloudName;
    }

    public boolean getDrawLeft() {
        return drawLeft;
    }

    public void setDrawLeft(boolean drawLeft) {
        this.drawLeft = drawLeft;
    }

}
