#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

int NArr[1000000];

int main(void) {

	int N;
	scanf("%d", &N);

	int i;
	int max = -1000000, min = 1000000;

	for (i = 0; i < N; i++)
	{
		scanf("%d", NArr + i);
		if (NArr[i] > max) max = NArr[i];
		if (NArr[i] < min) min = NArr[i];
	}

	printf("%d %d", min, max);


	return 0;
}