# seqs
seqs (= **s**anj0 **eq**uation **s**olver) is a command-line equation
solver for linux and macOS built using Java (and therefore also works on windows;
installer and launch script only available for UNIX(-like))

### CAUTION: seqs evaluates user input with JavaScript, without checking it first.
Never let any third party invoke `seqs` on your system!

## How seqs solves equations
seqs solves any equation using the following steps:
1. through string-manipulation, manipulate the given equation as follows:
    1. split it by the `=` sign
    2. merge both hands on the left side, combined by a `-` sign and replace
       the former left hand by a `0`:
       `x + 2 = x * 3` -> `(x + 2)-(x*3) = 0`
    3. treat the right hand of the equation like a function and find its root
       (the x for which it yields 0, therefore the solution of our equation `e(x) = 0`)
       using newton's method
        1. use Java's builtin JavaScript engine to evaluate functions
        2. find the functions derivative using `e'(x) = (e(x + dx) - e(x)) / dx`
           where `dx` is a very small number
        3. the depth `d` defines how many iterations to apply newton's method for   

## Usage
Once installed using the installation script, seqs can be invoked from anywhere
using `seqs`:
```bash
chmod +x install.sh
./install.sh
seqs -h
```
Invoking `seqs` with the `-h` or `--help` option prints the following help
(as of 1.0-SNAPSHOT on 29.03.21):
```bash
usage: seqs <EQUATION> [-d <INTEGER>] [-dx <DOUBLE>] [-h] [-m] [-s
       <INTEGER>] [-v]
Solves an equation
The equation has to be valid JavaScript
 -d,--depth <INTEGER>   the number of times to apply the newton formula -
                        the higher this value the (potentially) more exact
                        the solution becomes
                        Default: 3
 -dx <DOUBLE>           the dx value to be used for deriving
                        Default: 1.0E-6
 -h,--help              prints this help
 -m,--human             i'm a human - treat me like one (prefixes the
                        solution with 'x = ')
 -s,--start <INTEGER>   the starting value for the newton method to solve
                        the equation
                        Default: 2.0
 -v,--verbose           prints out some more information

Example usage: seqs -d 5 "pow(x,2)+4 = 7"

Built and maintained by sanj0 at https://www.github.com/sanj0/seqs
```

### Uninstall seqs
The seqs installation script places two files on the system:

1. `/usr/local/bin/seqs.jar`
2. `/usr/local/bin/seqs`

To uninstall seqs, simply delete the two files using your file explorer or the
following command:
`rm /usr/local/bin/seqs.jar && rm /usr/local/bin/seqs`

### Equation syntax
Any equation has to contain three parts the left hand, the equal sign `=`
and the right hand. `x` is the variable any equation is solved for, it can be
on both sides of the equation. The eqations has to be in valid JavaScript syntax,
shortcuts for the following `Math.*` method are provided:
- pow -> Math.pow
- sqrt -> Math.sqrt
- cbrt -> Math.cbrt
- log -> Math.log
- abs -> Math.abs
- ceil -> Math.ceil
- floor -> Math.floor
- sin -> Math.sin
- cos -> Math.cos
- tan -> Math.tan
