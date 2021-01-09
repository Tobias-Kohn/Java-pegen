package org.python.parser;

import org.python.antlr.adapter.AstAdapters;
import org.python.antlr.ast.Module;
import org.python.antlr.base.expr;
import org.python.core.*;
import org.python.antlr.ast.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PythonAstFactory implements AstFactory<PyObject> {

    private final PyObject _Add = new org.python.antlr.op.Add();
    private final PyObject _And = new org.python.antlr.op.And();
    private final PyObject _BitAnd = new org.python.antlr.op.BitAnd();
    private final PyObject _BitOr = new org.python.antlr.op.BitOr();
    private final PyObject _BitXor = new org.python.antlr.op.BitXor();
    private final PyObject _Div = new org.python.antlr.op.Div();
    private final PyObject _Eq = new org.python.antlr.op.Eq();
    private final PyObject _FloorDiv = new org.python.antlr.op.FloorDiv();
    private final PyObject _Gt = new org.python.antlr.op.Gt();
    private final PyObject _GtE = new org.python.antlr.op.GtE();
    private final PyObject _In = new org.python.antlr.op.In();
    private final PyObject _Invert = new org.python.antlr.op.Invert();
    private final PyObject _Is = new org.python.antlr.op.Is();
    private final PyObject _IsNot = new org.python.antlr.op.IsNot();
    private final PyObject _LShift = new org.python.antlr.op.LShift();
    private final PyObject _Lt = new org.python.antlr.op.Lt();
    private final PyObject _LtE = new org.python.antlr.op.LtE();
    private final PyObject _MatMult = null;
    private final PyObject _Mod = new org.python.antlr.op.Mod();
    private final PyObject _Mult = new org.python.antlr.op.Mult();
    private final PyObject _Not = new org.python.antlr.op.Not();
    private final PyObject _NotEq = new org.python.antlr.op.NotEq();
    private final PyObject _NotIn = new org.python.antlr.op.NotIn();
    private final PyObject _Or = new org.python.antlr.op.Or();
    private final PyObject _Pow = new org.python.antlr.op.Pow();
    private final PyObject _RShift = new org.python.antlr.op.RShift();
    private final PyObject _Sub = new org.python.antlr.op.Sub();

    private final PyObject _Del = new org.python.antlr.op.Del();
    private final PyObject _Load = new org.python.antlr.op.Load();
    private final PyObject _Param = new org.python.antlr.op.Param();
    private final PyObject _Store = new org.python.antlr.op.Store();
    private final PyObject _UAdd = new org.python.antlr.op.UAdd();
    private final PyObject _USub = new org.python.antlr.op.USub();

    public PyObject AnnAssign(PyObject target, PyObject annotation, PyObject value, int simple,
                              int line_no, int col_offset) {
        throw new RuntimeException("AnnAssign");
    }

    public PyObject Assert(PyObject test, PyObject msg, int line_no, int col_offset) {
        Assert result = new Assert(test, msg);
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject Assign(PyObject[] targets, PyObject value, PyObject type_comment, int line_no, int col_offset) {
        Assign result = new Assign();
        result.setTargets(new AstList(Arrays.asList(targets), AstAdapters.exprAdapter));
        result.setValue(value);
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject AsyncFor(PyObject target, PyObject iter, PyObject[] body, PyObject[] orelse, PyObject type_comment,
                             int line_no, int col_offset) {
        throw new RuntimeException("AsyncFor");
    }

    public PyObject AsyncFunctionDef(String name, PyObject args, PyObject[] body, PyObject decorator_list,
                                     PyObject returns, PyObject type_comments,  int line_no, int col_offset) {
        throw new RuntimeException("AsyncFucntionDef");
    }

    public PyObject AsyncWith(PyObject[] items, PyObject[] body, PyObject type_comment, int line_no, int col_offset) {
        throw new RuntimeException("AsyncWith");
    }

    public PyObject Attribute(PyObject value, String attr, PyObject ctx, int line_no, int col_offset) {
        Attribute result = new Attribute(value, Py.newString(attr), ctx);
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject AugAssign(PyObject target, PyObject op, PyObject value, int line_no, int col_offset) {
        AugAssign result = new AugAssign(target, op, value);
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject Await(PyObject value, int line_no, int col_offset) {
        throw new RuntimeException("Await");
    }

    public PyObject BinOp(PyObject left, PyObject op, PyObject right, int line_no, int col_offset) {
        BinOp result = new BinOp(left, op, right);
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject BoolOp(PyObject op, PyObject[] values, int line_no, int col_offset) {
        BoolOp result = new BoolOp();
        result.setOp(op);
        result.setValues(new AstList(Arrays.asList(values), AstAdapters.exprAdapter));
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject Break(int line_no, int col_offset) {
        Break result = new Break();
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject Call(PyObject func, PyObject[] args, PyObject[] keywords, int line_no, int col_offset) {
        Call result = new Call();
        result.setFunc(func);
        if (args != null)
            result.setArgs(new AstList(Arrays.asList(args), AstAdapters.exprAdapter));
        else
            result.setArgs(new AstList(Collections.emptyList(), AstAdapters.exprAdapter));
        if (keywords == null)
            keywords = new PyObject[0];
        result.setKeywords(new AstList(Arrays.asList(keywords), AstAdapters.keywordAdapter));
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject ClassDef(String name, PyObject[] bases, PyObject[] keywords, PyObject[] body,
                             PyObject decorator_list, int line_no, int col_offset) {
        ClassDef result = new ClassDef();
        result.setName(Py.newString(name));
        if (bases == null)
            bases = new PyObject[0];
        result.setBases(new AstList(Arrays.asList(bases), AstAdapters.exprAdapter));
        result.setBody(new AstList(Arrays.asList(body), AstAdapters.stmtAdapter));
        if (decorator_list == null)
            decorator_list = new AstList(Collections.emptyList(), AstAdapters.exprAdapter);
        result.setDecorator_list(decorator_list);
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject Compare(PyObject left, PyObject[] ops, PyObject[] comparators, int line_no, int col_offset) {
        Compare result = new Compare();
        result.setLeft(left);
        result.setOps(new AstList(Arrays.asList(ops), AstAdapters.cmpopAdapter));
        result.setComparators(new AstList(Arrays.asList(comparators), AstAdapters.exprAdapter));
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject Constant(PyObject value, PyObject kind, int line_no, int col_offset) {
        if (value instanceof PyNone) {
            return Name("None", _Load, line_no, col_offset);
        }
        else if (value instanceof PyBoolean) {
            return Name((value == Py.True) ? "True" : "False", _Load, line_no, col_offset);
        }
        throw new RuntimeException("Constant");
    }

    public PyObject Continue(int line_no, int col_offset) {
        Continue result = new Continue();
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject Delete(PyObject[] targets, int line_no, int col_offset) {
        Delete result = new Delete();
        result.setTargets(new AstList(Arrays.asList(targets), AstAdapters.exprAdapter));
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject Dict(PyObject[] keys, PyObject[] values, int line_no, int col_offset) {
        Dict result = new Dict();
        result.setKeys(new AstList(Arrays.asList(keys), AstAdapters.exprAdapter));
        result.setValues(new AstList(Arrays.asList(values), AstAdapters.exprAdapter));
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject DictComp(PyObject key, PyObject value, PyObject[] generators, int line_no, int col_offset) {
        DictComp result = new DictComp();
        result.setKey(key);
        result.setValue(value);
        result.setGenerators(new AstList(Arrays.asList(generators), AstAdapters.comprehensionAdapter));
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject ExceptHandler(PyObject type_, String name, PyObject[] body, int line_no, int col_offset) {
        ExceptHandler result = new ExceptHandler();
        result.setExceptType(type_);
        if (name != null)
            result.setName(new Name(Py.newString(name), _Store));
        result.setBody(new AstList(Arrays.asList(body), AstAdapters.stmtAdapter));
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject Expr(PyObject value, int line_no, int col_offset) {
        Expr result = new Expr(value);
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject Expression(PyObject expr) {
        return new Expression(expr);
    }

    public PyObject For(PyObject target, PyObject iter, PyObject[] body, PyObject[] orelse,
                        PyObject type_comment, int line_no, int col_offset) {
        For result = new For();
        result.setTarget(target);
        result.setIter(iter);
        result.setBody(new AstList(Arrays.asList(body), AstAdapters.stmtAdapter));
        if (orelse != null)
            result.setOrelse(new AstList(Arrays.asList(orelse), AstAdapters.stmtAdapter));
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject FormattedValue(PyObject value, int conversion, PyObject format_spec, int line_no, int col_offset) {
        throw new RuntimeException("FormattedValue");
    }

    public PyObject FunctionDef(String name, PyObject args, PyObject[] body, PyObject decorator_list, PyObject returns,
                                PyObject type_comments,  int line_no, int col_offset) {
        FunctionDef result = new FunctionDef();
        result.setName(Py.newString(name));
        result.setArgs(args);
        result.setBody(new AstList(Arrays.asList(body), AstAdapters.stmtAdapter));
        if (decorator_list == null)
            decorator_list = new AstList(Collections.emptyList(), AstAdapters.exprAdapter);
        result.setDecorator_list(decorator_list);
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject FunctionType(PyObject[] a, PyObject b) {
        throw new RuntimeException("FunctionType");
    }

    public PyObject GeneratorExp(PyObject elt, PyObject[] generators, int line_no, int col_offset) {
        GeneratorExp result = new GeneratorExp();
        result.setElt(elt);
        result.setGenerators(new AstList(Arrays.asList(generators), AstAdapters.comprehensionAdapter));
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject Global(PyObject[] names, int line_no, int col_offset) {
        Global result = new Global();
        result.setNames(new AstList(Arrays.asList(names), AstAdapters.identifierAdapter));
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject If(PyObject test, PyObject[] body, PyObject[] orelse, int line_no, int col_offset) {
        If result = new If();
        result.setTest(test);
        result.setBody(new AstList(Arrays.asList(body), AstAdapters.stmtAdapter));
        if (orelse != null)
            result.setOrelse(new AstList(Arrays.asList(orelse), AstAdapters.stmtAdapter));
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject IfExp(PyObject test, PyObject body, PyObject orelse, int line_no, int col_offset) {
        IfExp result = new IfExp(test, body, orelse);
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject Import(PyObject[] names, int line_no, int col_offset) {
        Import result = new Import();
        result.setNames(new AstList(Arrays.asList(names), AstAdapters.identifierAdapter));
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject ImportFrom(String module, PyObject[] names, int level, int line_no, int col_offset) {
        // This is broken in Jython itself (GitHub issue #77)
        ImportFrom result = new ImportFrom();
        result.setModule(Py.newString(module));
        result.setNames(new AstList(Arrays.asList(names), AstAdapters.identifierAdapter));
        result.setLevel(Py.newInteger(level));
        System.out.println(result.getLevel());
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject Interactive(PyObject[] body) {
        throw new RuntimeException("Interactive");
    }

    public PyObject JoinedStr(PyObject[] values, int line_no, int col_offset) {
        throw new RuntimeException("JoinedStr");
    }

    public PyObject Lambda(PyObject args, PyObject body, int line_no, int col_offset) {
        Lambda result = new Lambda(args, body);
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject List(PyObject[] elts, PyObject ctx, int line_no, int col_offset) {
        org.python.antlr.ast.List result = new org.python.antlr.ast.List();
        result.setElts(new AstList(Arrays.asList(elts), AstAdapters.exprAdapter));
        result.setCtx(ctx);
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject ListComp(PyObject elt, PyObject[] generators, int line_no, int col_offset) {
        ListComp result = new ListComp();
        result.setElt(elt);
        result.setGenerators(new AstList(Arrays.asList(generators), AstAdapters.comprehensionAdapter));
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject Module(PyObject[] body, int line_no, int col_offset) {
        org.python.antlr.ast.Module result = new org.python.antlr.ast.Module();
        result.setBody(new AstList(Arrays.asList(body), AstAdapters.stmtAdapter));
        return result;
    }

    public PyObject Name(String id, PyObject ctx, int line_no, int col_offset) {
        Name result = new Name(Py.newString(id), ctx);
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject NamedExpr(PyObject target, PyObject value, int line_no, int col_offset) {
        throw new RuntimeException("NamedExpr");
    }

    public PyObject Nonlocal(PyObject[] names, int line_no, int col_offset) {
        throw new RuntimeException("Nonlocal");
    }

    public PyObject Pass(int line_no, int col_offset) {
        Pass result = new Pass();
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject Return(PyObject value, int line_no, int col_offset) {
        Return result = new Return(value);
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject Raise(PyObject exc, PyObject cause, int line_no, int col_offset) {
        if (cause != null)
            throw new RuntimeException("raise with cause");
        Raise result = new Raise();
        result.setExceptType(exc);
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject Set(PyObject[] elts, int line_no, int col_offset) {
        Set result = new Set();
        result.setElts(new AstList(Arrays.asList(elts), AstAdapters.exprAdapter));
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject SetComp(PyObject elt, PyObject[] generators, int line_no, int col_offset) {
        SetComp result = new SetComp();
        result.setElt(elt);
        result.setGenerators(new AstList(Arrays.asList(generators), AstAdapters.comprehensionAdapter));
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject Slice(PyObject lower, PyObject upper, PyObject step, int line_no, int col_offset) {
        return new Slice(lower, upper, step);
    }

    public PyObject Starred(PyObject value, PyObject ctx, int line_no, int col_offset) {
        throw new RuntimeException("Starred");
    }

    public PyObject Subscript(PyObject value, PyObject slice, PyObject ctx, int line_no, int col_offset) {
        if (!(slice instanceof Slice) && !(slice instanceof Index))
            slice = new Index(slice);
        Subscript result = new Subscript(value, slice, ctx);
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject Tuple(PyObject[] elts, PyObject ctx, int line_no, int col_offset) {
        Tuple result = new Tuple();
        result.setElts(new AstList(Arrays.asList(elts), AstAdapters.exprAdapter));
        result.setCtx(ctx);
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject Try(PyObject[] body, PyObject[] handlers, PyObject[] orelse, PyObject[] finalbody,
                        int line_no, int col_offset) {
        // If we have both handlers and a final-body, we have to nest a TryExcept inside a TryFinally
        if (handlers != null) {
            TryExcept result = new TryExcept();
            result.setHandlers(new AstList(Arrays.asList(handlers), AstAdapters.excepthandlerAdapter));
            result.setBody(new AstList(Arrays.asList(body), AstAdapters.stmtAdapter));
            if (orelse != null)
                result.setOrelse(new AstList(Arrays.asList(orelse), AstAdapters.stmtAdapter));
            result.setLineno(line_no);
            result.setCol_offset(col_offset);
            if (finalbody != null)
                body = new PyObject[]{ result };
            else
                return result;
        }
        TryFinally result = new TryFinally();
        result.setBody(new AstList(Arrays.asList(body), AstAdapters.stmtAdapter));
        result.setFinalbody(new AstList(Arrays.asList(finalbody), AstAdapters.stmtAdapter));
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject TypeIgnore(int line_no, String tag) {
        throw new RuntimeException("TypeIgnore");
    }

    public PyObject UnaryOp(PyObject op, PyObject operand, int line_no, int col_offset) {
        UnaryOp result = new UnaryOp(op, operand);
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject While(PyObject test, PyObject[] body, PyObject[] orelse, int line_no, int col_offset) {
        While result = new While();
        result.setTest(test);
        result.setBody(new AstList(Arrays.asList(body), AstAdapters.stmtAdapter));
        if (orelse != null)
            result.setOrelse(new AstList(Arrays.asList(orelse), AstAdapters.stmtAdapter));
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject With(PyObject[] items, PyObject[] body, PyObject type_comment, int line_no, int col_offset) {
        WithItem item;
        if (items.length == 1)
            item = (WithItem) items[0];
        else
            throw new RuntimeException("with");
        With result = new With();
        result.setContext_expr(item.getContextExpr());
        if (item.getOptionalVars() != null)
            result.setOptional_vars(item.getOptionalVars());
        result.setBody(new AstList(Arrays.asList(body), AstAdapters.stmtAdapter));
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject Yield(PyObject value, int line_no, int col_offset) {
        Yield result = new Yield(value);
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject YieldFrom(PyObject value, int line_no, int col_offset) {
        throw new RuntimeException("YieldFrom");
    }

    public PyObject alias(String name, String asname) {
        return new alias(Py.newString(name), (asname != null) ? Py.newString(asname) : Py.None);
    }

    public PyObject arg(String arg, PyObject annotation, PyObject type_comment, int line_no, int col_offset) {
        Name result = new Name();
        result.setId(Py.newString(arg));
        result.setCtx(_Param);
        result.setLineno(line_no);
        result.setCol_offset(col_offset);
        return result;
    }

    public PyObject arguments(PyObject[] posonlyargs, PyObject[] args, PyObject vararg, PyObject[] kwonlyargs,
                              PyObject[] kw_defaults, PyObject kwarg, PyObject[] defaults) {
        arguments result = new arguments();
        if (args != null)
            result.setArgs(new AstList(Arrays.asList(args), AstAdapters.exprAdapter));
        else
            result.setArgs(new AstList(Collections.emptyList(), AstAdapters.exprAdapter));
        result.setVararg(vararg);
        result.setKwarg(kwarg);
        if (defaults != null)
            result.setDefaults(new AstList(Arrays.asList(defaults), AstAdapters.exprAdapter));
        else
            result.setDefaults(new AstList(Collections.emptyList(), AstAdapters.exprAdapter));
        return result;
    }

    public PyObject comprehension(PyObject target, PyObject iter, PyObject[] ifs, int is_async) {
        throw new RuntimeException("comprehension");
    }

    public PyObject keyword(String arg, PyObject value, int line_no, int col_offset) {
        return new keyword(Py.newString(arg), value);
    }

    public PyObject withitem(PyObject context_expr, PyObject optional_vars) {
        return new WithItem(context_expr, optional_vars);
    }

    public PyObject Add() { return _Add; }
    public PyObject And() { return _And; }
    public PyObject BitAnd() { return _BitAnd; }
    public PyObject BitOr() { return _BitOr; }
    public PyObject BitXor() { return _BitXor; }
    public PyObject Div() { return _Div; }
    public PyObject Eq() { return _Eq; }
    public PyObject FloorDiv() { return _FloorDiv; }
    public PyObject Gt() { return _Gt; }
    public PyObject GtE() { return _GtE; }
    public PyObject In() { return _In; }
    public PyObject Invert() { return _Invert; }
    public PyObject Is() { return _Is; }
    public PyObject IsNot() { return _IsNot; }
    public PyObject LShift() { return _LShift; }
    public PyObject Lt() { return _Lt; }
    public PyObject LtE() { return _LtE; }
    public PyObject MatMult() { return _MatMult; }
    public PyObject Mod() { return _Mod; }
    public PyObject Mult() { return _Mult; }
    public PyObject Not() { return _Not; }
    public PyObject NotEq() { return _NotEq; }
    public PyObject NotIn() { return _NotIn; }
    public PyObject Or() { return _Or; }
    public PyObject Pow() { return _Pow; }
    public PyObject RShift() { return _RShift; }
    public PyObject Sub() { return _Sub; }

    public PyObject Del() { return _Del; }
    public PyObject Load() { return _Load; }
    public PyObject Store() { return _Store; }
    public PyObject UAdd() { return _UAdd; }
    public PyObject USub() { return _USub; }

    public PyObject Ellipsis() { return Py.Ellipsis; }
    public PyObject False() { return Py.False; }
    public PyObject None() { return Py.None; }
    public PyObject True() { return Py.True; }

    public PyObject add_type_comment_to_arg(PyObject arg, Token type_comment) {
        return arg;
    }
    public PyObject[] interactive_exit() { return null; }

    public PyObject[] singleton_seq(PyObject item) {
        return new PyObject[]{ item };
    }

    public PyObject[] seq_append_to_end(PyObject[] seq, PyObject item) {
        PyObject[] result = new PyObject[seq.length + 1];
        System.arraycopy(seq, 0, result, 0, seq.length);
        result[seq.length] = item;
        return result;
    }

    public PyObject[] seq_insert_in_front(PyObject item, PyObject[] seq) {
        PyObject[] result = new PyObject[seq.length + 1];
        System.arraycopy(seq, 0, result, 1, seq.length);
        result[0] = item;
        return result;
    }

    public PyObject[] seq_flatten(PyObject[] seq) {
        return seq;
    }

    public PyObject join_names_with_dot(PyObject a, PyObject b) {
        Name n1 = (Name) a;
        Name n2 = (Name) b;
        String name = n1.getInternalId() + "." + n2.getInternalId();
        Name result = new Name();
        result.setId(Py.newString(name));
        result.setCtx(n1.getCtx());
        result.setLineno(n1.getLineno());
        result.setCol_offset(n1.getCol_offset());
        return result;
    }

    public int seq_count_dots(PyObject[] items) {
        int result = 0;
        for (PyObject item : items) {
            if (item instanceof Ellipsis || item instanceof PyEllipsis)
                result += 3;
            else if (item instanceof Dot)
                result += 1;
        }
        return result;
    }

    public PyObject alias_for_star() {
        return alias("*", null);
    }

    public PyObject[] map_names_to_ids(PyObject[] arg) {
        throw new RuntimeException("map_names_to_ids");
    }

    public PyObject cmpop_expr_pair(PyObject op, PyObject item) {
        return new CmpopExprPair(op, item);
    }

    public PyObject[] get_cmpops(PyObject[] items) {
        PyObject[] result = new PyObject[items.length];
        for (int i = 0; i < items.length; i++)
            result[i] = ((CmpopExprPair)items[i]).getCmpOp();
        return result;
    }

    public PyObject[] get_exprs(PyObject[] items) {
        PyObject[] result = new PyObject[items.length];
        for (int i = 0; i < items.length; i++)
            result[i] = ((CmpopExprPair)items[i]).getExpr();
        return result;
    }

    public PyObject set_expr_context(PyObject expr, PyObject ctx) {
        if (expr instanceof Attribute)
            ((Attribute) expr).setCtx(ctx);
        else if (expr instanceof org.python.antlr.ast.List)
            ((org.python.antlr.ast.List) expr).setCtx(ctx);
        else if (expr instanceof Name)
            ((Name) expr).setCtx(ctx);
        else if (expr instanceof Subscript)
            ((Subscript) expr).setCtx(ctx);
        else if (expr instanceof Tuple)
            ((Tuple) expr).setCtx(ctx);
        return expr;
    }

    public PyObject key_value_pair(PyObject key, PyObject value) {
        return new KeyValuePair(key, value);
    }

    public PyObject[] get_keys(PyObject[] items) {
        PyObject[] result = new PyObject[items.length];
        for (int i = 0; i < items.length; i++)
            result[i] = ((KeyValuePair)items[i]).getKey();
        return result;
    }

    public PyObject[] get_values(PyObject[] items) {
        PyObject[] result = new PyObject[items.length];
        for (int i = 0; i < items.length; i++)
            result[i] = ((KeyValuePair)items[i]).getValue();
        return result;
    }

    public PyObject name_default_pair(PyObject arg, PyObject value, Token tc) {
        return new NameDefaultPair(arg, value);
    }

    public PyObject slash_with_default(PyObject[] plain_names, PyObject[] names_with_defaults) {
        throw new RuntimeException("slash_with_default");
    }

    public PyObject star_etc(PyObject vararg, PyObject[] kwonlyargs, PyObject kwarg) {
        return new StarEtc(vararg, kwonlyargs, kwarg);
    }

    public PyObject make_arguments(PyObject[] slash_without_default, PyObject slash_with_default,
                                   PyObject[] plain_names, PyObject[] names_with_default, PyObject star_etc) {
        arguments result = new arguments();
        if (plain_names == null)
            plain_names = new PyObject[0];
        if (names_with_default != null && names_with_default.length > 0) {
            PyObject[] names = new PyObject[plain_names.length + names_with_default.length];
            PyObject[] deflt = new PyObject[names_with_default.length];
            System.arraycopy(plain_names, 0, names, 0, plain_names.length);
            for (int i = 0; i < names_with_default.length; i++) {
                names[plain_names.length + i] = ((NameDefaultPair) names_with_default[i]).getArg();
                deflt[i] = ((NameDefaultPair) names_with_default[i]).getValue();
            }
            result.setArgs(new AstList(Arrays.asList(names), AstAdapters.exprAdapter));
            result.setDefaults(new AstList(Arrays.asList(deflt), AstAdapters.exprAdapter));
        } else {
            result.setArgs(new AstList(Arrays.asList(plain_names), AstAdapters.exprAdapter));
            result.setDefaults(new AstList(Collections.emptyList(), AstAdapters.exprAdapter));
        }
        if (star_etc != null) {
            result.setVararg(((StarEtc)star_etc).getVararg());
            result.setKwarg(((StarEtc)star_etc).getKwarg());
        }
        return result;
    }

    public PyObject function_def_decorators(PyObject[] decorators, PyObject function) {
        if (function instanceof FunctionDef) {
            FunctionDef func = (FunctionDef) function;
            func.setDecorator_list(new AstList(Arrays.asList(decorators), AstAdapters.exprAdapter));
            return func;
        } else
            throw new RuntimeException("function_def_decorators");
    }

    public PyObject class_def_decorators(PyObject[] decorators, PyObject cls) {
        if (cls instanceof ClassDef) {
            ((ClassDef) cls).setDecorator_list(new AstList(Arrays.asList(decorators), AstAdapters.exprAdapter));
            return cls;
        } else
            throw new RuntimeException("class_def_decorators");
    }

    public PyObject keyword_or_starred(PyObject element, int is_keyword) {
        // TODO: actually create a keyword or starred item here!
        return element;
    }

    public PyObject[] seq_extract_starred_exprs(PyObject[] a) {
        return null;
    }

    public PyObject[] seq_delete_starred_exprs(PyObject[] a) {
        return a;
    }

    public PyObject collect_call_seqs(PyObject[] a, PyObject[] b, int line_no, int col_offset) {
        if (b == null)
            return Call(dummy_name(), a, null, line_no, col_offset);
        PyObject[] starreds = seq_extract_starred_exprs(b);
        PyObject[] keywords = seq_delete_starred_exprs(b);
        PyObject[] args = join_sequences(a, starreds);
        return Call(dummy_name(), args, keywords, line_no, col_offset);
    }

    public PyObject concatenate_strings(PyObject[] items) {
        StringBuilder stringBuilder = new StringBuilder();
        for (PyObject item : items) {
            if (item instanceof Str) {
                PyObject s = ((Str) item).getS();
                if (s instanceof PyString || s instanceof PyUnicode) {
                    StringParser.StringValue strValue = StringParser.parsestr(s.asString());
                    if (strValue.kind == StringParser.StringType.UNICODE)
                        stringBuilder.append(strValue.value);
                }
            }
        }
        return new Str(Py.newUnicode(stringBuilder.toString()));
    }

    public PyObject[] join_sequences(PyObject[] a, PyObject[] b) {
        if (a.length == 0)
            return b;
        if (b.length == 0)
            return a;
        PyObject[] result = new PyObject[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    public PyObject make_module(PyObject[] body) {
        Module result = new Module();
        result.setBody(new AstList(Arrays.asList(body), AstAdapters.stmtAdapter));
        return result;
    }

    public PyObject augoperator(PyObject op) {
        return op;
    }

    public PyObject dummy_name() {
        return new Name(Py.newString(""), Load());
    }

    public PyObject empty_arguments() {
        arguments result = new arguments();
        result.setArgs(new AstList(Collections.emptyList(), AstAdapters.exprAdapter));
        result.setDefaults(new AstList(Collections.emptyList(), AstAdapters.exprAdapter));
        return result;
    }

    public String get_expr_name(PyObject a) {
        if (a instanceof Name)
            return get_expr_name(((Name) a).getId());
        if (a instanceof PyString || a instanceof PyUnicode)
            return a.asString();
        return null;
    }

    public PyObject from_token(Token tok) {
        switch (tok.getTokenType()) {
            case DOT -> {
                Dot result = new Dot();
                result.setLineno(tok.getStartLine());
                result.setCol_offset(tok.getStartOffset());
                return result;
            }
            case NAME -> {
                String n = tok.getText();
                Name result = new Name();
                result.setId(Py.newString(n));
                result.setCtx(Load());
                result.setLineno(tok.getStartLine());
                result.setCol_offset(tok.getStartOffset());
                return result;
            }
            case NUMBER -> {
                String n = tok.getText();
                if (n.matches("[0-9]+"))
                    return new Num(Py.newLong(n));
                return new Num(Py.newFloat(Double.parseDouble(n)));
            }
            case STRING -> {
                String s = tok.getText();
                return new Str(Py.newUnicode(s));
            }
        }
        return Py.None;
    }

    public PyObject[] to_seq(List<PyObject> elts) {
        PyObject[] result = new PyObject[elts.size()];
        elts.toArray(result);
        return result;
    }

    public PyObject type_comment(Token tc) { return null; }
    public PyObject type_comment(PyObject tc) { return null; }

    public PyObject[] get_call_args(PyObject item) {
        if (item instanceof Call) {
            Call call = (Call) item;
            List<expr> args = call.getInternalArgs();
            PyObject[] result = new PyObject[args.size()];
            args.toArray(result);
            return result;
        }
        return null;
    }

    public PyObject[] get_call_keywords(PyObject item) {
        if (item instanceof Call) {
            Call call = (Call) item;
            List<keyword> keywords = call.getInternalKeywords();
            PyObject[] result = new PyObject[keywords.size()];
            keywords.toArray(result);
            return result;
        }
        return null;
    }

    public PyObject get_pair_key(PyObject pair) {
        throw new RuntimeException("get_pair_key");
    }

    public PyObject get_pair_value(PyObject pair) {
        throw new RuntimeException("get_pair_value");
    }
}
