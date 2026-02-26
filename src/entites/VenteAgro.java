package entites;

import java.util.Date;

public class VenteAgro {

    private int idVente;
    private ProduitAgro produit;
    private Agriculteur agriculteur;
    private Date date;
    private double quantite;

    public VenteAgro(){}

    public VenteAgro(ProduitAgro produit, Agriculteur agriculteur, Date date, double quantite) {
        this.produit = produit;
        this.agriculteur = agriculteur;
        this.date = date;
        this.quantite = quantite;
    }

    public int getIdVente() { return idVente; }
    public void setIdVente(int idVente) { this.idVente = idVente; }

    public ProduitAgro getProduit() { return produit; }
    public void setProduit(ProduitAgro produit) { this.produit = produit; }

    public Agriculteur getAgriculteur() { return agriculteur; }
    public void setAgriculteur(Agriculteur agriculteur) { this.agriculteur = agriculteur; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public double getQuantite() { return quantite; }
    public void setQuantite(double quantite) { this.quantite = quantite; }
@Override
public String toString() {
    return "Vente #" + idVente +
            " | Produit=" + produit.getNom() +
            " | Agriculteur=" + agriculteur.getNom() +
            " | Quantité=" + quantite;
}



}
