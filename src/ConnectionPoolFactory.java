import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;


public class ConnectionPoolFactory implements PooledObjectFactory<Connection> {


	public void destroyObject(PooledObject<Connection> arg0) throws Exception {		
		Connection conn = arg0.getObject();
		conn.destroy();
	}

	public PooledObject<Connection> makeObject() throws Exception {		
		Connection conn = new Connection();		
		return new DefaultPooledObject<Connection>(conn);
	}


	public boolean validateObject(PooledObject<Connection> arg0) {
		return true;
	}
	
	public void activateObject(PooledObject<Connection> arg0) throws Exception {		
		
	}
	
	public void passivateObject(PooledObject<Connection> arg0) throws Exception {
		
	}


}

