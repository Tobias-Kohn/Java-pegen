# JavaPegen

**A translation of Python's new _pegen_ parser to Java.**

_This work is currently under active development: not all features are fully implemented, yet!_

[Python](https://www.python.org/) has recently [switched to a new parser](https://www.python.org/dev/peps/pep-0617/), 
using _Parsing Expression Grammars_ (or PEG for short).  Of course, the parser itself is automatically generated from
a [grammar file](https://github.com/python/cpython/blob/master/Grammar/python.gram).  As the 
[parser generator (pegen)](https://github.com/we-like-parsers/pegen) is naturally open source itself, this opens up
the opportunity to not only generate the parser in _C_, but also in other languages.  This project provides an
extension to the parser generator, together with the necessary framework to run the parser in _Java_, i.e. it allows
you to parse the latest Python code directly in Java without any external dependencies.  And when Python gets updated
with new features, just run the Java parser generator on the new grammar and be done.

The main intention of the project is to provide an up-to-date Python parser for [Jython](https://www.jython.org/).
However, the entire parser and parser generator are deliberately kept without reference to Jython or any other
dependency, so you can put into your project whatever it may be.


## Design

In order to compile Python source code, we need several components:

- **Byte-to-text decoder:** taking a stream of bytes in UTF-8 encoding, say, and returning a string;
- **Tokenizer:** grouping characters together to form tokens (such as keywords, numbers, etc.) and keeping track of
  the indentation;
- **Parser:** taking a linear list of tokens and creating an _abstract syntax tree_ (AST);
- **AST-factory:** responsible for creating the individual elements that make up the abstract syntax tree;
- **Code-compiler:** takes the AST and generates actual code that can then be executed by the interpreter.

In CPython, the tokenizer itself is directly responsible for the decoding of input characters.  However, since the
Java ecosystem has built-in sophisticated support for strings, we use a design where the translation of bytes to
text is separate from the actual tokenizer itself.  If your Python program is not loaded from a file, but directly
from a Java _String_, there is no need to go through this decoding stage.

The actual Parser itself is the only part that is generated from the grammar.  All the rest needs to be present in
Java in order to support the parser.

Apart from the tokenizer, you will find a `Parser` class which provides the basic infrastructure for the generated
parser, as well as a `ASTFactory` interface.  The Code-compiler is not part of the parsing stage.  If you want to
use the parser, you must implement the `ASTFactory` interface so that the parser can create all the different nodes
necessary to represent the code.

The entire parser uses generics (you will find a type parameter `T`).  The actual relationship between the various
AST nodes is of no concern to the parser itself, but fully delegated to the AST factory.  However, it also means
that all AST nodes need to have a common base class (which is `PyObject` in the case of Jython).  You will also 
have to perfom type checks and casts in the AST Factory, of course.


## Parser Generator

The parser generator (to be found in `pegen`) is an extension to the 
[Python-version of pegen](https://github.com/we-like-parsers/pegen/tree/master/pegen).  Details on its design and
usage can be found there.  In short (you need Python3.8 or later):
```
python3 pegen java input.gram
```
This will create a file `parser.java` in the current directory, containing the `GeneratedParser` class.


## Usage

```java
import org.python.antlr.ast.Module;
import org.python.core.Py;
import org.python.core.PyObject;
import org.python.parser.Parser;
import org.python.util.PythonInterpreter;

public static parse(String source, string filename) {
    ASTFactory<PyObject> ast_f = new PythonAstFactory();
    Module mod = (Module)Parser.run_parser(source, filename, ast_f);
    PyCode code = Py.compile_flags(mod, "<module>", "__main__", true, true, Py.getCompilerFlags());
    PythonInterpreter interpreter = new PythonInterpreter();
    interpreter.exec(code);
}
```


## State

The parser itself is the parser for the full Python grammar.  However, the supporting infrastructure around it has
still some bits and pieces missing, limiting what it can do.  If you are using the Jython 2-backend provided here,
there are some fundamental limitations since the Python input is actually compiled to a Python 2 interpreter.

Some of the missing parts (in no particular order):
- Support for f-Strings;
- Error detection and handling;
- Parameters and arguments other than purely positional;
- Class definitions;
- Async and await;

Since Jython 2 has no support for features such as (type) annotations, they are simply ignored by the compiler.
In the long run, this is clearly not the desired behaviour, but once Jython 3 has matured enough to support the
full range of Python 3, it is quite easy to enable the compiler to emit the correct code for it.

Although some advanced features like `async` and `await` are not supported, yet, the parser itself works on
the premise that the code adheres to Python 3.8+ and does not offer special treatement for `async` and `await`
as in the original tokenizer/parser for CPython.


## Contributors

For the most part, this project features translations of code written in C to Java, building on the excellent work
done in the Python community.  The original parser generator seems to be mainly developed by Guido van Rossum, 
Lysandros Nikolaou and Pablo Galindo.  Please refer to the respective project for full list and acknowledgement of
authors.

Java translations and additions to existing code:
- Tobias Kohn






