package lucene;

public class Result {
	private int id;
	private int order;
	private String name;
	private String before;
	private String exact;
	private String after;
	private double score;
	
	public Result(int id, int order, String name, String before, String exact, String after, double score){
		this.id = id;
		this.order = order;
		this.name = name;
		this.before = before;
		this.exact = exact;
		this.after = after;
		this.score = score;
	}
	
	public String getBefore() {
		return before;
	}

	public String getExact() {
		return exact;
	}

	public String getAfter() {
		return after;
	}

	public int getId(){
		return id;
	}

	public int getOrder() {
		return order;
	}

	public String getName() {
		return name;
	}

	public double getScore() {
		return score;
	}

	
	
}
