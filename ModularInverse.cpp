

#include <bits/stdc++.h>
using namespace std;

// using fermat's theorem
// (a / b) mod M is equal to modular inverse of a * modular inverse of b mod M
// modular inverse of b mod M = (b ^ (M - 2)) % M
// b ^ (M - 2) can be found out using binary exponentiation
class ModularInverse {
public:
    static long long findModularInverse(long long b, long long M) {
        return powMod(b, M - 2, M) % M;
    }

    static long long powMod(long long a, long long b, long long M) {
        if (b == 0)
            return 1;

        long long half = powMod(a, b / 2, M);
        long long res = (half * half) % M;

        if (b % 2 == 1)
            res = (res * (a % M)) % M;

        return res;
    }
};

int main() {
    long long M = 1000000007;
    long long b = 2;

    cout << ModularInverse::findModularInverse(b, M) << endl;
    return 0;
}
