# Calculator Compiler in JAVA

The Calculator consist of five operations :  `+, -, *, ()`

It can be extended if you implements other Expression Class.

# Structure
1. Compiler act as the Factory to create Expressions from plain text
2. All Expressions are implement one common interface: `Expression`
3. Current Expression includes:
    - Constant
        * A Floating Point Number
    - BinaryExpr
        * Binary Expression 
        * e.g: `1 + 1` or `5 * 3`
        * Operands can be another expression, not only constant
    - ParenthesisExpr
        * Will pointed to the expression inside the Parenthesis
        * e.g: `(1 + 1)` will pointed to the BinaryExpr of `1 + 1`
        
        
# Design Pattern
1. Factory Pattern
    - From Compiler to create Expressions
    
# Abstract Syntax Tree

Expression will compile into a tree, whole tree will be created recursively in the compiler

![Syntax Tree](./snapshot/tree_diagram.png)