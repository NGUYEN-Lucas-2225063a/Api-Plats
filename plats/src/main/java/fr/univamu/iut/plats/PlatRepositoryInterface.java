package fr.univamu.iut.plats;

import java.util.List;

/**
 * Interface d'accès aux données des plats
 */
public interface PlatRepositoryInterface {

    /**
     *  Méthode fermant le dépôt où sont stockées les informations sur les plats
     */
    public void close();

    /**
     * Méthode retournant le plat dont l'ID est passé en paramètre
     * @param id identifiant du plat recherché
     * @return un objet Plat représentant le plat recherché
     */
    public Plat getPlat(int id);

    /**
     * Méthode retournant la liste de tous les plats
     * @return une liste d'objets plats
     */
    public List<Plat> getAllPlats();

    /**
     * Méthode permettant de mettre à jour un plat enregistré
     * @param id identifiant du plat à mettre à jour
     * @param nom nouveau nom du plat
     * @param description nouvelle description du plat
     * @param prix nouveau prix du plat
     * @param createurNom nouveau nom du créateur du plat
     * @return true si le plat existe et la mise à jour a été faite, false sinon
     */
    public boolean updatePlat(int id, String nom, String description, double prix, String createurNom);

    /**
     * Méthode permettant d'ajouter un nouveau plat
     * @param plat objet Plat représentant le plat à ajouter
     * @return l'objet Plat ajouté
     */
    public Plat addPlat(Plat plat);

    /**
     * Méthode permettant de supprimer un plat existant
     * @param id identifiant du plat à supprimer
     * @return true si le plat existe et a été supprimé, false sinon
     */
    public boolean deletePlat(int id);
}
