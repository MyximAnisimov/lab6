package common.utility.response;

public class HeightSumCommandResponse extends Response{
    public final int sum;
    public HeightSumCommandResponse(int sum, String error){
        super("heightSum", error);
        this.sum=sum;
    }
}
