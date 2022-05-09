p = 23
q = 5
a = 6
b = 15


X = (q**a)%p
Y = (q**b)%p

A = (Y**a)%p
B = (X**b)%p

print(A)
print(B)