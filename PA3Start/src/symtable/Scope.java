package symtable;
import java.util.*;
import ast.node.*;
import symtable.*;
import exceptions.InternalException;

public class Scope {
    public HashMap<String,STE> mHashMap;
    public Scope mEnclosing;
    public String mName;
    public HashMap<String, VarSTE> variables;
    public String scopeType;
    public Scope(String mName){
        this.mName = mName;
        this.variables = new HashMap<String, VarSTE>();
        mHashMap = new HashMap<String, STE>();
    }
    public void insert(STE ste){
        if(mHashMap.containsKey(ste.getName())){
            System.out.println("Duplicate value");
        }
        else{
            mHashMap.put(ste.getName(),ste);
        }

    }

    public STE lookup(String ste){
        //System.out.println(mHashMap.toString());
        if(mHashMap.containsKey(ste)){
            return mHashMap.get(ste);
        }
        return null;
    }

    public void addClass(ClassSTE ste){
        //ClassSTE news = ste;
        //this.classes.put(news.getName(), news);
        this.mHashMap.put(ste.getName(), ste);
    }
    public void addMethod(MethodSTE ste){
        //MethodSTE news = ste;
        //this.methods.put(news.getName(), news);
        this.mHashMap.put(ste.getName(), ste);
    }
    public void addFormal(VarSTE ste){
        //VarSTE news = ste;
        this.variables.put(ste.getName(), ste);
        this.mHashMap.put(ste.getName(), ste);
    }
    public void add(STE ste){
        this.mHashMap.put(ste.getName(), ste);
    }
    public int addVars(Formal node, int offset){
        VarSTE news = new VarSTE(node, offset);
        this.variables.put(node.getName(), news);
        this.mHashMap.put(node.getName(), news);
        if(node.getType() instanceof IntType){
            offset += 2;
        }
        else{
            offset += 1;
        }
        return offset;
    }
    public VarSTE lookupVars(String s){
        return variables.get(s);
    }
    public HashMap<String, STE> getHashMap(){

        return mHashMap;
    }
    public void setHashMap(HashMap<String, STE> dict){
        mHashMap = dict;
    }
    public Scope getEnclosing(){
        return mEnclosing;
    }
    public void setEnclosing(Scope Enclosing){
        this.mEnclosing = Enclosing;
    }

}
