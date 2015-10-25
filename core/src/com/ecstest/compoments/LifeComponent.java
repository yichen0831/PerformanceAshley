package com.ecstest.compoments;

import com.badlogic.ashley.core.Component;

public class LifeComponent implements Component {
    public float life;
    public float maxLife;

    public LifeComponent() {
        life = 0;
        maxLife = 10.0f;
    }
}
