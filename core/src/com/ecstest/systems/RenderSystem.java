package com.ecstest.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ecstest.compoments.RenderComponent;
import com.ecstest.compoments.TransformComponent;

public class RenderSystem extends EntitySystem {

    private SpriteBatch batch;
    private ImmutableArray<Entity> entities;

    private static ComponentMapper<TransformComponent> transformComponentCM = ComponentMapper.getFor(TransformComponent.class);
    private static ComponentMapper<RenderComponent> renderComponentCM = ComponentMapper.getFor(RenderComponent.class);

    public RenderSystem(SpriteBatch batch) {
        this.batch = batch;
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(TransformComponent.class, RenderComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        entities.forEach(entity -> {
            TransformComponent transformComponent = transformComponentCM.get(entity);
            RenderComponent renderComponent = renderComponentCM.get(entity);
            batch.draw(renderComponent.textureRegion, transformComponent.position.x, transformComponent.position.y, transformComponent.width / 2, transformComponent.height / 2, transformComponent.width, transformComponent.height, transformComponent.scale.x, transformComponent.scale.y, transformComponent.rotation);
        });

    }
}
