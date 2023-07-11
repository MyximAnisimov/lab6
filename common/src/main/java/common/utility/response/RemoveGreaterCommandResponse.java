package common.utility.response;

import common.collections.Person;

import java.util.List;

public class RemoveGreaterCommandResponse extends Response{
    private final List<Person> person;
    public RemoveGreaterCommandResponse(List <Person> person, String error){
        super("remove_greater", error);
            this.person=person;

        }
    }
