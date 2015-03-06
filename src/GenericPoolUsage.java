import java.util.ArrayList;
import java.util.List;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;

public class GenericPoolUsage {

	public static void main(String args[]) throws Exception {
		PooledObjectFactory<Connection> factory = new ConnectionPoolFactory();
		GenericObjectPool<Connection> pool = new GenericObjectPool<Connection>(
				factory);

		// GenericPoolConfig conf;
		// pool.setConfig(conf);

		pool.setMaxTotal(3);
		// pool.setBlockWhenExhausted(true);
		// pool.setMaxWaitMillis(2000);

		/*
		List<ConsumerDeadlock> threadList = new ArrayList<ConsumerDeadlock>();

		for (int n = 0; n < 3; n++) {
			String name = "c" + (n + 1);
			ConsumerDeadlock t = new ConsumerDeadlock(pool, name);
			threadList.add(t);
			t.start();
		}
		*/
		
		
		List<ConsumerCheck> threadList = new ArrayList<ConsumerCheck>();

		for (int n = 0; n < 3; n++) {
			String name = "c" + (n + 1);
			ConsumerCheck t = new ConsumerCheck(pool, name);
			threadList.add(t);
			t.start();
		}
		
		while (true) {
			Thread.currentThread().sleep(3000);
			System.out.println("[Pool] getCreatedCount="
					+ pool.getCreatedCount());
			System.out.println("[Pool] getNumActive=" + pool.getNumActive());
			System.out.println("[Pool] getNumIdle=" + pool.getNumIdle());
			System.out.println("[Pool] getNumWaiters=" + pool.getNumWaiters());
		}
	}
}