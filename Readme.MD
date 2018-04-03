# Calculator Compiler in JAVA

## Features

1. Five common operations :  `+, -, *, /, ()`
2. Extending New Polymorphic Behaviour 
    - Using Decorator Pattern with `extension.decorator.ExpressionDecorator` Class
    - Extending the `ExpressionDecorator` Class for new Add-on Behaviour
    - an example included in `extension.decorator.DecimalDecorator` for Formatting Decimal Place
3. Adding New Operations
    - Using Composite Pattern with `core.expression.Expression`

## Structure
![Structure Diagram](./snapshot/structure_diagram.png)
1. Compiler act as the Factory to create Expressions from plain text
2. All Expressions are implement one common interface: `Expression`
3. Current Expression includes:
    - Constant
        * A Floating Point Number
    - BinaryExpr
        * Binary Expression 
        * e.g: `1 + 1` or `5 * 3`
        * Operands can be another core.expression, not only constant
    - ParenthesisExpr
        * Will pointed to the core.expression inside the Parenthesis
        * e.g: `(1 + 1)` will pointed to the BinaryExpr of `1 + 1`
        
        
## Design Pattern
1. Composite Pattern
    - From Compiler to create Expressions
    
### Abstract Syntax Tree

Expression will core.compilor into a tree, whole tree will be created recursively in the compiler

![Syntax Tree](./snapshot/tree_diagram.png)

## How To Use
- git clone this repo.
- `java -jar /path/to/this/repo/calculator.jar`

### Todo-List
- [X] Tree Visualisation in CLI
- [ ] Calculator in GUI
- [ ] Tree Visualisation in GUI