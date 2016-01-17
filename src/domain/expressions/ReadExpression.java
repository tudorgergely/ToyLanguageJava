package domain.expressions;

import domain.state.Heap;
import domain.state.SymbolTable;
import java.util.Scanner;

public final class ReadExpression implements Expression {
    private static final long serialVersionUID = -1585179567296035427L;

    @Override
    public Integer eval(final SymbolTable symbolTable, final Heap heap) {
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
