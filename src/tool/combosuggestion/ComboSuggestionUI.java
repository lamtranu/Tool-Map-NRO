package tool.combosuggestion;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
/**
* ComboSuggestionUI: Custom UI cho JComboBox với
* - Arrow button tùy chỉnh
* - Border focus màu xanh
* - Renderer đẹp
* - Scroll bar custom
* - Hỗ trợ AutoComplete (cần thư viện SwingX)
*/
public class ComboSuggestionUI extends BasicComboBoxUI {
@Override
public void installUI(JComponent c) {
super.installUI(c);
// Custom border
final Border border = new Border();
// Editor JTextField
JTextField txt = (JTextField) comboBox.getEditor().getEditorComponent();
txt.addFocusListener(new FocusAdapter() {
@Override
public void focusGained(FocusEvent fe) {
border.setColor(new Color(128, 189, 255));
}
@Override
public void focusLost(FocusEvent fe) {
border.setColor(new Color(206, 212, 218));
}
});
// Popup listener cho arrow button
comboBox.addPopupMenuListener(new PopupMenuListener() {
@Override
public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
arrowButton.setBackground(new Color(180, 180, 180));
}
@Override
public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
arrowButton.setBackground(new Color(150, 150, 150));
}
@Override
public void popupMenuCanceled(PopupMenuEvent e) {
arrowButton.setBackground(new Color(150, 150, 150));
}
});
// Hỗ trợ AutoComplete (cần thư viện SwingX)
try {
Class.forName("org.jdesktop.swingx.autocomplete.AutoCompleteDecorator")
.getMethod("decorate", JComboBox.class)
.invoke(null, comboBox);
} catch (Exception ignored) {}
// Cài đặt JTextField
txt.setSelectionColor(new Color(54, 189, 248));
txt.setBorder(new EmptyBorder(0, 4, 0, 4));
comboBox.setBackground(Color.WHITE);
comboBox.setBorder(border);
}
@Override
protected JButton createArrowButton() {
return new ArrowButton();
}
@Override
protected ComboPopup createPopup() {
return new ComboSuggestionPopup(comboBox);
}
@Override
protected ListCellRenderer createRenderer() {
return (jlist, value, index, isSelected, cellHasFocus) -> {
String text = value == null ? "" : value.toString();
JLabel label = new JLabel(text);
label.setFont(comboBox.getFont());
label.setBorder(index >= 0 ? new EmptyBorder(5, 8, 5, 8) : null);
label.setOpaque(isSelected);
if (isSelected) {
label.setBackground(new Color(240, 240, 240));
label.setForeground(new Color(17, 155, 215));
} else {
label.setBackground(Color.WHITE);
label.setForeground(Color.BLACK);
}
return label;
};
}
@Override
public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
g.setColor(hasFocus ? new Color(128, 189, 255) : Color.WHITE);
g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
}
// === Inner classes === //
private class ComboSuggestionPopup extends BasicComboPopup {
public ComboSuggestionPopup(JComboBox<Object> combo) {
super(combo);
setBorder(new Border(1));
}
@Override
protected JScrollPane createScroller() {
JScrollPane scroll = super.createScroller();
this.list.setBackground(Color.WHITE);
JScrollBar sb = new JScrollBar();
sb.setPreferredSize(new Dimension(12, 70));
scroll.setVerticalScrollBar(sb);
return scroll;
}
}
private class ArrowButton extends JButton {
public ArrowButton() {
setContentAreaFilled(false);
setBorder(new EmptyBorder(5, 5, 5, 5));
setBackground(new Color(150, 150, 150));
}
@Override
public void paint(Graphics g) {
super.paint(g);
Graphics2D g2 = (Graphics2D) g.create();
g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
int width = getWidth();
int height = getHeight();
int sizeX = 12;
int sizeY = 8;
int x = (width - sizeX) / 2;
int y = (height - sizeY) / 2;
int[] px = {x, x + sizeX, x + sizeX / 2};
int[] py = {y, y, y + sizeY};
g2.setColor(getBackground());
g2.fillPolygon(px, py, px.length);
g2.dispose();
}
}
private class Border extends EmptyBorder {
private Color focusColor = new Color(128, 189, 255);
private Color color = new Color(206, 212, 218);
public Border(int border) {
super(border, border, border, border);
}
public Border() {
this(5);
}
public void setColor(Color color) {
this.color = color;
}
public void setFocusColor(Color focusColor) {
this.focusColor = focusColor;
}
@Override
public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
Graphics2D g2 = (Graphics2D) g.create();
g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
g2.setColor(c.isFocusOwner() ? focusColor : color);
g2.drawRect(x, y, width - 1, height - 1);
g2.dispose();
}
}
}
