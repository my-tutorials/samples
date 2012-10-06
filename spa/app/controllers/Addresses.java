package controllers;

import models.*;
import play.data.*;
import play.mvc.*;

import java.util.List;
import static play.libs.Json.toJson;
import static play.libs.Json.fromJson;
import org.codehaus.jackson.JsonNode;


public class Addresses extends Controller {

    public static Result getAll() { // GET

        List<Address> list = Address.find.orderBy("land").findList();
        return ok(toJson(list));
    }

    public static Result getById(Long id) { // GET

        Address modelToFind = Address.find.byId(id);
        if(modelToFind!=null) {
            return ok(toJson(modelToFind));
        } else {
            return badRequest("not found");
        }
    }

    public static Result create() { //POST

        Address model = fromJson(request().body().asJson(), Address.class);        
        model.save();
        return ok(toJson(model)); 
    }

    public static Result update(Long id) { //PUT
        
        Address model = fromJson(request().body().asJson(), Address.class);
        model.id = id;
        model.update();
        return ok(toJson(model));
    }

    public static Result delete(Long id) { // DELETE : delete

        Address modelToFind = Address.find.byId(id);
        if(modelToFind!=null) {
            modelToFind.delete();
            return ok(toJson(true));
        } else {
            return badRequest("not found");
        }
    }

    public static Result query(String fieldName, String value) { // GET
        //Address/lastName/equals/morane
        List<Address> list = Address.find.where().eq(fieldName, value).findList();
        return ok(toJson(list));
    }    

}

/* ROUTES

#Création
POST /address  controllers.Addresses.save()

#Mise à jour
PUT /address  controllers.Addresses.save()

#Rechercher par Id
GET  /address/:id  controllers.Addresses.getById(id: Long)

#Supprimer par Id
DELETE /address/:id  controllers.Addresses.delete(id: Long)

#Retrouver tous les éléments
GET /addresses controllers.Addresses.getAll()

#Retrouver certains éléments
GET /addresses/:fieldName/equals/:value controllers.Addresses.query(fieldName: String, value: String)

*/

