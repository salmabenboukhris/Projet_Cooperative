package presentation;

public class CreateUserTest {
    public static void main(String[] args) {
        service.UtilisateurService us = new service.UtilisateurService();
        boolean created = us.createUser("salmaasma", "1234");
        if (created) {
            System.out.println("Utilisateur créé avec succès.");
        } else {
            System.out.println("Échec de création de l'utilisateur.");
        }
    }
}
