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
        name = "_ast.name_default_pair",
        base = AST.class
)
public class NameDefaultPair extends PythonTree {

    public static final PyType TYPE;

    private PyObject arg;

    private PyObject value;

    public PyObject getArg() {
        return this.arg;
    }

    public void setArg(PyObject arg) {
        this.arg = arg;
    }

    public PyObject getValue() {
        return this.value;
    }

    public void setValue(PyObject value) {
        this.value = value;
    }

    static {
        TYPE = PyType.fromClass(NameDefaultPair.class);
    }

    public NameDefaultPair(PyType subType) {
        super(subType);
    }

    public NameDefaultPair() {
        this(TYPE);
    }

    public NameDefaultPair(PyObject arg, PyObject value) {
        this();
        setArg(arg);
        setValue(value);
    }

    public String toString() {
        return "name_default_pair";
    }
}
