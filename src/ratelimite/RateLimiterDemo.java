package ratelimite;

import com.google.common.util.concurrent.RateLimiter;

public class RateLimiterDemo {
	
	
	static int seed = 0;
	
	public static void main(String[] args){
		testNoRateLimiter();
		testWithRateLimiter();
	}
	
	public static void testNoRateLimiter(){
		long start = System.currentTimeMillis();
		
		for(int i = 0; i < 10; i++){
			System.out.println("call exec..." + i);
		}
		
		System.out.println("cost time:" + (System.currentTimeMillis() - start));
		
	}
	
	public static void testWithRateLimiter(){
		
		long start = System.currentTimeMillis();
		
		RateLimiter limiter = RateLimiter.create(10.0);	
		
		Thread[] pools = new Thread[15];
		
		Runnable work = new TestWork(limiter);
		
		for(int i = 0; i < 15; i++){
			pools[i] = new Thread(work);
			pools[i].setName("t-" + i);
		}
		
		for(int i = 0; i < 15; i++){
			pools[i].start();
		}
		
		for(int i = 0; i < 15; i++){
			try {
				pools[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("cost time:" + (System.currentTimeMillis() - start));
		
		System.out.println("seed=" + seed);
		
	}
}
