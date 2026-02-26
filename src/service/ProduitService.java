package service;

import dao.GenericDAO;
import entites.ProduitAgro;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitService implements GenericDAO<ProduitAgro> {

    private Connection conn = DBConnection.getConnection();

   @Override
public void create(ProduitAgro p) {

    String sql = "INSERT INTO ProduitAgro(nom,type,prix_kg) VALUES (?,?,?)";

    try {
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, p.getNom());
        ps.setString(2, p.getType());
        ps.setDouble(3, p.getPrixKg());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            p.setIdProduit(rs.getInt(1));   // ⭐ IMPORTANT
        }

        System.out.println("Produit ajouté !");

    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    @Override
    public void update(ProduitAgro p) {
        String sql = "UPDATE ProduitAgro SET nom=?, type=?, prix_kg=? WHERE id_produit=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getType());
            ps.setDouble(3, p.getPrixKg());
            ps.setInt(4, p.getIdProduit());
            ps.executeUpdate();
            System.out.println("Produit modifié !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM ProduitAgro WHERE id_produit=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Produit supprimé !");
        } catch (SQLException e) {
            System.out.println("Impossible de supprimer : produit utilisé !");
        }
    }

    @Override
    public ProduitAgro findById(int id) {
        String sql = "SELECT * FROM ProduitAgro WHERE id_produit=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new ProduitAgro(
                        rs.getInt("id_produit"),
                        rs.getString("nom"),
                        rs.getString("type"),
                        rs.getDouble("prix_kg")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ProduitAgro> findAll() {
        List<ProduitAgro> list = new ArrayList<>();
        String sql = "SELECT * FROM ProduitAgro";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                list.add(new ProduitAgro(
                        rs.getInt("id_produit"),
                        rs.getString("nom"),
                        rs.getString("type"),
                        rs.getDouble("prix_kg")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<ProduitAgro> search(String keyword) {
    List<ProduitAgro> list = new ArrayList<>();
    String sql = "SELECT * FROM ProduitAgro WHERE nom LIKE ? OR type LIKE ?";
    try {
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, "%" + keyword + "%");
        ps.setString(2, "%" + keyword + "%");
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            list.add(new ProduitAgro(
                    rs.getInt("id_produit"),
                    rs.getString("nom"),
                    rs.getString("type"),
                    rs.getDouble("prix_kg")
            ));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}
}
