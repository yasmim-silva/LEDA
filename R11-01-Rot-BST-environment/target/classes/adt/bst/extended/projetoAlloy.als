module computadores 

abstract sig Computador{}

one sig LENOVO,DELL,MAC,ACER extends Computador{}

sig Memoria,Rapido,Compacto in Computador{}

pred compacto[c:Computador] {
    c in Compacto
}

pred rapido[c:Computador] {
    c in Rapido
}

pred Memoria[c:Computador] {
    c in Memoria
}


run{} for 6