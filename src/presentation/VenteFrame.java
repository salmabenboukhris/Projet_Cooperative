package presentation;

import entites.VenteAgro;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class VenteFrame extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private service.VenteService venteService = new service.VenteService();
private JTextField txtSearch;
    public VenteFrame() {
        setTitle("Gestion des Ventes");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        model = new DefaultTableModel(new Object[]{"ID", "Produit", "Agriculteur", "Date", "Quantité"}, 0);
        table = new JTable(model);
        refreshTable();

        JScrollPane scrollPane = new JScrollPane(table);

        JButton addBtn = new JButton("Ajouter");
        JButton deleteBtn = new JButton("Supprimer");

        JPanel btnPanel = new JPanel();
         btnPanel.setBackground(new Color(180, 220, 180)); 
        btnPanel.add(addBtn);
        btnPanel.add(deleteBtn);

        add(scrollPane, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> addVente());
        deleteBtn.addActionListener(e -> deleteVente());
        txtSearch = new JTextField(20);
txtSearch.setToolTipText("Rechercher par produit ou agriculteur...");

JPanel topPanel = new JPanel();
topPanel.add(new JLabel("🔍 Recherche : "));
topPanel.add(txtSearch);

add(topPanel, BorderLayout.NORTH);
txtSearch.getDocument().addDocumentListener(new DocumentListener() {

    public void insertUpdate(DocumentEvent e) {
        filter();
    }

   
    public void removeUpdate(DocumentEvent e) {
        filter();
    }

    public void changedUpdate(DocumentEvent e) {
        filter();
    }
});
loadTable(venteService.findAll());

    }
    private void filter() {
    String keyword = txtSearch.getText().trim();

    List<VenteAgro> list;

    if (keyword.isEmpty()) {
        list = venteService.findAll();
    } else {
        list = venteService.search(keyword);
    }

    loadTable(list);
}

    private void refreshTable() {
        model.setRowCount(0);
        for (entites.VenteAgro v : venteService.findAll()) {
            String produit = v.getProduit() != null ? String.valueOf(v.getProduit().getIdProduit()) : "";
            String agri = v.getAgriculteur() != null ? String.valueOf(v.getAgriculteur().getIdAgriculteur()) : "";
            String date = v.getDate() != null ? v.getDate().toString() : "";
            model.addRow(new Object[]{v.getIdVente(), produit, agri, date, v.getQuantite()});
        }
    }

    private void addVente() {
        // Dropdown pour produits
        java.util.List<entites.ProduitAgro> produits = new service.ProduitService().findAll();
        JComboBox<String> produitCombo = new JComboBox<>();
        for (entites.ProduitAgro p : produits) {
            produitCombo.addItem(p.getIdProduit() + " - " + p.getNom());
        }

        // Dropdown pour agriculteurs
        java.util.List<entites.Agriculteur> agris = new service.AgriculteurService().findAll();
        JComboBox<String> agriCombo = new JComboBox<>();
        for (entites.Agriculteur a : agris) {
            agriCombo.addItem(a.getIdAgriculteur() + " - " + a.getNom());
        }

        // Calendrier pour la date
        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd"));

        JTextField quantiteField = new JTextField();
        Object[] fields = {"Produit:", produitCombo, "Agriculteur:", agriCombo, "Date:", dateSpinner, "Quantité:", quantiteField};
        int res = JOptionPane.showConfirmDialog(this, fields, "Ajouter Vente", JOptionPane.OK_CANCEL_OPTION);
        if (res == JOptionPane.OK_OPTION) {
            try {
                int produitIndex = produitCombo.getSelectedIndex();
                int agriIndex = agriCombo.getSelectedIndex();
                entites.ProduitAgro p = produits.get(produitIndex);
                entites.Agriculteur a = agris.get(agriIndex);
                java.util.Date date = (java.util.Date) dateSpinner.getValue();
                double quantite = Double.parseDouble(quantiteField.getText());
                venteService.create(new entites.VenteAgro(p, a, date, quantite));
                refreshTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur: " + ex.getMessage());
            }
        }
    }

    private void deleteVente() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Sélectionnez une vente à supprimer.");
            return;
        }
        int id = (int) model.getValueAt(row, 0);
        int res = JOptionPane.showConfirmDialog(this, "Confirmer suppression?", "Supprimer", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {
            venteService.delete(id);
            refreshTable();
        }
    }
    private void loadTable(List<VenteAgro> list) {

    DefaultTableModel model = (DefaultTableModel) table.getModel();
    model.setRowCount(0); // vider la table

    for (VenteAgro v : list) {
        model.addRow(new Object[]{
            v.getIdVente(),
            v.getProduit().getNom(),
            v.getAgriculteur().getNom(),
            v.getDate(),
            v.getQuantite()
        });
    }
}
}