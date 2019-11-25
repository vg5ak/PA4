package ast_visitors;

/**
 * CheckTypes
 *
 * This AST visitor traverses a MiniJava Abstract Syntax Tree and checks
 * for a number of type errors.  If a type error is found a SymanticException
 * is thrown
 *
 * CHANGES to make next year (2012)
 *  - make the error messages between *, +, and - consistent <= ??
 *
 * Bring down the symtab code so that it only does get and set Type
 *  for expressions
 */
/*
Functions Done:
defaultOut
outAndExp
outPlusExp
outMinusExp
outMulExp
outEqualExp
outNegExp
outByteCast
*/
import ast.node.*;
import java.io.PrintWriter;
import ast.visitor.DepthFirstVisitor;
import java.util.*;
import ast.visitor.*;
import symtable.*;
import exceptions.InternalException;
import exceptions.SemanticException;

public class BuildSymTable extends DepthFirstVisitor
{

   private SymTable mCurrentST;
   public BuildSymTable(SymTable st) {
     if(st==null) {
          throw new InternalException("unexpected null argument");
      }
      mCurrentST = st;
   }
   public BuildSymTable(){
       mCurrentST = new SymTable();
   }
   public SymTable getSymTable(){
       return mCurrentST;
   }

   @Override
   public void inProgram(Program node)
   {
       /*if(mCurrentST.lookupInnermost(node.getName()) != null);
       {
           throw SemanticException(
           "Redefined Symbol: " + node.getName(), node.getLine(), node.getPos()
           );
       }*/
       Scope programScope = new Scope("Program");
       //System.out.println("Enter Program");
       this.mCurrentST.addScope(programScope);

   	   this.mCurrentST.pushScope(programScope.mName);
   	   //this.mCurrentST.addScope(programScope);
   	   this.mCurrentST.setExpType(node, Type.VOID);
       //mCurrentST.setExpType(node, Type.VOID);
   }
   @Override
   public void inMainClass(MainClass node)
   {
       /*if(mCurrentST.lookupInnermost(node.getName()) != null);
       {
           throw SemanticException(
           "Redefined Symbol: " + node.getName(), node.getLine(), node.getPos()
           );
       }*/
       ClassSTE newSTE = new ClassSTE(node);
       Scope programScope = this.mCurrentST.lookupClosestScope("Program");
       if(programScope != null){
           this.mCurrentST.lookupClosestScope("Program").add(newSTE);
       }
       this.mCurrentST.addScope(newSTE.mScope);

       mCurrentST.setExpType(node, Type.CLASS);




   }
   @Override
   public void inTopClassDecl(TopClassDecl node)
   {
       System.out.println("EnterTopClass");
       ClassSTE newSTE = new ClassSTE(node);
       Scope programScope = this.mCurrentST.lookupClosestScope("Program");
       if(programScope != null){
           this.mCurrentST.lookupClosestScope("Program").add(newSTE);
       }
       this.mCurrentST.addScope(newSTE.mScope);

       mCurrentST.setExpType(node, Type.CLASS);
       //ClassSTE classSTE = new ClassSTE(node.getName(), false, new Scope(node.getName(), "TopClassDecl"));
   	   //this.mCurrentST.lookupScope("Program").put(classSTE.mName, classSTE);
   	   //this.mCurrentST.pushScope(classSTE.mScope);
   	   //this.mCurrentST.mCurrentScopeStack.push(classSTE.mScope);
   }
   public int setOffset(int Offset, VarSTE ste){
       System.out.println("Offset"+Offset);
       int et = 0;
       if(ste.getName()=="this"){
           et = et + Offset + 2;
       }
       else if(ste.mType == Type.INT||ste.mType == Type.TONE){
           et = Offset + 2;
       }
       else {
           et = Offset + 1;
       }
       return et;
   }
   @Override
   public void inMethodDecl(MethodDecl node)
   {
       /*if(mCurrentST.lookupInnermost(node.getName()) != null);
       {
           throw SemanticException(
           "Redefined Symbol: " + node.getName(), node.getLine(), node.getPos()
           );
       }*/

       System.out.println("EnterMethod");
       Scope scope = mCurrentST.peek();
       System.out.println("Make new scope");
       MethodSTE newSTE = new MethodSTE(node);
       System.out.println("Make new STE");
       mCurrentST.setExpType(node, newSTE.mType);
       System.out.println("EnternewMethod");
       scope = new makeScope(node.getName(), "MethodDeclNode");
       int offset = 0;
       VarSTE var = new VarSTE(newSTE.mSignature.peek(), offset);
       var.setName("this");
       var.mType = Type.CLASS;
       var.mOffset = 1;
       scope.add(var);
       offset = var.mOffset;
       offset = setOffset(offset, var);
       for(Iterator<Formal> iter = newSTE.mSignature.iterator(); iter.hasNext();){
          // System.out.println("EnterNewFormal: " + i);
           Formal formal = iter.next();
           var = new VarSTE(formal, offset);
           scope.add(var);
           mCurrentST.setExpType(formal, var.mType);
           offset = setOffset(offset, var);
       }
       System.out.println("Finish EnterNewFormal");
       mCurrentST.lookupClosestScope("TopClassDecl").add(newSTE);
       //mCurrentST.lookupClosestScope("TopClassDecl");
       System.out.println("lookedupclosestscope");
       mCurrentST.addScope(scope);//*/
   }

