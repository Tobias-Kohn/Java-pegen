package org.python.parser;

import java.util.List;

/**
 * The AstFactoryBase provides a basis for a rather quick implementation of the AstFactory.  There is a stub provided
 * for most methods that just returns `null`.  Additionally, it creates singletons for various objects such as binary
 * operators, using the `createSpecialNode` method.
 *
 * It is probably a bad idea to build on this for production code, as returning `null` might unexpectedly break your
 * code.  Its intended purpose is more for testing, debugging, or when only a small "toy"-system is needed.
 */
public abstract class AstFactoryBase<T> implements AstFactory<T> {
    
    protected abstract T createSpecialNode(String name);
    protected abstract T[] createArray(int length);

    private final T _Add = createSpecialNode("+");
    private final T _And = createSpecialNode("and");
    private final T _BitAnd = createSpecialNode("&");
    private final T _BitOr = createSpecialNode("|");
    private final T _BitXor = createSpecialNode("^");
    private final T _Div = createSpecialNode("/");
    private final T _Eq = createSpecialNode("==");
    private final T _FloorDiv = createSpecialNode("//");
    private final T _Gt = createSpecialNode(">");
    private final T _GtE = createSpecialNode(">=");
    private final T _In = createSpecialNode("in");
    private final T _Invert = createSpecialNode("~");
    private final T _Is = createSpecialNode("is");
    private final T _IsNot = createSpecialNode("is not");
    private final T _LShift = createSpecialNode("<<");
    private final T _Lt = createSpecialNode("<");
    private final T _LtE = createSpecialNode("<=");
    private final T _MatMult = createSpecialNode("@");
    private final T _Mod = createSpecialNode("%");
    private final T _Mult = createSpecialNode("*");
    private final T _Not = createSpecialNode("not");
    private final T _NotEq = createSpecialNode("!=");
    private final T _NotIn = createSpecialNode("not in");
    private final T _Or = createSpecialNode("or");
    private final T _Pow = createSpecialNode("**");
    private final T _RShift = createSpecialNode(">>");
    private final T _Sub = createSpecialNode("-");

    private final T _Del = createSpecialNode("del");
    private final T _Load = createSpecialNode("load");
    private final T _Store = createSpecialNode("store");
    private final T _UAdd = createSpecialNode("(+)");
    private final T _USub = createSpecialNode("(-)");

    private final T _Ellipsis = createSpecialNode("...");
    private final T _False = createSpecialNode("False");
    private final T _None = createSpecialNode("None");
    private final T _True = createSpecialNode("True");
    
    public T AnnAssign(T target, T annotation, T value, int simple, int line_no, int col_offset) { return null; }
    public T Assert(T test, T msg, int line_no, int col_offset) { return null; }
    public T Assign(T[] targets, T value, T type_comment, int line_no, int col_offset) { return null; }
    public T AsyncFor(T target, T iter, T[] body, T[] orelse, T type_comment, int line_no, int col_offset) { return null; }
    public T AsyncFunctionDef(String name, T args, T[] body, T decorator_list, T returns, T type_comments,  int line_no, int col_offset) { return null; }
    public T AsyncWith(T[] items, T[] body, T type_comment, int line_no, int col_offset) { return null; }
    public T Attribute(T value, String attr, T ctx, int line_no, int col_offset) { return null; }
    public T AugAssign(T target, T op, T value, int line_no, int col_offset) { return null; }
    public T Await(T value, int line_no, int col_offset) { return null; }
    public T BinOp(T left, T op, T right, int line_no, int col_offset) { return null; }
    public T BoolOp(T op, T[] values, int line_no, int col_offset) { return null; }
    public T Break(int line_no, int col_offset) { return null; }
    public T Call(T func, T[] args, T[] keywords, int line_no, int col_offset) { return null; }
    public T ClassDef(String name, T[] bases, T[] keywords, T[] body, T decorator_list, int line_no, int col_offset) { return null; }
    public T Compare(T left, T[] ops, T[] comparators, int line_no, int col_offset) { return null; }
    public T Constant(T value, T kind, int line_no, int col_offset) { return null; }
    public T Continue(int line_no, int col_offset) { return null; }
    public T Delete(T[] targets, int line_no, int col_offset) { return null; }
    public T Dict(T[] keys, T[] values, int line_no, int col_offset) { return null; }
    public T DictComp(T key, T value, T[] generators, int line_no, int col_offset) { return null; }
    public T ExceptHandler(T type_, String name, T[] body, int line_no, int col_offset) { return null; }
    public T Expr(T value, int line_no, int col_offset) { return null; }
    public T Expression(T expr) { return null; }
    public T For(T target, T iter, T[] body, T[] orelse, T type_comment, int line_no, int col_offset) { return null; }
    public T FormattedValue(T value, int conversion, T format_spec, int line_no, int col_offset) { return null; }
    public T FunctionDef(String name, T args, T[] body, T decorator_list, T returns, T type_comments,  int line_no, int col_offset) { return null; }
    public T FunctionType(T[] a, T b) { return null; }
    public T GeneratorExp(T elt, T[] generators, int line_no, int col_offset) { return null; }
    public T Global(T[] names, int line_no, int col_offset) { return null; }
    public T If(T test, T[] body, T[] orelse, int line_no, int col_offset) { return null; }
    public T IfExp(T test, T body, T orelse, int line_no, int col_offset) { return null; }
    public T Import(T[] names, int line_no, int col_offset) { return null; }
    public T ImportFrom(String module, T[] names, int level, int line_no, int col_offset) { return null; }
    public T Interactive(T[] body) { return null; };
    public T JoinedStr(T[] values, int line_no, int col_offset) { return null; }
    public T Lambda(T args, T body, int line_no, int col_offset) { return null; }
    public T List(T[] elts, T ctx, int line_no, int col_offset) { return null; }
    public T ListComp(T elt, T[] generators, int line_no, int col_offset) { return null; }
    public T Module(T[] body, int line_no, int col_offset) { return null; }
    public T Name(String id, T ctx, int line_no, int col_offset) { return null; }
    public T NamedExpr(T target, T value, int line_no, int col_offset) { return null; }
    public T Nonlocal(T[] names, int line_no, int col_offset) { return null; }
    public T Pass(int line_no, int col_offset) { return null; }
    public T Return(T value, int line_no, int col_offset) { return null; }
    public T Raise(T exc, T cause, int line_no, int col_offset) { return null; }
    public T Set(T[] elts, int line_no, int col_offset) { return null; }
    public T SetComp(T elt, T[] generators, int line_no, int col_offset) { return null; }
    public T Slice(T lower, T upper, T step, int line_no, int col_offset) { return null; }
    public T Starred(T value, T ctx, int line_no, int col_offset) { return null; }
    public T Subscript(T value, T slice, T ctx, int line_no, int col_offset) { return null; }
    public T Tuple(T items, T ctx, int line_no, int col_offset) { return null; }
    public T Tuple(T[] elts, T ctx, int line_no, int col_offset) { return null; }
    public T Try(T[] body, T[] handlers, T[] orelse, T[] finalbody, int line_no, int col_offset) { return null; }
    public T TypeIgnore(int line_no, String tag) { return null; }
    public T UnaryOp(T op, T operand, int line_no, int col_offset) { return null; }
    public T While(T test, T[] body, T[] orelse, int line_no, int col_offset) { return null; }
    public T With(T[] items, T[] body, T type_comment, int line_no, int col_offset) { return null; }
    public T Yield(T value, int line_no, int col_offset) { return null; }
    public T YieldFrom(T value, int line_no, int col_offset) { return null; }
    
