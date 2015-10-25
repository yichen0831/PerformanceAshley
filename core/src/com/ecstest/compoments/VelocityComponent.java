package com.ecstest.compoments;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class VelocityComponent implements Component {
    public Vector2 velocity;

    public VelocityComponent(Vector2 velocity) {
        this.velocity = velocity;
    }

    public VelocityComponent(float speed, float angleDeg) {
        velocity = new Vector2();
        velocity.x = speed * MathUtils.sinDeg(angleDeg);
        velocity.y = speed * MathUtils.cosDeg(angleDeg);
    }

    public VelocityComponent() {
        this(new Vector2());
    }
}
