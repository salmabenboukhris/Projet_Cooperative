package presentation;

import javax.swing.*;
import java.awt.*;

public class PanelImage extends JPanel {

    private Image image;

    public PanelImage(String path) {
        image = new ImageIcon(getClass().getResource(path)).getImage();
        setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}