package common.utility.response;

public class ClearCommandResponse extends Response{
    public ClearCommandResponse(String error) {
        super("clear", error);
    }
}
