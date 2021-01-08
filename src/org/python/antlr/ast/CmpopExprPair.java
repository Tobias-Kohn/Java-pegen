package org.python.antlr.ast;

import org.python.antlr.AST;
import org.python.antlr.PythonTree;
import org.python.core.PyObject;
import org.python.core.PyType;
import org.python.expose.ExposedType;

/*
 * INCOMPLETE HELPER-CLASS!
 */
@ExposedType(
        name = "_ast.cmpop_expr_pair",
        base = AST.class
)
public class CmpopExprPair extends PythonTree {

    public static final PyType TYPE;

    private PyObject cmpop;

    private PyObject expr;

    public PyObject getCmpOp() {
        return this.cmpop;
    }

    public void setCmpOp(PyObject cmpop) {
        this.cmpop = cmpop;
    }

    public PyObject getExpr() {
        return this.expr;
    }

    public void setExpr(PyObject expr) {
        this.expr = expr;
    }

    static {
        TYPE = PyType.fromClass(NameDefaultPair.class);
    }

    public CmpopExprPair(PyType subType) {
        super(subType);
    }

    public CmpopExprPair() {
        this(TYPE);
    }

    public CmpopExprPair(PyObject op, PyObject expr) {
        this();
        setCmpOp(op);
        setExpr(expr);
    }

    public String toString() {
        return "name_default_pair";
    }
}
