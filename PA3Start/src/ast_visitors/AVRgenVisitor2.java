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
import java.lang.*;
import ast.node.*;
import ast.visitor.DepthFirstVisitor;
import java.util.*;
import ast.visitor.*;
import symtable.*;
import exceptions.InternalException;
import exceptions.SemanticException;
import java.io.*;
import label.Label;
import java.lang.String;

public class AVRgenVisitor extends DepthFirstVisitor
{

   private SymTable mCurrentST;
   private PrintWriter mPrintWriter;
   public AVRgenVisitor(PrintWriter pw, SymTable st) {
     mPrintWriter = pw;
     mCurrentST = st;
     //Label l = new Label();
   }

   //========================= Overriding the visitor interface
   @Override
   public void defaultOut(Node node) {
       //mPrintWriter.println("This node is not implemented");
   }
   /*
   Most Boolean Expressions
   TrueExp
   FalseExp
   AndExp
   EqualExp
   NotExp*/
   //======================== Overriding the Bool Exp Nodes

   @Override
    public void outBlockStatement(BlockStatement node)
    {

    }
    @Override
    public void inNewExp(NewExp node){
        mPrintWriter.println("ldi r24, lo8(0)");
        mPrintWriter.println("ldi r25, hi8(0)");
        mPrintWriter.println("call malloc");
        mPrintWriter.println("push r25");
        mPrintWriter.println("push r24");
        this.mCurrentST.pushScope(node.getId());

    }
    @Override
    public void visitThisLiteral(ThisLiteral node){
        mPrintWriter.println("ldd r31, Y+2");
        mPrintWriter.println("ldd r30, Y+1");
        mPrintWriter.println("push r31");
        mPrintWriter.println("push r30");
        Scope first = mCurrentST.mScopeStack.peek();
        mCurrentST.mScopeStack.pop();
        Scope second = mCurrentST.mScopeStack.pop();
        mCurrentST.addScope(second);
        mCurrentST.addScope(first);
        mCurrentST.pushScope(second.mName);

    }
    public static String intToString(int i){
        return String.valueOf(i);
    }

