package common.collections;


import common.utility.Validatable;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Класс человека
 */

public class Person implements Comparable<Person>, Validatable, Serializable {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer height; //Поле не может быть null, Значение поля должно быть больше 0
    private LocalDate birthday; //Поле может быть null
    private String passportID; //Поле не может быть null
    private Country nationality; //Поле может быть null
    private Location location; //Поле не может быть null
public static Map<Integer, Person> people =new HashMap<>();
    public Person(int id,String name, Coordinates coordinates, LocalDate creationDate, Integer height, LocalDate birthday,String passportID, Country nationality, Location location) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.height = height;
        this.birthday = birthday;
        this.passportID = passportID;
        this.nationality = nationality;
        this.location = location;
    }
    public void setID(int id){
        this.id=id;
    }
    public int getID(){
        return id;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public void setCoordinates(Coordinates coord){
        this.coordinates=coord;
    }
    public Coordinates getCoordinates(){
        return coordinates;
    }
    public void setLocalDate(LocalDate LD){
       this.creationDate=LD;
    }
    public LocalDate getCreationDate(){
        return creationDate;
    }
    public void setHeight(Integer height){
        this.height=height;
    }
    public Integer getHeight(){
        return height;
    }
    public void setBirthday(LocalDate localdate){
        this.birthday=localdate;
    }
    public LocalDate getBirthday(){
        return birthday;
    }
    public void setPassportID(String passportID){
        this.passportID=passportID;
    }
    public String getPassportID(){
        return passportID;
    }
    public void setNationality(Country nationality){
this.nationality=nationality;
    }
    public Country getNationality(){
        return nationality;
    }
    public void setLocation(Location location){
        this.location=location;
    }
    public Location getLocation(){
        return location;
    }

    public Person copy(int id) {
        return new Person(id, this.name, this.coordinates, this.creationDate,
                this.height, this.birthday, this.passportID, this.nationality, this.location
        );
    }
    public void update(Person person) {
        this.name = person.name;
        this.coordinates = person.coordinates;
        this.creationDate = person.creationDate;
        this.height = person.height;
        this.birthday = person.birthday;
        this.passportID = person.passportID;
        this.nationality = person.nationality;
        this.location = person.location;
    }
    @Override
    public boolean validate(){
        if(id <= 0){
            return false;
        }
        if(coordinates == null) {
            return false;
        }
        if(creationDate == null) {
            return false;
        }
        if(height < 0) {
            return false;
        }
        if(passportID ==null) {
            return false;
        }
        return (name != null || !name.equals(""));}

    @Override
    public boolean equals(Object o){
        if(this==o){
            return true;
        }
        else if(o==null||o.getClass()!=getClass()){
            return false;
        }
        Person pers=(Person) o;
        return Objects.equals(id,pers.id)&& Objects.equals(name,pers.name)&&Objects.equals(coordinates,pers.coordinates)
                &&Objects.equals(creationDate,pers.creationDate)&&Objects.equals(height,pers.height)&&
                Objects.equals(birthday,pers.birthday)&&Objects.equals(passportID,pers.passportID)&&
                Objects.equals(nationality,pers.nationality)&&Objects.equals(location,pers.location);
    }
    @Override
    public int hashCode(){
        return Objects.hash(id,name,coordinates,creationDate,height,birthday,passportID,nationality,location);
    }
    @Override
    public String toString(){
        return "Person: { id= "+id+", "+ "name= "+name+", "+ "coordinates= "+coordinates+", "+
                "creationDate= "+creationDate+", "+"height= "+height+", "+"birthday= "+birthday+", "+
                "passportID= "+passportID+", "+"nationality= "+nationality+", "+"location= "+location+" }";}
    @Override
    public int compareTo(Person o) {
        return height.compareTo(o.height);
    }
}

