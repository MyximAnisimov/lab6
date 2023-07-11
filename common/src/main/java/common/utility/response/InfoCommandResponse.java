package common.utility.response;

public class InfoCommandResponse extends Response{
    public final String infoMessage;

    public InfoCommandResponse(String infoMessage, String error) {
        super("info", error);
        this.infoMessage = infoMessage;
    }
}
