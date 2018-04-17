package ie.gmit.sw;

import java.util.*;
import java.net.*;
import java.io.*;

//Author - Arron Healy - G00346883

@SuppressWarnings("unused")
public class Menu {
	
	  File cipherFile;
		
	  Scanner console = new Scanner(System.in);
	
	  boolean keepRunning = true;
	
	  String plainText;
	
	  String key1;

	  String key2;

	  String uKey1;

	  String uKey2;
	
	  int fileNum;

	  int fileKey;
	 
	  char confirm;
	 
	  int urlKey, urlKey2;
	 
	  int urlKeyChoice;
	 
	  String urlChoice;
	 
	  char confirmUrl, confirmPrint;
	
	  String select = "Please select a number from options listed above: ";
	
	public Menu()
	{
		show();
	}
	
	public void show()
	{
		//boolean keepRunning = true;
		
		while(keepRunning)
		{
			System.out.println("\n\n1 - Enter Encrypt Four-Square");
			System.out.println("2 - Generate and Print Four-Square");
			System.out.println("3 - Exit");
			System.out.print(select);
			
			String option = console.next();
			process(option);
		}
	}
	
	//menu to call methods
	private void process(String option)
	{
		try
		{
			int selection = Integer.parseInt(option);
			
			switch(selection)
			{
			case 1:
				encryptMenu();
				break;
			case 2:
				printMenu();
				break;
			case 3:
				System.exit(0);
				break;
				default:
					keepRunning = false;
					System.exit(0);
			}
			
		}
		catch(Exception e)
		{
			System.out.println("\nNot available! Try again.");
		}
	}//process option menu
	
