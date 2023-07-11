package common.utility.response;

import common.collections.Person;

public class RemoveFirstCommandResponse extends Response{
    public RemoveFirstCommandResponse( String error){
        super("remove_first", error);
    }
}
