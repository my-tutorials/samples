package models;

import play.db.ebean.Model;
import javax.persistence.*;

@Entity
public class Human extends Model{

    @Id
    public Long id;
    public String firstName;
    public String lastName;
    public Long age;

    public static Finder<Long, Human> find = 
            new Finder<Long, Human>(Long.class, Human.class);

}