
int d(int n);

int main() {

	int checkArr[10001] = { 0, };

	int i;

	for (i = 1; i < 10001; i++) {
		if (d(i) <= 10000)	checkArr[d(i)] = 1;
	}

	for (i = 1; i < 10000; i++)
	{
		if (checkArr[i] != 1)	printf("%d\n", i);
	}


	return 0;
}

int d(int n) {

	int result = n;

	while (n > 0)
	{
		result += n % 10;
		n /= 10;
	}

	return result;
}