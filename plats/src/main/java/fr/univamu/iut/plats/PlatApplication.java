package fr.univamu.iut.plats;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe principale de l'application des menus
 */
@ApplicationPath("/api")
@ApplicationScoped
public class PlatApplication extends Application {
    @Override
    public Set<Object> getSingletons() {
        Set<Object> set = new HashSet<>();

        // Création de la connection à la base de données et initialisation du service associé
        PlatService service = null;
        try {
            PlatRepositoryMariadb db = new PlatRepositoryMariadb("jdbc:mariadb://mysql-javalog.alwaysdata.net/javalog_library_db", "javalog", "Zetsurider2*");
            service = new PlatService(db);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // Création de la ressource en lui passant paramètre les services à exécuter en fonction
        // des différents endpoints proposés (i.e. requêtes HTTP acceptées)
        set.add(new PlatResource(service));

        return set;
    }
}
