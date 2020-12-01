package drawer;

import java.awt.*;

public interface Drawable {
    void drawAll(Graphics2D graphics2D, World world);

    void clear(World world);
}
