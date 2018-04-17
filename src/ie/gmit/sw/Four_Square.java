package ie.gmit.sw;

import java.util.*;

//Author - Arron Healy - G00346883

public class Four_Square {
	
	final int size = 5;
	
	char[] userKey1, userKey2;
	
	char[] alpha1 = "ABCDEFGHIKLMNOPQRSTUVWXYZ".toCharArray();
	
	char[][] plainSquare1 = new char[size][size];
	
	char[][] cipherSquare1 = new char[size][size];
	
	char[][] cipherSquare2 = new char[size][size];
	
	String cKey1, cKey2;
	
	ArrayList <Character> alphabet = new ArrayList<Character>();
	
	ArrayList <Character> key1 = new ArrayList<Character>();
	ArrayList <Character> key2 = new ArrayList<Character>();
	
	
	//constructor run time O(n) per loop and depending on methods called during construction
	//set square method O(n squared) dependent on character needed as breaks early if found at start of matrix
	//constructor to build with keywords
	
	public Four_Square(String uKey1, String uKey2)
	{
		//strings to build cipher key from method call
		cKey1 = dropDupAndAppend(uKey1);
		cKey2 = dropDupAndAppend(uKey2);
		
		//populate cipher key array from string
		this.userKey1 = cKey1.trim().toCharArray();
		this.userKey2 = cKey2.trim().toCharArray();
		
		//populate array lists with alphabet and cipher key arrays
		for(int i = 0; i < 25; i++)
		{
			alphabet.add(alpha1[i]);
			key1.add(this.userKey1[i]);
			key2.add(this.userKey2[i]);
		}
		
		//call set square methods
		setSquares();
		
	}//four square enter keyword constructor
	
	
	//constructor run time o(n) as loops through defined array to populate array list
	//set square method O(n squared) dependent on character needed as breaks early if found at start of matrix
	//constructor to build with generated keywords
		
	public Four_Square()
	{
		
		//populate array lists from defined array
		for(char c : alpha1) 
		{
		    alphabet.add(c);
		    key1.add(c);
		    key2.add(c);
		}//for each
		
		//shuffle array lists for cipher keys
		Collections.shuffle(key1);
		Collections.shuffle(key2);
		
		//call set square methods
		setSquares();
		
	}//four square generate key constructor
	
	//set square method O(n squared) to populate 2D arrays
	//used to build your four square
	
