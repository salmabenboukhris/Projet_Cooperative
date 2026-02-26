package presentation;

import entites.ProduitAgro;
import service.ProduitService;
import java.util.List;

public class ProduitTest {

    public static void main(String[] args) {

        ProduitService service = new ProduitService();

        System.out.println("===== TEST PRODUIT =====");

        // 🔹 CREATE
        ProduitAgro p1 = new ProduitAgro("Tomate", "Legume", 3.5);
        service.create(p1);

        ProduitAgro p2 = new ProduitAgro("Orange", "Fruit", 4.2);
        service.create(p2);

        // 🔹 FIND ALL
        System.out.println("\nListe des produits :");
        List<ProduitAgro> list = service.findAll();
        for (ProduitAgro p : list) {
            System.out.println(p);
        }

        // 🔹 UPDATE
        System.out.println("\nModification prix produit...");
        p1.setPrixKg(5.0);
        service.update(p1);

        // 🔹 FIND BY ID
        System.out.println("\nRecherche produit par ID :");
        ProduitAgro prod = service.findById(p1.getIdProduit());
        if (prod != null)
            System.out.println(prod);
        else
            System.out.println("Produit non trouvé");

        // 🔹 DELETE
        System.out.println("\nSuppression produit...");
       service.delete(p2.getIdProduit());


        // 🔹 LISTE APRÈS DELETE
        System.out.println("\nListe après suppression :");
        for (ProduitAgro p : service.findAll()) {
            System.out.println(p);
        }
    }
}
