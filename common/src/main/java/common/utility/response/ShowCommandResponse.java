package common.utility.response;

import common.collections.Person;

import java.util.List;

public class ShowCommandResponse extends Response{
    public final List<Person> person;

    public ShowCommandResponse(List<Person> person, String error) {
        super("show", error);
        this.person = person;
    }
}
