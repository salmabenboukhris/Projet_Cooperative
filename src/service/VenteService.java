package service;

import dao.GenericDAO;
import entites.*;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VenteService implements GenericDAO<VenteAgro> {

    private Connection conn = DBConnection.getConnection();

    @Override
    public void create(VenteAgro v) {
        String sql = "INSERT INTO VenteAgro(id_produit,id_agriculteur,date_vente,quantite) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, v.getProduit().getIdProduit());
            ps.setInt(2, v.getAgriculteur().getIdAgriculteur());
            ps.setDate(3, new java.sql.Date(v.getDate().getTime()));
            ps.setDouble(4, v.getQuantite());
            ps.executeUpdate();
            System.out.println("Vente ajoutée !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(VenteAgro v) { }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM VenteAgro WHERE id_vente=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Vente supprimée !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public VenteAgro findById(int id) { return null; }

    @Override
    public List<VenteAgro> findAll() {
        List<VenteAgro> list = new ArrayList<>();
        String sql = "SELECT * FROM VenteAgro";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
           while (rs.next()) {

    ProduitAgro p = new ProduitAgro();
    p.setIdProduit(rs.getInt("id_produit"));

    Agriculteur a = new Agriculteur();
    a.setIdAgriculteur(rs.getInt("id_agriculteur"));

    VenteAgro v = new VenteAgro();
    v.setIdVente(rs.getInt("id_vente"));
    v.setProduit(p);
    v.setAgriculteur(a);
    v.setDate(rs.getDate("date_vente"));
    v.setQuantite(rs.getDouble("quantite"));

    list.add(v);
}

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public Map<String, Integer> countVentesParProduit() {
    Map<String, Integer> stats = new HashMap<>();

    String sql =
        "SELECT p.nom_produit, COUNT(v.id_vente) as total " +
        "FROM venteagro v " +
        "JOIN produitagro p ON v.id_produit = p.id_produit " +
        "GROUP BY p.nom_produit";

    try {
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            stats.put(
                rs.getString("nom_produit"),
                rs.getInt("total")
            );
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return stats;
}
    // 🔍 Recherche paramétrée sur nom produit ou nom agriculteur
    public List<VenteAgro> search(String keyword) {
        String like = "%" + keyword + "%";
        return query(
                "SELECT v.id_vente, v.date_vente, v.quantite, "
                        + "p.id_produit, p.nom AS nom_produit, p.type, p.prix_kg, "
                        + "a.id_agriculteur, a.nom AS nom_agri, a.commune, a.contact "
                        + "FROM VenteAgro v "
                        + "JOIN ProduitAgro p ON p.id_produit = v.id_produit "
                        + "JOIN Agriculteur a ON a.id_agriculteur = v.id_agriculteur "
                        + "WHERE p.nom LIKE ? OR a.nom LIKE ?",
                like, like);
    }

   
    // méthode privée partagée pour éviter la duplication
    private List<VenteAgro> query(String sql, String param1, String param2) {
        List<VenteAgro> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            if (param1 != null) {
                ps.setString(1, param1);
                ps.setString(2, param2);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProduitAgro p = new ProduitAgro(
                        rs.getInt("id_produit"),
                        rs.getString("nom_produit"),
                        rs.getString("type"),
                        rs.getDouble("prix_kg"));

                Agriculteur a = new Agriculteur(
                        rs.getInt("id_agriculteur"),
                        rs.getString("nom_agri"),
                        rs.getString("commune"),
                        rs.getString("contact"));

                VenteAgro v = new VenteAgro();
                v.setIdVente(rs.getInt("id_vente"));
                v.setProduit(p);
                v.setAgriculteur(a);
                v.setDate(rs.getDate("date_vente"));
                v.setQuantite(rs.getDouble("quantite"));
                list.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}