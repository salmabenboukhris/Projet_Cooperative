package presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.*;

public class MenuFrame extends JFrame {

    public MenuFrame() {
        BackgroundPanel bg = new BackgroundPanel("/images/photocooperative.jpeg");
        bg.setLayout(new GridBagLayout());
        setContentPane(bg);
        setTitle("Menu");
        setSize(400,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
       
        JButton prod = new JButton("Produits");
        JButton agri = new JButton("Agriculteurs");
        JButton vente = new JButton("Ventes");
        JButton stats = new JButton("Statistiques");

       JPanel p = new JPanel();
p.setOpaque(false);
p.setLayout(new GridLayout(2,2,30,30));
p.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));

Font f = new Font("Arial", Font.BOLD, 18);

prod.setFont(f);
agri.setFont(f);
vente.setFont(f);
stats.setFont(f);

prod.setPreferredSize(new Dimension(200,80));
agri.setPreferredSize(new Dimension(200,80));
vente.setPreferredSize(new Dimension(200,80));
stats.setPreferredSize(new Dimension(200,80));

p.add(prod);
p.add(agri);
p.add(vente);
p.add(stats);

bg.add(p);

        prod.addActionListener(e -> new ProduitFrame().setVisible(true));
        agri.addActionListener(e -> new AgriculteurFrame().setVisible(true));
        vente.addActionListener(e -> new VenteFrame().setVisible(true));
        stats.addActionListener(e -> new StatistiqueFrame().setVisible(true));
    }
}
