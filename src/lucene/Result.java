package lucene;

public class Result {
	private int id;
	private int order;
	private String name;
	private double score;
	
	public Result(int id, int order, String name, double score){
		this.id = id;
		this.order = order;
		this.name = name;
		this.score = score;
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
