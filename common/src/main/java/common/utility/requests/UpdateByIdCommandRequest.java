package common.utility.requests;

import common.collections.Person;

public class UpdateByIdCommandRequest extends Request{
    public final int id;
    public final Person updatedPerson;
    public UpdateByIdCommandRequest(int id, Person updatedPerson){
        super("update_by_id");
        this.id=id;
        this.updatedPerson=updatedPerson;
    }
}
