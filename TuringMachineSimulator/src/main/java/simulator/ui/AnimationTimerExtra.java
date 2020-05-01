
package simulator.ui;

import javafx.animation.AnimationTimer;

public abstract class AnimationTimerExtra extends AnimationTimer {
    
    public abstract void handle(long now);
    
    public abstract void setInterval(long interval);
    
}
