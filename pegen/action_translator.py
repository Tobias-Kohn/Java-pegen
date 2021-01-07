from typing import List, Tuple


AST_FUNCTIONS = (
  "Add", 
  "And",
  "BitAnd",
  "BitOr",
  "BitXor",
  "Del",
  "Div", 
  "Eq",
  "FloorDiv",
  "Gt",
  "GtE",
  "Invert",
  "In",
  "Is",
  "IsNot",
  "LShift",
  "Lt",
  "LtE",
  "MatMult",
  "Mod",
  "Mult", 
  "Not",
  "NotEq",
  "NotIn",
  "Or",
  "Pow",
  "RShift", 
  "Sub",
  "Load",
  "Store",
  "UAdd",
  "USub",
)

AST_METHODS = (
  "Expression",
  "FunctionType",
  "Interactive",
  "singleton_seq",
)

TYPES = (
  "asdl",
  "asdl_expr_seq",
  "asdl_stmt_seq",
  "asdl_alias_seq",
  "asdl_arg_seq",
  "expr_ty"
)

ACTIONS_TRANSFORMS = {
  "Py_Ellipsis": "ast.Ellipsis()",
  "Py_False": "ast.False()",
  "Py_None": "ast.None()",
  "Py_True": "ast.True()",
  "NULL": "null",
  "EXTRA": "line_no , col_offset",
  "NEW_TYPE_COMMENT": "ast.type_comment",
  "STAR_TARGETS": "TargetsType.STAR_TARGETS",
  "DEL_TARGETS": "TargetsType.DEL_TARGETS",
  "FOR_TARGETS": "TargetsType.FOR_TARGETS",
}


def _tokenize(action: str) -> List[Tuple[str, str]]:
    result = []
    i = 0
    while i < len(action):
        while i < len(action) and action[i] <= ' ':
            i += 1
        if i >= len(action):
            break
        ch = action[i]
        j = i
        if ch == "-" and i+1 < len(action) and action[i+1] == ">":
            i += 2
            tp = 'SYM'
        elif ch in "()[]{}":
            i += 1
            tp = 'PAR'
        elif ch in ".,+-*/:;?!&":
            i += 1
            tp = 'SYM'
        elif ch.isdigit():
            while i < len(action) and action[i].isdigit():
                i += 1
            tp = 'NUM'
        elif ch.isalpha() or ch == "_":
            while i < len(action) and (action[i].isalpha() or action[i] == "_"):
                i += 1
            s = action[j:i]
            if s in TYPES:
                tp = 'TYPE'
            else:
                tp = 'NAME'
        elif ch == '"':
            i += 1
            while i < len(action):
                ch = action[i]
                i += 1
                if ch == '"':
                    break
                elif ch == '\\':
                    i += 1
            tp = 'STR'
        else:
            print(f"ERROR Parsing '{action}' at {i}")
            print(action[:i])
            print(' ' * i + action[i:])
            break
        result.append((tp, action[j:i]))
            
    return result
    
    
def _parse(tokens: List[Tuple[str, str]]):
    stack = [[]]
    for (tp, value) in tokens:
        if tp == 'PAR':
            if value in "([{":
                stack.append([])
                stack[-1].append((tp, value))
            else:
                stack[-1].append((tp, value))
                item = stack.pop()
                if 3 <= len(item) <= 4 and item[1][0] == 'TYPE':
                    pass        # Get rid of type casts
                elif len(item) == 3:
                    if len(stack[-1]) == 0 or stack[-1][-1][0] != 'NAME':
                        stack[-1].append(item[1])
                    elif item[1] == ('NAME', "p"):
                        del item[1]
                        stack[-1].append(item)
                    else:
                        stack[-1].append(item)
                elif len(item) >= 5 and item[1] == ('NAME', "p") and item[2] == ('SYM', ","):
                    del item[1:3]
                    stack[-1].append(item)
                elif len(item) >= 3 and len(stack[-1]) > 0 and stack[-1][-1][0] == 'NAME' and stack[-1][-1][1].isupper():
                    macro = stack[-1][-1][1]
                    if macro == "CHECK_VERSION" and len(item) >= 6:
                        del stack[-1][-1]
                        del item[-1]
                        del item[:5]
                        stack[-1].extend(item)
                    elif macro in ("CHECK", "CHECK_NULL_ALLOWED"):
                        del item[0]
                        del item[-1]
                        del stack[-1][-1]
                        stack[-1].extend(item)
                    else:
                        stack[-1].append(item)
                else:
                    stack[-1].append(item)
        elif tp == 'SYM' and value == "->":
            stack[-1].append(('SYM', "."))
        elif tp == 'SYM' and value == "?" and stack[-1][-1][0] == 'NAME':
            stack[-1][-1] = ('NAME', f"({stack[-1][-1][1]} != null)")
            stack[-1].append((tp, value))
        elif tp == 'NAME' and len(stack[-1]) >= 2 and stack[-1][-1] == ('SYM', ".") and stack[-1][-2][0] == 'NAME':
            del stack[-1][-1]
            value = stack[-1][-1][1] + "." + value
            stack[-1][-1] = ('NAME', value)
            if value == "p.arena":
                if len(stack[-1]) == 1:
                    del stack[-1][-1]
                elif stack[-1][-2] == ('SYM', ","):
                    del stack[-1][-2:]
                
        else:
            stack[-1].append((tp, value))
    return stack[-1]
    
    
def _assemble(ast):
    def _asm(items):
        for item in items:
            if isinstance(item, list):
                _asm(item)
            else:
                tp, value = item
                if tp == 'NAME':
                    if value in AST_FUNCTIONS:
                        value = "ast." + value + "()"
                    elif value in AST_METHODS:
                        value = "ast." + value
                    elif value in ACTIONS_TRANSFORMS:
                        value = ACTIONS_TRANSFORMS[value]
                    elif value.startswith("_PyPegen_ast."):
                        value = value[9:]
                    elif value.startswith("_PyPegen_"):
                        value = "ast." + value[9:]
                    elif value.startswith("_Py_"):
                        value = "ast." + value[4:]
                    elif value.endswith(".v.Name.id"):
                        value = f"ast.get_expr_name({value[:-10]})"
                    elif value.endswith(".v.Call.args"):
                        value = f"ast.get_call_args({value[:-12]})"
                    elif value.endswith(".v.Call.keywords"):
                        value = f"ast.get_call_keywords({value[:-16]})"
                    elif value.endswith(".kind"):
                        value = value[:-5]
                    elif value.endswith(".key"):
                        value = f"ast.get_pair_key({value[:-4]})"
                    elif value.endswith(".value"):
                        value = f"ast.get_pair_value({value[:-6]})"
                result.append(value)
            
    result = []
    _asm(ast)
    return ' '.join(result)
    

def translate_action(action: str) -> str:
    t = _tokenize(action)
    ast = _parse(t)
    result = _assemble(ast)
    return result


if __name__ == "__main__":
    TEST = """( asdl * ) _PyPegen_make_seq ( p, CHECK ( a, ( params ) ? NULL : a -> v . Name . value ) , Add ( p ), EXTRA )"""
    TEST = """CHECK_VERSION ( 5 , "Async for loops are" , ast.AsyncFor ( t , ex , b , a . v . Name . id , ( b ) ? ( b ) . v . Name . id : null , NEW_TYPE_COMMENT ( p , tc ) , lineno, col_offset ) )"""
    print(TEST)
    print(translate_action(TEST))
    print("done")

