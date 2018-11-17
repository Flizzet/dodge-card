package com.flizzet.guicomponent;

import com.flizzet.entity.Entity;

/**
 * Abstract GuiComponent .
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public abstract class GuiComponent extends Entity {

    /** To be called upon triggering of the {@link GuiComponet} */
    public abstract void triggered();
    
}
