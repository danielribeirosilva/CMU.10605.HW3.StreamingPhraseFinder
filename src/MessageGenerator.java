import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class MessageGenerator {

	public static void main(String[] args) {
		
		try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			
			String line = br.readLine();
			String[] lineSplit;
			
			while (line != null) {
				if(line.charAt(0)=='!'){
					bw.append(line+"\n");
				}
				else{
					lineSplit = line.split("[\\s+,]",3);
					bw.append(lineSplit[0]+"\t~ "+lineSplit[0]+" "+lineSplit[1]+"\n");
					bw.append(lineSplit[1]+"\t~ "+lineSplit[0]+" "+lineSplit[1]+"\n");
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
