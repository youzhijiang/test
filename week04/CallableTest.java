package com.youzhi;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class CallableTest {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService service = Executors.newFixedThreadPool(5);
		Callable<Integer> callable = new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				int a = 0;
				for(int i =0; i < 10; i++){
					a += i;
				}
				return a;
			}
			
			
		};
		FutureTask<Integer> futureTask = new FutureTask<>(callable);
		service.submit(futureTask);
		int result = futureTask.get();
		System.out.println("获取线程的执行结果："+result);
	}

}
