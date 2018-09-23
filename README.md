# mscheme
My first scheme intepreter. This toy is written by java 

## Concepts

### S-expression
mscheme use [S-expression](https://en.wikipedia.org/wiki/S-expression) to represent [AST](https://en.wikipedia.org/wiki/Abstract_syntax_tree) 

For exampple, the `Sexpression` of `(+ 1 2 (* 3 4))` behind `SExpression.java` can be shown as: 

![AST_demo](http://upload.wikimedia.org/wikipedia/commons/thumb/1/11/S-expression_tree.svg/220px-S-expression_tree.svg.png)

### eval-apply cycle
eval-apply cycle is described in [SICP 4.1](https://mitpress.mit.edu/sicp/full-text/book/book-Z-H-26.html#%_sec_4.1.1)

![eval-apply cycle](https://klose911.github.io/html/intepreter/pic/eval-apply.gif)

More details can be found here [元求值器](https://klose911.github.io/html/intepreter/meta_evalutor.html)

## Install
```shell
git clone https://github.com/klose911/mscheme.git
cd mscheme
mvn clean package
java -jar target/mscheme-exec.jar
```

## Features

1. datatype
- `number`
- `bool`
- `string`
- `pair`
- `list`
- `procedure`

2. special form
- `quote`
- `define`
- `set!`
- `if`
- `lambda`

3. primitive procedures
- `number`: `+`, `-`, `*`, `/`, `>`, `<`, `=`
- `pair/list`: `cons`, `car`, `cdr`, `list`, `null?`
- `bool`: `and`, `or`, `not`, `eq`

## TODO
1. support `begin` expressions
2. support `cond` expressions 
3. support `continuation` mechanism
4. support syntax `macros` 

## Known Issues
1. input with `newline` character will not be treated as a single expression
2. `lambda` expression must be used to define procedure, like `(define add (lambda (x y) (+ x y))`.  
   The format of `(define (add x y)  (+ x y))` should be supported 
