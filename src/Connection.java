public class Connection {
	private static int base = 0;

	private int id;

	private String name;

	public Connection() {
		this.id = base++;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void doWork() {
		// does nothing
	}

	public String toString() {
		return ("Id: " + this.id);
	}

	public void finalize() {
		System.err.println("Connection " + toString());
	}

	public void destroy() {
		System.out.println("Connection " + toString() + " is destroyed");
	}
}