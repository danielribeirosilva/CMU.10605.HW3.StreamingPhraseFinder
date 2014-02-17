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
			
			String line = br.readLine();
			String[] lineSplit, frequencies, terms;
			String currentTerm;
			long countB=0, countC=0;
			
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
