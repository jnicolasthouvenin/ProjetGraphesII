package vendors;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public interface Debug {
	default void debug(String str) throws IOException {
		File outputFile = new File("/home/jules/Documents/GraphesII/Projet/debug.txt");
		appendToFile(outputFile,str+"\n");
	}
	
	default void clearDebug() throws IOException {
		File outputFile = new File("/home/jules/Documents/GraphesII/Projet/debug.txt");
		FileWriter fileWriter = new FileWriter(outputFile);
		fileWriter.write("");
		fileWriter.close();
	}
	
	/** 
	 * Void appending the String to the file and breaking the line in a standard way.
	 *  
	 * @param       file the file.
	 * @param		str the content.
	 * @throws IOException 
	 */
	default void toDebug(File file, String str) throws IOException {
		File outputFile = new File(file.getAbsolutePath()+"\\debug.txt");
		appendToFile(outputFile,str+"\n");
	}
	
	/** 
	 * Void appending the String to the file.
	 *  
	 * @param       file the file.
	 * @param		str the String
	 * @throws IOException
	 */
	default void appendToFile(File file, String str) throws IOException {
		FileWriter fileWriter = new FileWriter(file,true);
		fileWriter.append(str);
		fileWriter.close();
	}
}
