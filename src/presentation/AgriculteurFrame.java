package presentation;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class AgriculteurFrame extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private service.AgriculteurService agriculteurService = new service.AgriculteurService();
private JTextField searchField = new JTextField(15);
private JButton searchBtn = new JButton("Rechercher");
private JButton resetBtn = new JButton("Afficher tout");
    public AgriculteurFrame() {
        setTitle("Gestion des Agriculteurs");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        model = new DefaultTableModel(new Object[]{"ID", "Nom", "Commune", "Contact"}, 0);
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

        addBtn.addActionListener(e -> addAgriculteur());
        editBtn.addActionListener(e -> editAgriculteur());
        deleteBtn.addActionListener(e -> deleteAgriculteur());
    searchBtn.addActionListener(e -> searchAgriculteur());
resetBtn.addActionListener(e -> refreshTable());
    }

    private void refreshTable() {
        model.setRowCount(0);
        for (entites.Agriculteur a : agriculteurService.findAll()) {
            model.addRow(new Object[]{a.getIdAgriculteur(), a.getNom(), a.getCommune(), a.getContact()});
        }
    }

    private void addAgriculteur() {
        JTextField nomField = new JTextField();
        JTextField communeField = new JTextField();
        JTextField contactField = new JTextField();
        Object[] fields = {"Nom:", nomField, "Commune:", communeField, "Contact:", contactField};
        int res = JOptionPane.showConfirmDialog(this, fields, "Ajouter Agriculteur", JOptionPane.OK_CANCEL_OPTION);
        if (res == JOptionPane.OK_OPTION) {
            try {
                String nom = nomField.getText();
                String commune = communeField.getText();
                String contact = contactField.getText();
                agriculteurService.create(new entites.Agriculteur(nom, commune, contact));
                refreshTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur: " + ex.getMessage());
            }
        }
    }
private void searchAgriculteur() {

    String keyword = searchField.getText();

    model.setRowCount(0);

    for (entites.Agriculteur a : agriculteurService.search(keyword)) {
        model.addRow(new Object[]{
                a.getIdAgriculteur(),
                a.getNom(),
                a.getCommune(),
                a.getContact()
        });
    }
}
    private void editAgriculteur() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Sélectionnez un agriculteur à modifier.");
            return;
        }
        int id = (int) model.getValueAt(row, 0);
        entites.Agriculteur a = agriculteurService.findById(id);
        JTextField nomField = new JTextField(a.getNom());
        JTextField communeField = new JTextField(a.getCommune());
        JTextField contactField = new JTextField(a.getContact());
        Object[] fields = {"Nom:", nomField, "Commune:", communeField, "Contact:", contactField};
        int res = JOptionPane.showConfirmDialog(this, fields, "Modifier Agriculteur", JOptionPane.OK_CANCEL_OPTION);
        if (res == JOptionPane.OK_OPTION) {
            try {
                a.setNom(nomField.getText());
                a.setCommune(communeField.getText());
                a.setContact(contactField.getText());
                agriculteurService.update(a);
                refreshTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur: " + ex.getMessage());
            }
        }
    }

    private void deleteAgriculteur() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Sélectionnez un agriculteur à supprimer.");
            return;
        }
        int id = (int) model.getValueAt(row, 0);
        int res = JOptionPane.showConfirmDialog(this, "Confirmer suppression?", "Supprimer", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {
            agriculteurService.delete(id);
            refreshTable();
        }
    }
}
