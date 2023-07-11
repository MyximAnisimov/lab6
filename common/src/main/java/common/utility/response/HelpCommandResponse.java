package common.utility.response;

public class HelpCommandResponse extends Response{
    public final String helpMessage;

    public HelpCommandResponse(String helpMessage, String error) {
        super("help", error);
        this.helpMessage = helpMessage;
    }
}
