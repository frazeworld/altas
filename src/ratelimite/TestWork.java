package ratelimite;

import com.google.common.util.concurrent.RateLimiter;

public class TestWork implements Runnable{

	private RateLimiter limiter = null;
	
	public TestWork(RateLimiter limiter){
		this.limiter = limiter;
	}
	
	@Override
	public void run() {
		
		System.out.println(Thread.currentThread().getName());
		limiter.acquire();
		
	}

}
