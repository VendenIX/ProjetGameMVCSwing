package projetgamemvcswing.vue.InterfaceGraphique;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Islem
 */


public class DottedLineSeparator extends JComponent {

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Set the stroke to a dashed pattern
        float[] dashPattern = {2.0f, 2.0f};
        g2d.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, dashPattern, 0.0f));

        // Draw a horizontal line
        g2d.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);

        g2d.dispose();
    }
}

