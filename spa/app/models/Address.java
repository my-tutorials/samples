package models;

import play.db.ebean.Model;
import javax.persistence.*;

@Entity
public class Address extends Model{

    @Id
    public Long id;
    public String town;
    public String land;

    public static Finder<Long, Address> find = 
            new Finder<Long, Address>(Long.class, Address.class);

}