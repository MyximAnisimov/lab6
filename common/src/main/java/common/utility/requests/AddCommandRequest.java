package common.utility.requests;

import common.collections.Person;

public class AddCommandRequest extends Request{
    public final Person person;
    public AddCommandRequest(Person person){
        super("add");
        this.person=person;
    }
}
