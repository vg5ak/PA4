package symtable;
import java.util.*;
import ast.node.*;

import exceptions.InternalException;




public class makeScope extends Scope{
    public int offset;
	public HashMap<String, MethodSTE> methods;
    public HashMap<String, VarSTE> formals;
    public HashMap<String, ClassSTE> classes;
    
    public makeScope(String name){
        super(name);
        this.classes = new HashMap<String, ClassSTE>();
        scopeType = "Program";
    }
    public makeScope(String name, String type){
        super(name);
        if(type == "ClassScope"){
            this.methods = new HashMap<String, MethodSTE>();
            this.formals = new HashMap<String, VarSTE>();
            scopeType = type;
        }
        else{
            this.formals = new HashMap<String, VarSTE>();
            scopeType = type;
        }


    }
    public void addClass(ClassSTE ste){
        ClassSTE news = ste;
        this.classes.put(news.getName(), news);
        this.mHashMap.put(news.getName(), news);
    }
    public void addMethod(MethodSTE ste){
        MethodSTE news = ste;
        this.methods.put(news.getName(), news);
        this.mHashMap.put(news.getName(), news);
    }
    public void addFormal(VarSTE ste){
        VarSTE news = ste;
        this.formals.put(news.getName(), news);
        this.mHashMap.put(news.getName(), news);
    }
    public ClassSTE lookupClass(String s){
        return classes.get(s);
    }
    public MethodSTE lookupMethod(String s){
        return methods.get(s);
    }
    public VarSTE lookupFormal(String s){
        return formals.get(s);
    }
    public STE lookup(String s){
        return mHashMap.get(s);
    }
    public int offset(Type t, int o){
        if(t == Type.INT){
            return o+=2;
        }
        else{
            return o+=1;
        }
    }

}
