package tool.chart.blankchart;

public class SeriesSize {

    private double x;
    private double y;
    private double width;
    private double height;

    public SeriesSize() {
    }

    public SeriesSize(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Getters & Setters
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }

    public double getY() { return y; }
    public void setY(double y) { this.y = y; }

    public double getWidth() { return width; }
    public void setWidth(double width) { this.width = width; }

    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }

    @Override
    public String toString() {
        return "SeriesSize[x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + "]";
    }
}
