
package symtable;
import java.util.*;
import ast.node.*;




public class ClassSTE extends STE{
    public boolean mMain;
    public ClassSTE mSuperClass = null;
    public Scope mScope;

    public ClassSTE(TopClassDecl node){
        super(node.getName());
        this.mScope = new makeScope(node.getName(), "ClassScope");
        this.mScope.scopeType = "TopClassDecl";
        this.mMain = false;
        mSuperClass = null;
    }
    public ClassSTE(MainClass node){
        super(node.getName());
        this.mScope = new makeScope(node.getName(), "ClassScope");
        this.mScope.scopeType = "MainClass";
        this.mMain = true;
        mSuperClass = null;
    }

}
