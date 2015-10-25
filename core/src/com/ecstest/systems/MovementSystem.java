package com.ecstest.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.ecstest.compoments.TransformComponent;
import com.ecstest.compoments.VelocityComponent;

public class MovementSystem extends EntitySystem {

    private ImmutableArray<Entity> entities;

    private static ComponentMapper<TransformComponent> transformComponentCM = ComponentMapper.getFor(TransformComponent.class);
    private static ComponentMapper<VelocityComponent> velocityCompenetCM = ComponentMapper.getFor(VelocityComponent.class);


    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(TransformComponent.class, VelocityComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        entities.forEach(entity -> {
            TransformComponent transformComponent = transformComponentCM.get(entity);
            VelocityComponent velocityComponent = velocityCompenetCM.get(entity);

            transformComponent.position.x += velocityComponent.velocity.x * deltaTime;
            transformComponent.position.y += velocityComponent.velocity.y * deltaTime;

        });
    }
}
