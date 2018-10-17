package demo;

import java.io.*; 
public class TestReadData 
{ 
public static void main(String[] args) throws Exception 
{ 
	// pass the path to the file as a parameter 
	FileReader fr = 
	new FileReader("D:\\ProjectOOP\\ProjectOOP\\test.txt"); 

	int x,tmp;
	while ((x=fr.read()) != -1) {
		char i = (char)x;
		if( i == '{') {
			System.out.print("[");
		}
		else if (i == '}') {
			System.out.println("]");
		}
		else if (i == ' ') {
			System.out.print("");
		}
		else if ((i >= '0' && i <= '9')) {
			if (tmp < )
			tmp = i;
			int num = Character.getNumericValue(i);
			System.out.print("int = " + num);
		}
		else if (i == ',') {
			
		}
		else {
			System.out.print((char) i);
		}
	} 
	 
} 
} 