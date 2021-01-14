package org.python.parser;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * The actual parser is generated automatically by the "pegen" parser generator.  This class provides the basic
 * infrastructure for the generated parser, such as consuming tokens in various forms, i.e. implementing the grammar
 * rules for the terminals.
 *
 * @param <T>  This is the type of the AST-nodes produced by the parser.
 */
public abstract class Parser<T> {

    protected AstFactory<T> ast;
    protected final String filename;
    private final BufferedTokenizer tokenizer;
    protected int _level = 0;
    protected final boolean verbose;

    Parser(Tokenizer tokenizer, String filename, boolean verbose, AstFactory<T> ast) {
        this.tokenizer = new BufferedTokenizer(tokenizer);
        this.filename = filename;
        this.verbose = verbose;
        this.ast = ast;
    }

    public static <U> U run_parser(String source, String filename, AstFactory<U> ast) {
        return run_parser(source, filename, ast, false);
    }

    public static <U> U run_parser(String source, String filename, AstFactory<U> ast, boolean verbose) {
        Parser<U> parser = new GeneratedParser<U>(new Tokenizer(source), filename, verbose, ast);
        return parser.start();
    }

    protected int mark() {
        return this.tokenizer.mark();
    }

    protected void reset(int mark) {
        this.tokenizer.reset(mark);
    }

    protected T start() {
        return file();
    };

    protected T start(ParserInputType inputType) {
        switch (inputType) {
            case FILE:
                return file();
            case SINGLE:
                return interactive();
            case EVAL:
                return eval();
            case FUNC_TYPE:
                return func_type();
            case FSTRING:
                return fstring();
            default:
                return start();
        }
    }

    protected abstract T file();

    protected abstract T interactive();

    protected abstract T eval();

    protected abstract T func_type();

    protected abstract T fstring();

    protected abstract boolean isKeyword(String name);

    public String getFilename() {
        return filename;
    }

    public String showpeek() {
        Token tok = tokenizer.peek();
        return ("" + tok.getStartLine() + "." + tok.getStartOffset() + ": " +
                tok.getTokenType().toString() + ":" + tok.getText());
    }

    private Token _expect(TokenType tokenType) {
        logl(String.format("expect<>() at %d: %s", getPos(), tokenType.toString()));
        Token tok = tokenizer.peek();
        if (tok.getTokenType() == tokenType) {
            tokenizer.advance();
            return tok;
        }
        return null;
    }

    protected int col_offset() {
        return tokenizer.getCurrentOffset();
    }

    protected int lineno() {
        return tokenizer.getCurrentLineNo();
    }

    protected int getPos() {
        return tokenizer.getPos();
    }

    public T name() {
        logl(String.format("name() at %d", getPos()));
        Token tok = tokenizer.peek();
        if (tok.getTokenType() == TokenType.NAME && !isKeyword(tok.getText())) {
            tokenizer.advance();
            return ast.from_token(tok);
        }
        return null;
    }

    public Token number() {
        return _expect(TokenType.NUMBER);
    }

    public Token string() {
        return _expect(TokenType.STRING);
    }

    public Token op() {
        return _expect(TokenType.OP);
    }

    public Token expect(TokenType tokenType) {
        logl(String.format("expect() at %d: %s", getPos(), tokenType.toString()));
        Token tok = tokenizer.peek();
        if (tok.getTokenType() == tokenType && !isKeyword(tok.getText())) {
            tokenizer.advance();
            return tok;
        }
        return null;
    }

    public Token expectStr(String tokenType) {
        logl(String.format("expectStr() at %d: %s", getPos(), tokenType));
        Token tok = tokenizer.peek();
        if (tok.getTokenType().toString().equals(tokenType)) {
            if (isKeyword(tokenType))
                return null;
            else {
                tokenizer.advance();
                return tok;
            }
        }
        String tokenText = tok.getTokenType().getText();
        if (tokenText != null && tokenText.equals(tokenType)) {
            tokenizer.advance();
            return tok;
        }
        return null;
    }

    public Token expectStr(char tokenType) {
        return expectStr(Character.toString(tokenType));
    }

    public Token expectKeyword(String keyword) {
        logl(String.format("expectKeyword() at %d: %s", getPos(), keyword));
        Token tok = tokenizer.peek();
        if (tok.getTokenType() == TokenType.NAME && tok.getText().equals(keyword)) {
            tokenizer.advance();
            return tok;
        }
        return null;
    }

    public boolean positive_lookahead(Supplier<?> func) {
        int m = mark();
        boolean result = func.get() != null;
        reset(m);
        return result;
    }

    public boolean positive_lookahead(Function<String, ?> func, String arg) {
        int m = mark();
        boolean result = func.apply(arg) != null;
        reset(m);
        return result;
    }

    public boolean positive_lookahead(Function<String, ?> func, char arg) {
        int m = mark();
        boolean result = func.apply(Character.toString(arg)) != null;
        reset(m);
        return result;
    }

    public boolean negative_lookahead(Supplier<?> func) {
        int m = mark();
        boolean result = func.get() == null;
        reset(m);
        return result;
    }

    public boolean negative_lookahead(Function<String, ?> func, String arg) {
        int m = mark();
        boolean result = func.apply(arg) == null;
        reset(m);
        return result;
    }

    public boolean negative_lookahead(Function<String, ?> func, char arg) {
        int m = mark();
        boolean result = func.apply(Character.toString(arg)) == null;
        reset(m);
        return result;
    }

    protected void log(String s) {
        if (verbose) {
            System.out.print(new String(new char[this._level * 2]).replace('\0', ' '));
            System.out.println(s);
        }
    }

    protected void logl(String s) {
        if (verbose) {
            System.out.print(new String(new char[this._level * 2]).replace('\0', ' '));
            System.out.print(s);
            System.out.println(String.format(" (looking at %s)", showpeek()));
        }
    }

    protected void log(String s, Object o) {
        if (o != null)
            log(s + o.toString());
        else
            log(s + "null");
    }

    protected T[] null_if_empty(T[] seq) {
        if (seq.length == 0)
            return null;
        return seq;
    }

    protected T RAISE_SYNTAX_ERROR(String... msg) {
        return null;
    }

    protected T RAISE_SYNTAX_ERROR_KNOWN_LOCATION(T token, String... msg) {
        return null;
    }

    protected T RAISE_SYNTAX_ERROR_KNOWN_LOCATION(Token token, String... msg) {
        return null;
    }

    protected T RAISE_SYNTAX_ERROR_INVALID_TARGET(Object kind, T token) {
        return null;
    }

    protected T RAISE_INDENTATION_ERROR(String msg) {
        return null;
    }

    public void make_syntax_error(String filename) {
        System.out.println("There is an error to report!");
    }
}
