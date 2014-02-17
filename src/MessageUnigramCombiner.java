import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class MessageUnigramCombiner {

	
	public static void main(String[] args) {
		try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			
			String line;
			String[] lineSplit, frequencies, terms;
			String currentTerm;
			long countB=0L, countC=0L;
			
			//read and throw back first two lines (word counts for unigrams and bigrams)
			//unigram counts
			line = br.readLine();
			bw.append(line+"\n");
			//bigram counts (throw away, we will get it again later)
			line = br.readLine();
			//bw.append(line+"\n");
			
			//read first terms-related line
			line = br.readLine();
			
			while (line != null) {
				lineSplit = line.split("\\t",2);
				currentTerm = lineSplit[0];
				
				if(lineSplit[1].charAt(0)!='~'){
					frequencies = lineSplit[1].split("[\\t+\\s+=]");
					countB = Long.parseLong(frequencies[1]);
					countC = Long.parseLong(frequencies[3]);
				}
				else{
					terms = lineSplit[1].split("[\\s+]");
					if(currentTerm.equals(terms[1])){
						bw.append(terms[1]+" "+terms[2]+",Bx="+String.valueOf(countB)+",Cx="+String.valueOf(countC)+"\n");
					}
					else{
						bw.append(terms[1]+" "+terms[2]+",By="+String.valueOf(countB)+",Cy="+String.valueOf(countC)+"\n");
					}
				}
				
				line = br.readLine();
			}
			
			bw.flush();
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
