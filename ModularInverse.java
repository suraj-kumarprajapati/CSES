

// using fermat's theorem
// (a / b) mod M is equal to modular inverse of a * modular inverse of b mod M
// modular inverse of b mod M = (b ^ (M - 2)) % M
// b ^ (M - 2) can be found out using binary exponentiation
class ModularInverse {

    public static int findModularInverse(int b, int M) {
        return pow(b, M - 2, M) % M;
    }

    public static int pow(int a, int b, int M) {
        if(b == 0)
            return 1;
        
        long half = pow(a, b / 2, M);
        long res = (half * half) % M;
        if(b % 2 == 1)
            res = (res * (a % M)) % M;

        return (int) res;
    }


}