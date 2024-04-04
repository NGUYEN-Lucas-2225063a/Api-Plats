package fr.univamu.iut.plats;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant d'accéder aux plats stockés dans une base de données MariaDB
 */
public class PlatRepositoryMariadb implements PlatRepositoryInterface, Closeable {

    /**
     * Accès à la base de données (session)
     */
    protected Connection dbConnection;

    /**
     * Constructeur de la classe
     *
     * @param url  chaîne de caractères avec l'URL de connexion à la base de données
     * @param user chaîne de caractères contenant l'identifiant de connexion à la base de données
     * @param pwd  chaîne de caractères contenant le mot de passe à utiliser
     */
    public PlatRepositoryMariadb(String url, String user, String pwd) throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection(url, user, pwd);
    }

    @Override
    public void close() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Plat getPlat(int id) {
        Plat selectedPlat = null;

        String query = "SELECT * FROM Plat WHERE id=?";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();

            if (result.next()) {
                String nom = result.getString("nom");
                String description = result.getString("description");
                double prix = result.getDouble("prix");
                String createurNom = result.getString("createur_nom");

                selectedPlat = new Plat(nom, description, prix, createurNom);
                selectedPlat.setId(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return selectedPlat;
    }

    @Override
    public List<Plat> getAllPlats() {
        List<Plat> plats = new ArrayList<>();

        String query = "SELECT * FROM Plat";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                int id = result.getInt("id");
                String nom = result.getString("nom");
                String description = result.getString("description");
                double prix = result.getDouble("prix");
                String createurNom = result.getString("createur_nom");

                Plat plat = new Plat(nom, description, prix, createurNom);
                plat.setId(id);
                plats.add(plat);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return plats;
    }

    @Override
    public boolean updatePlat(int id, String nom, String description, double prix, String createurNom) {
        String query = "UPDATE Plat SET nom=?, description=?, prix=?, createur_nom=? WHERE id=?";
        int rowsAffected = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(5, id);
            ps.setString(1, nom);
            ps.setString(2, description);
            ps.setDouble(3, prix);
            ps.setString(4, createurNom);


            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rowsAffected > 0;
    }

    @Override
    public Plat addPlat(Plat plat) {
        String query = "INSERT INTO Plat (nom, description, prix, createur_nom) VALUES (?, ?, ?, ?)";
        int id = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, plat.getNom());
            ps.setString(2, plat.getDescription());
            ps.setDouble(3, plat.getPrix());
            ps.setString(4, plat.getCreateurNom());

            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        plat.setId(id);
        return plat;
    }

    @Override
    public boolean deletePlat(int id) {
        String query = "DELETE FROM Plat WHERE id=?";
        int rowsAffected = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rowsAffected > 0;
    }
}
