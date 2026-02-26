package entites;

public class Utilisateur {
    private int id;
    private String username;
    private String passwordHash;

    public Utilisateur() {}

    public Utilisateur(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public Utilisateur(int id, String username, String passwordHash) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getPasswordHash() { return passwordHash; }

    public void setId(int id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
}
