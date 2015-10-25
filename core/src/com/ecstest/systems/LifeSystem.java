package com.ecstest.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.ecstest.compoments.LifeComponent;

public class LifeSystem extends EntitySystem {

    private ImmutableArray<Entity> entities;
    private ComponentMapper<LifeComponent> lifeComponentCM = ComponentMapper.getFor(LifeComponent.class);

    private boolean removeEntity;

    public boolean isRemoveEntity() {
        return removeEntity;
    }

    public void setRemoveEntity(boolean removeEntity) {
        this.removeEntity = removeEntity;
    }


    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(LifeComponent.class).get());
        removeEntity = false;
    }

    @Override
    public void update(float deltaTime) {
        entities.forEach(entity -> {
            LifeComponent lifeComponent = lifeComponentCM.get(entity);
            lifeComponent.life += deltaTime;

            if (removeEntity) {
                if (lifeComponent.life > lifeComponent.maxLife) {
                    getEngine().removeEntity(entity);
                }
            }
        });

    }
}
