package org.python.parser;

import java.util.List;

/**
 * The parser does not create the AST nodes directly, but uses this interface and thus allows for customisation of the
 * AST nodes and what kind of nodes are effectively created.  Some of the functions will most probably not create new
 * nodes, but return global singletons.
 *
 * Note that there are series of additional auxiliary functions present, which need to be implemented.  These are
 * located here because they depend on the exact implementation of the generic type `T`.
 *
 * In contrast to the C version, we rely on a generic type parameter `T` here, at the expense of not further
 * distinguishing between expressions and statements, say, or other special types of nodes.  On the one hand, this
 * reflects Python's dynamic typing, which can be found in the AST.  On the other hand, it means that the usual type
 * casts have to be done in the concrete implementation of this AstFactory, rather than the parser itself.
 */
public interface AstFactory<T> {

    T AnnAssign(T target, T annotation, T value, int simple, int line_no, int col_offset);
    T Assert(T test, T msg, int line_no, int col_offset);
    T Assign(T[] targets, T value, T type_comment, int line_no, int col_offset);
    T AsyncFor(T target, T iter, T[] body, T[] orelse, T type_comment, int line_no, int col_offset);
    T AsyncFunctionDef(String name, T args, T[] body, T decorator_list, T returns, T type_comments,  int line_no, int col_offset);
    T AsyncWith(T[] items, T[] body, T type_comment, int line_no, int col_offset);
    T Attribute(T value, String attr, T ctx, int line_no, int col_offset);
    T AugAssign(T target, T op, T value, int line_no, int col_offset);
    T Await(T value, int line_no, int col_offset);
    T BinOp(T left, T op, T right, int line_no, int col_offset);
    T BoolOp(T op, T[] values, int line_no, int col_offset);
    T Break(int line_no, int col_offset);
    T Call(T func, T[] args, T[] keywords, int line_no, int col_offset);
    T ClassDef(String name, T[] bases, T[] keywords, T[] body, T decorator_list, int line_no, int col_offset);
    T Compare(T left, T[] ops, T[] comparators, int line_no, int col_offset);
    T Constant(T value, T kind, int line_no, int col_offset);
    T Continue(int line_no, int col_offset);
    T Delete(T[] targets, int line_no, int col_offset);
    T Dict(T[] keys, T[] values, int line_no, int col_offset);
    T DictComp(T key, T value, T[] generators, int line_no, int col_offset);
    T ExceptHandler(T type_, String name, T[] body, int line_no, int col_offset);
    T Expr(T value, int line_no, int col_offset);
    T Expression(T expr);
    T For(T target, T iter, T[] body, T[] orelse, T type_comment, int line_no, int col_offset);
    T FormattedValue(T value, int conversion, T format_spec, int line_no, int col_offset);
    T FunctionDef(String name, T args, T[] body, T decorator_list, T returns, T type_comments,  int line_no, int col_offset);
    T FunctionType(T[] a, T b);
    T GeneratorExp(T elt, T[] generators, int line_no, int col_offset);
    T Global(T[] names, int line_no, int col_offset);
    T If(T test, T[] body, T[] orelse, int line_no, int col_offset);
    T IfExp(T test, T body, T orelse, int line_no, int col_offset);
    T Import(T[] names, int line_no, int col_offset);
    T ImportFrom(String module, T[] names, int level, int line_no, int col_offset);
    T Interactive(T[] body);
    T JoinedStr(T[] values, int line_no, int col_offset);
    T Lambda(T args, T body, int line_no, int col_offset);
    T List(T[] elts, T ctx, int line_no, int col_offset);
    T ListComp(T elt, T[] generators, int line_no, int col_offset);
    T Module(T[] body, int line_no, int col_offset);
    T Name(String id, T ctx, int line_no, int col_offset);
    T NamedExpr(T target, T value, int line_no, int col_offset);
    T Nonlocal(T[] names, int line_no, int col_offset);
    T Pass(int line_no, int col_offset);
    T Return(T value, int line_no, int col_offset);
    T Raise(T exc, T cause, int line_no, int col_offset);
    T Set(T[] elts, int line_no, int col_offset);
    T SetComp(T elt, T[] generators, int line_no, int col_offset);
    T Slice(T lower, T upper, T step, int line_no, int col_offset);
    T Starred(T value, T ctx, int line_no, int col_offset);
    T Subscript(T value, T slice, T ctx, int line_no, int col_offset);
    T Tuple(T[] elts, T ctx, int line_no, int col_offset);
    T Try(T[] body, T[] handlers, T[] orelse, T[] finalbody, int line_no, int col_offset);
    T TypeIgnore(int line_no, String tag);
    T UnaryOp(T op, T operand, int line_no, int col_offset);
    T While(T test, T[] body, T[] orelse, int line_no, int col_offset);
    T With(T[] items, T[] body, T type_comment, int line_no, int col_offset);
    T Yield(T value, int line_no, int col_offset);
    T YieldFrom(T value, int line_no, int col_offset);

