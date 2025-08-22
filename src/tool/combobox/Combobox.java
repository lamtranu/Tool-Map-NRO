package tool.combobox;

import tool.scrollbar.ScrollBarCustom;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

@SuppressWarnings("serial")
public class Combobox<E> extends JComboBox<E> {

    private String labelText = "Label";
    private Color lineColor = new Color(3, 155, 216);
    private boolean mouseOver;

    public Combobox() {
        setBackground(new Color(65, 65, 65));
        setBorder(new EmptyBorder(15, 3, 5, 3));
        setUI(new ComboUI(this));
        setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                                                          int index, boolean isSelected, boolean cellHasFocus) {
                Component com = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setBorder(new EmptyBorder(5, 5, 5, 5));
                if (isSelected) {
                    com.setBackground(new Color(240, 240, 240));
                }
                return com;
            }
        });
    }

    public String getLabelText() {
        return labelText;
    }

    public void setLabelText(String labelText) {
        this.labelText = labelText;
        repaint();
    }

    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
        repaint();
    }

    public void setLineChooseColor(final Color color) {
        setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                                                          int index, boolean isSelected, boolean cellHasFocus) {
                Component com = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setBorder(new EmptyBorder(5, 5, 5, 5));
                if (isSelected) {
                    com.setBackground(color);
                }
                return com;
            }
        });
    }

    // ======================= Custom ComboBox UI ======================= //
    private class ComboUI extends BasicComboBoxUI {
        private final Animator animator;
        private boolean animateHintText = true;
        private float location;
        private boolean show;
        private final Combobox<?> combo;

        public ComboUI(Combobox<?> combo) {
            this.combo = combo;

            // Hover effect
            combo.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    mouseOver = true;
                    combo.repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    mouseOver = false;
                    combo.repaint();
                }
            });

            // Focus animation
            combo.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    showing(false);
                }

                @Override
                public void focusLost(FocusEvent e) {
                    showing(true);
                }
            });

            // Item state change
            combo.addItemListener(e -> {
                if (!combo.isFocusOwner()) {
                    if (combo.getSelectedIndex() == -1) {
                        showing(true);
                    } else {
                        showing(false);
                    }
                }
            });

            // Popup arrow button effect
            combo.addPopupMenuListener(new PopupMenuListener() {
                @Override
                public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                    arrowButton.setBackground(new Color(200, 200, 200));
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

            // Animator
            animator = new Animator(300, new TimingTargetAdapter() {
                @Override
                public void begin() {
                    animateHintText = (combo.getSelectedIndex() == -1);
                }

                @Override
                public void timingEvent(float fraction) {
                    location = fraction;
                    combo.repaint();
                }
            });
            animator.setResolution(0);
            animator.setAcceleration(0.5f);
            animator.setDeceleration(0.5f);
        }

        @Override
        protected JButton createArrowButton() {
            return new ArrowButton();
        }

        @Override
        protected ComboPopup createPopup() {
            BasicComboPopup pop = new BasicComboPopup(comboBox) {
                @Override
                protected JScrollPane createScroller() {
                    list.setFixedCellHeight(30);
                    JScrollPane scroll = new JScrollPane(list);
                    scroll.setBackground(Color.WHITE);
                    ScrollBarCustom sb = new ScrollBarCustom();
                    sb.setUnitIncrement(30);
                    sb.setForeground(new Color(180, 180, 180));
                    scroll.setVerticalScrollBar(sb);
                    return scroll;
                }
            };
            pop.setBorder(new LineBorder(new Color(200, 200, 200), 1));
            return pop;
        }

        @Override
        public void paint(Graphics g, JComponent c) {
            super.paint(g, c);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

            int width = combo.getWidth();
            int height = combo.getHeight();

            g2.setColor(mouseOver ? lineColor : new Color(150, 150, 150));
            g2.fillRect(2, height - 1, width - 4, 1);

            createHintText(g2);
            createLineStyle(g2);

            g2.dispose();
        }

        private void createHintText(Graphics2D g2) {
            Insets in = combo.getInsets();
            g2.setColor(new Color(150, 150, 150));
            FontMetrics fm = g2.getFontMetrics();
            Rectangle2D r2 = fm.getStringBounds(combo.getLabelText(), g2);
            double height = combo.getHeight() - in.top - in.bottom;
            double textY = (height - r2.getHeight()) / 2;

            double size;
            if (animateHintText) {
                size = show ? (18.0 * (1.0 - location)) : (18.0 * location);
            } else {
                size = 18.0;
            }

            g2.drawString(combo.getLabelText(),
                    in.right,
                    (int) (in.top + textY + fm.getAscent() - size));
        }

        private void createLineStyle(Graphics2D g2) {
            if (combo.isFocusOwner()) {
                double width = combo.getWidth() - 4;
                int height = combo.getHeight();
                g2.setColor(lineColor);

                double size = show ? width * (1.0 - location) : width * location;
                double x = (width - size) / 2;

                g2.fillRect((int) (x + 2), height - 2, (int) size, 2);
            }
        }

        private void showing(boolean action) {
            if (animator.isRunning()) {
                animator.stop();
            } else {
                location = 1f;
            }
            animator.setStartFraction(1f - location);
            show = action;
            location = 1f - location;
            animator.start();
        }

        // Custom arrow button
        private class ArrowButton extends JButton {
            public ArrowButton() {
                setContentAreaFilled(false);
                setBorder(new EmptyBorder(5, 5, 5, 5));
                setBackground(new Color(150, 150, 150));
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int w = getWidth();
                int h = getHeight();
                int size = 10;
                int x = (w - size) / 2;
                int y = (h - size) / 2 + 5;
                int[] px = {x, x + size, x + size / 2};
                int[] py = {y, y, y + size};

                g2.setColor(getBackground());
                g2.fillPolygon(px, py, px.length);

                g2.dispose();
            }
        }
    }
}
