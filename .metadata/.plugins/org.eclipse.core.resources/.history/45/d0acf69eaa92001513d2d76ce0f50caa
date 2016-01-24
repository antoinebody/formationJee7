package executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskProg1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//biblio jasypt
		
		Runnable runnable = new SomeRunnable();
        // create a fixed thread pool
		ExecutorService pool = Executors.newFixedThreadPool(3);
        
		// run the task 5 times using the pool
        for (int i = 0; i < 5; i++) {
            pool.execute(runnable);
        }
        pool.shutdown();
	}

}
