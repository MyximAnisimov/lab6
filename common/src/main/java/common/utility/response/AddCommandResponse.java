package common.utility.response;

public class AddCommandResponse extends Response{
    public final int newId;

    public AddCommandResponse(int newId, String error) {
        super("add", error);
        this.newId = newId;
    }
}
