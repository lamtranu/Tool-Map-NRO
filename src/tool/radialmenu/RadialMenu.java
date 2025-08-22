package tool.radialmenu;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
public class RadialMenu
extends JComponent
{
public Color getColorHover() {
/*  36 */     return this.colorHover;
}
public void setColorHover(Color colorHover) {
/*  40 */     this.colorHover = colorHover;
}
public int getButtonSize() {
/*  44 */     return this.buttonSize;
}
public void setButtonSize(int buttonSize) {
/*  48 */     this.buttonSize = buttonSize;
}
public int getItemSize() {
/*  52 */     return this.itemSize;
}
public void setItemSize(int itemSize) {
/*  56 */     this.itemSize = itemSize;
}
/*  59 */   private int buttonSize = 50;
/*  60 */   private int itemSize = 35;
/*  61 */   private float animateSize = 0.0F;
private Animator animator;
private boolean showing;
private boolean mouseOver;
private Color colorHover;
/*  66 */   private final List<EventRadialMenu> events = new ArrayList<>();
/*  67 */   private final List<RadialItem> items = new ArrayList<>();
public RadialMenu() {
/*  70 */     setBackground(new Color(20, 176, 211));
/*  71 */     setForeground(new Color(250, 250, 250));
/*  72 */     this.colorHover = new Color(42, 205, 241);
/*  73 */     TimingTargetAdapter timingTargetAdapter = new TimingTargetAdapter()
{
public void timingEvent(float fraction) {
/*  76 */           if (RadialMenu.this.showing) {
/*  77 */             RadialMenu.this.animateSize = 1.0F - fraction;
} else {
/*  79 */             RadialMenu.this.animateSize = fraction;
}
/*  81 */           RadialMenu.this.repaint();
}
public void end() {
/*  86 */           RadialMenu.this.showing = !RadialMenu.this.showing;
}
};
/*  90 */     this.animator = new Animator(500, (TimingTarget)timingTargetAdapter);
/*  91 */     this.animator.setResolution(0);
/*  92 */     this.animator.setAcceleration(0.5F);
/*  93 */     this.animator.setDeceleration(0.5F);
/*  94 */     addMouseListener(new MouseAdapter()
{
public void mousePressed(MouseEvent me) {
/*  97 */             if (SwingUtilities.isLeftMouseButton(me)) {
/*  98 */               RadialMenu.this.mouseOver = RadialMenu.this.isMouseOverMenu(me);
/*  99 */               if (RadialMenu.this.mouseOver) {
/* 100 */                 if (!RadialMenu.this.animator.isRunning()) {
/* 101 */                   RadialMenu.this.animator.start();
}
} else {
/* 104 */                 int index = RadialMenu.this.isMouseOverItem(me);
/* 105 */                 if (index >= 0) {
/* 106 */                   RadialMenu.this.runEvent(index);
}
}
}
}
});
/* 112 */     addMouseMotionListener(new MouseMotionAdapter()
{
public void mouseMoved(MouseEvent me) {
/* 115 */             boolean over = RadialMenu.this.isMouseOverMenu(me);
/* 116 */             if (over || RadialMenu.this.isMouseOverItem(me) >= 0) {
/* 117 */               RadialMenu.this.setCursor(new Cursor(12));
} else {
/* 119 */               RadialMenu.this.setCursor(new Cursor(0));
}
/* 121 */             if (over != RadialMenu.this.mouseOver) {
/* 122 */               RadialMenu.this.mouseOver = over;
/* 123 */               RadialMenu.this.repaint();
}
}
});
}
public void paint(Graphics grphcs) {
/* 131 */     Graphics2D g2 = (Graphics2D)grphcs;
/* 132 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 133 */     if (!this.items.isEmpty()) {
/* 134 */       int width = getWidth();
/* 135 */       int height = getHeight();
/* 136 */       int size = (int)((Math.min(width, height) / 2) - this.itemSize / 1.5F);
/* 137 */       int centerX = width / 2;
/* 138 */       int centerY = height / 2;
/* 139 */       float anglePerItem = (360 / this.items.size());
/* 140 */       float currentAnimate = this.animateSize;
/* 141 */       for (int i = 0; i < this.items.size(); i++) {
/* 142 */         RadialItem item = this.items.get(i);
/* 143 */         float angle = 90.0F + i * anglePerItem;
/* 144 */         Point location = toLocation(angle, (size * currentAnimate));
/* 145 */         g2.setColor(item.getColor());
/* 146 */         int itemX = centerX + location.x - this.itemSize / 2;
/* 147 */         int itemY = centerY - location.y - this.itemSize / 2;
/* 148 */         g2.fillOval(itemX, itemY, this.itemSize, this.itemSize);
/* 150 */         int iconX = itemX + (this.itemSize - item.getIcon().getIconWidth()) / 2;
/* 151 */         int iconY = itemY + (this.itemSize - item.getIcon().getIconHeight()) / 2;
/* 152 */         g2.drawImage(toImage(item.getIcon()), iconX, iconY, (ImageObserver)null);
}
}
/* 155 */     createMenuButton(g2);
/* 156 */     g2.dispose();
}
private void createMenuButton(Graphics2D g2) {
/* 160 */     int width = getWidth();
/* 161 */     int height = getHeight();
/* 162 */     int x = (width - this.buttonSize) / 2;
/* 163 */     int y = (height - this.buttonSize) / 2;
/* 164 */     if (this.mouseOver) {
/* 165 */       g2.setColor(this.colorHover);
} else {
/* 167 */       g2.setColor(getBackground());
}
/* 169 */     g2.fillOval(x, y, this.buttonSize, this.buttonSize);
/* 170 */     int stroke = 3;
/* 171 */     g2.setColor(getForeground());
/* 172 */     int lineSize = (int)(this.buttonSize - this.buttonSize * 0.5F);
/* 173 */     int lineSpace = lineSize / 3;
/* 174 */     int lineX = (width - lineSize) / 2;
/* 175 */     int lineY = height / 2;
/* 176 */     g2.setStroke(new BasicStroke(stroke));
/* 177 */     int startY = lineY - lineSpace;
/* 178 */     int endY = lineY + lineSpace;
/* 179 */     double space = (this.animateSize * (endY - startY));
/* 180 */     startY = (int)(startY + space);
/* 181 */     endY = (int)(endY - space);
/* 182 */     g2.drawLine(lineX, lineY - lineSpace, lineX + lineSize, startY);
/* 183 */     g2.drawLine(lineX, lineY + lineSpace, lineX + lineSize, endY);
/* 184 */     float alpha = 1.0F - this.animateSize;
/* 185 */     g2.setComposite(AlphaComposite.getInstance(3, alpha));
/* 186 */     g2.drawLine(lineX, lineY, lineX + lineSize, lineY);
}
private Point toLocation(float angle, double size) {
/* 190 */     int x = (int)(Math.cos(Math.toRadians(angle)) * size);
/* 191 */     int y = (int)(Math.sin(Math.toRadians(angle)) * size);
/* 192 */     return new Point(x, y);
}
private boolean isMouseOverMenu(MouseEvent me) {
/* 196 */     int width = getWidth();
/* 197 */     int height = getHeight();
/* 198 */     int x = (width - this.buttonSize) / 2;
/* 199 */     int y = (height - this.buttonSize) / 2;
/* 200 */     Shape s = new Ellipse2D.Double(x, y, this.buttonSize, this.buttonSize);
/* 201 */     return s.contains(me.getPoint());
}
private int isMouseOverItem(MouseEvent me) {
/* 205 */     int index = -1;
/* 206 */     if (this.showing) {
/* 207 */       int width = getWidth();
/* 208 */       int height = getHeight();
/* 209 */       int centerX = width / 2;
/* 210 */       int centerY = height / 2;
/* 211 */       float anglePerItem = (360 / this.items.size());
/* 212 */       int size = (int)((Math.min(width, height) / 2) - this.itemSize / 1.5F);
/* 213 */       for (int i = 0; i < this.items.size(); i++) {
/* 214 */         float angle = 90.0F + i * anglePerItem;
/* 215 */         Point location = toLocation(angle, (size * 1.0F));
/* 216 */         int itemX = centerX + location.x - this.itemSize / 2;
/* 217 */         int itemY = centerY - location.y - this.itemSize / 2;
/* 218 */         Shape shape = new Ellipse2D.Double(itemX, itemY, this.itemSize, this.itemSize);
/* 219 */         if (shape.contains(me.getPoint())) {
/* 220 */           return i;
}
}
}
/* 224 */     return index;
}
private void runEvent(int index) {
/* 228 */     for (EventRadialMenu event : this.events) {
/* 229 */       event.menuSelected(index);
}
}
public void addItem(RadialItem item) {
/* 234 */     this.items.add(item);
}
public Image toImage(Icon icon) {
/* 238 */     return ((ImageIcon)icon).getImage();
}
public void setShowMenu(boolean show) {
/* 242 */     if (this.showing != show &&
/* 243 */       !this.animator.isRunning()) {
/* 244 */       this.animator.start();
}
}
public void addEvent(EventRadialMenu event) {
/* 250 */     this.events.add(event);
}
}



