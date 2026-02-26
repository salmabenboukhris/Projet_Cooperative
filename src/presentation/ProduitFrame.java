package presentation;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class ProduitFrame extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JTextField searchField = new JTextField(15);
private JButton searchBtn = new JButton("Rechercher");

private JButton resetBtn = new JButton("Afficher tout");
    private service.ProduitService produitService = new service.ProduitService();

    public ProduitFrame() {
        setTitle("Gestion des Produits");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        model = new DefaultTableModel(new Object[]{"ID", "Nom", "Type", "Prix/kg"}, 0);
        table = new JTable(model);
        refreshTable();

        JScrollPane scrollPane = new JScrollPane(table);

        JButton addBtn = new JButton("Ajouter");
        JButton editBtn = new JButton("Modifier");
        JButton deleteBtn = new JButton("Supprimer");

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(180, 220, 180));
        btnPanel.add(addBtn);
        btnPanel.add(editBtn);
        btnPanel.add(deleteBtn);
        JPanel topPanel = new JPanel();
topPanel.setBackground(new Color(180,220,180));
topPanel.add(new JLabel("Recherche:"));
topPanel.add(searchField);
topPanel.add(searchBtn);
topPanel.add(resetBtn);

add(topPanel, BorderLayout.NORTH);

        add(scrollPane, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
searchBtn.addActionListener(e -> searchProduit());
resetBtn.addActionListener(e -> refreshTable());
        addBtn.addActionListener(e -> addProduit());
        editBtn.addActionListener(e -> editProduit());
        deleteBtn.addActionListener(e -> deleteProduit());
    }

    private void refreshTable() {
        model.setRowCount(0);
        for (entites.ProduitAgro p : produitService.findAll()) {
            model.addRow(new Object[]{p.getIdProduit(), p.getNom(), p.getType(), p.getPrixKg()});
        }
    }
private void searchProduit() {
    String keyword = searchField.getText();
    model.setRowCount(0);
    for (entites.ProduitAgro p : produitService.search(keyword)) {
        model.addRow(new Object[]{
                p.getIdProduit(),
                p.getNom(),
                p.getType(),
                p.getPrixKg()
        });
    }
}
    private void addProduit() {
        JTextField nomField = new JTextField();
        JTextField typeField = new JTextField();
        JTextField prixField = new JTextField();
        Object[] fields = {"Nom:", nomField, "Type:", typeField, "Prix/kg:", prixField};
        int res = JOptionPane.showConfirmDialog(this, fields, "Ajouter Produit", JOptionPane.OK_CANCEL_OPTION);
        if (res == JOptionPane.OK_OPTION) {
            try {
                String nom = nomField.getText();
                String type = typeField.getText();
                double prix = Double.parseDouble(prixField.getText());
                produitService.create(new entites.ProduitAgro(nom, type, prix));
                refreshTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur: " + ex.getMessage());
            }
        }
    }

    private void editProduit() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Sélectionnez un produit à modifier.");
            return;
        }
        int id = (int) model.getValueAt(row, 0);
        entites.ProduitAgro p = produitService.findById(id);
        JTextField nomField = new JTextField(p.getNom());
        JTextField typeField = new JTextField(p.getType());
        JTextField prixField = new JTextField(String.valueOf(p.getPrixKg()));
        Object[] fields = {"Nom:", nomField, "Type:", typeField, "Prix/kg:", prixField};
        int res = JOptionPane.showConfirmDialog(this, fields, "Modifier Produit", JOptionPane.OK_CANCEL_OPTION);
        if (res == JOptionPane.OK_OPTION) {
            try {
                p.setNom(nomField.getText());
                p.setType(typeField.getText());
                p.setPrixKg(Double.parseDouble(prixField.getText()));
                produitService.update(p);
                refreshTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur: " + ex.getMessage());
            }
        }
    }

    private void deleteProduit() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Sélectionnez un produit à supprimer.");
            return;
        }
        int id = (int) model.getValueAt(row, 0);
        int res = JOptionPane.showConfirmDialog(this, "Confirmer suppression?", "Supprimer", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {
            produitService.delete(id);
            refreshTable();
        }
    }
}
