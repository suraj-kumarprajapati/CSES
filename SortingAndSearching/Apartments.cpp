

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    ios::sync_with_stdio(false);  // Fast I/O
    cin.tie(nullptr);

    int n, m, k;
    cin >> n >> m >> k;

    vector<int> desired(n);
    vector<int> available(m);

    for (int i = 0; i < n; ++i) {
        cin >> desired[i];
    }
    for (int i = 0; i < m; ++i) {
        cin >> available[i];
    }

    sort(desired.begin(), desired.end());
    sort(available.begin(), available.end());

    int i = 0, j = 0, count = 0;

    while (i < n && j < m) {
        int minSize = desired[i] - k;
        int maxSize = desired[i] + k;

        if (available[j] >= minSize && available[j] <= maxSize) {
            count++;
            i++;
            j++;
        } else if (available[j] < minSize) {
            j++;
        } else {
            i++;
        }
    }

    cout << count << '\n';
    return 0;
}