	//print four square menu
	public void printMenu()
	{
		int choice = keyOption();
		
		if(choice == 1)
		{
			Four_Square out = new Four_Square();
			
			long print = System.currentTimeMillis();
			
			out.print();
			
			System.out.println("\nPrint Running Time (ms): " + (System.currentTimeMillis() - print));
		}
		else if(choice == 2)
		{
			System.out.println("\nPlease Enter 2 Keywords: ");
			String userKey1 = getKey();
			
			String userKey2 = getKey();
			
			Four_Square userPrint = new Four_Square(userKey1, userKey2);
			
			long uPrint = System.currentTimeMillis();
			
			userPrint.print();
			
			System.out.println("\nPrint Running Time (ms): " + (System.currentTimeMillis() - uPrint));
		}
		else
		{
			keepRunning = false;
		}
		
	}//printMenu
	
	
	public void encryptMenu() throws FileNotFoundException
	{
		int choice, keyChoice = 0;
		
		System.out.println("\n1 - Enter Plaintext for Encryption");
		System.out.println("2 - Enter File for Encryption");
		System.out.println("3 - Enter URL for Encryption");
		System.out.print("\nPlease enter Number from encrypt options: ");
		
		choice = console.nextInt();

		
//start plain text encryption
		switch(choice)
		{
		case 1:
			keyChoice = keyOption();
			
			if(keyChoice == 1)
			{
				String cipherText;
				
				System.out.print("\n\nPlease enter Plain Text to be encrypted: ");
				console.nextLine();
				plainText = console.nextLine().toUpperCase().replaceAll(" ", "");
				
				System.out.println("\nYour Plain Text before Encryption: " + plainText);
				
				long encryptPlain = System.currentTimeMillis();
				
				//generate four square
				Four_Square genKey = new Four_Square();
				
				//encrypt plaintext
				cipherText = genKey.encrypt(plainText);
				
				System.out.println("\nYour Text after Encryption: " + cipherText);
				
				System.out.println("\nEncrypt Text Running Time (ms): " + (System.currentTimeMillis() - encryptPlain));
				
				System.out.print("\nWould you like to print decrypted version of Text? Enter 'Y' or 'N': ");
				confirm = confirm();
				
				switch(confirm)
				{
				case 'y':
				case 'Y':
					long decryptPlain1 = System.currentTimeMillis();
					
					//option to decrypt plain text 
					System.out.println("\nYour Cipher Text Decrypted: " + genKey.decrypt(cipherText));
					System.out.println("\nDecrypt Text Running Time (ms): " + (System.currentTimeMillis() - decryptPlain1));
					break;
					
				case 'n':
				case 'N':
					break;
					
					default:
						break;
				}//switch
				
				System.out.print("\n\nWould you like to print the created Four-Square? Enter 'Y' or 'N': ");
				confirmPrint =  confirm();
				
				switch(confirmPrint)
				{
				case 'y':
				case 'Y':
					//confirm print four square and count run time
					long print1 = System.currentTimeMillis();
					genKey.print();
					System.out.println("\nYour Created Four-Square!");
					
					System.out.println("\nPrint Running Time (ms): " + (System.currentTimeMillis() - print1));
					break;
				case 'n':
				case 'N':
					System.out.println("\nYour Four-Square Cipher Remains a Secret!");
					break;
					default:
						break;
				}

			}
			else if(keyChoice == 2)
			{
				System.out.println("\nPlease Enter 2 Keywords: ");
				key1 = getKey();
				
				key2 = getKey();
				
				String cipher2;
				
				System.out.print("\n\nPlease enter Plain Text to be encrypted: ");
				console.nextLine();
				plainText = console.nextLine().toUpperCase().replaceAll(" ", "");
				
				System.out.println("\nYour Plain Text before Encryption: " + plainText);
				
				long startPlain = System.currentTimeMillis();
				
				//enter keywords for plain text
				Four_Square setKey = new Four_Square(key1, key2);
				
				cipher2 = setKey.encrypt(plainText);
				
				System.out.println("\nYour Text After Encryption: " + cipher2);
				
				System.out.println("\nRunning Time (ms): " + (System.currentTimeMillis() - startPlain));
				
				System.out.print("\nWould you like to print decrypted version of Text? Enter 'Y' or 'N': ");
				confirm = confirm();
				
				switch(confirm)
				{
				case 'y':
				case 'Y':
					long decryptPlain = System.currentTimeMillis();
					System.out.println("\nYour Plain Text Decrypted: " + setKey.decrypt(cipher2));
					System.out.println("\nRunning Time (ms): " + (System.currentTimeMillis() - decryptPlain));
					break;
				case 'n':
				case 'N':
					break;
					default:
						break;
				}//switch
				
				System.out.print("\n\nWould you like to print the created Four-Square? Enter 'Y' or 'N': ");
				confirmPrint =  confirm();
				
				switch(confirmPrint)
				{
				case 'y':
				case 'Y':
					long print2 = System.currentTimeMillis();
					setKey.print();
					System.out.println("\nYour Created Four-Square!");
					
					System.out.println("\nPrint Running Time (ms): " + (System.currentTimeMillis() - print2));
					break;
					
				case 'n':
				case 'N':
					System.out.println("\nYour Four-Square Cipher Remains a Secret!");
					break;
					
					default:
						break;
				}//switch
				
			}
			else
			{
				keepRunning = false;
			}//if
			
			
			//end key choice
			
			break;
//end plain text encryption

//file encryption
		case 2:
			
			System.out.println("\n1 - Encrypt Poblacht Na HEireann");
			System.out.println("2 - Encrypt DeBello Gallico");
			System.out.println("3 - Encrypt War and Peace");
			System.out.print("Please choose option for Key Generation: ");
			fileNum = console.nextInt();
			
			if(fileNum == 1)
			{
				//file to be encrypted
				cipherFile = new File("poblachtNaHEireann.txt");
				
				InputStream file1 = new FileInputStream(cipherFile);
				
				fileKey = keyOption();
				
				if(fileKey == 1)
				{
					System.out.println();
					//generate four square
					Four_Square fileParse1 = new Four_Square();
					
					Parser p = new Parser(file1, fileParse1);
				}
				else
				{
					System.out.println("\nPlease Enter 2 Keywords: ");
					uKey1 = getKey();
					
					uKey2 = getKey();
					
					Four_Square fileParse2 = new Four_Square(uKey1, uKey2);
					
					Parser user1 = new Parser(file1, fileParse2);
				}
			}
			else if(fileNum == 2)
			{
				//file to be encrypted
				cipherFile = new File("DeBelloGallico.txt");
				
				InputStream file2 = new FileInputStream(cipherFile);
				
				fileKey = keyOption();
				
				if(fileKey == 1)
				{
					//generate four square
					Four_Square fileParse3 = new Four_Square();
					
					//parse file method
					Parser d = new Parser(file2, fileParse3);
				}
				else
				{
					System.out.println("\nPlease Enter 2 Keywords: ");
					uKey1 = getKey();
					
					uKey2 = getKey();
					
					//key word four square 
					Four_Square fileParse4 = new Four_Square(uKey1, uKey2);
					
					Parser user = new Parser(file2, fileParse4);
				}//if
			}
			else if(fileNum == 3)
			{
				//file to be encrypted
				cipherFile = new File("WarAndPeace-LeoTolstoy.txt");
				
				InputStream file3 = new FileInputStream(cipherFile);
				
				fileKey = keyOption();
				
				if(fileKey == 1)
				{
					//generate four square
					Four_Square fileParser5 = new Four_Square();
					
					//parse file
					Parser w = new Parser(file3, fileParser5);
				}
				else
				{
					uKey1 = getKey();
					
					uKey2 = getKey();
					
					//enter keys for four square
					Four_Square fileParser6 = new Four_Square(uKey1, uKey2);
					
					//parse file based on four square
					Parser p = new Parser(file3, fileParser6);
				}//else
			}
			else
			{
				keepRunning = false;
			}//if
			
			break;
			
//end file encryption
			
			
		case 3:
			
			System.out.println("\n1 - Enter URL for Encryption");
			System.out.println("2 - Encrypt Google URL");
			System.out.println("3 - Encrypt Facebook URL");
			System.out.println("4 - Encrypt GMIT URL");
			System.out.print("Please choose option for Key Generation: ");
			urlKey = console.nextInt();
			
			
			
			if(urlKey == 1)
			{
				urlKey2 = keyOption();
				
				String enterUrl;
				
				//user enter url
				System.out.print("\nEnter Url for encryption - Note must be correct Url! (https://www.example.com/): ");
				enterUrl = console.next();
				
				if(urlKey2 == 1)
				{
					System.out.println();
					//generate four square
					Four_Square fileParse1 = new Four_Square();
					
					//parse user url
					Parser genUrl = new Parser(enterUrl, fileParse1);
				}
				else
				{
					System.out.println("\nPlease Enter 2 Keywords: ");
					uKey1 = getKey();
					
					uKey2 = getKey();
					
					//user key word four square & url
					Four_Square fileParse2 = new Four_Square(uKey1, uKey2);
					
					Parser keyUrl = new Parser(enterUrl, fileParse2);
				}
				
			}
			else if(urlKey == 2)
			{
				//url for encryption
				urlChoice = "https://www.google.ie/?gws_rd=cr&dcr=0&ei=IZiWWrLCLYy4kwWGm5XoAQ";
				
				
				fileKey = keyOption();
				
				if(fileKey == 1)
				{
					System.out.println();
					//generate four square for url parsing
					Four_Square urlParse1 = new Four_Square();
					
					Parser google = new Parser(urlChoice, urlParse1);
				}
				else
				{
					System.out.println("\nPlease Enter 2 Keywords: ");
					uKey1 = getKey();
					
					uKey2 = getKey();
					
					//user key words for url encryption
					Four_Square urlParse2 = new Four_Square(uKey1, uKey2);
					
					Parser user1 = new Parser(urlChoice, urlParse2);
				}
			}
			else if(urlKey == 3)
			{
				//url for encryption
				urlChoice = "https://www.Facebook.com/";
				
				fileKey = keyOption();
				
				if(fileKey == 1)
				{
					System.out.println();
					//generate four square and url for encryption
					Four_Square urlParse3 = new Four_Square();
					
					Parser fb = new Parser(urlChoice, urlParse3);
				}
				else
				{
					System.out.println("\nPlease Enter 2 Keywords: ");
					uKey1 = getKey();
					
					uKey2 = getKey();
					
					//enter keywords for encryption of url
					Four_Square urlParse4 = new Four_Square(uKey1, uKey2);
					
					Parser user1 = new Parser(urlChoice, urlParse4);
				}
			}
			else if(urlKey == 4)
			{
				//url choice
				urlChoice = "https://www.gmit.ie/";
				
				fileKey = keyOption();
				
				if(fileKey == 1)
				{
					System.out.println();
					//generate four square for url parsing
					Four_Square urlParse5 = new Four_Square();
					
					Parser google = new Parser(urlChoice, urlParse5);
				}
				else
				{
					System.out.println("\nPlease Enter 2 Keywords: ");
					uKey1 = getKey();
					
					uKey2 = getKey();
					
					//enter keywords for url encryption
					Four_Square urlParse6 = new Four_Square(uKey1, uKey2);
					
					Parser user1 = new Parser(urlChoice, urlParse6);
				}
			}
			else
			{
				keepRunning = false;
			}//if
			
			
			break;
			
			default:
			keepRunning = false;
			
			}//switch
		
	}//end encrypt menu
	
//end url encryption
	
	//method to for cipher key input
	private String getKey()
	{
		String key;
		
		do
		{
			System.out.print("\nPlease enter Keyword - must be greater than 2 and less than 26: ");
			key = console.next();
		}while(key.length() < 2 || key.length() > 25);
		
		return key;
		
	}//getKey
	
	
	//method to handle cipher key choice
	private int keyOption()
	{
		int choice;
		
		do{
			System.out.println("\n1 - Generate Keys");
			System.out.println("2 - Enter Keys");
			System.out.println("3 - Exit");
			System.out.print("Please choose option for Key Generation: ");
			choice = console.nextInt();
			
		}while(choice < 1 || choice > 3);
		
		return choice;
		
	}//keyOption
	
	//method to confirm character input
	public char confirm()
	{
		char letter;
		
		do
		{
			System.out.print("\n\nEnter 'Y' or 'N': ");
			letter = console.next().charAt(0);
			
		}while(letter != 'y' && letter != 'Y' && letter != 'n' && letter != 'N');
		
		return letter;
	}//confirm
	

}//Menu