    @Override
    public void inIdLiteral(IdLiteral node){
        STE x = mCurrentST.lookupInnermost(node.getLexeme());
        VarSTE y;
        //if(x instanceof VarSTE){
            y = (VarSTE)x;
        //}
        if(x == null){
            System.exit(0);
        }
        if(x.mType != Type.INT){
            mPrintWriter.println("ldd r24, Y + "+intToString(y.mOffset));
            mPrintWriter.println("push r24");
        }
        else if(x.mType == Type.INT){
            mPrintWriter.println("ldd r25, Y + "+intToString(y.mOffset+1));
            mPrintWriter.println("ldd r25, Y + "+intToString(y.mOffset));
            mPrintWriter.println("push r25");
            mPrintWriter.println("push r24");
        }


    }
    @Override
    public void outMeggyToneStart(MeggyToneStart node){
        mPrintWriter.println("pop r22");
        mPrintWriter.println("pop r23");
        mPrintWriter.println("pop r24");
        mPrintWriter.println("pop r25");
        mPrintWriter.println("call _Z18Tone_Startjj");
    }
    @Override
    public void outLtExp(LtExp node)
    {
        Type lexpType = this.mCurrentST.getExpType(node.getLExp());
        Type rexpType = this.mCurrentST.getExpType(node.getRExp());
        Label l0, l1, l2, l3, l4, l5, l6, l7;
        l0 = new Label(); l1 = new Label();
        l2 = new Label();l3 = new Label();
        l4 = new Label();
        l5 = new Label();
        l6 = new Label();
        l7 = new Label();
        if(lexpType == Type.INT && rexpType == Type.INT){
            System.out.println("Goes in double ints");
            mPrintWriter.println("pop    r18");
            mPrintWriter.println("pop    r19");
            //mPrintWriter.println(# load a two byte expression off stack);
            mPrintWriter.println("pop    r24");
            mPrintWriter.println("pop    r25");
            mPrintWriter.println("cp    r24, r18");
            mPrintWriter.println("cpc   r25, r19");
            mPrintWriter.println("brlt "+l4);

            //mPrintWriter.println(# load false);
            mPrintWriter.println(l3+" :");
            mPrintWriter.println("ldi     r24, 0");
            mPrintWriter.println("jmp      "+l5);

            //mPrintWriter.println(# load true);
            mPrintWriter.println(l4+":");
            mPrintWriter.println("ldi    r24, 1");

            //mPrintWriter.println(# push result of less than);
            mPrintWriter.println(l5+":");
            //mPrintWriter.println(# push one byte expression onto stack);
            mPrintWriter.println("push   r24");
        }
        else if(lexpType == Type.BYTE && rexpType == Type.INT){
            System.out.println("Goes in byte and ints");
            //mPrintWriter.println(# less than expression);
            //mPrintWriter.println(# load a two byte expression off stack);
            mPrintWriter.println("pop    r18");
            mPrintWriter.println("pop    r19");
            //mPrintWriter.println(# load a one byte expression off stack);
            mPrintWriter.println("pop    r24");
            //mPrintWriter.println(# promoting a byte to an int);
            mPrintWriter.println("tst     r24");
            mPrintWriter.println("brlt     " + l6);
            mPrintWriter.println("ldi    r25, 0");
            mPrintWriter.println("jmp    "+l7);
            mPrintWriter.println(l6+":");
            mPrintWriter.println("ldi    r25, hi8(-1)");
            mPrintWriter.println(l7+":");
            mPrintWriter.println("cp    r24, r18");
            mPrintWriter.println("cpc   r25, r19");
            mPrintWriter.println("brlt "+l4);

            //mPrintWriter.println(# load false);
            mPrintWriter.println(l3+":");
            mPrintWriter.println("ldi     r24, 0");
            mPrintWriter.println("jmp      "+l5);

            //mPrintWriter.println(# load true);
            mPrintWriter.println(l4+":");
            mPrintWriter.println("ldi    r24, 1");

            //mPrintWriter.println(# push result of less than);
            mPrintWriter.println(l5+":");
            //mPrintWriter.println(# push one byte expression onto stack);
            mPrintWriter.println("push   r24");
        }
        else if(lexpType == Type.BYTE && rexpType == Type.BYTE){
            mPrintWriter.println("pop    r18");
            //# load a one byte expression off stack
            mPrintWriter.println("pop    r24");
            mPrintWriter.println("cp    r24, r18");
            mPrintWriter.println("brlt "+l4);

            //mPrintWriter.println(# load false
            mPrintWriter.println(l3+":");
            mPrintWriter.println("ldi     r24, 0");
            mPrintWriter.println("jmp      "+l5);

            //mPrintWriter.println(# load true
            mPrintWriter.println(l4+":");
            mPrintWriter.println("ldi    r24, 1");

            //mPrintWriter.println(    # push result of less than
            mPrintWriter.println(l5+":");
            //# push one byte expression onto stack
            mPrintWriter.println("push   r24");
        }
        else if(lexpType == Type.INT && rexpType == Type.BYTE){
            mPrintWriter.println("pop    r18");
            //mPrintWriter.println(# load a two byte expression off stack
            mPrintWriter.println("pop    r24");
            mPrintWriter.println("pop    r25");
            //mPrintWriter.println(# promoting a byte to an int
            mPrintWriter.println("tst     r18");
            mPrintWriter.println("brlt     "+l6);
            mPrintWriter.println("ldi    r19, 0");
            mPrintWriter.println("jmp  "+  l7);
            mPrintWriter.println(l6+":");
            mPrintWriter.println("ldi    r19, hi8(-1)");
            mPrintWriter.println(l7+":");
            mPrintWriter.println("cp    r24, r18");
            mPrintWriter.println("cpc   r25, r19");
            mPrintWriter.println("brlt " +l4);

            //mPrintWriter.println(# load false
            mPrintWriter.println(l3+":");
            mPrintWriter.println("ldi     r24, 0");
            mPrintWriter.println("jmp   "+   l5);

            //mPrintWriter.println(# load true
            mPrintWriter.println(l4+":");
            mPrintWriter.println("ldi    r24, 1");

            //mPrintWriter.println(# push result of less than
            mPrintWriter.println(l5+":");
            //mPrintWriter.println(# push one byte expression onto stack
            mPrintWriter.println("push   r24");
        }

    }
   @Override
   public void visitAndExp(AndExp node)
   {

       Label l0, l1;
       l0 = new Label(); l1 = new Label();
       inAndExp(node);
       if(node.getLExp() != null)
       {
           node.getLExp().accept(this);
       }
       //System.out.println("Hello");
       mPrintWriter.println("pop r24");
       mPrintWriter.println("push r24");
       mPrintWriter.println("ldi r25, 0");
       mPrintWriter.println("cp r24, r25");
       mPrintWriter.println("brne  "+ l1);
       mPrintWriter.println("jmp " + l0);
       //System.out.println("Hello");
       mPrintWriter.println(l1 + ":");
       mPrintWriter.println("pop r24");
       //System.out.println("Hello");
       if(node.getRExp() != null)
       {
           node.getRExp().accept(this);
           //mPrintWriter.println("ldi    r22, 1");
           //mPrintWriter.println(# push one byte expression onto stack
           //mPrintWriter.println("push   r22");
           //mPrintWriter.println(# load a one byte expression off stack
           //mPrintWriter.println("pop    r24");
           //mPrintWriter.println(# push one byte expression onto stack
           //mPrintWriter.println("push   r24");
       }
        //System.out.println("Hello");
       mPrintWriter.println("pop r24");
       mPrintWriter.println("push r24");
       mPrintWriter.println(l0 + ":");
       outAndExp(node);

   }
   @Override
   public void inAndExp(AndExp node){

   }
   @Override
   public void outAndExp(AndExp node){

   }
   @Override
   public void inToneExp(ToneLiteral node){
       mPrintWriter.println("ldi r25, hi8("+node.getIntValue()+")");
       mPrintWriter.println("ldi r24, lo8("+node.getIntValue()+")");
       mPrintWriter.println("push r25");
       mPrintWriter.println("push r24");
   }

