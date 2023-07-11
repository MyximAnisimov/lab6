package common.utility.response;

import common.collections.Person;

public class HeadCommandResponse extends Response{
   public final Person person;
    public HeadCommandResponse(Person person, String error){
        super("head", error);
        this.person=person;
    }
}
