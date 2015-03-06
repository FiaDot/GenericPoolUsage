import org.apache.commons.pool2.impl.GenericObjectPool;

// AS-IS
public class ConsumerCheck extends Thread {
	private String name;
	private GenericObjectPool<Connection> pool;

	public ConsumerCheck(GenericObjectPool<Connection> pool, String name) {
		this.pool = pool;
		this.name = name;
	}

	public void run() {
		while (true) {
			try {
				sleep(1000);
				action_set();				
				
			} catch (Exception e) {
				e.printStackTrace();			
			}
			
			System.out.println("Run=" + name);
		}
	}

	private void returnInvalidObject(Connection conn) {
		try {
			pool.invalidateObject(conn);
		} catch (Exception e) {		
			//e.printStackTrace();
			System.out.println("Set= " + name + " invalidateObject exception");
		}
	}
	
	private void action_set()  {
		Connection conn = null;
		
		try {
			conn = pool.borrowObject();			
			System.out.println("Set=" + name + " " + conn.toString());
			throw new Exception("fault");
		} catch (Exception e) {
			System.out.println("Set=" + name + " invalid return");
			returnInvalidObject(conn);
			//e.printStackTrace();			
		} 
		
		if ( null != conn ) 
			pool.returnObject(conn);
		System.out.println("Set=" + name + " return");		
	}
	
	
	private void action_timeout() {
		Connection conn = null;
		
		try {
			conn = pool.borrowObject();
			System.out.println("Timeout=" + name + " " + conn.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.returnObject(conn);
			System.out.println("Timeout=" + name + " return");
		}
	}
}