   //==========================Overriding the Integer and Byte Exp Nodes
   @Override
   public void outPlusExp(PlusExp node)
   {
       Type lexpType = this.mCurrentST.getExpType(node.getLExp());
       Type rexpType = this.mCurrentST.getExpType(node.getRExp());
       mPrintWriter.println("#Addddddddddddding");
       Label l1 = new Label();
       Label l0 = new Label();
       Label l2 = new Label();
       Label l3 = new Label();
       //# Load constant int 1
       if(lexpType == Type.BYTE && rexpType == Type.INT){
           /*mPrintWriter.println("ldi    r24,lo8("+node.getLExp().value+")");
           mPrintWriter.println("ldi    r25,hi8("+node.getLExp().value+")");
           //# push two byte expression onto stack
           mPrintWriter.println("push   r25");
           mPrintWriter.println("push   r24");
           //# Load constant int 0
           mPrintWriter.println("ldi    r24,lo8("+node.getRExp().value+")");
           mPrintWriter.println("ldi    r25,hi8("+node.getRExp().value+")");
           //# push two byte expression onto stack
           mPrintWriter.println("push   r25");
           mPrintWriter.println("push   r24");
           //# Casting int to byte by popping
           //# 2 bytes off stack and only pushing low order bits
           //# back on.  Low order bits are on top of stack.
           mPrintWriter.println("pop    r24");
           mPrintWriter.println("pop    r25");
           mPrintWriter.println("push   r24");*/
           //# load a one byte expression off stack
           mPrintWriter.println("pop    r18");
           mPrintWriter.println("pop    r19");
           //mPrintWriter.println(# load a one byte expression off stack
           mPrintWriter.println("pop    r24");
           //mPrintWriter.println(# promoting a byte to an int
           mPrintWriter.println("tst     r24");
           mPrintWriter.println("brlt "  +  l0);
           mPrintWriter.println("ldi    r25, 0");
           mPrintWriter.println("jmp " +  l1);
           mPrintWriter.println(l0 + ":");
           mPrintWriter.println("ldi    r25, hi8(-1)");
           mPrintWriter.println( l1 + ":");

          // mPrintWriter.println(# Do add operation
           mPrintWriter.println("add    r24, r18");
           mPrintWriter.println("adc    r25, r19");
           //mPrintWriter.println(# push two byte expression onto stack
           mPrintWriter.println("push   r25");
           mPrintWriter.println("push   r24");
       }
       else if(lexpType == Type.BYTE && rexpType == Type.BYTE){
           /*mPrintWriter.println("ldi    r24,lo8("+node.getLExp().value+")");
           mPrintWriter.println("ldi    r25,hi8("+node.getLExp().value+")");
           //# push two byte expression onto stack
           mPrintWriter.println("push   r25");
           mPrintWriter.println("push   r24");
           //# Casting int to byte by popping
           //# 2 bytes off stack and only pushing low order bits
           //# back on.  Low order bits are on top of stack.
           mPrintWriter.println("pop    r24");
           mPrintWriter.println("pop    r25");
           mPrintWriter.println("push   r24");
           mPrintWriter.println("ldi    r24,lo8("+node.getRExp().value+")");
           mPrintWriter.println("ldi    r25,hi8("+node.getRExp().value+")");
           //# push two byte expression onto stack
           mPrintWriter.println("push   r25");
           mPrintWriter.println("push   r24");
           //# Casting int to byte by popping
           //# 2 bytes off stack and only pushing low order bits
           //# back on.  Low order bits are on top of stack.
           mPrintWriter.println("pop    r24");
           mPrintWriter.println("pop    r25");
           mPrintWriter.println("push   r24");*/
           //# load a one byte expression off stack
           mPrintWriter.println("pop    r18");
           //# load a one byte expression off stack
           mPrintWriter.println("pop    r24");
           //# promoting a byte to an int
           mPrintWriter.println("tst     r24");
           mPrintWriter.println("brlt     " + l0);
           mPrintWriter.println("ldi    r25, 0");
           mPrintWriter.println("jmp    " + l1);
           mPrintWriter.println(l0 + ":");
           mPrintWriter.println("ldi    r25, hi8(-1)");
          mPrintWriter.println( l1 + ":");
           //# promoting a byte to an int
           mPrintWriter.println("tst     r18");
           mPrintWriter.println("brlt     " + l2);
           mPrintWriter.println("ldi    r19, 0");
           mPrintWriter.println("jmp    " + l3);
           mPrintWriter.println(l2 + ":");
           mPrintWriter.println("ldi    r19, hi8(-1)");
           mPrintWriter.println(l3 + ":");

           //mPrintWriter.println(# Do add operation
           mPrintWriter.println("add    r24, r18");
           mPrintWriter.println("adc    r25, r19");
           //mPrintWriter.println(# push two byte expression onto stack
           mPrintWriter.println("push   r25");
           mPrintWriter.println("push   r24");
       }
       else if(lexpType == Type.INT && rexpType == Type.BYTE){
           /*mPrintWriter.println("ldi    r24,lo8("+node.getLExp().value+")");
           mPrintWriter.println("ldi    r25,hi8("+node.getLExp().value+")");
           //mPrintWriter.println(# push two byte expression onto stack);
           mPrintWriter.println("push   r25");
           mPrintWriter.println("push   r24");
           //mPrintWriter.println(# Casting int to byte by popping);
           //mPrintWriter.println(# 2 bytes off stack and only pushing low order bits);
           //mPrintWriter.println(# back on.  Low order bits are on top of stack.);
           mPrintWriter.println("pop    r24");
           mPrintWriter.println("pop    r25");
           mPrintWriter.println("push   r24");
           //mPrintWriter.println(# Load constant int 0);
           mPrintWriter.println("ldi    r24,lo8("+node.getRExp().value+")");
           mPrintWriter.println("ldi    r25,hi8("+node.getRExp().value+")");
           //# push two byte expression onto stack);
            mPrintWriter.println("push   r25");
            mPrintWriter.println("push   r24");*/
           //# load a two byte expression off stack);
            mPrintWriter.println("pop    r18");
            //mPrintWriter.println("pop    r19");
           //# load a one byte expression off stack
            mPrintWriter.println("pop    r24");
            mPrintWriter.println("pop    r25");
           //# promoting a byte to an int
            mPrintWriter.println("tst     r18");
            mPrintWriter.println("brlt" +     l0);
            mPrintWriter.println("ldi    r19, 0");
            mPrintWriter.println("jmp    " + l1);
            mPrintWriter.println(l0 + ":");
            mPrintWriter.println("ldi    r19, hi8(-1)");
            mPrintWriter.println(l1 + ":");

            //mPrintWriter.println(# Do add operation
            mPrintWriter.println("add    r24, r18");
            mPrintWriter.println("adc    r25, r19");
            //mPrintWriter.println(# push two byte expression onto stack
            mPrintWriter.println("push   r25");
            mPrintWriter.println("push   r24");
       }
       else{
           mPrintWriter.println("pop    r18");
           mPrintWriter.println("pop    r19");
          //# load a one byte expression off stack
           mPrintWriter.println("pop    r24");
           mPrintWriter.println("pop    r25");

           mPrintWriter.println("add    r24, r18");
           mPrintWriter.println("adc    r25, r19");
           //mPrintWriter.println(# push two byte expression onto stack
           mPrintWriter.println("push   r25");
           mPrintWriter.println("push   r24");
       }
    }
       @Override
       public void outMinusExp(MinusExp node){
           Label l1 = new Label();
           Label l0 = new Label();
           Label l2 = new Label();
           Label l3 = new Label();
           Type lexpType = this.mCurrentST.getExpType(node.getLExp());
           Type rexpType = this.mCurrentST.getExpType(node.getRExp());
           //# Load constant int 1
           if(lexpType == Type.BYTE && rexpType == Type.INT){
               /*mPrintWriter.println("ldi    r24,lo8("+node.getLExp().value+")");
               mPrintWriter.println("ldi    r25,hi8("+node.getLExp().value+")");
               //# push two byte expression onto stack
               mPrintWriter.println("push   r25");
               mPrintWriter.println("push   r24");
               //# Load constant int 0
               mPrintWriter.println("ldi    r24,lo8("+node.getRExp().value+")");
               mPrintWriter.println("ldi    r25,hi8("+node.getRExp().value+")");
               //# push two byte expression onto stack
               mPrintWriter.println("push   r25");
               mPrintWriter.println("push   r24");
               //# Casting int to byte by popping
               //# 2 bytes off stack and only pushing low order bits
               //# back on.  Low order bits are on top of stack.
               mPrintWriter.println("pop    r24");
               mPrintWriter.println("pop    r25");
               mPrintWriter.println("push   r24");*/
               //# load a one byte expression off stack
               mPrintWriter.println("pop    r18");
               mPrintWriter.println("pop    r19");
               //mPrintWriter.println(# load a one byte expression off stack
               mPrintWriter.println("pop    r24");
               //mPrintWriter.println(# promoting a byte to an int
               mPrintWriter.println("tst     r24");
               mPrintWriter.println("brlt "  +  l0);
               mPrintWriter.println("ldi    r25, 0");
               mPrintWriter.println("jmp " +  l1);
               mPrintWriter.println(l0 + ":");
               mPrintWriter.println("ldi    r25, hi8(-1)");
               mPrintWriter.println( l1 + ":");

              // mPrintWriter.println(# Do add operation
               mPrintWriter.println("sub    r24, r18");
               mPrintWriter.println("sbc    r25, r19");
               //mPrintWriter.println(# push two byte expression onto stack
               mPrintWriter.println("push   r25");
               mPrintWriter.println("push   r24");
           }
           else if(lexpType == Type.BYTE && rexpType == Type.BYTE){
               /*mPrintWriter.println("ldi    r24,lo8("+node.getLExp().value+")");
               mPrintWriter.println("ldi    r25,hi8("+node.getLExp().value+")");
               //# push two byte expression onto stack
               mPrintWriter.println("push   r25");
               mPrintWriter.println("push   r24");
               //# Casting int to byte by popping
               //# 2 bytes off stack and only pushing low order bits
               //# back on.  Low order bits are on top of stack.
               mPrintWriter.println("pop    r24");
               mPrintWriter.println("pop    r25");
               mPrintWriter.println("push   r24");
               mPrintWriter.println("ldi    r24,lo8("+node.getRExp().value+")");
               mPrintWriter.println("ldi    r25,hi8("+node.getRExp().value+")");
               //# push two byte expression onto stack
               mPrintWriter.println("push   r25");
               mPrintWriter.println("push   r24");
               //# Casting int to byte by popping
               //# 2 bytes off stack and only pushing low order bits
               //# back on.  Low order bits are on top of stack.
               mPrintWriter.println("pop    r24");
               mPrintWriter.println("pop    r25");
               mPrintWriter.println("push   r24");*/
               //# load a one byte expression off stack
               mPrintWriter.println("pop    r18");
               //# load a one byte expression off stack
               mPrintWriter.println("pop    r24");
               //# promoting a byte to an int
               mPrintWriter.println("tst     r24");
               mPrintWriter.println("brlt     " + l0);
               mPrintWriter.println("ldi    r25, 0");
               mPrintWriter.println("jmp    " + l1);
               mPrintWriter.println(l0 + ":");
               mPrintWriter.println("ldi    r25, hi8(-1)");
              mPrintWriter.println( l1 + ":");
               //# promoting a byte to an int
               mPrintWriter.println("tst     r18");
               mPrintWriter.println("brlt     " + l2);
               mPrintWriter.println("ldi    r19, 0");
               mPrintWriter.println("jmp    " + l3);
               mPrintWriter.println(l2 + ":");
               mPrintWriter.println("ldi    r19, hi8(-1)");
               mPrintWriter.println(l3 + ":");

               //mPrintWriter.println(# Do add operation
               mPrintWriter.println("sub    r24, r18");
               mPrintWriter.println("sbc    r25, r19");
               //mPrintWriter.println(# push two byte expression onto stack
               mPrintWriter.println("push   r25");
               mPrintWriter.println("push   r24");
           }
           else if(lexpType == Type.INT && rexpType == Type.BYTE){
               /*mPrintWriter.println("ldi    r24,lo8("+node.getLExp().value+")");
               mPrintWriter.println("ldi    r25,hi8("+node.getLExp().value+")");
               //mPrintWriter.println(# push two byte expression onto stack);
               mPrintWriter.println("push   r25");
               mPrintWriter.println("push   r24");
               //mPrintWriter.println(# Casting int to byte by popping);
               //mPrintWriter.println(# 2 bytes off stack and only pushing low order bits);
               //mPrintWriter.println(# back on.  Low order bits are on top of stack.);
               mPrintWriter.println("pop    r24");
               mPrintWriter.println("pop    r25");
               mPrintWriter.println("push   r24");
               //mPrintWriter.println(# Load constant int 0);
               mPrintWriter.println("ldi    r24,lo8("+node.getRExp().value+")");
               mPrintWriter.println("ldi    r25,hi8("+node.getRExp().value+")");
               //# push two byte expression onto stack);
                mPrintWriter.println("push   r25");
                mPrintWriter.println("push   r24");*/
               //# load a two byte expression off stack);
                mPrintWriter.println("pop    r18");
                //mPrintWriter.println("pop    r19");
               //# load a one byte expression off stack
                mPrintWriter.println("pop    r24");
                mPrintWriter.println("pop    r25");
               //# promoting a byte to an int
                mPrintWriter.println("tst     r18");
                mPrintWriter.println("brlt" +     l0);
                mPrintWriter.println("ldi    r19, 0");
                mPrintWriter.println("jmp    " + l1);
                mPrintWriter.println(l0 + ":");
                mPrintWriter.println("ldi    r19, hi8(-1)");
                mPrintWriter.println(l1 + ":");

                //mPrintWriter.println(# Do add operation
                mPrintWriter.println("sub    r24, r18");
                mPrintWriter.println("sbc    r25, r19");
                //mPrintWriter.println(# push two byte expression onto stack
                mPrintWriter.println("push   r25");
                mPrintWriter.println("push   r24");
           }
           else{
               mPrintWriter.println("pop    r18");
               mPrintWriter.println("pop    r19");
              //# load a one byte expression off stack
               mPrintWriter.println("pop    r24");
               mPrintWriter.println("pop    r25");

               mPrintWriter.println("sub    r24, r18");
               mPrintWriter.println("sbc    r25, r19");
               //mPrintWriter.println(# push two byte expression onto stack
               mPrintWriter.println("push   r25");
               mPrintWriter.println("push   r24");
           }
       }


