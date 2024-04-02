package fr.univamu.iut.plats;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

/**
 * Ressource associée aux plats
 * (point d'accès de l'API REST)
 */
@Path("/plats")
public class PlatResource {

    /**
     * Service utilisé pour accéder aux données des plats et récupérer/modifier leurs informations
     */
    private PlatService service;

    /**
     * Constructeur par défaut
     */
    public PlatResource() {}

    /**
     * Constructeur permettant d'initialiser le service avec une interface d'accès aux données
     * @param platRepo objet implémentant l'interface d'accès aux données
     */
    public PlatResource(PlatRepositoryInterface platRepo) {
        this.service = new PlatService(platRepo);
    }

    /**
     * Constructeur permettant d'initialiser le service d'accès aux plats
     */
    public PlatResource(PlatService service) {
        this.service = service;
    }

    /**
     * Enpoint permettant de publier de tous les plats enregistrés
     *
     * @return la liste des plats (avec leurs informations) au format JSON
     */
    @GET
    @Produces("application/json")
    public String getAllPlats() {
        return service.getAllPlatsJSON();
    }

    /**
     * Endpoint permettant de publier les informations d'un plat dont l'identifiant est passé paramètre dans le chemin
     *
     * @param id identifiant du plat recherché
     * @return les informations du plat recherché au format JSON
     */
    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getPlat(@PathParam("id") int id) {

        String result = service.getPlatJSON(id);

        // si le plat n'a pas été trouvé
        if (result == null)
            throw new NotFoundException();

        return result;
    }

    /**
     * Endpoint permettant de mettre à jours les informations d'un plat
     *
     * @param id   identifiant du plat à mettre à jour
     * @param plat le plat transmis en HTTP au format JSON et converti en objet Plat
     * @return une réponse "updated" si la mise à jour a été effectuée, une erreur NotFound sinon
     */
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public Response updatePlat(@PathParam("id") int id, Plat plat) {

        // si le plat n'a pas été trouvé
        if (!service.updatePlat(id, plat))
            throw new NotFoundException();
        else
            return Response.ok("updated").build();
    }
}
