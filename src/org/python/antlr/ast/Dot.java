package org.python.antlr.ast;

import org.python.antlr.AST;
import org.python.antlr.PythonTree;
import org.python.core.PyType;
import org.python.expose.ExposedType;

/*
 * INCOMPLETE HELPER-CLASS!
 */
@ExposedType(
        name = "_ast.Dot",
        base = AST.class
)
public class Dot extends PythonTree {

    public static final PyType TYPE;
    private int lineno;
    private int col_offset;

    static {
        TYPE = PyType.fromClass(NameDefaultPair.class);
    }

    public Dot(PyType subType) {
        super(subType);
    }

    public Dot() {
        this(TYPE);
    }

    public int getLineno() {
        return this.lineno != -1 ? this.lineno : this.getLine();
    }

    public void setLineno(int num) {
        this.lineno = num;
    }

    public int getCol_offset() {
        return this.col_offset != -1 ? this.col_offset : this.getCharPositionInLine();
    }

    public void setCol_offset(int num) {
        this.col_offset = num;
    }

    public String toString() {
        return "Dot";
    }
}
