package tool.chart.blankchart;

public class NiceScale {

    private double min;
    private double max;
    private int maxTicks = 10;
    private double tickSpacing;
    private double range;
    private double niceMin;
    private double niceMax;

    public NiceScale(double min, double max) {
        this.min = min;
        this.max = max;
        calculate();
    }

    private void calculate() {
        this.range = niceNum(max - min, false);
        this.tickSpacing = niceNum(range / (maxTicks - 1), true);
        this.niceMin = Math.floor(min / tickSpacing) * tickSpacing;
        this.niceMax = Math.ceil(max / tickSpacing) * tickSpacing;
    }

    private double niceNum(double range, boolean round) {
        double exponent = Math.floor(Math.log10(range));
        double fraction = range / Math.pow(10, exponent);
        double niceFraction;

        if (round) {
            if (fraction < 1.5) niceFraction = 1;
            else if (fraction < 3) niceFraction = 2;
            else if (fraction < 7) niceFraction = 5;
            else niceFraction = 10;
        } else {
            if (fraction <= 1) niceFraction = 1;
            else if (fraction <= 2) niceFraction = 2;
            else if (fraction <= 5) niceFraction = 5;
            else niceFraction = 10;
        }

        return niceFraction * Math.pow(10, exponent);
    }

    // Set min & max together
    public void setMinMax(double min, double max) {
        this.min = min;
        this.max = max;
        calculate();
    }

    // Setters & Getters
    public void setMaxTicks(int maxTicks) {
        this.maxTicks = maxTicks;
        calculate();
    }

    public double getTickSpacing() { return tickSpacing; }
    public double getNiceMin() { return niceMin; }
    public double getNiceMax() { return niceMax; }
    public int getMaxTicks() { return maxTicks; }
    public double getMin() { return min; }
    public void setMin(double min) {
        this.min = min;
        calculate();
    }
    public double getMax() { return max; }
    public void setMax(double max) {
        this.max = max;
        calculate();
    }
}
