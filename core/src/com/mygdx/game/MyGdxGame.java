package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;


public class MyGdxGame extends ApplicationAdapter implements InputProcessor{
	private	SpriteBatch batch;
	private Texture img;
	private Texture njuska;
	private Sprite pozadina;
	private Sprite spriteNjuska;
	private OrthographicCamera cam;
	private Viewport viewport;

	private Texture slikaZivoti;
	private Sprite zivoti;
	private Sprite zivoti2;
	private Sprite zivoti3;

	private float njuskaX=-40, njuskaY=-400;

	private Texture pecurkaSlika;
	private float pecurkaY;
	private Sprite pecurka1, pecurka2, pecurka3;

	private boolean prviZivot = true, drugiZivot = true, treciZivot = true;

	private boolean isMenu=true, isGame=false, isGameOver=false;
	private Texture dugmeStart, dugmeQuit;
    private Sprite dugStart, dugQuit;

    private Random rand;
    private int brzinaPadanja;
    private Vector3 touch = new Vector3();

    private int prvaX, prvaY;
    private int drugaX, drugaY;
    private int trecaX, trecaY;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture(Gdx.files.internal("svemir.jpeg"));
		pozadina = new Sprite(img);
        cam = new OrthographicCamera();
        viewport= new StretchViewport(480, 800, cam);

        ucitajDugmice();
        ucitajKaraktera();
        ucitajZivote();
        ucitajPecurke();

        rand = new Random();


        Gdx.input.setInputProcessor(this);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(cam.combined);

		batch.begin();



        batch.draw(pozadina, -240, -400);

        if(isMenu){
            dugStart.draw(batch);
            dugQuit.draw(batch);
        }

        if(isGame) {
            njuskaX -= 2.5 * Gdx.input.getAccelerometerX();
            if (njuskaX < -240) {
                njuskaX = -240;
            } else if (njuskaX > 144) {
                njuskaX = 144;
            }
            spriteNjuska.setPosition(njuskaX, njuskaY);
            spriteNjuska.draw(batch);



            pecurka1.draw(batch);
            pecurka2.draw(batch);
            pecurka3.draw(batch);


            if (prviZivot) {
                zivoti.draw(batch);
            }
            if (drugiZivot) {
                zivoti2.draw(batch);
            }
            if (treciZivot) {
                zivoti3.draw(batch);
            }
        }

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		slikaZivoti.dispose();
		njuska.dispose();
		pecurkaSlika.dispose();
	}

	@Override
    public void resize(int height, int width){
        viewport.update(height, width);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        cam.unproject(touch.set(screenX, screenY, 0));

        if(touch.x >= -73 && touch.x <= 72 && touch.y > 150 && touch.y < 199){
            isMenu=false;
            isGame=true;
        }
        if(touch.x >= -65 && touch.x <= 66 && touch.y >= -25 && touch.y <= 34){
            Gdx.app.exit();
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }


    public void ucitajDugmice(){
        //Dugmici
        dugmeQuit = new Texture(Gdx.files.internal("button_quit (1).png"));
        dugmeStart = new Texture(Gdx.files.internal("button_start (1).png"));

        dugQuit = new Sprite(dugmeQuit);
        dugStart = new Sprite(dugmeStart);

        dugStart.setPosition(-73, 150);
        dugQuit.setPosition(-65, -25);

    }

    public void ucitajKaraktera(){
        njuska = new Texture(Gdx.files.internal("njuska.png"));

        spriteNjuska = new Sprite(njuska);
        spriteNjuska.setSize(96, 120);
        spriteNjuska.setPosition(njuskaX, njuskaY);
    }

    public void ucitajZivote(){

        slikaZivoti = new Texture(Gdx.files.internal("thmb.png"));

        zivoti = new Sprite(slikaZivoti);
        zivoti.setPosition(180, 330);
        zivoti.setSize(50, 50);

        zivoti2 = new Sprite(slikaZivoti);
        zivoti2.setPosition(125, 330);
        zivoti2.setSize(50, 50);

        zivoti3 = new Sprite(slikaZivoti);
        zivoti3.setPosition(70, 330);
        zivoti3.setSize(50, 50);
    }
    public void ucitajPecurke(){

        pecurkaSlika = new Texture(Gdx.files.internal("c.png"));

        pecurka1 = new Sprite(pecurkaSlika);
        prvaX = -200; drugaX = 300;
        pecurka1.setPosition(prvaX, prvaY);
        pecurka1.setSize(100, 100);

        pecurka2 = new Sprite(pecurkaSlika);
        drugaX = -50; drugaY = 300;
        pecurka2.setPosition(drugaX, drugaY);
        pecurka2.setSize(100, 100);

        pecurka3 = new Sprite(pecurkaSlika);
        trecaX = 100; trecaY = 300;
        pecurka3.setPosition(trecaX, trecaY);
        pecurka3.setSize(100, 100);
    }
}
