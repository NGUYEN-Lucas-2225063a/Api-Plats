package fr.univamu.iut.plats;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.List;

/**
 * Classe utilisée pour récupérer les informations nécessaires à la ressource
 * (permet de dissocier ressource et mode d'accès aux données)
 */
public class PlatService {

    /**
     * Objet permettant d'accéder au dépôt où sont stockées les informations sur les plats
     */
    protected PlatRepositoryInterface platRepo;

    /**
     * Constructeur permettant d'injecter l'accès aux données
     *
     * @param platRepo objet implémentant l'interface d'accès aux données
     */
    public PlatService(PlatRepositoryInterface platRepo) {
        this.platRepo = platRepo;
    }

    /**
     * Méthode retournant les informations sur les plats au format JSON
     *
     * @return une chaîne de caractères contenant les informations au format JSON
     */
    public String getAllPlatsJSON() {
        List<Plat> allPlats = platRepo.getAllPlats();

        // Création du JSON et conversion de la liste de plats
        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(allPlats);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return result;
    }

    /**
     * Méthode retournant au format JSON les informations sur un plat recherché
     *
     * @param id l'identifiant du plat recherché
     * @return une chaîne de caractères contenant les informations au format JSON
     */
    public String getPlatJSON(int id) {
        String result = null;
        Plat plat = platRepo.getPlat(id);

        // Si le plat a été trouvé
        if (plat != null) {
            // Création du JSON et conversion du plat
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(plat);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }

    /**
     * Méthode permettant d'ajouter un nouveau plat
     *
     * @param plat objet Plat représentant le plat à ajouter
     * @return l'objet Plat ajouté
     */
    public boolean updatePlat(int id, Plat plat) {
        return platRepo.updatePlat(id, plat.nom, plat.description, plat.prix, plat.createurNom);
    }

}
