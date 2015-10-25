package com.ecstest;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ecstest.compoments.LifeComponent;
import com.ecstest.compoments.RenderComponent;
import com.ecstest.compoments.TransformComponent;
import com.ecstest.compoments.VelocityComponent;
import com.ecstest.gui.Hud;
import com.ecstest.systems.LifeSystem;
import com.ecstest.systems.MovementSystem;
import com.ecstest.systems.RenderSystem;

public class PerformanceAshely extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture img;

	private OrthographicCamera camera;

//	private Engine engine;
	private PooledEngine engine;

	private float count;

	private boolean removeEntity;

	private Hud hud;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		camera = new OrthographicCamera(40, 30);

//		engine = new Engine();
		engine = new PooledEngine();

		MovementSystem movementSystem = new MovementSystem();
		RenderSystem renderSystem = new RenderSystem(batch);
		LifeSystem lifeSystem = new LifeSystem();

		engine.addSystem(movementSystem);
		engine.addSystem(renderSystem);
		engine.addSystem(lifeSystem);

		count = 0;

		removeEntity = false;
		hud = new Hud(this);
	}

	private void createEntity() {
		for (int i = 0; i < 100; i++) {
//			Entity entity = new Entity();
			Entity entity = engine.createEntity(); // pooled engine
			entity.add(new TransformComponent());
			entity.add(new VelocityComponent(1.0f, count++));
			entity.add(new RenderComponent(new TextureRegion(img)));
			entity.add(new LifeComponent());
			engine.addEntity(entity);
		}
	}

	public Engine getEngine() {
		return engine;
	}

	public boolean getRemoveEntity() {
		return removeEntity;
	}

	private void inputHandle() {
		if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
			LifeSystem lifeSystem = engine.getSystem(LifeSystem.class);
			removeEntity = !removeEntity;
			lifeSystem.setRemoveEntity(removeEntity);
		}
	}

	@Override
	public void render () {
		inputHandle();
		createEntity();

		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		engine.update(Gdx.graphics.getDeltaTime());

		batch.end();


		hud.draw();

	}


	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
		hud.dispose();
	}
}
