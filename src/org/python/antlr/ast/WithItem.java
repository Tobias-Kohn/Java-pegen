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
        name = "_ast.withitem",
        base = AST.class
)
public class WithItem extends PythonTree {

    public static final PyType TYPE;

    private PyObject context_expr;

    private PyObject optional_vars;

    public PyObject getContextExpr() {
        return this.context_expr;
    }

    public void setContextExpr(PyObject context_expr) {
        this.context_expr = context_expr;
    }

    public PyObject getOptionalVars() {
        return this.optional_vars;
    }

    public void setOptionalVars(PyObject optional_vars) {
        this.optional_vars = optional_vars;
    }

    static {
        TYPE = PyType.fromClass(NameDefaultPair.class);
    }

    public WithItem(PyType subType) {
        super(subType);
    }

    public WithItem() {
        this(TYPE);
    }

    public WithItem(PyObject key, PyObject value) {
        this();
        setContextExpr(key);
        setOptionalVars(value);
    }

    public String toString() {
        return "withitem";
    }
}
