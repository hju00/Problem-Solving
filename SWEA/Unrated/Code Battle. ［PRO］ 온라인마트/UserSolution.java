package online_mart;

import java.util.*;

class UserSolution
{
	HashMap<Integer, Product> pDB;
	ProductManager[][] pm;
	
	static class ProductManager	{
		TreeSet<Product> pl;
		int discountLog;
		int productCnt;
		
		public ProductManager() {
			super();
			this.pl = new TreeSet<>();
			this.discountLog = 0;
			this.productCnt = 0;
		}
	}
	
	static class toShow implements Comparable<toShow> {
		int id;
		int price;
		public toShow(int id, int price) {
			super();
			this.id = id;
			this.price = price;
		}
		@Override
		public int compareTo(toShow o) {
			if(this.price == o.price)
				return Integer.compare(this.id, o.id);
			return Integer.compare(this.price, o.price);
		}
		
	}
	
	static class Product implements Comparable<Product>{
		int id;
		int cate;
		int comp;
		int price;
		boolean closed = false;
		
		public Product(int id, int cate, int comp, int price) {
			super();
			this.id = id;
			this.cate = cate;
			this.comp = comp;
			this.price = price;
		}

		@Override
		public int compareTo(Product o) {
			if(this.price == o.price)
				return Integer.compare(this.id, o.id);
			return Integer.compare(this.price, o.price);
		}
	}

	
    public void init()
    {
    	pDB = new HashMap<>();
    	pm = new ProductManager[6][6];
    	
    	for(int i = 0; i < 6; i++)
    		for(int j = 0; j < 6; j++)
    			pm[i][j] = new ProductManager();
    	
    }

    public int sell(int mID, int mCategory, int mCompany, int mPrice)
    {
    	// 해당 상품의 카테고리와 제조사의 할인 금액만큼 더해야 함
    	int toPlus = pm[mCategory][mCompany].discountLog;
    	int newPrice = mPrice + toPlus;
    	
    	Product nProduct = new Product(mID, mCategory, mCompany, newPrice);
    	
    	// 상품DB 추가
    	pDB.put(mID, nProduct);
    	
    	// 상품 목록에 추가 및 상품 개수 증가
    	pm[mCategory][mCompany].pl.add(nProduct);
    	pm[mCategory][mCompany].productCnt++;
    	
    	return pm[mCategory][mCompany].productCnt;
    }

    public int closeSale(int mID)
    {
    	// 판매하고 있지 않은 상품인 경우
    	if(!pDB.containsKey(mID))
    		return -1;
    	
    	Product toRemove = pDB.get(mID);
    	
    	// 판매가 종료된 상품인 경우
    	if(toRemove.closed)
    		return -1;
    	
    	// 판매 종료 플래그 true
    	toRemove.closed = true;
    	
    	// 해당 카테고리와 제조사의 상품 갯수만 감소 (lazy delete)
    	ProductManager cPM = pm[toRemove.cate][toRemove.comp];
    	cPM.productCnt--;
    	
    	return toRemove.price - cPM.discountLog;
    }

    public int discount(int mCategory, int mCompany, int mAmount)
    {
    	ProductManager cPM = pm[mCategory][mCompany];
    	cPM.discountLog += mAmount;
    	
    	// Iterator 를 통해 상품 목록 순회
    	Iterator<Product> iter = cPM.pl.iterator();
    	while(iter.hasNext())	{
    		Product p = iter.next();
    		
    		// 할인 이후 가격이 0 이하가 되는 상품 제거
    		if(p.price - cPM.discountLog > 0)
    			break;
    		
    		// 현재 순회한 상품이 판매 종료되는 것을 pDB에 반영
    		if(!pDB.get(p.id).closed)	{
    			pDB.get(p.id).closed = true;
    			cPM.productCnt--;
    		}
    		
    		iter.remove();
    	}
    	
    	return cPM.productCnt;
    }

    Solution.RESULT show(int mHow, int mCode)
    {
        Solution.RESULT res = new Solution.RESULT();

        int startCate = 1, endCate = 5;
        int startComp = 1, endComp = 5;
        
        if(mHow == 1)	{
        	startCate = mCode;
        	endCate = mCode;
        }
        else if(mHow == 2)	{
        	startComp = mCode;
        	endComp = mCode;
        }
        
        // 각 카테고리 / 제조사 별로 5개씩 후보에 추가해 정렬 후 최종 5개만 추천
        ArrayList<toShow> candidate = new ArrayList<>();
        
        for(int i = startCate; i <= endCate; i++)	{
        	for(int j = startComp; j <= endComp; j++)	{
        		ProductManager cPM = pm[i][j];
        		int cnt = 0;
        		
        		for(Product p : cPM.pl)	{
        			if(cnt == 5)	break;
        			
        			Product realP = pDB.get(p.id);
        			if(!realP.closed)	{
        				int realPrice = realP.price - cPM.discountLog;
        				candidate.add(new toShow(realP.id, realPrice));
        				cnt++;
        			}
        		}
        	}
        }
        
        // 최악일 때 125개 정렬이므로 부담이 크진 않음
        Collections.sort(candidate);
        
        // 최대 5개만 추천
        res.cnt = Math.min(candidate.size(), 5);
        for(int i = 0; i < res.cnt; i++)
        	res.IDs[i] = candidate.get(i).id;
        
        return res;
    }
}
