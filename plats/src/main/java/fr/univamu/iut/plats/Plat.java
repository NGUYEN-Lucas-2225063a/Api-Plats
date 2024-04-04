package fr.univamu.iut.plats;

/// By Lucas N'guyen
/**
 * Classe représentant un plat
 */
public class Plat {

    protected int id;
    protected String nom;
    protected String description;
    protected double prix;
    protected String createurNom; // Nom de la personne ayant créé le plat

    // Constructeur
    public Plat(String nom, String description, double prix, String createurNom) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.createurNom = createurNom;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getCreateurNom() {
        return createurNom;
    }

    public void setCreateurNom(String createurNom) {
        this.createurNom = createurNom;
    }


    @Override
    public String toString() {
        return "Plat{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                ", createurNom='" + createurNom + '\'' +
                '}';
    }
}
