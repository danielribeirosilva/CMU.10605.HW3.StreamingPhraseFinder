import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;


public class PhraseGenerator {
	
	public class biGramData implements Comparable<biGramData>{
		String x; //first word
		String y; //second word
		double I; //Informativeness
		double P; //Phraseness
		Double score; //Score
		
		public biGramData(String x, String y, double I, double P, double score){
			this.x = x;
			this.y = y;
			this.I = I;
			this.P = P;
			this.score = score;
		}

		@Override
		public int compareTo(biGramData other) {
			return this.score.compareTo(other.score);
		}
		
		public boolean equals(biGramData other) {
			return (this.x.equals(other.x)	&&
					this.y.equals(other.x)	&&
					this.I	== other.I		&&
					this.P	== other.P		&&
					this.score.equals(other.score)
					);
		}
		
	}
	
	public static double sigmoid(double x){
		return 1D / (1D + Math.exp(-x)) ;
	}
	
	public static double score(double I, double P){
		return I + P;
		//return sigmoid(I) + sigmoid(P);
		//return sigmoid(I) * sigmoid(P);
	}

	public static void main(String[] args) {
		
		PhraseGenerator pg = new PhraseGenerator();
		int K = 20;
		
		try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line;
            String a,b;
			String[] lineSplit;
			//long Bx=0L,By=0L;
			long Cx=0L,Cy=0L,Bxy=0L,Cxy=0L;
			//long BxTotalWordCount;
			long CxTotalWordCount,BxyTotalWordCount,CxyTotalWordCount; 
			double p ,q; 
			double phraseness, informativeness, score;
            PriorityQueue<biGramData> pq = new PriorityQueue<biGramData>(); 
			
            //read first line with unigram word counts
            line = br.readLine();
            lineSplit = line.split("[=,]");
            //BxTotalWordCount = Long.parseLong(lineSplit[1]);
            CxTotalWordCount = Long.parseLong(lineSplit[3]);
            
            //read second line with bigram word counts
            line = br.readLine();
            lineSplit = line.split("[=,]");
            BxyTotalWordCount = Long.parseLong(lineSplit[1]);
            CxyTotalWordCount = Long.parseLong(lineSplit[3]);
            
            //read first line of block (1/3) Bx/Cx
			line = br.readLine();
			
			while (line != null) {
				
				lineSplit = line.split("[\\s,=]");
				a = lineSplit[0];
				b = lineSplit[1];
				//Bx = Long.parseLong(lineSplit[3]);
				Cx = Long.parseLong(lineSplit[5]);
				
				//read second line of block (2/3) By/Cy
				line = br.readLine();
				lineSplit = line.split("[\\s,=]");
				//By = Long.parseLong(lineSplit[3]);
				Cy = Long.parseLong(lineSplit[5]);
				
				//read third line of block (3/3) Bxy/Cxy
				line = br.readLine();
				lineSplit = line.split("[\\s,=]");
				Bxy = Long.parseLong(lineSplit[3]);
				Cxy = Long.parseLong(lineSplit[5]);
				
				
				//COMPUTE SCORES
				
				//Phraseness
				p = ( (double)Cxy + 1D ) / (double)CxyTotalWordCount;
				q = ( ( (double)Cx + 1D ) / (double)CxTotalWordCount ) * ( ( (double)Cy + 1D ) / (double)CxTotalWordCount );
				phraseness = p * Math.log( p / q );
				
				//Informativeness
				p = ( (double)Cxy + 1D ) / (double)CxyTotalWordCount;
				q = ( (double)Bxy + 1D ) / (double)BxyTotalWordCount;
				informativeness = p * Math.log( p / q );
				
				//final score
				score = score(informativeness, phraseness);
				biGramData bd = pg.new biGramData(a,b,informativeness,phraseness,score);
				if(pq.isEmpty() || pq.peek().score < score){
					pq.add(bd);
					//only keep top K
					if(pq.size() > K){
						pq.poll();
					}
				}
				
				//read first line of next block
				line = br.readLine();
			}
			
			br.close();
			
			String finalResult = "";
			String currentResult;
			
			while(!pq.isEmpty()){
				biGramData bgd = pq.poll();
				currentResult = bgd.x+" "+bgd.y+"\t"+String.valueOf(bgd.score)+"\t"+String.valueOf(bgd.P)+"\t"+String.valueOf(bgd.I)+"\n";
				finalResult = currentResult + finalResult;
			}
			
			System.out.print(finalResult);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
