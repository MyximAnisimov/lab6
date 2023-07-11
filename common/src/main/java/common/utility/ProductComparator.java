package common.utility;

import common.collections.Person;

import java.util.Comparator;

public class ProductComparator implements Comparator<Person> {
    public int compare(Person a, Person b){
        return a.getHeight().compareTo(b.getHeight());
}}