   @Override
   public void outMulExp(MulExp node){
       /*mPrintWriter.println("ldi    r24,lo8("+node.getLExp().value+")");
       mPrintWriter.println("ldi    r25,hi8("+node.getLExp().value+")");
       mPrintWriter.println("push   r25");
       mPrintWriter.println("push   r24");
       mPrintWriter.println("pop    r24");
       mPrintWriter.println("pop    r25");
       mPrintWriter.println("push   r24");
       mPrintWriter.println("ldi    r24,lo8("+node.getRExp().value+")");
       mPrintWriter.println("ldi    r25,hi8("+node.getRExp().value+")");
       mPrintWriter.println("push   r25");
       mPrintWriter.println("push   r24");
       mPrintWriter.println("pop    r24");
       mPrintWriter.println("pop    r25");
       mPrintWriter.println("push   r24");*/
       Type lexpType = this.mCurrentST.getExpType(node.getLExp());
       Type rexpType = this.mCurrentST.getExpType(node.getRExp());
       if(lexpType == Type.BYTE && rexpType == Type.BYTE){
       mPrintWriter.println("pop    r18");
       mPrintWriter.println("pop    r22");
       mPrintWriter.println("mov    r24, r18");
       mPrintWriter.println("mov    r26, r22");
       mPrintWriter.println("muls   r24, r26");
       mPrintWriter.println("push   r1");
       mPrintWriter.println("push   r0");

       mPrintWriter.println("eor    r0,r0");
       mPrintWriter.println("eor    r1,r1");
   }
   }
   @Override
   public void outEqualExp(EqualExp node){
      /* mPrintWriter.println("#EqualExp");
       Label l3 = new Label();
       Label l4 = new Label();
       Label l5 = new Label();
       Type lexpType = this.mCurrentST.getExpType(node.getLExp());
       Type rexpType = this.mCurrentST.getExpType(node.getRExp());
       /*mPrintWriter.println("ldi    r24,lo8("+node.getLExp().value+")");
       mPrintWriter.println("ldi    r25,hi8("+node.getLExp().value+")");
       //# push two byte expression onto stack
       mPrintWriter.println("push   r25");
       mPrintWriter.println("push   r24");*/

       //# Load constant int 1
       /*mPrintWriter.println("ldi    r24,lo8("+node.getRExp().value+")");
       mPrintWriter.println("ldi    r25,hi8("+node.getRExp().value+")");
       //# push two byte expression onto stack
       mPrintWriter.println("push   r25");
       mPrintWriter.println("push   r24");
       //# equality check expression
       //# load a two byte expression off stack
       if(lexpType == Type.BOOL){
           mPrintWriter.println("pop    r18");
       }
       else{
           mPrintWriter.println("pop    r18");
           mPrintWriter.println("pop    r19");
       }
       //# load a two byte expression off stack
       if(rexpType == Type.BOOL){
            mPrintWriter.println("pop    r24");
       }
       else{
           mPrintWriter.println("pop    r24");
           mPrintWriter.println("pop    r25");
       }
       mPrintWriter.println("cp    r24, r18");
       mPrintWriter.println("cpc   r25, r19");
       mPrintWriter.println("breq "+l4);
       //mPrintWriter.println(# result is false
       mPrintWriter.println(l3 + ":");
       mPrintWriter.println("ldi     r24, 0");
       mPrintWriter.println("jmp      "+l5);
       //mPrintWriter.println(# result is true
       mPrintWriter.println(l4 +":");
       mPrintWriter.println("ldi     r24, 1");
       //mPrintWriter.println(# store result of equal expression
       mPrintWriter.println( l5 + ":");
      //mPrintWriter.println( # push one byte expression onto stack
       mPrintWriter.println("push   r24");*/

       Label l3, l4, l5; l3 = new Label();l4 = new Label(); l5 = new Label();
       Label l1, l2, l6, l7; l1 = new Label(); l2  = new Label(); //l3  = new Label();
       l6  = new Label(); l7  = new Label();
       Type lexpType = this.mCurrentST.getExpType(node.getLExp());
       Type rexpType = this.mCurrentST.getExpType(node.getRExp());

       if(lexpType == Type.BYTE && rexpType == Type.BYTE){
           mPrintWriter.println("pop    r18");
       //# load a one byte expression off stack
        mPrintWriter.println("pop    r24");
        mPrintWriter.println("cp    r24, r18");
        mPrintWriter.println("breq " + l4);

       //# result is false
        mPrintWriter.println(l3 + ":");
        mPrintWriter.println("ldi     r24, 0");
        mPrintWriter.println("jmp      l5");

       //# result is true
        mPrintWriter.println(l4 + ":");
        mPrintWriter.println("ldi     r24, 1");

       //# store result of equal expression
        mPrintWriter.println(l5 + ":");
        mPrintWriter.println("# push one byte expression onto stack");
        mPrintWriter.println("push   r24");
        }
        else if(rexpType == Type.INT && lexpType == Type.BYTE){
            mPrintWriter.println("pop    r18");
            mPrintWriter.println("pop    r19");
            //# load a one byte expression off stack
            mPrintWriter.println("pop    r24");
            //# promoting a byte to an int
            mPrintWriter.println("tst     r24");
            mPrintWriter.println("brlt"  +   l6);
            mPrintWriter.println("ldi    r25, 0");
            mPrintWriter.println("jmp " +  l7);
            mPrintWriter.println(l6 + ":");
            mPrintWriter.println("ldi    r25, hi8(-1)");
            mPrintWriter.println(l7 + ":");
            mPrintWriter.println("cp    r24, r18");
            mPrintWriter.println("cpc   r25, r19");
            mPrintWriter.println("breq " + l4);

            //# result is false
            mPrintWriter.println(l3 + ":");
            mPrintWriter.println("ldi     r24, 0");
            mPrintWriter.println("jmp  " +   l5);
            mPrintWriter.println(l4 + ":");
                mPrintWriter.println("ldi     r24, 1");

                mPrintWriter.println("# store result of equal expression");
            mPrintWriter.println(l5 + ":");
                mPrintWriter.println("# push one byte expression onto stack");
                mPrintWriter.println("push   r24");
        }
        else if(lexpType == Type.INT && rexpType == Type.BYTE){
            mPrintWriter.println("pop    r24");
            mPrintWriter.println("pop    r25");
            //# load a one byte expression off stack
            mPrintWriter.println("pop    r18");
            //# promoting a byte to an int
            mPrintWriter.println("tst     r18");
            mPrintWriter.println("brlt"  +   l6);
            mPrintWriter.println("ldi    r19, 0");
            mPrintWriter.println("jmp " +  l7);
            mPrintWriter.println(l6 + ":");
            mPrintWriter.println("ldi    r19, hi8(-1)");
            mPrintWriter.println(l7 + ":");
            mPrintWriter.println("cp    r24, r18");
            mPrintWriter.println("cpc   r25, r19");
            mPrintWriter.println("breq " + l4);

            //# result is false
            mPrintWriter.println(l3 + ":");
            mPrintWriter.println("ldi     r24, 0");
            mPrintWriter.println("jmp  " +   l5);
            mPrintWriter.println(l4 + ":");
                mPrintWriter.println("ldi     r24, 1");

                mPrintWriter.println("# store result of equal expression");
            mPrintWriter.println(l5 + ":");
                mPrintWriter.println("# push one byte expression onto stack");
                mPrintWriter.println("push   r24");
        }
        else if(lexpType == Type.INT && rexpType == Type.INT){
            mPrintWriter.println("pop    r18");
            mPrintWriter.println("pop    r19");
            //mPrintWriter.println(# load a two byte expression off stack);
            mPrintWriter.println("pop    r24");
            mPrintWriter.println("pop    r25");
            mPrintWriter.println("cp    r24, r18");
            mPrintWriter.println("cpc   r25, r19");
            mPrintWriter.println("breq " +  l4);

            //mPrintWriter.println(# result is false);
            mPrintWriter.println(l3 + ":");
            mPrintWriter.println("ldi     r24, 0");
            mPrintWriter.println("jmp " +      l5);

            //mPrintWriter.println(# result is true);
            mPrintWriter.println(l4 + ":");
            mPrintWriter.println("ldi     r24, 1");

            //mPrintWriter.println(# store result of equal expression);
            mPrintWriter.println(l5 + ":");
            //mPrintWriter.println(# push one byte expression onto stack);
            mPrintWriter.println("push   r24");
        }
        else  {
            mPrintWriter.println("pop    r18");
            //mPrintWriter.println("pop    r19");
            //mPrintWriter.println(# load a two byte expression off stack);
            mPrintWriter.println("pop    r24");
            //mPrintWriter.println("pop    r25");
            mPrintWriter.println("cp    r24, r18");
            //mPrintWriter.println("cpc   r25, r19");
            mPrintWriter.println("breq " +  l4);

            //mPrintWriter.println(# result is false);
            mPrintWriter.println(l3 + ":");
            mPrintWriter.println("ldi     r24, 0");
            mPrintWriter.println("jmp " +      l5);

            //mPrintWriter.println(# result is true);
            mPrintWriter.println(l4 + ":");
            mPrintWriter.println("ldi     r24, 1");

            //mPrintWriter.println(# store result of equal expression);
            mPrintWriter.println(l5 + ":");
            //mPrintWriter.println(# push one byte expression onto stack);
            mPrintWriter.println("push   r24");
        }


       }

