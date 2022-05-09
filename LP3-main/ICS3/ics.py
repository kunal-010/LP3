def encyption(m,public):
    e, n = public
    c = m ** e % n
    return c

def decryption(c,private):
    d , n = private
    p = c ** d % n
    return p

def generate() :
    
    from random import randint
    from math import gcd
    from sympy import mod_inverse

    
    p,q = 877,751

    n = p * q
    fi_n = (p-1)*(q-1)

    while True:
        e = randint(1,fi_n)
        if gcd(fi_n,e) == 1:
            break

    d = mod_inverse(e,fi_n) 
    return (e,n),(d,n)


public, private = generate()

c = encyption(ord("A"),public)
print(c)
print(decryption(d,private))

