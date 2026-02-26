package presentation;

import entites.Agriculteur;
import service.AgriculteurService;
import java.util.List;

public class AgriculteurTest {

    public static void main(String[] args) {

        AgriculteurService service = new AgriculteurService();

        System.out.println("===== TEST AGRICULTEUR =====");

        // creation des agriculteurs
        Agriculteur a1 = new Agriculteur("Ahmed", "Taroudant", "0600000000");
        service.create(a1);

        Agriculteur a2 = new Agriculteur("Salma", "Agadir", "0611111111");
        service.create(a2);

        //trouver tous les agriculteurs
        System.out.println("\nListe des agriculteurs :");
        List<Agriculteur> list = service.findAll();
        for (Agriculteur a : list) {
            System.out.println(a);
        }

        // modifier agriculteur
        a1.setNom("Ahmed Updated");
        service.update(a1);

        // trouver un agriculteur par son id
        System.out.println("\nRecherche par ID :");
        System.out.println(service.findById(a1.getIdAgriculteur()));

        // supprimer 
        service.delete(a2);

        System.out.println("\nAprès suppression :");
        for (Agriculteur a : service.findAll()) {
            System.out.println(a);
        }
    }
}