   @Override
   public void outNegExp(NegExp node){
       /*mPrintWriter.println("ldi    r24,lo8("+node.getExp().value+")");
       mPrintWriter.println("ldi    r25,hi8("+node.getExp().value+")");
       //# push two byte expression onto stack
       mPrintWriter.println("push   r25");
       mPrintWriter.println("push   r24");*/

       //# neg int
       //# load a two byte expression off stack
       mPrintWriter.println("pop    r24");
       mPrintWriter.println("pop    r25");
       mPrintWriter.println("ldi     r22, 0");
       mPrintWriter.println("ldi     r23, 0");
       mPrintWriter.println("sub     r22, r24");
       mPrintWriter.println("sbc     r23, r25");
       //# push two byte expression onto stack
       mPrintWriter.println("push   r23");
       mPrintWriter.println("push   r22");

       //# Casting int to byte by popping
       //# 2 bytes off stack and only pushing low order bits
       //# back on.  Low order bits are on top of stack.
       /*mPrintWriter.println("pop    r24");
       mPrintWriter.println("pop    r25");
       mPrintWriter.println("push   r24");*/
   }
   @Override
   public void outByteCast(ByteCast node){
       mPrintWriter.println("#ByteCast");
       if(this.mCurrentST.getExpType(node.getExp()) == Type.INT){
           mPrintWriter.println("pop    r24");
           mPrintWriter.println("pop    r25");
           mPrintWriter.println("push   r24");
       }
       else if (this.mCurrentST.getExpType(node.getExp()) == Type.BYTE){
           mPrintWriter.println("pop    r24");
           mPrintWriter.println("push   r24");
        }
   }
   @Override
   public void outNotExp(NotExp node){
       mPrintWriter.println("pop r24");
       mPrintWriter.println("ldi r22, 1");
       mPrintWriter.println("eor r24, r22");
       mPrintWriter.println("push r24");
   }
   /*
   Other expressions
   ButtonExp
   ColorExp*/
   //================================================================Other Expressions
    @Override
    public void visitIntLiteral(IntLiteral node){
        mPrintWriter.println("#Integer");
        mPrintWriter.println("ldi    r24,lo8("+node.getIntValue()+")");
        mPrintWriter.println("ldi    r25,hi8("+node.getIntValue()+")");
        mPrintWriter.println("push   r25");
        mPrintWriter.println("push   r24");
    }
    @Override
    public void visitButtonLiteral(ButtonLiteral node){
            if(node.getIntValue() == 1)
                mPrintWriter.println("lds r24, Button_B");
            if(node.getIntValue() == 2)
                mPrintWriter.println("lds r24, Button_A");
            if(node.getIntValue() == 4)
                mPrintWriter.println("lds r24, Button_Up");
            if(node.getIntValue() == 8)
                mPrintWriter.println("lds r24, Button_Down");
            if(node.getIntValue() == 16)
                mPrintWriter.println("lds r24, Button_Left");
            if(node.getIntValue() == 32)
                mPrintWriter.println("lds r24, Button_Right");
            outButtonExp(node);
    }
    @Override
    public void visitColorLiteral(ColorLiteral node){
        mPrintWriter.println("ldi    r22,"+node.getIntValue());
        mPrintWriter.println("push   r22");

    }
    @Override
    public void visitTrueLiteral(TrueLiteral node)
    {
        mPrintWriter.println("ldi r22, 1");
        mPrintWriter.println("push r22");
    }
    @Override
    public void visitFalseLiteral(FalseLiteral node)
    {
        mPrintWriter.println("ldi r22, 0");
        mPrintWriter.println("push r22");
    }
    /*==============================================================Statements
    BlockStatment
    IfStatement
    MeggyDelay
    MeggySetPixel
    WhileStatement*/
    @Override
    public void visitIfStatement(IfStatement node){
        mPrintWriter.println("#IFStatement");
        Label l0 = new Label();
        Label l1 = new Label();
        Label l2 = new Label();
        //mPrintWriter.println(l1 + ":");
        inIfStatement(node);
        if(node.getExp() != null)
        {
            node.getExp().accept(this);
        }
        mPrintWriter.println("pop    r24");
        //#load zero into reg
        mPrintWriter.println("ldi    r25, 0");

        //#use cp to set SREG
        mPrintWriter.println("cp     r24, r25");
        //#WANT breq MJ_L0
        mPrintWriter.println("brne " +   l1);
        mPrintWriter.println("jmp " +  l0);

        //# then label for if
        mPrintWriter.println(l1 + ":");
        if(node.getThenStatement() != null)
        {
            node.getThenStatement().accept(this);
        }
        mPrintWriter.println("jmp " + l2);

        //# else label for if
        mPrintWriter.println(l0 + ":");
        if(node.getElseStatement() != null)
        {
            node.getElseStatement().accept(this);
        }
        mPrintWriter.println(l2 + ":");

        outIfStatement(node);
    }
    @Override
    public void visitWhileStatement(WhileStatement node){
        mPrintWriter.println("#While");
        Label l1 = new Label();
        Label l2 = new Label();
        Label l3 = new Label();
        inWhileStatement(node);
        mPrintWriter.println(l1 + ":");
        //
        if(node.getExp() != null)
        {
            node.getExp().accept(this);
        }
        mPrintWriter.println("pop    r24");
        mPrintWriter.println("ldi    r25,0");
        mPrintWriter.println("cp     r24, r25");
        //# WANT breq MJ_L2
        mPrintWriter.println("brne   " + l2);
        mPrintWriter.println("jmp    " + l3);

        //# while loop body
        mPrintWriter.println(l2 + ":");
        if(node.getStatement() != null)
        {
            node.getStatement().accept(this);
        }
        mPrintWriter.println("jmp   " + l1);
        //# end of while
        outWhileStatement(node);
        mPrintWriter.println(l3 + ":");

        //
    }
    public static String makeString(int i){
        return String.valueOf(i);
    }
    @Override
    public void inMethodDecl(MethodDecl node){
        MethodSTE newSTE = new MethodSTE(node);
        mCurrentST.pushScope(newSTE.getName());
        String mName = node.callSign();


        mPrintWriter.println("global "+mName);
        mPrintWriter.println(".type "+mName+", @function");
        mPrintWriter.println(mName+":");
        mPrintWriter.println("push r29");
        mPrintWriter.println("push r28");
        mPrintWriter.println("ldi r30, 0");


        LinkedList<Formal> list = newSTE.mSignature;
        mPrintWriter.println("push r30");
        mPrintWriter.println("push r30");

        for(int i = 0; i < list.size(); i++){
            if(this.mCurrentST.getExpType(list.get(i)).getAVRTypeSize() == 2){
                mPrintWriter.println("push r30");
                mPrintWriter.println("push r30");
            }
            else if(this.mCurrentST.getExpType(list.get(i)).getAVRTypeSize() == 1){
                mPrintWriter.println("push r30");
            }
        }
        mPrintWriter.println("in r28, __SP_L__");
        mPrintWriter.println("in r29, __SP_H__");
        mPrintWriter.println("std Y + 2, r25");
        mPrintWriter.println("std Y + 1, r24");
        int register = 24;
        for(Iterator<Formal> iter = newSTE.mSignature.iterator(); iter.hasNext();){
            //VarSTE ste = (VarSTE)iter.getValue();
            VarSTE ste = new VarSTE(iter.next(), 0);
            if(ste.getType() == Type.INT){
                mPrintWriter.println("std Y + " + makeString(ste.mOffset+1) + ", r" + makeString(register-1));
                mPrintWriter.println("std Y + " + makeString(ste.mOffset+1) + ", r" + makeString(register-2));
            }
            else{
                mPrintWriter.println("std Y + " + makeString(ste.mOffset+1) + ", r" + makeString(register-2));
            }
            register -=2;
        }
    }
    @Override
    public void outMethodDecl(MethodDecl node){
        String mName = node.callSign();
        MethodSTE newSTE = new MethodSTE(node);
        LinkedList<Formal> list = newSTE.mSignature;
        Label l0 = new Label();
        Label l1 = new Label();
        if(newSTE.mType == Type.INT && this.mCurrentST.getExpType(node.getExp())==Type.BYTE){
        mPrintWriter.println("pop    r24");
        //mPrintWriter.println(# promoting a byte to an int);
        mPrintWriter.println("tst     r24");
        mPrintWriter.println("brlt     " + l0);
        mPrintWriter.println("ldi    r25, 0");
        mPrintWriter.println("jmp    "+l1);
        mPrintWriter.println(l0+":");
        mPrintWriter.println("ldi    r25, hi8(-1)");
        mPrintWriter.println(l1+":");
        }
        else if(newSTE.mType == Type.INT){
            mPrintWriter.println("pop r25");
            mPrintWriter.println("pop r24");
        }
        else{
            mPrintWriter.println("pop r24");
        }
        mPrintWriter.println("pop r30");
        mPrintWriter.println("pop r30");
        for(int i = 0; i < list.size(); i++){
            if(this.mCurrentST.getExpType(list.get(i)).getAVRTypeSize() == 2){
                mPrintWriter.println("pop r30");
                mPrintWriter.println("pop r30");
            }
            else if(this.mCurrentST.getExpType(list.get(i)).getAVRTypeSize() == 1){
                mPrintWriter.println("pop r30");
            }
        }
        mPrintWriter.println("pop r28");
        mPrintWriter.println("pop r29");
        mPrintWriter.println("ret");
        mPrintWriter.println(".size "+mName+ ", .-"+mName);

    }
    @Override
    public void outCallExp(CallExp node){

    }