	public void setSquares()
	{
		int count = -1;
		
		//populate 2D arrays from array list characters
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				count++;
				plainSquare1[i][j] = alphabet.get(count);
				cipherSquare1[i][j] = key1.get(count);
				cipherSquare2[i][j] = key2.get(count);
			}//inner for
			
		}//outer for
		
	}//setSquare
	
	
	//method O(n) three loops to create cipher keys  
	public String dropDupAndAppend(String key)
	{
		
		Set <Character> set = new LinkedHashSet<Character>();
		
		//add keyword to set
			for(char c : key.toUpperCase().toCharArray())
			{
				if(c == 'J')
				{
					c = 'I';
				}//if
				
				set.add(c);
				
			}//for each
			
			//once keyword in set loop over alphabet array and add unique characters
			for(char c : alpha1)
			{
				if(set.contains(c))
				{
					continue;
				}
				else
				{
					set.add(c);
				}//if
				
			}//for each
			
			StringBuilder s = new StringBuilder();
			
			//loop over set and add to string builder
			for(char c : set)
			{
				s.append(c);
			}//for each
		
		
		return s.toString();
		
	}//dropDupAndAppend
	
	
	//print method o(n squared) although big increase
	//used to show your built four square table
	
	public void print()
	{
		System.out.println();
		
		//print plain alphabet 2D array
		for(int i = 0; i < 5; i++) 
		{
			
		    for(int j = 0; j < plainSquare1.length; ++j)
		    {
		    	 System.out.print(" " + plainSquare1[i][j]);
		    	 
		    	 if(j == 4)
		    	 {
		    		 System.out.print(" |");
		    	 }//if
		    }//for
		          
		//print cipher key 2D array
		    for(int j = 0; j < cipherSquare1.length; ++j)
		    {
		    	System.out.print(" " + cipherSquare1[i][j]);
		    }//for
		    
		    System.out.println();
		    
		    if(i == 4)
		    {
		    	System.out.print(" - - - - - | - - - - -");
		    }//if
		
		}//for
		
		System.out.println();
		
		//print second cipher key 2D array
		for(int i = 0; i < 5; i++) 
		{
		    
		    for(int j = 0; j < cipherSquare2.length; ++j)
		    {
		    	System.out.print(" " + cipherSquare2[i][j]);
		    	
		    	if(j == 4)
		    	{
		    		System.out.print(" |");
		    	}//if
		    }//for
		    
		    //print alphabet 2D array again
		    for(int j = 0; j < plainSquare1.length; ++j)
		    {
		    	System.out.print(" " + plainSquare1[i][j]);
		    }//for
		    
		    System.out.println();
		
		}//for

	}//print
	
	
	//encrypt method O(n / 2) as increased by 2 for bigram dependent on length of text entered
	
	public String encrypt(String plainText)
	{
		StringBuilder s = new StringBuilder();
		
		//check text is even length if not append X to end
		if(plainText.length() % 2 != 0)
		{
			plainText += "X";
		}//if
		
		
		//loop over plain text
		for(int i = 0; i < plainText.length(); i+=2)
		{ 
			//call encrypt methods and append to string builder			
			s.append(getEncryptedChar1(plainText.charAt(i), plainText.charAt(i + 1)));
			s.append(getEncryptedChar2(plainText.charAt(i), plainText.charAt(i + 1)));
			
		}//for
		
		return s.toString();
		
	}//encrypt
	
	
	//decrypt method O(n / 2) as increased by 2 for bigram dependent on length of text entered
		
	public String decrypt(String cipherText)
	{
		StringBuilder s = new StringBuilder();
		
		for(int i = 0; i < cipherText.length(); i+=2)
		{
			s.append(getDecryptedChar1(cipherText.charAt(i), cipherText.charAt(i + 1)));
			s.append(getDecryptedChar2(cipherText.charAt(i), cipherText.charAt(i + 1)));
		}//for
		
		return s.toString();
		
	}//Decrypt
	
	
	//getEncrypted char o(n) iterates through array list length 
	
	private char getEncryptedChar1(char plain1, char plain2)
	{
		int plainIndex1 = 0, plainIndex2 = 0;
		
		for(char c: alphabet)
		{
			if(c == plain1)
			{
				plainIndex1 = alphabet.indexOf(c) / 5;
			}//if
			
			if(c == plain2)
			{
				plainIndex2 = alphabet.indexOf(c) % 5;
			}//if
			
		}//for
		
		return cipherSquare1[plainIndex1][plainIndex2];
		
	}//getEncrypt1
	
	//getEncrypted char 2 same as getEncrypted char1 O(n) dependent on position in arraylist
	
	private char getEncryptedChar2(char plain1, char plain2)
	{
		int plainIndex1 = 0, plainIndex2 = 0;
		
		for(char c: alphabet)
		{
			if(c == plain1)
			{
				plainIndex1 = alphabet.indexOf(c) % 5;
			}//if
			
			if(c == plain2)
			{
				plainIndex2 = alphabet.indexOf(c) / 5;
			}//if
			
			//counter++;
			
		}//for
		
		return cipherSquare2[plainIndex2][plainIndex1];
		
	}//getEncrypt2
	
	
	//getDecrypted char 1 O(n) dependent on position of char in list
	
	private char getDecryptedChar1(char cipher1, char cipher2)
	{
		int plainIndexRow = 0, plainIndexCol = 0;
		
		for(char c: key1)
		{
			if(cipher1 == c)
			{
				plainIndexRow = key1.indexOf(c) / 5;
			}//if
			
		}//for
		
		for(char c: key2)
		{
			if(cipher2 == c)
			{
				plainIndexCol = key2.indexOf(c) % 5;
			}//if
		}//for
		
		
		return plainSquare1[plainIndexRow][plainIndexCol];
		
	}//getDecrypt1
	
	
	//getDecrypted char 2 O(n) dependent on list length
		
	private char getDecryptedChar2(char cipher1, char cipher2)
	{
		int plainIndexRow = 0, plainIndexCol = 0;
		
		for(char c: key1)
		{
			if(cipher1 == c)
			{
				plainIndexCol = key1.indexOf(c) % 5;
			}//if
			
		}//for
		
		for(char c: key2)
		{
			if(cipher2 == c)
			{
				plainIndexRow = key2.indexOf(c) / 5;
			}//if
		}//for
		
		
		return plainSquare1[plainIndexRow][plainIndexCol];
		
	}//getDecrypt2


}//Four_Square