    public T alias(String name, String asname) { return null; }
    public T arg(String arg, T annotation, T type_comment, int line_no, int col_offset) { return null; }
    public T arguments(T[] posonlyargs, T[] args, T vararg, T[] kwonlyargs, T[] kw_defaults, T kwarg, T[] defaults) { return null; }
    public T comprehension(T target, T iter, T[] ifs, int is_async) { return null; }
    public T keyword(String arg, T value, int line_no, int col_offset) { return null; }
    public T withitem(T context_expr, T optional_vars) { return null; }

    public T Add() { return _Add; }
    public T And() { return _And; }
    public T BitAnd() { return _BitAnd; }
    public T BitOr() { return _BitOr; }
    public T BitXor() { return _BitXor; }
    public T Div() { return _Div; }
    public T Eq() { return _Eq; }
    public T FloorDiv() { return _FloorDiv; }
    public T Gt() { return _Gt; }
    public T GtE() { return _GtE; }
    public T In() { return _In; }
    public T Invert() { return _Invert; }
    public T Is() { return _Is; }
    public T IsNot() { return _IsNot; }
    public T LShift() { return _LShift; }
    public T Lt() { return _Lt; }
    public T LtE() { return _LtE; }
    public T MatMult() { return _MatMult; }
    public T Mod() { return _Mod; }
    public T Mult() { return _Mult; }
    public T Not() { return _Not; }
    public T NotEq() { return _NotEq; }
    public T NotIn() { return _NotIn; }
    public T Or() { return _Or; }
    public T Pow() { return _Pow; }
    public T RShift() { return _RShift; }
    public T Sub() { return _Sub; }

    public T Del() { return _Del; }
    public T Load() { return _Load; }
    public T Store() { return _Store; }
    public T UAdd() { return _UAdd; }
    public T USub() { return _USub; }

    public T Ellipsis() { return _Ellipsis; }
    public T False() { return _False; }
    public T None() { return _None; }
    public T True() { return _True; }

    public T[] singleton_seq(T item) {
        T[] result = createArray(1);
        result[0] = item;
        return result;
    }

    public T[] seq_append_to_end(T[] seq, T item) {
        T[] result = createArray(seq.length + 1);
        System.arraycopy(seq, 0, result, 0, seq.length);
        result[seq.length] = item;
        return result;
    }

    public T[] seq_insert_in_front(T item, T[] seq) {
        T[] result = createArray(seq.length + 1);
        System.arraycopy(seq, 0, result, 1, seq.length);
        result[0] = item;
        return result;
    }

    public T[] seq_flatten(T[] seq) {
        return seq;
    }

    public T alias_for_star() {
        return alias("*", null);
    }

    public T[] join_sequences(T[] a, T[] b) {
        if (a.length == 0)
            return b;
        if (b.length == 0)
            return a;
        T[] result = createArray(a.length + b.length);
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    public T[] to_seq(List<T> elts) {
        T[] result = createArray(elts.size());
        elts.toArray(result);
        return result;
    }

    public T augoperator(T op) { return op; }

    public T dummy_name() { return Name("", Load(), -1, -1); }

    public T empty_arguments() {
        return arguments(
                createArray(0),
                createArray(0),
                null,
                createArray(0),
                createArray(0),
                null,
                createArray(0)
                );
    }

    public T type_comment(Token tc) { return null; }

    public T type_comment(T tc) { return null; }
}
