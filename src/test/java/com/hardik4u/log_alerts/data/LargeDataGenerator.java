package com.hardik4u.log_alerts.data;

import java.io.PrintWriter;
import java.util.Random;

public class LargeDataGenerator {

	public static void main(String[] args) throws Exception {
        PrintWriter pw = new PrintWriter("e:/large_logfile.txt");
        Random rnd = new Random();
        for (int i=0; i<2*1024*1024; i++){
        	Integer rndGen1 = rnd.nextInt();
        	Integer rndGen2 = rnd.nextInt();
        	Integer rndGen3 = rnd.nextInt();
        	
        	String testData = "{\"id\":\"scsmbst"+rndGen1+"\", \"state\":\"STARTED\", \"type\":\"APPLICATION_LOG\", \"host\":\"12345\", \"timestamp\":1491377495212}\r\n"
        			+ "{\"id\":\"scsmbst"+rndGen2+"\", \"state\":\"STARTED\", \"timestamp\":1491377495213}\r\n"
        			+ "{\"id\":\"scsmbst"+rndGen3+"\", \"state\":\"FINISHED\", \"timestamp\":1491377495218}\r\n"
        			+ "{\"id\":\"scsmbst"+rndGen1+"\", \"state\":\"FINISHED\", \"type\":\"APPLICATION_LOG\", \"host\":\"12345\", \"timestamp\":1491377495217}\r\n"
        			+ "{\"id\":\"scsmbst"+rndGen3+"\", \"state\":\"STARTED\", \"timestamp\":1491377495210}\r\n"
        			+ "{\"id\":\"scsmbst"+rndGen2+"\", \"state\":\"FINISHED\", \"timestamp\":1491377495216}\r\n";
            pw.write(testData);
        }
        pw.flush();
        pw.close();
    }

}
