package com.ecstest.compoments;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class RenderComponent implements Component {
    public TextureRegion textureRegion;

    public RenderComponent(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }
}
