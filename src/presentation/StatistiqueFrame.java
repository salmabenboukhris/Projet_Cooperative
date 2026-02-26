package presentation;

import static com.sun.org.apache.xpath.internal.XPath.SELECT;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import java.util.Map;
import static javax.swing.text.html.HTML.Tag.SELECT;



public class StatistiqueFrame extends JFrame {
    private service.VenteService venteService = new service.VenteService();

    public StatistiqueFrame() {
        setTitle("Statistiques des ventes");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/cooperative", "root", "");

           String sql =
    "SELECT p.nom AS produit, " +
    "a.nom AS agriculteur, " +
    "COUNT(v.id_vente) AS nombre_ventes " +
    "FROM VenteAgro v " +
    "JOIN ProduitAgro p ON v.id_produit = p.id_produit " +
    "JOIN Agriculteur a ON v.id_agriculteur = a.id_agriculteur " +
    "GROUP BY p.nom, a.nom " +
    "ORDER BY p.nom";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String produit = rs.getString("produit");
                String agriculteur = rs.getString("agriculteur");
                int nbVentes = rs.getInt("nombre_ventes");

                dataset.addValue(nbVentes, agriculteur, produit);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Nombre de ventes par produit et agriculteur",
                "Produits",
                "Nombre de ventes",
                dataset
        );

        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new Dimension(800, 600));

        add(panel);
        setVisible(true);
    }
    

    private void afficherGraphe() {

    service.VenteService vs = new service.VenteService();
    Map<String,Integer> stats = vs.countVentesParProduit();

    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    for (String produit : stats.keySet()) {
        dataset.addValue(stats.get(produit), "Ventes", produit);
    }

    JFreeChart chart = ChartFactory.createBarChart(
            "Nombre de ventes par produit",
            "Produit",
            "Nombre de ventes",
            dataset
    );

    ChartPanel panel = new ChartPanel(chart);
    setContentPane(panel);
}
}