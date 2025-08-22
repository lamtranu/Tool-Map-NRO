package tool.draw_map.layer;
import java.awt.image.BufferedImage;
public interface Layer {
void draw();
BufferedImage getBufferedImage();
void clearImage();
void setShow(boolean paramBoolean);
boolean isShow();
void setSizeImage(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
void clear();
}



