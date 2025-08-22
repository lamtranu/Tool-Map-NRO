package tool.chart;

import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

public class LegendItem extends JPanel {

    private final LabelColor lbColor;
    private final JLabel lbName;

    public LegendItem(ModelLegend data) {
        lbColor = new LabelColor();
        lbName = new JLabel();

        setOpaque(false);

        lbColor.setBackground(data.getColor()); // màu chính
        lbColor.setBorderColor(Color.DARK_GRAY); // tùy chọn viền
        lbColor.setBorderWidth(1);
        lbName.setText(data.getName());
        lbName.setForeground(new Color(180, 180, 180));

        initLayout();
    }

    private void initLayout() {
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lbColor, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbName)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(lbColor, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbName))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }
}
