package player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import helpers.GameInfo;
import sun.security.acl.WorldGroupImpl;

public class Player extends Sprite {

    private World world;
    private Body body;


    public Player(World world, float x, float y){

        super(new Texture("Player/Player 1.png"));
        this.world= world;
        setPosition(x - getWidth()/2, y - getHeight()/2);
        createBody();

    }

    void createBody(){

        BodyDef bodyDef = new BodyDef();

        // a static body is not affected by gravity or other forces
        // a kinematic body is not affected by gravity but affected by other forces
        // a dynamic body is  affected by gravity but affected by other forces

        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.position.set(getX()/ GameInfo.PPM, getY()/ GameInfo.PPM);

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((getWidth()/2)/ GameInfo.PPM, (getHeight()/2)/ GameInfo.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 4f;
        fixtureDef.friction = 2f;
        fixtureDef.shape = shape;




        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData("Player");

        shape.dispose();
    }

    public void drawPlayer(SpriteBatch batch){
        batch.draw(this, getX() - getWidth() / 2f,
                getY() - getHeight() / 2f );
    }

    public void updatePlayer(){
        this.setPosition(body.getPosition().x * GameInfo.PPM, body.getPosition().y * GameInfo.PPM);
    }

    public Body getBody(){
        return this.body;
    }
}



































