package presentation;

import entites.Agriculteur;
import service.AgriculteurService;
import java.util.List;

public class AgriculteurTest {

    public static void main(String[] args) {

        AgriculteurService service = new AgriculteurService();

        System.out.println("===== TEST AGRICULTEUR =====");

        // 🔹 CREATE
        Agriculteur a1 = new Agriculteur("Ahmed", "Taroudant", "0600000000");
        service.create(a1);

        Agriculteur a2 = new Agriculteur("Salma", "Agadir", "0611111111");
        service.create(a2);

        // 🔹 FIND ALL
        System.out.println("\nListe des agriculteurs :");
        List<Agriculteur> list = service.findAll();
        for (Agriculteur a : list) {
            System.out.println(a);
        }

        // 🔹 UPDATE
        a1.setNom("Ahmed Updated");
        service.update(a1);

        // 🔹 FIND BY ID
        System.out.println("\nRecherche par ID :");
        System.out.println(service.findById(a1.getIdAgriculteur()));

        // 🔹 DELETE
        service.delete(a2);

        System.out.println("\nAprès suppression :");
        for (Agriculteur a : service.findAll()) {
            System.out.println(a);
        }
    }
}
