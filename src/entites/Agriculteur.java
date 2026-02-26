package entites;

public class Agriculteur {

    private int idAgriculteur;
    private String nom;
    private String commune;
    private String contact;

    public Agriculteur(){}

    public Agriculteur(int id, String nom, String commune, String contact) {
        this.idAgriculteur = id;
        this.nom = nom;
        this.commune = commune;
        this.contact = contact;
    }

    public Agriculteur(String nom, String commune, String contact) {
        this.nom = nom;
        this.commune = commune;
        this.contact = contact;
    }

    public int getIdAgriculteur() { return idAgriculteur; }
    public void setIdAgriculteur(int idAgriculteur) { this.idAgriculteur = idAgriculteur; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getCommune() { return commune; }
    public void setCommune(String commune) { this.commune = commune; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    @Override
    public String toString() {
        return idAgriculteur+" - "+nom+" ("+commune+")";
    }
}
