package controllers;

import models.*;
import play.data.*;
import play.mvc.*;


import java.util.List;

import static play.libs.Json.toJson;

import static play.libs.Json.fromJson;
import org.codehaus.jackson.JsonNode;


public class Humans extends Controller {

    /*
        Retourner une liste (au format JSON) de Humans
        cela correspond à un appel http de type GET
    */
    public static Result getAll() { // GET

        List<Human> list = Human.find.orderBy("lastName").findList();
        return ok(toJson(list));
    }

    /*
        Retrouver un "Human" (au format JSON) par son id
        Cela correspond à un appel http de type GET
        Si il n'existe pas on génère une erreur
    */
    public static Result getById(Long id) { // GET

        Human modelToFind = Human.find.byId(id);

        if(modelToFind!=null) {
            return ok(toJson(modelToFind));
        } else {
            return badRequest("not found");
        }
        
    }

    /*
        Créer ou sauvegarder un "Human", c'est une requête de type POST ou PUT.
        - On récupère les paramètres grâce à bindFromRequest
        - si l'id du modèle n'est pas null c'est une mise à jour (PUT)
        - sinon c'est une création (POST)

    */
    public static Result create() { //POST


        Human model = fromJson(request().body().asJson(), Human.class);

        //Si c'est une nouvelle adresse
        
        if(model.address.id==null) {
            model.address.save();
            System.out.println(model.address.id);
        }
        
        model.save();

        return ok(toJson(model));
        
    }

    public static Result update(Long id) { //PUT
        
        Human model = fromJson(request().body().asJson(), Human.class);

        model.id = id;
        model.update();

        return ok(toJson(model));
        
    }

    /*
        Retrouver un "Human" (au format JSON) par son id
        Puis le supprimer
        Cela correspond à un appel http de type DELETE
        Si il n'existe pas on génère une erreur
    */
    public static Result delete(Long id) { // DELETE

        Human modelToFind = Human.find.byId(id);
        if(modelToFind!=null) {
            modelToFind.delete();
            return ok(toJson(true));
        } else {
            return badRequest("not found");
        }

    }

    /*
        Requêtes de type GET pour ne ramener qu'un certain nombre d'enregistrements
    */
    public static Result query(String fieldName, String value) { // GET
        //humans/lastName/equals/morane
        List<Human> list = Human.find.where().eq(fieldName, value).findList();
        return ok(toJson(list));
    }    
/*
    public static Result query(String fieldName, Long value) { // GET
        List<Human> list = Human.find.where().eq(fieldName, value).findList();
        return ok(toJson(list));
    }
*/    
  
}