    T alias(String name, String asname);
    T arg(String arg, T annotation, T type_comment, int line_no, int col_offset);
    T arguments(T[] posonlyargs, T[] args, T vararg, T[] kwonlyargs, T[] kw_defaults, T kwarg, T[] defaults);
    T comprehension(T target, T iter, T[] ifs, int is_async);
    T keyword(String arg, T value, int line_no, int col_offset);
    T withitem(T context_expr, T optional_vars);

    T Add();
    T And();
    T BitAnd();
    T BitOr();
    T BitXor();
    T Del();
    T Div();
    T Eq();
    T FloorDiv();
    T Gt();
    T GtE();
    T In();
    T Invert();
    T Is();
    T IsNot();
    T Load();
    T LShift();
    T Lt();
    T LtE();
    T MatMult();
    T Mod();
    T Mult();
    T Not();
    T NotEq();
    T NotIn();
    T Or();
    T Pow();
    T RShift();
    T Sub();
    T Store();
    T UAdd();
    T USub();

    T Ellipsis();
    T False();
    T None();
    T True();

    T add_type_comment_to_arg(T arg, Token type_comment);

    T[] interactive_exit();

    /**
     * Creates a single-element asdl_seq* that contains a
     */
    T[] singleton_seq(T item);

    /**
     * Creates a copy of seq and appends a to it
     */
    T[] seq_append_to_end(T[] seq, T item);

    /**
     * Creates a copy of seq and prepends a to it
     */
    T[] seq_insert_in_front(T item, T[] seq);

    /**
     * Flattens an seq of seq.
     *
     * In Java, this will just return the argument passed to it.  Because of the strict differentiation between
     * individual elements and sequences/array, there are no sequences of sequences.
     */
    T[] seq_flatten(T[] seq);

    /**
     * Creates a new name of the form `<first_name>.<second_name>`.
     */
    T join_names_with_dot(T a, T b);

    /**
     * Counts the total number of dots in seq's tokens.
     */
    int seq_count_dots(T[] items);

    /**
     * Creates an alias with '*' as the identifier name.
     */
    T alias_for_star();

    /**
     * Creates a new seq with the identifiers of all the names in seq.
     */
    T[] map_names_to_ids(T[] arg);

    /**
     * Constructs a CmpopExprPair.
     */
    T cmpop_expr_pair(T op, T item);

    T[] get_cmpops(T[] items);
    T[] get_exprs(T[] items);

    /**
     * Creates an `expr` equivalent to `expr` but with `ctx` as context.
     */
    T set_expr_context(T expr, T ctx);

    /**
     * Constructs a KeyValuePair that is used when parsing a dict's key value pairs.
     */
    T key_value_pair(T key, T value);

    /**
     * Extracts all keys from an asdl_seq* of KeyValuePair*'s.
     */
    T[] get_keys(T[] items);

    /**
     * Extracts all values from an asdl_seq* of KeyValuePair*'s.
     */
    T[] get_values(T[] items);

    /**
     * Constructs a NameDefaultPair.
     */
    T name_default_pair(T arg, T value, Token tc);

    /**
     * Constructs a SlashWithDefault.
     */
    T slash_with_default(T[] plain_names, T[] names_with_defaults);

    /**
     * Constructs a StarEtc.
     */
    T star_etc(T vararg, T[] kwonlyargs, T kwarg);

    /**
     * Constructs an arguments object out of all the parsed constructs in the parameters rule.
     */
    T make_arguments(T[] slash_without_default, T slash_with_default, T[] plain_names, T[] names_with_default, T star_etc);

    /**
     * Construct a FunctionDef equivalent to function_def, but with decorators.
     */
    T function_def_decorators(T[] decorators, T function);

    /**
     * Construct a ClassDef equivalent to class_def, but with decorators.
     */
    T class_def_decorators(T[] decorators, T cls);

    /**
     * Construct a KeywordOrStarred.
     */
    T keyword_or_starred(T element, int is_keyword);

    /**
     * Extract the starred expressions of an asdl_seq* of KeywordOrStarred*s.
     */
    T[] seq_extract_starred_exprs(T[] a);

    /**
     * Return a new asdl_seq* with only the keywords in kwargs.
     */
    T[] seq_delete_starred_exprs(T[] a);

    T collect_call_seqs(T[] expr, T[] b, int line_no, int col_offset);

    T concatenate_strings(T[] items);

    T[] join_sequences(T[] a, T[] b);

    // int check_barry_as_flufl(Token token);

    T make_module(T[] body);

    /**
     * Encapsulates the value of an operator into an AugOperator struct.
     *
     * In Java, it just returns `op`, as the `op` itself is already encapsulated due to the generic typing.
     */
    T augoperator(T op);

    T dummy_name();

    /**
     * Constructs an empty arguments_ty object, that gets used when a function accepts no arguments.
     */
    T empty_arguments();

    // T nonparen_genexp_in_call(T a);
    // T arguments_parsing_error(T a);
    String get_expr_name(T a);

    T from_token(Token tok);
    T[] to_seq(List<T> elts);
    T type_comment(Token tc);
    T type_comment(T tc);

    // T aux_make_seq(T a, T[] b);

    // Needed to handle access to fields of objects of type T
    T[] get_call_args(T item);
    T[] get_call_keywords(T item);
    T get_pair_key(T pair);
    T get_pair_value(T pair);
}
