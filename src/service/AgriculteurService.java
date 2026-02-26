package service;

import dao.GenericDAO;
import entites.Agriculteur;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgriculteurService implements GenericDAO<Agriculteur> {

    private Connection conn = DBConnection.getConnection();
    

    @Override
    public void create(Agriculteur a) {
        String sql = "INSERT INTO Agriculteur(nom,commune,contact) VALUES (?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, a.getNom());
            ps.setString(2, a.getCommune());
            ps.setString(3, a.getContact());
            ps.executeUpdate();

            // récupérer id auto généré
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                a.setIdAgriculteur(rs.getInt(1));
            }

            System.out.println("Agriculteur ajouté !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Agriculteur a) {
        String sql = "UPDATE Agriculteur SET nom=?, commune=?, contact=? WHERE id_agriculteur=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, a.getNom());
            ps.setString(2, a.getCommune());
            ps.setString(3, a.getContact());
            ps.setInt(4, a.getIdAgriculteur());
            ps.executeUpdate();
            System.out.println("Agriculteur modifié !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Agriculteur WHERE id_agriculteur=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Agriculteur supprimé !");
        } catch (SQLException e) {
            System.out.println("Impossible de supprimer : agriculteur utilisé !");
        }
    }

    // 🔥 IMPORTANT : cette méthode était cassée
    @Override
    public Agriculteur findById(int id) {
        Agriculteur a = null;
        try {
            String sql = "SELECT * FROM Agriculteur WHERE id_agriculteur=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                a = new Agriculteur(
                        rs.getInt("id_agriculteur"),
                        rs.getString("nom"),
                        rs.getString("commune"),
                        rs.getString("contact")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    @Override
    public List<Agriculteur> findAll() {
        List<Agriculteur> list = new ArrayList<>();
        String sql = "SELECT * FROM Agriculteur";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new Agriculteur(
                        rs.getInt("id_agriculteur"),
                        rs.getString("nom"),
                        rs.getString("commune"),
                        rs.getString("contact")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // ✔️ version delete utilisée par tes tests
    public void delete(Agriculteur a) {
        delete(a.getIdAgriculteur());
    }
   public List<Agriculteur> search(String keyword) {

    List<Agriculteur> list = new ArrayList<>();
    String sql = "SELECT * FROM Agriculteur WHERE nom LIKE ? OR commune LIKE ?";

    try {
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, "%" + keyword + "%");
        ps.setString(2, "%" + keyword + "%");

        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            list.add(new Agriculteur(
                    rs.getInt("id_agriculteur"),
                    rs.getString("nom"),
                    rs.getString("commune"),
                    rs.getString("contact")
            ));
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return list;
}
}
