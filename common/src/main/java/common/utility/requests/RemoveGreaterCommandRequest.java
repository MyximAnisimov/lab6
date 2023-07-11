package common.utility.requests;

public class RemoveGreaterCommandRequest extends Request{
    public final int height;
    public RemoveGreaterCommandRequest(int height){
        super("removeGreater");
        this.height=height;
    }
}
