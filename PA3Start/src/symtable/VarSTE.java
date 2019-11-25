package symtable;
import java.util.*;
import ast.node.*;
import symtable.*;
import exceptions.InternalException;

public class VarSTE extends STE {
    //private HashMap<String, Formal> mSignature;
    //private Scope mScope;
    public Type mType;
    public String mBase;
    public int mOffset;

    /*public VarSTE(String mName){
        super(mName);
        mType = Type.VOID;
        mBase = "INVALID";
        mOffset = 0;
    }
    public VarSTE(String mName, Type type, String base, int offset){
        super(mName);
        this.mType = type;
        this.mBase = base;
        this.mOffset = offset;
    }*/
    public VarSTE(Formal node, int offset){
        super(node.getName());
        mType = setType(node);
        mBase = "Y";
        mOffset = offset;

    }

    public VarSTE(VarDecl node, int offset){
        super(node.getName());
        mType = setType(node);
        mBase = "Y";
        mOffset = setOffset(node, offset);
    }
    public Type getType(){
        return this.mType;
    }
    public Type setType(VarDecl node){
        Type mType = Type.VOID;
        if(node.getType() instanceof IntType){
            mType = Type.INT;
        }
        else if(node.getType() instanceof ByteType){
            mType = Type.BYTE;
        }
        else if(node.getType() instanceof BoolType){
            mType = Type.BOOL;
        }
        else if(node.getType() instanceof ColorType){
            mType = Type.COLOR;
        }
        else if(node.getType() instanceof ToneType){
            mType = Type.TONE;
        }
        else if(node.getType() instanceof ButtonType){
            mType = Type.BUTTON;
        }
        return mType;
    }
    public String getBase(){
        return mBase;
    }
    public Type setType(Formal node){
        Type mType = Type.VOID;
        if(node.getType() instanceof IntType){
            mType = Type.INT;
        }
        else if(node.getType() instanceof ByteType){
            mType = Type.BYTE;
        }
        else if(node.getType() instanceof BoolType){
            mType = Type.BOOL;
        }
        else if(node.getType() instanceof ColorType){
            mType = Type.COLOR;
        }
        else if(node.getType() instanceof ToneType){
            mType = Type.TONE;
        }
        else if(node.getType() instanceof ButtonType){
            mType = Type.BUTTON;
        }
        return mType;
    }
    public void setBase(String base){
        this.mBase = base;
    }
    public int getOffset(){
        return this.mOffset;
    }
    public int setOffset(VarDecl node, int Offset){
        int et;
        if(node.getType() instanceof IntType){
            et = Offset + 2;
        }
        else {
            et = Offset + 1;
        }
        return et;
    }
    public int setOffset(Formal node, int Offset){
        int et;
        if(node.getType() instanceof IntType){
            et = Offset + 2;
        }
        else {
            et = Offset + 1;
        }
        return et;
    }

}
