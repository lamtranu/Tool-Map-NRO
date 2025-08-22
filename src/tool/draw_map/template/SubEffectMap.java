package tool.draw_map.template;
import tool.db.EffectTemplate;
import tool.draw_map.DrawMapScr;
import tool.utils.Util;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
public class SubEffectMap extends EffectMap {
private int typeEff;
private BufferedImage img;
private int[] x;
private int[] y;
private int[] vx;
private int[] vy;
private DrawMapScr drawMapScr;
public SubEffectMap(EffectTemplate temp, int x, int y, int layer, int loop, int loopCount) {
/*  19 */     super(temp, x, y, layer, loop, loopCount);
}
public SubEffectMap() {
/*  23 */     super(null, -1, -1, 3, -1, -1);
}
public int getTypeEff() {
/*  27 */     return this.typeEff;
}
public SubEffectMap(int typeEff, DrawMapScr drawMapScr) {
/*  42 */     this();
/*  43 */     this.drawMapScr = drawMapScr; try {
int sum, i;
/*  45 */       this.typeEff = typeEff;
/*  46 */       this.img = Util.getImageBEffectByType(this.typeEff);
/*  47 */       switch (this.typeEff) {
case 4:
/*  49 */           sum = Util.range(30, 40);
/*  50 */           this.x = new int[sum];
/*  51 */           this.y = new int[sum];
/*  52 */           this.vx = new int[sum];
/*  53 */           this.vy = new int[sum];
/*  54 */           for (i = 0; i < sum; i++) {
/*  55 */             this.x[i] = Util.range(0, drawMapScr.mapWidth);
/*  56 */             this.y[i] = Util.range(0, 50);
/*  57 */             this.vx[i] = 0;
/*  58 */             this.vy[i] = 0;
}
break;
case 1:
case 2:
case 5:
case 6:
case 7:
case 11:
/*  67 */           sum = Util.range(30, 50);
/*  68 */           if (this.typeEff == 11) {
/*  69 */             sum = 100;
}
/*  71 */           this.x = new int[sum];
/*  72 */           this.y = new int[sum];
/*  73 */           this.vx = new int[sum];
/*  74 */           this.vy = new int[sum];
/*  75 */           for (i = 0; i < sum; i++) {
/*  76 */             this.x[i] = Util.range(-10, drawMapScr.mapWidth + 10);
/*  77 */             this.y[i] = Util.range(0, drawMapScr.mapHeight);
/*  79 */             this.vx[i] = Util.range(-3, 3);
/*  80 */             this.vy[i] = Util.range(1, 4);
/*  81 */             if (typeEff == 11) {
/*  82 */               this.vx[i] = Math.abs(Util.range(1, 3));
/*  83 */               this.vy[i] = Math.abs(Util.range(1, 3));
}
}
break;
case 12:
/*  88 */           sum = 500;
/*  89 */           this.x = new int[sum];
/*  90 */           this.y = new int[sum];
/*  91 */           this.vx = new int[sum];
/*  92 */           this.vy = new int[sum];
/*  93 */           for (i = 0; i < sum; i++) {
/*  94 */             this.x[i] = Util.range(-10, drawMapScr.mapWidth + 10);
/*  95 */             this.y[i] = Util.range(0, drawMapScr.mapHeight);
/*  97 */             this.vx[i] = -12;
/*  98 */             this.vy[i] = 12;
}
break;
}
/* 103 */     } catch (Exception e) {
/* 104 */       e.printStackTrace();
}
}
public void paint(Graphics2D g, boolean changeColor) {
/* 110 */     if (this.img != null) {
/* 111 */       if (this.typeEff == 12) {
/* 112 */         paintMua(g);
} else {
/* 114 */         int num = (this.typeEff == 4) ? 2 : ((this.typeEff != 11) ? 4 : 3);
/* 115 */         paintLacay1(g, num);
}
}
}
public void paintMua(Graphics2D g) {
/* 121 */     for (int i = 0; i < this.x.length; i++) {
/* 123 */       g.drawImage(this.img, this.x[i], this.y[i], null);
/* 124 */       this.x[i] = this.x[i] + this.vx[i];
/* 125 */       this.y[i] = this.y[i] + this.vy[i];
/* 126 */       if (this.x[i] < -10) {
/* 127 */         this.x[i] = Util.range(-10, this.drawMapScr.mapWidth + 10);
}
/* 129 */       if (this.y[i] > this.drawMapScr.mapHeight + 10) {
/* 130 */         this.y[i] = 0;
}
}
}
public void paintLacay1(Graphics2D g, int num) {
/* 136 */     for (int i = 0; i < this.x.length; i++) {
/* 138 */       g.drawImage(this.img.getSubimage(0, this.img.getHeight() / num * Util.range(0, num - 1), this.img
/* 139 */             .getWidth(), this.img.getHeight() / num), this.x[i], this.y[i], null);
/* 140 */       this.x[i] = this.x[i] + this.vx[i];
/* 141 */       this.y[i] = this.y[i] + this.vy[i];
/* 142 */       if (this.x[i] < -10) {
/* 143 */         this.x[i] = this.drawMapScr.mapWidth + 10;
/* 144 */         this.y[i] = -10;
/* 145 */         this.x[i] = Util.range(-10, this.drawMapScr.mapWidth + 10);
/* 146 */         this.vx[i] = Util.range(-3, 3);
/* 147 */         this.vy[i] = Util.range(1, 4);
/* 148 */         if (this.typeEff == 11) {
/* 149 */           this.vx[i] = Math.abs(Util.range(1, 3));
/* 150 */           this.vy[i] = Math.abs(Util.range(1, 3));
}
/* 152 */       } else if (this.x[i] > this.drawMapScr.mapWidth + 10) {
/* 153 */         this.x[i] = Util.range(-10, this.drawMapScr.mapWidth + 10);
/* 154 */         this.x[i] = -10;
/* 155 */         this.y[i] = -10;
/* 156 */         this.vx[i] = Util.range(-3, 3);
/* 157 */         this.vy[i] = Util.range(1, 4);
/* 158 */         if (this.typeEff == 11) {
/* 159 */           this.vx[i] = Math.abs(Util.range(1, 3));
/* 160 */           this.vy[i] = Math.abs(Util.range(1, 3));
}
}
/* 163 */       if (this.y[i] > this.drawMapScr.mapHeight + 10) {
/* 164 */         this.y[i] = Util.range(0, this.drawMapScr.mapHeight);
/* 165 */         this.y[i] = -10;
/* 166 */         this.vx[i] = Util.range(-3, 3);
/* 167 */         this.vy[i] = Util.range(1, 4);
/* 168 */         if (this.typeEff == 11) {
/* 169 */           this.vx[i] = Math.abs(Util.range(1, 3));
/* 170 */           this.vy[i] = Math.abs(Util.range(1, 3));
}
}
}
}
}



