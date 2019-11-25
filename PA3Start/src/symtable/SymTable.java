package symtable;
import java.util.*;
import ast.node.*;
import symtable.*;
import java.io.*;
import exceptions.InternalException;

/**
 * SymTable
 * ....
 * The symbol table also keeps a mapping of expression nodes to
 * types because that information is needed elsewhere especially
 * when looking up method call information.
 *
 * @author mstrout
 * WB: Simplified to only expression types
 */
public class SymTable {
    public HashMap<Node,Type> mExpType = new HashMap<Node,Type>();
    public HashMap<String,VarSTE> idlit = new HashMap<String, VarSTE>();
    public Scope mGlobalScope;
    private Stack<Integer> methodStack;
    public LinkedList<Scope> mScopeStack;
    public SymTable() {
        mScopeStack = new LinkedList<Scope>();
    }
    public Scope peek(){
        return mScopeStack.peek();
    }
    /** Lookup a symbol in this symbol table.

     * Starts looking in innermost scope and then

     * look in enclosing scopes.

     * Returns null if the symbol is not found.

     */

    public STE lookup(String sym) {
        for(Iterator<Scope> i = mScopeStack.iterator(); i.hasNext();){
            Scope x = (Scope)i.next();
            
            if(x.mHashMap.get(sym) != null){
                return x.lookup(sym);
            }
        }
        return null;
    }

    public Scope lookupClosestScope(String sym) {
        for(Iterator<Scope> i = mScopeStack.iterator(); i.hasNext();){
            Scope x = i.next();
            if(x.mName.contains(sym) ||x.scopeType.contains(sym)){
                return x;
            }
        }
        return null;
    }


    /** Lookup a symbol in innermost scope only.

     * return null if the symbol is not found

     */

    public STE lookupInnermost(String sym) {

        Scope currentScope = mScopeStack.peek();

        return currentScope.lookup(sym);

    }



    /** When inserting an STE will just insert

     * it into the scope at the top of the scope stack.

     */

    public void insert(STE ste) {
        STE x = ste;
        mScopeStack.peek().add(x);

    }


    public void outPutDot(Node node){


    }

    /**

     * Lookup the given method scope and make it the innermost

     * scope.  That is, make it the top of the scope stack.

     */


    public void addScope(Scope s){
        mScopeStack.push(s);
    }

    public void pushScope(String id) {
        Scope s = mScopeStack.getFirst();
        if(mScopeStack.peek().lookup(id) != null){
            for(Iterator<Scope> i = mScopeStack.iterator(); i.hasNext();){
                s = i.next();
                if(s.mName.equals(id)){
                    mScopeStack.remove(s);
                    break;
                }
            }
            mScopeStack.push(s);
        }
    }



    public void popScope() {
        this.mScopeStack.pop();
    }

    public void setExpType(Node exp, Type t)
    {
    	this.mExpType.put(exp, t);
    }

    public Type getExpType(Node exp)
    {
    	return this.mExpType.get(exp);
    }

/*
 */

}
