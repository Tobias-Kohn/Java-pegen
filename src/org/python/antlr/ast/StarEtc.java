package org.python.antlr.ast;

import org.python.antlr.AST;
import org.python.antlr.PythonTree;
import org.python.antlr.adapter.AstAdapters;
import org.python.core.Py;
import org.python.core.PyObject;
import org.python.core.PyType;
import org.python.expose.ExposedType;

/*
 * INCOMPLETE HELPER-CLASS!
 */
@ExposedType(
        name = "_ast.star_etc",
        base = AST.class
)
public class StarEtc extends PythonTree {

    public static final PyType TYPE;

    private String vararg;

    private String kwarg;

    public PyObject getVararg() {
        return (this.vararg != null) ? Py.newString(this.vararg): Py.None;
    }

    public void setVararg(PyObject vararg) {
        if (vararg instanceof Name)
            this.vararg = ((Name) vararg).getInternalId();
        else
            this.vararg = AstAdapters.py2identifier(vararg);
    }

    public PyObject getKwarg() {
        return (this.kwarg != null) ? Py.newString(this.kwarg): Py.None;
    }

    public void setKwarg(PyObject kwarg) {
        if (kwarg instanceof Name)
            this.kwarg = ((Name) kwarg).getInternalId();
        else
            this.kwarg = AstAdapters.py2identifier(kwarg);
    }

    static {
        TYPE = PyType.fromClass(NameDefaultPair.class);
    }

    public StarEtc(PyType subType) {
        super(subType);
    }

    public StarEtc() {
        this(TYPE);
    }

    public StarEtc(PyObject vararg, PyObject[] kwonlyargs, PyObject kwarg) {
        this();
        setVararg(vararg);
        setKwarg(kwarg);
    }

    public String toString() {
        return "star_etc";
    }
}
