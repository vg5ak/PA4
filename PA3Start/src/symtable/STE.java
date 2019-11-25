package symtable;
import java.util.*;
import ast.node.*;

import exceptions.InternalException;

public abstract class  STE {
    public String name;
    public Type mType;
    public STE(){
        this.name = "";
    }
    public STE(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

}
