\ENCODING=ISO-8859-1
\Problem name: ilog.cplex

Minimize
 obj: 6 x1 + x2 + 4 x3 + 2 x4 + 3 x5 + 4 x6 + x7 + 3 x8
Subject To
 v: x1 + x2 - x6  = 1
 v: - x1 + x3 - x5 - x7  = 0
 v: - x2 + x4 + x5  = 0
 v: - x4 + x6 + x7 + x8  = 0
 v: - x3 - x8  = -1
End
