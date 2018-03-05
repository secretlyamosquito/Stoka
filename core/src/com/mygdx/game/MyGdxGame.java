package com.mygdx.game;

//KONACNOOOO GOTOVOOOOOOO!!

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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



	private float njuskaX=-40, njuskaY=-400;

	private Texture pecurkaSlika;
	private Sprite pecurka1, pecurka2, pecurka3;


	private boolean isMenu=true, isGame=false, isGameOver=false;
	private Texture dugmeStart, dugmeQuit;
    private Sprite dugStart, dugQuit;

    private Random rand;
    private int brzinaPadanja1;
    private int brzinaPadanja2;
    private int brzinaPadanja3;
    private Vector3 touch = new Vector3();

    private int prvaX, prvaY;
    private int drugaX, drugaY;
    private int trecaX, trecaY;

    private Preferences prefs;
    private int Score;
    private int scoringNow=0;

    private BitmapFont font;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture(Gdx.files.internal("svemir.jpeg"));
		pozadina = new Sprite(img);
        cam = new OrthographicCamera();
        viewport= new StretchViewport(480, 800, cam);
        rand = new Random();

        ucitajDugmice();
        ucitajKaraktera();
        ucitajPecurke();

        prefs = Gdx.app.getPreferences("highscore");
        Score = prefs.getInteger("score");
        font = new BitmapFont(Gdx.files.internal("font.fnt"), Gdx.files.internal("font.png"), false);

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
            isGame=false;
            isGameOver=false;
            dugStart.draw(batch);
            dugQuit.draw(batch);
            font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
            font.getData().setScale(0.5f);
            font.draw(batch, Integer.toString(Score), 170, 370);
        }

        if(isGame) {
            isMenu=false;
            isGameOver=false;
            igraJeUToku();
        }
        if(isGameOver){
            isMenu=false;
            isGame=false;
            dugStart.draw(batch);
            dugQuit.draw(batch);
            if(scoringNow>Score){
                prefs.putInteger("score", scoringNow);
                prefs.flush();
                Score = scoringNow;
            }
            font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
            font.getData().setScale(0.5f);
            font.draw(batch, Integer.toString(Score), 170, 370);

            font.draw(batch, "GAME OVER", -100, -150);
        }

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		njuska.dispose();
		pecurkaSlika.dispose();
		dugmeStart.dispose();
		dugmeQuit.dispose();
		font.dispose();

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

        if(touch.x >= -73 && touch.x <= 72 && touch.y > 150 && touch.y < 199 && (isMenu==true || isGameOver==true)){
            if(isGameOver){
                isGameOver=false;
                isGame=true;
                isMenu=false;
                prvaY=300;
                drugaY=300;
                trecaY=300;
                scoringNow=0;
            }
            if(isMenu){
                isMenu=false;
                isGame=true;
                isGameOver=false;
            }

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


    private void ucitajDugmice(){
        //Dugmici
        dugmeQuit = new Texture(Gdx.files.internal("button_quit (1).png"));
        dugmeStart = new Texture(Gdx.files.internal("button_start (1).png"));

        dugQuit = new Sprite(dugmeQuit);
        dugStart = new Sprite(dugmeStart);

        dugStart.setPosition(-73, 150);
        dugQuit.setPosition(-65, -25);

    }

    private void ucitajKaraktera(){
        njuska = new Texture(Gdx.files.internal("njuska.png"));

        spriteNjuska = new Sprite(njuska);
        spriteNjuska.setSize(96, 120);
        spriteNjuska.setPosition(njuskaX, njuskaY);
    }


    private void ucitajPecurke(){

        pecurkaSlika = new Texture(Gdx.files.internal("c.png"));

        pecurka1 = new Sprite(pecurkaSlika);
        prvaX = -200; prvaY = 300;
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

        brzinaPadanja1 = rand.nextInt(3)+2;
        brzinaPadanja2 = rand.nextInt(3)+2;
        brzinaPadanja3 = rand.nextInt(3)+2;
        while((brzinaPadanja1==brzinaPadanja2) || (brzinaPadanja1==brzinaPadanja3) || (brzinaPadanja2==brzinaPadanja3)){
            brzinaPadanja1 = rand.nextInt(3)+2;
            brzinaPadanja2 = rand.nextInt(3)+2;
            brzinaPadanja3 = rand.nextInt(3)+2;
        }
    }

    private void igraJeUToku(){
        njuskaX -= 2.5 * Gdx.input.getAccelerometerX();
        if (njuskaX < -240) {
            njuskaX = -240;
        } else if (njuskaX > 144) {
            njuskaX = 144;
        }
        spriteNjuska.setPosition(njuskaX, njuskaY);
        spriteNjuska.draw(batch);

        font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        font.getData().setScale(0.5f);


        pecurka1.setPosition(prvaX, prvaY);
        pecurka2.setPosition(drugaX, drugaY);
        pecurka3.setPosition(trecaX, trecaY);

        prvaY-=brzinaPadanja1;
        drugaY-=brzinaPadanja2;
        trecaY-=brzinaPadanja3;

        sudar();
        GameOver();

        pecurka1.draw(batch);
        pecurka2.draw(batch);
        pecurka3.draw(batch);


        font.draw(batch, Integer.toString(scoringNow), -190, 370);

    }

    private void GameOver(){
        if (prvaY<-400 || drugaY<-400 || trecaY<-400){
            isGameOver=true;
            isMenu=false;
            isGame=false;
        }
    }

    private void sudar(){
        if(prvaY<-302 && njuskaX<prvaX+50 && njuskaX+96>prvaX){
            scoringNow++;
            prvaY=300;
            brzinaPadanja1 = rand.nextInt(3)+2;
            while(brzinaPadanja1==brzinaPadanja3 || brzinaPadanja1==brzinaPadanja2){
                brzinaPadanja1 = rand.nextInt(3)+2;
            }
        }

        if(drugaY<-302 && njuskaX<drugaX+50 && njuskaX+96>drugaX){
            scoringNow++;
            drugaY=300;
            brzinaPadanja2 = rand.nextInt(3)+2;
            while(brzinaPadanja2==brzinaPadanja1 || brzinaPadanja2==brzinaPadanja3){
                brzinaPadanja2 = rand.nextInt(3)+2;
            }
        }

        if(trecaY<-302 && njuskaX<trecaX+50 && njuskaX+96>trecaX){
            scoringNow++;
            trecaY=300;
            brzinaPadanja3 = rand.nextInt(3)+2;
            while(brzinaPadanja3==brzinaPadanja2 || brzinaPadanja3==brzinaPadanja1){
                brzinaPadanja3 = rand.nextInt(3)+2;
            }
        }
    }
}
