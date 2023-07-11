package common.collections;


import common.utility.Validatable;

import java.io.Serializable;
import java.util.Objects;

/**
 * Класс координат человека
 */

public class Coordinates implements Validatable, Serializable {
    private double x;
    private int y;
    public Coordinates(double x, int y){
        this.x=x;
        this.y=y;
    }
    public void setX(int x){
        this.x=x;
    }
    public double getX(){
        return x;
    }
    public void setY(int y){
        this.y=y;
    }
    public int getY(){
        return y;
    }
    @Override
    public boolean validate(){
    return this.getY() > -438;}
 @Override
    public boolean equals(Object o){
        if(this==o){
            return true;
        }
        else if(o==null||o.getClass()!=getClass()){
            return false;
        }
        Coordinates coord=(Coordinates) o;
        return Objects.equals(x,coord.x)&& Objects.equals(y,coord.y);
 }
 @Override
    public int hashCode(){
        return Objects.hash(x,y);
 }
 @Override
    public String toString(){
        return "Coordinates: { X= "+x+", "+
                "Y= "+y+" }";
 }
}
