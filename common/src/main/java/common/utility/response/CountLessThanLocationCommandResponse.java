package common.utility.response;

public class CountLessThanLocationCommandResponse extends Response{
public final int sum;
public CountLessThanLocationCommandResponse(int sum, String error){
    super("count_less_than_location", error);
    this.sum=sum;
}
}