    @Override
    public void outMeggySetPixel(MeggySetPixel node)
    {
        mPrintWriter.println("pop r20");
        mPrintWriter.println("pop r22");
        mPrintWriter.println("pop r24");
        mPrintWriter.println("call _Z6DrawPxhhh");
        mPrintWriter.println("call _Z12DisplaySlatev");
    }
    @Override
    public void outMeggyDelay(MeggyDelay node)
    {
        /*mPrintWriter.println("ldi    r24,lo8(" + node.getExp() + ")");
        mPrintWriter.println("ldi    r25,hi8(" + node.getExp()+ ")");
        //# push two byte expression onto stack
        mPrintWriter.println("push   r25")
        mPrintWriter.println("push   r24")*/

        //### Meggy.delay() call
        //# load delay parameter
        //# load a two byte expression off stack
        mPrintWriter.println("pop    r24");
        mPrintWriter.println("pop    r25");
        mPrintWriter.println("call   _Z8delay_msj");
    }
    //@Override
    public void outMeggyGetPixel(MeggyGetPixel node){
        mPrintWriter.println("#MeggyGetPixel");

        mPrintWriter.println("pop    r22");
        //# load a one byte expression off stack
        mPrintWriter.println("pop    r24");
        mPrintWriter.println("call   _Z6ReadPxhh");
        //# push one byte expression onto stack
        mPrintWriter.println("push   r24");
    }

