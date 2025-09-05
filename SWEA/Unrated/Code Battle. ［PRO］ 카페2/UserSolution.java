import java.util.*;

class UserSolution
{
	static int n;			// 카페 음료의 종류 (음료 1 ~ n)
	static int idx;			// 주문 순서
	static int orderCnt;	// 남은 주문의 개수
	
	static HashMap<Integer, Integer> idToIdx;
	static Order[] orderDB;
	static TreeSet<Order> hurryOrders;
	static PriorityQueue<Integer> beverages[];
	
	static class Order implements Comparable<Order>{
		int id;
		int idx;
		int cnt;
		ArrayList<Integer> completeList = new ArrayList<>();
		boolean canceled = false;
		boolean complete = false;

		public Order(int id, int idx, int cnt) {
			super();
			this.id = id;
			this.idx = idx;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Order o) {
			if(this.cnt == o.cnt)
				return Integer.compare(this.idx, o.idx);
			return Integer.compare(o.cnt, this.cnt);
		}
	}
	
    public void init(int N)
    {
    	n = N;
    	idx = 0;
    	orderCnt = 0;
    	
    	idToIdx = new HashMap<>();
    	orderDB = new Order[20001];
    	beverages = new PriorityQueue[N + 1];
    	hurryOrders = new TreeSet<>();
    	
    	for(int i = 1; i <= N; i++)
    		beverages[i] = new PriorityQueue<>();
    	
        return;
    }

    public int order(int mID, int M, int mBeverages[])
    {
    	idToIdx.put(mID, idx);
    	
    	Order nOrder = new Order(mID, idx, M);
    	
    	// orderDB 에 추가
    	orderDB[idx] = nOrder;
    	
    	// hurryOrders 에 추가
    	hurryOrders.add(nOrder);
    	
    	// 음료 목록에 idx 추가
    	for(int i = 0; i < M; i++) {
    		int b = mBeverages[i];
    		beverages[b].add(idx);
    	}
    	
    	// idx 와 orderCnt 증가
    	idx++;
    	orderCnt++;
    	
        return orderCnt;
    }

    public int supply(int mBeverage)
    {
		// lazy delete
		while(!beverages[mBeverage].isEmpty()) {
			int mIdx = beverages[mBeverage].peek();
			Order c = orderDB[mIdx];
			
			if(c.canceled || c.complete) {
				beverages[mBeverage].poll();
				continue;
			}
			
			break;
		}
		
		// 남아 있는 모든 주문들에서 음료 mBeverage가 없는 경우
		if(beverages[mBeverage].isEmpty())
			return -1;
		
		Order c = orderDB[beverages[mBeverage].poll()];
		
		// 정렬을 유지하기 위해 선삭제
		hurryOrders.remove(c);
		
		// 해당 음료 제작 완료 목록에 추가
		c.completeList.add(mBeverage);
		
		// 해당 남은 음료 개수 감소
		c.cnt--;
		
		// 주문이 완료된 경우
		if(c.cnt == 0) {
			c.complete = true;
			orderCnt--;
		}
		// 주문이 완료되지 않은 경우
		else
			// 정렬 위해 후삽입
			hurryOrders.add(c);
    	
        return c.id;
    }

    public int cancel(int mID)
    {
    	int mIdx = idToIdx.get(mID);
    	Order c = orderDB[mIdx];
    	
    	// 이미 모든 음료가 제조되어 손님에게 전달된 경우
    	if(c.complete)
    		return 0;
    	
    	// 이미 취소된 경우
    	if(c.canceled)
    		return -1;
    	
    	// 취소된 주문 선삭제
    	hurryOrders.remove(c);
    	
    	// 주문 취소 플래스 전환
    	c.canceled = true;
    	
    	// 해당 주문의 음료들을 다른 주문에 배치
    	for(int beverage : c.completeList) 
    		supply(beverage);
    	
    	// 주문이 취소 되었으므로 주문 개수 감소
    	orderCnt--;
    	
    	// 주문에 남은 음료 반환
        return c.cnt;
    }

    public int getStatus(int mID)
    {
    	int mIdx = idToIdx.get(mID);
    	Order c = orderDB[mIdx];
    	
    	if(c.complete)
    		return 0;

    	if(c.canceled)
    		return -1;
    	
        return c.cnt;
    }

    Solution.RESULT hurry()
    {
        Solution.RESULT res = new Solution.RESULT();

        res.cnt = Math.min(hurryOrders.size(), 5);

        Iterator<Order> iter = hurryOrders.iterator();
        int i = 0;
        while(iter.hasNext()) {
        	if(i == res.cnt)	break;
        	res.IDs[i] = iter.next().id;
        	i++;
        }
        
        return res;
    }
}
