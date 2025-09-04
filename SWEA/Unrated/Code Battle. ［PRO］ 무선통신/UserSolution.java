package wireless;

import java.util.*;

class UserSolution {

	// 버킷 사이즈를 N이나 mLimit에 따라 유동적으로 바꾸면 더 적은 양의 Radio를 순회하나?
	static final int BN = 100;	// 버킷 한 칸의 사이즈
	
	static int n;			// 도시의 한 변 길이 ( 300 ≤ N ≤ 10,000 )
	static int l;			// 무선통신기 연결 파워제한 (1,000 ≤ mLimit ≤ 30,000, 1,000의 배수)
	static int bSize;		// bucket 사이즈
	
	static Radio[] radioDB;
	static ArrayList<Radio>[][] bucket;
	
	static boolean isIn(int y, int x) {
		return y >= 0 && y < bSize && x >= 0 && x < bSize;
	}
	
	static int getPower(Radio a, Radio b) {
		int dist = getDist(a, b);
		if(a.freq == b.freq)
			return dist * 10;
		return dist * 10 + 1000;
	}
	
	static int getDist(Radio a, Radio b) {
		return Math.abs(a.y - b.y) + Math.abs(a.x - b.x);
	}
	
	static class Power implements Comparable<Power>{
		int power;
		int id;
		
		public Power(int power, int id) {
			super();
			this.power = power;
			this.id = id;
		}
		
		@Override
		public int compareTo(Power o) {
			if(this.power == o.power)
				return Integer.compare(this.id, o.id);
			return Integer.compare(this.power, o.power);
		}
	}
	
	static class Radio {
		int id;
		int freq;
		int y;
		int x;
		
		public Radio(int id, int freq, int y, int x) {
			super();
			this.id = id;
			this.freq = freq;
			this.y = y;
			this.x = x;
		}
	}
	
	void init(int N, int mLimit)
	{
		n = N;
		l = mLimit;
		bSize = N / BN + 1;
		
		radioDB = new Radio[50001];
		bucket = new ArrayList[bSize][bSize];
		// N이 10,000 일 경우 bucket은 100 X 100 사이즈
		for(int i = 0; i < bSize; i++)
			for(int j = 0; j < bSize; j++)
				bucket[i][j] = new ArrayList<>();
	}

	void addRadio(int K, int mID[], int mFreq[], int mY[], int mX[])
	{
		for(int i = 0; i < K; i++) {
			// 라디오 정보
			Radio r = new Radio(mID[i], mFreq[i], mY[i], mX[i]);
			
			// radioDB에 추가
			radioDB[mID[i]] = r;
			
			// 버킷 좌표
			int by = mY[i] / BN;
			int bx = mX[i] / BN;
			
			bucket[by][bx].add(r);
		}
		
	}

	int getMinPower(int mID, int mCount)
	{
		Radio target = radioDB[mID];
		
		// 동시에 연결하는 최소 파워를 반환하기 위한 정렬 자료구조
		TreeSet<Power> candidates = new TreeSet<>();
		
		int by = target.y / BN;
		int bx = target.x / BN;
		int limit = l / 10;
		
		// 탐색할 버킷의 범위 정하기
		int scope;
		if(limit % BN == 0)	scope = limit / BN;
		else				scope = limit / BN + 1;
		
		for(int i = by - scope; i <= by + scope; i++)
			for(int j = bx - scope; j <= bx + scope; j++) {
				// 버킷 배열의 범위를 벗어나면 pass
				if(!isIn(i, j))		continue;
				
				for(Radio r : bucket[i][j])	{
					int power = getPower(target, r);
					
					// 연결하는데 필요한 파워가 mLimit 보다 초과할 경우 연결할 수 없음
					if(power > l)	continue;
					// 자기 자신인 경우 제외
					if(r.id == target.id) 	continue;
					
					// 후보에 추가
					candidates.add(new Power(power, r.id));
				}
				
			}
		
		// mCount 가 100 이하기 때문에 100개 순회는 ㄱㅊ
		int ret = 0;
		int cnt = 0;
		Iterator<Power> iter = candidates.iterator();
		
		while(iter.hasNext())	{
			if(cnt == mCount)	break;
			ret += iter.next().power;
			cnt++;
		}
		
		return ret;
	}
}
