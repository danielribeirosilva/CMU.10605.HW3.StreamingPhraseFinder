import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class Aggregate {
	
	public static void main(String[] args) {
		try {
        	
        	int type = Integer.parseInt(args[0]); // 1 for bigram and 0 for unigram
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			
			String line = br.readLine();
			
			//UNIGRAMS
			if(type == 0){
				
				//first line
				String[] lineSplit = line.split("\\s+");
				int year = Integer.parseInt(lineSplit[1]);
				long wordCount = Long.parseLong(lineSplit[2]);
				String lastWord = lineSplit[0];
				long backgroundCount = 0;
				long foregroundCount = 0;
				if(year <= 1989){
					backgroundCount += wordCount; 
				}
				else{
					foregroundCount += wordCount;
				}
				
				//remaining lines
				line = br.readLine();
				while (line != null) {
					
					lineSplit = line.split("\\s+");
					year = Integer.parseInt(lineSplit[1]);
					wordCount = Long.parseLong(lineSplit[2]);
					
					//if word changed, output line and update variables
					if(!lineSplit[0].equals(lastWord)){
						bw.append(lastWord+"\tBx="+String.valueOf(backgroundCount)+"\tCx="+String.valueOf(foregroundCount)+"\n");
						lastWord = lineSplit[0];
						backgroundCount = 0;
						foregroundCount = 0;
						if(year <= 1989){
							backgroundCount += wordCount; 
						}
						else{
							foregroundCount += wordCount;
						}
					}
					else{
						//keep updating counters
						if(year <= 1989){
							backgroundCount += wordCount; 
						}
						else{
							foregroundCount += wordCount;
						}
					}
					line = br.readLine();
				}
			
			}
			// BIGRAMS
			else{
				
				//first line
				String[] lineSplit = line.split("\\s+");
				int year = Integer.parseInt(lineSplit[2]);
				long wordCount = Long.parseLong(lineSplit[3]);
				String[] lastWords = {lineSplit[0],lineSplit[1]};
				long backgroundCount = 0;
				long foregroundCount = 0;
				if(year <= 1989){
					backgroundCount += wordCount; 
				}
				else{
					foregroundCount += wordCount;
				}
				
				//remaining lines
				line = br.readLine();
				while (line != null) {
					
					lineSplit = line.split("\\s+");
					year = Integer.parseInt(lineSplit[2]);
					wordCount = Long.parseLong(lineSplit[3]);
					
					//if word changed, output line and update variables
					if( !lineSplit[1].equals(lastWords[1]) || !lineSplit[0].equals(lastWords[0]) ){
						bw.append(lastWords[0]+" "+lastWords[1]+"\tBxy="+String.valueOf(backgroundCount)+"\tCxy="+String.valueOf(foregroundCount)+"\n");
						lastWords = new String[]{lineSplit[0],lineSplit[1]};
						backgroundCount = 0;
						foregroundCount = 0;
						if(year <= 1989){
							backgroundCount += wordCount; 
						}
						else{
							foregroundCount += wordCount;
						}
					}
					else{
						//keep updating counters
						if(year <= 1989){
							backgroundCount += wordCount; 
						}
						else{
							foregroundCount += wordCount;
						}
					}
					line = br.readLine();
				}
				
				
			}
			
			bw.flush();
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