    @Override
    public void inMeggyCheckButton(MeggyCheckButton node){
        //mPrintWriter.println(call _)
        mPrintWriter.println("call    _Z16CheckButtonsDownv");
    }

    @Override
    public void outMeggyCheckButton(MeggyCheckButton node)
    {


        Label l3, l4, l5;
        l3 = new Label();
        l4 = new Label();
        l5 = new Label();
        mPrintWriter.println("tst r24");
        mPrintWriter.println("breq " + l3);
        mPrintWriter.println(l4 + ":");
        mPrintWriter.println("ldi r24, 1");
        mPrintWriter.println("jmp "  +l5);
        mPrintWriter.println(l3 + ":");
        mPrintWriter.println(l5 + ":");
        mPrintWriter.println("push r24");
    }
    public void inProgram(Program node){
        System.out.println("Generate prolog using avrH.rtl.s");
        InputStream mainPrologue=null;

        BufferedReader reader=null;

        try {
            // The syntax for loading a text resource file
            // from a jar file here:
            // http://www.rgagnon.com/javadetails/java-0077.html
            mainPrologue
                = this.getClass().getClassLoader().getResourceAsStream(
                    "avrH.rtl.s");
            reader = new BufferedReader(new
                InputStreamReader(mainPrologue));

            String line = null;
            while ((line = reader.readLine()) != null) {
              mPrintWriter.println(line);
            }
        } catch ( Exception e2) {
            e2.printStackTrace();
        }
        finally{
            try{
                if(mainPrologue!=null) mainPrologue.close();
                if(reader!=null) reader.close();
            }
            catch (IOException e) {
               e.printStackTrace();
            }
        }
    }
    public void inTopClassDecl(TopClassDecl node){
        mCurrentST.pushScope(node.getName());
    }
    public void outTopClassDecl(TopClassDecl node){
        mCurrentST.popScope();
    }
    public void inMainClass(MainClass node){
        mCurrentST.pushScope(node.getName());
    }
    public void outProgram(Program node)
    {
        System.out.println("Generate epilog using avrF.rtl.s");
    InputStream mainEpilogue=null;BufferedReader reader2=null;
    try {
    mainEpilogue
        = this.getClass().getClassLoader().getResourceAsStream(
            "avrF.rtl.s");
    reader2 = new BufferedReader(new
        InputStreamReader(mainEpilogue));

    String line = null;
    while ((line = reader2.readLine()) != null) {
      mPrintWriter.println(line);
    }
    } catch ( Exception e2) {
    e2.printStackTrace();
    }
    finally{
    try{
        if(mainEpilogue!=null) mainEpilogue.close();
        if(reader2!=null) reader2.close();
    }
    catch (IOException e) {
       e.printStackTrace();
    }
    }
      mPrintWriter.flush();
    }
    public void outMainClass(MainClass node)
    {
        mCurrentST.popScope();
    }


}
