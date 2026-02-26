package presentation;

import entites.Agriculteur;
import entites.ProduitAgro;
import entites.VenteAgro;

import service.AgriculteurService;
import service.ProduitService;
import service.VenteService;

import java.util.List;
import java.util.Date;

public class VenteTest {

    public static void main(String[] args) {

        VenteService venteService = new VenteService();
        ProduitService produitService = new ProduitService();
        AgriculteurService agriculteurService = new AgriculteurService();

        System.out.println("===== TEST VENTE =====");

        // 🔹 CREATION PRODUIT
        ProduitAgro p = new ProduitAgro("Pomme", "Fruit", 6);
        produitService.create(p);

        // 🔹 CREATION AGRICULTEUR
        Agriculteur a = new Agriculteur("Youssef", "Marrakech", "0622222222");
        agriculteurService.create(a);

        // 🔹 CREATION VENTE
        VenteAgro v = new VenteAgro(p, a, new Date(), 100);
        venteService.create(v);

        // 🔹 FIND ALL
        System.out.println("\nListe des ventes :");
        List<VenteAgro> list = venteService.findAll();
        for (VenteAgro vente : list) {
            System.out.println(vente);
        }

        // 🔹 UPDATE
        System.out.println("\nModification quantité...");
        v.setQuantite(150);
        venteService.update(v);

        // 🔹 FIND BY ID
        System.out.println("\nRecherche vente par ID :");
        VenteAgro vente = venteService.findById(v.getIdVente());
        if (vente != null)
            System.out.println(vente);
        else
            System.out.println("Vente non trouvée");

        // 🔹 DELETE
        System.out.println("\nSuppression vente...");
        venteService.delete(v.getIdVente());


        // 🔹 LISTE APRÈS DELETE
        System.out.println("\nListe après suppression :");
        for (VenteAgro ve : venteService.findAll()) {
            System.out.println(ve);
        }
    }
}
