package tool.chart;

public class ModelChart {

    private String label;
    private double[] values;

    public ModelChart() {
    }

    public ModelChart(String label, double[] values) {
        this.label = label;
        this.values = values;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double[] getValues() {
        return values;
    }

    public void setValues(double[] values) {
        this.values = values;
    }

    /** Lấy giá trị lớn nhất trong mảng values, trả về 0 nếu values null hoặc rỗng */
    public double getMaxValues() {
        if (values == null || values.length == 0) return 0;
        double max = values[0];
        for (double v : values) {
            if (v > max) max = v;
        }
        return max;
    }
}
