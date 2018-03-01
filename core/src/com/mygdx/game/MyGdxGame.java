package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class MyGdxGame extends ApplicationAdapter {
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
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture(Gdx.files.internal("svemir.jpeg"));
		pozadina = new Sprite(img);

		njuska = new Texture(Gdx.files.internal("njuska.png"));
		spriteNjuska = new Sprite(njuska);
        spriteNjuska.setSize(96, 120);
        spriteNjuska.setPosition(njuskaX, njuskaY);
        cam = new OrthographicCamera();
        viewport= new StretchViewport(480, 800, cam);


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

        pecurkaSlika = new Texture(Gdx.files.internal("c.png"));
        pecurka1 = new Sprite(pecurkaSlika);
        pecurka1.setPosition(-200, 300);
        pecurka1.setSize(100, 100);

        pecurka2 = new Sprite(pecurkaSlika);
        pecurka2.setPosition(-50, 300);
        pecurka2.setSize(100, 100);

        pecurka3 = new Sprite(pecurkaSlika);
        pecurka3.setPosition(100, 300);
        pecurka3.setSize(100, 100);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(cam.combined);

		batch.begin();

		njuskaX-=2.5*Gdx.input.getAccelerometerX();
        if(njuskaX<-240){
            njuskaX=-240;
        }
        else if(njuskaX>144){
            njuskaX=144;
        }
        spriteNjuska.setPosition(njuskaX, njuskaY);
		batch.draw(pozadina, -240, -400);
        spriteNjuska.draw(batch);


        pecurka1.draw(batch);
        pecurka2.draw(batch);
        pecurka3.draw(batch);


        if(prviZivot) {
            zivoti.draw(batch);
        }
        if(drugiZivot) {
            zivoti2.draw(batch);
        }
        if(treciZivot) {
            zivoti3.draw(batch);
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
}
