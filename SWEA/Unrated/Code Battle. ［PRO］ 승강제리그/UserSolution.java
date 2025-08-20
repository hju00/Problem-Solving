import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeSet;

class UserSolution {
	
	static TreeSet<Player> league[];
	static int n, l, m;
	
	static class Player implements Comparable<Player> {
		int id;
		int ability;
		
		public Player(int id, int ability) {
			super();
			this.id = id;
			this.ability = ability;
		}

		@Override
		public int compareTo(Player o) {
			if(this.ability != o.ability)
				return Integer.compare(o.ability, this.ability);
			return Integer.compare(this.id, o.id);
		}
		
	}
	
    void init(int N, int L, int mAbility[]) {
    	
    	// 다른 함수에서 사용하기 위해 전역변수 지정
    	n = N;
    	l = L;
    	m = N / L;
    	
    	// 선수들이 포함된 리그
    	// m 번의 정렬이 move, trade 시 발생하기 때문에 정렬을 자동으로 해주는 TreeSet 사용
    	league = new TreeSet[L];
    	for(int i = 0; i < L; i++)	league[i] = new TreeSet();
    	
    	for(int i = 0; i < N; i++)	{
    		Player p = new Player(i, mAbility[i]);
    		league[i / m].add(p);
    	}
    	
    }

    int move() {
        
    	int sum = 0;
    	
    	Player temp1[] = new Player[l];
    	Player temp2[] = new Player[l];
    	
    	for(int i = 1; i < l; i++)	{
    		temp1[i] = league[i].first();
    		league[i].remove(temp1[i]);
    		sum += temp1[i].id;
    	}
    	
    	for(int i = 0; i < l - 1; i++)	{
    		temp2[i] = league[i].last();
    		league[i].remove(temp2[i]);
    		sum += temp2[i].id;
    	}
    	
    	for(int i = 0; i < l; i++)	{
    		if(i < l - 1)
    			league[i].add(temp1[i + 1]);
    		if(i > 0)
    			league[i].add(temp2[i - 1]);
    	}
    	    	
    	return sum;
    }

    int trade() {
    	
    	int sum = 0;
    	
    	Player temp1[] = new Player[l];
    	Player temp2[] = new Player[l];
    	
    	for(int i = 0; i < l - 1; i++)	{
    		Iterator<Player> iter = league[i].iterator();
    		int cnt = -1;
    		
    		// 각 리그의 중간 선수를 TreeSet 에서 뽑는 작업
    		while(iter.hasNext())	{
    			Player p = iter.next();
    			cnt++;
    			if(cnt == m / 2)	{
    				temp2[i] = p;
    				break;
    			}
    		}
    		
    		league[i].remove(temp2[i]);
    		sum += temp2[i].id;
    	}
    	
    	for(int i = 1; i < l; i++)	{
    		temp1[i] = league[i].first();
    		league[i].remove(temp1[i]);
    		sum += temp1[i].id;
    	}
    	
    	for(int i = 0; i < l; i++)	{
    		if(i < l - 1)
    			league[i].add(temp1[i + 1]);
    		if(i > 0)
    			league[i].add(temp2[i - 1]);
    	}
    	    	
        return sum;
    }

}