   @Override
    public void outFormal(Formal node) {
    VarSTE newSTE = new VarSTE(node, 0);
	this.mCurrentST.setExpType(node, newSTE.mType);
    }
    @Override
    public void outVarDecl(VarDecl node) {
    VarSTE newSTE = new VarSTE(node, 0);
	this.mCurrentST.setExpType(node, newSTE.mType);
    }
    @Override
    public void outMethodDecl(MethodDecl node) {
    MethodSTE newSTE = new MethodSTE(node);
	this.mCurrentST.setExpType(node, newSTE.mType);
	//this.mCurrentST.mCurrentScopeStack.pop();
    }

    @Override
    public void outTopClassDecl(TopClassDecl node) {
	//this.mCurrentST.mCurrentScopeStack.pop();
    }

    @Override
    public void outMainClass(MainClass node) {
	//this.mCurrentST.mCurrentScopeStack.pop();
    }

    public void outNewExp(NewExp node) {
	this.mCurrentST.setExpType(node, Type.CLASS);
    }

    public void outCallStatement(CallStatement node) {
	this.mCurrentST.setExpType(node, Type.VOID);
    }

    public void outIntLiteral(IntLiteral node) {
	this.mCurrentST.setExpType(node, Type.INT);
    }

    public void outColorLiteral(ColorLiteral node) {
	this.mCurrentST.setExpType(node, Type.COLOR);
    }

    public void outButtonLiteral(ButtonLiteral node) {
	this.mCurrentST.setExpType(node, Type.BUTTON);
    }

    public void outTrueLiteral(TrueLiteral node) {
	this.mCurrentST.setExpType(node, Type.BOOL);
    }

    public void outFalseLiteral(FalseLiteral node) {
	this.mCurrentST.setExpType(node, Type.BOOL);
    }

    public void outToneLiteral(ToneLiteral node) {
	this.mCurrentST.setExpType(node, Type.TONE);
    }

    public void outThisLiteral(ThisLiteral node) {
	this.mCurrentST.setExpType(node, Type.CLASS);
    }

    public void outIntType(IntType node) {
	this.mCurrentST.setExpType(node, Type.INT);
    }

    public void outByteType(ByteType node) {
	this.mCurrentST.setExpType(node, Type.BYTE);
    }

    public void outColorType(ColorType node) {
	this.mCurrentST.setExpType(node, Type.COLOR);
    }

    public void outButtonType(ButtonType node) {
	this.mCurrentST.setExpType(node, Type.BUTTON);
    }

    public void outBoolType(BoolType node) {
	this.mCurrentST.setExpType(node, Type.BOOL);
    }

    public void outToneType(ToneType node) {
	this.mCurrentST.setExpType(node, Type.TONE);
    }

    public void outVoidType(VoidType node) {
	this.mCurrentST.setExpType(node, Type.VOID);
    }



}
