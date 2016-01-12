package domain.expressions;

import domain.theADTs.MyDictionary;
import domain.theADTs.MyHeap;

import java.io.Serializable;
import java.util.Scanner;

public final class ReadExpression implements Serializable, Expression {
    @Override
    public int eval(MyDictionary SymTable, MyHeap heap) {
        Scanner r = new Scanner(System.in);
        System.out.print("Introduce the number");
        int n = r.nextInt();
        r.nextLine();
        return n;
    }

    @Override
    public String toString() {
        return "readExp()";
    }
}
