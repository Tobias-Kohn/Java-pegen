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
        name = "_ast.key_value_pair",
        base = AST.class
)
public class KeyValuePair extends PythonTree {

    public static final PyType TYPE;

    private PyObject key;

    private PyObject value;

    public PyObject getKey() {
        return this.key;
    }

    public void setKey(PyObject key) {
        this.key = key;
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

    public KeyValuePair(PyType subType) {
        super(subType);
    }

    public KeyValuePair() {
        this(TYPE);
    }

    public KeyValuePair(PyObject key, PyObject value) {
        this();
        setKey(key);
        setValue(value);
    }

    public String toString() {
        return "key_value_pair";
    }
}