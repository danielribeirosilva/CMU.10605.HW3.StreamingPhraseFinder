import java.util.PriorityQueue;


public class tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String line = "!BxWordCount=21263254435,CxWordCount=9557153125";
		String[] lineSplit = line.split("[,=]");
		for(String s : lineSplit)
			System.out.println(s);
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		
		pq.add(1);
		pq.add(2);
		pq.add(3);
		
		int lol = pq.poll();
		System.out.println(lol);
	}

}
