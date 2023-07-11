package common.utility.response;

public class RemoveByIdCommandResponse extends Response{
    public RemoveByIdCommandResponse(String error) {
        super("remove_by_id", error);
    }
}
