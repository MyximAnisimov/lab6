package common.utility.requests;

import common.collections.Person;

import java.io.Serializable;

public class Request implements Serializable {
    private String commandName;
    public Request(String commandName){
        this.commandName=commandName;
    }
    public String getName() {
        return commandName;
    }
}
