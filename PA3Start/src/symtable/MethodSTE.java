package symtable;
import java.util.*;
import ast.node.*;

import exceptions.InternalException;

public class MethodSTE extends STE {
    //String mName;
    public LinkedList<Formal> mSignature;
    public Scope mScope;
    public Type mType;
    public MethodSTE(MethodDecl node){
        super(node.getName());
        this.mSignature = node.getFormals();
        mType = ITypetoType(node);
        mScope = new makeScope(node.getName(), "Variables");
    }
    public String printMethodSignature(){
        String s = "";
        s += "(";
        for(int i = 0; i < mSignature.size();i++){
            Type t = ITypetoType(mSignature.get(i));
            if(i == mSignature.size()-1){
                s = s + " " + t + " " + mSignature.get(i).getName() + " ";
            }
            else{
                s = s + " " + t + " " + mSignature.get(i).getName() + ", ";
            }

        }
        s = s + ") return ";
        s = s + this.mType.toString();
        return s;
    }
    public Type ITypetoType(MethodDecl node){
        Type ype;
        if(node.getType() instanceof IntType){
            ype = Type.INT;
        }
        else if(node.getType() instanceof ByteType){
            ype = Type.BYTE;
        }
        else if(node.getType() instanceof BoolType){
            ype = Type.BOOL;
        }
        else if(node.getType() instanceof ColorType){
            ype = Type.COLOR;
        }
        else if(node.getType() instanceof ToneType){
            ype = Type.TONE;
        }
        else if(node.getType() instanceof ButtonType){
            ype = Type.BUTTON;
        }
        else {
            ype = Type.VOID;
        }
        return ype;
    }
    public Type ITypetoType(Formal node){
        Type ype;
        if(node.getType() instanceof IntType){
            ype = Type.INT;
        }
        else if(node.getType() instanceof ByteType){
            ype = Type.BYTE;
        }
        else if(node.getType() instanceof BoolType){
            ype = Type.BOOL;
        }
        else if(node.getType() instanceof ColorType){
            ype = Type.COLOR;
        }
        else if(node.getType() instanceof ToneType){
            ype = Type.TONE;
        }
        else if(node.getType() instanceof ButtonType){
            ype = Type.BUTTON;
        }
        else {
            ype = Type.VOID;
        }
        return ype;
    }


}
