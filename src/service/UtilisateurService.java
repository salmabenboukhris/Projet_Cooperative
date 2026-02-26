package service;

import util.DBConnection;
import java.sql.*;

public class UtilisateurService {

    private Connection conn = DBConnection.getConnection();

    public boolean login(String username, String password){

        String sql = "SELECT * FROM utilisateur WHERE username=? AND password=?";

        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }
        public boolean createUser(String username, String password) {
            String hashed = util.HashUtil.hash(password);
            String sql = "INSERT INTO utilisateur (username, password) VALUES (?, ?)";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, hashed);
                int rows = ps.executeUpdate();
                return rows > 0;
            } catch(Exception e) {
                e.printStackTrace();
            }
            return false;
        }
}
