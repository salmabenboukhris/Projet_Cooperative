package entites;

public class ProduitAgro {

    private int idProduit;
    private String nom;
    private String type;
    private double prixKg;

    public ProduitAgro(){}

    public ProduitAgro(int idProduit, String nom, String type, double prixKg) {
        this.idProduit = idProduit;
        this.nom = nom;
        this.type = type;
        this.prixKg = prixKg;
    }

    public ProduitAgro(String nom, String type, double prixKg) {
        this.nom = nom;
        this.type = type;
        this.prixKg = prixKg;
    }

    public int getIdProduit() { return idProduit; }
    public void setIdProduit(int idProduit) { this.idProduit = idProduit; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getPrixKg() { return prixKg; }
    public void setPrixKg(double prixKg) { this.prixKg = prixKg; }

    @Override
    public String toString() {
        return idProduit+" - "+nom+" ("+type+") : "+prixKg+" DH";
    }
}
