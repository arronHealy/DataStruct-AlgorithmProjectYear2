package ie.gmit.sw;

import java.io.*;
import java.net.*;
import java.util.*;

//Author - Arron Healy - G00346883

public class Parser {
	
	Scanner console = new Scanner(System.in);
	
	char confirm, confirmPrint, confirmDecrypt, confirmPrintSquare;
	
	File decryptUrlFile = new File("CipherUrl.txt");
	
	File cipher = new File("Cipher.txt");
	
	
	public Parser(InputStream input, Four_Square f)
	{
		parse(input, f);
	}//parser file constructor
	
	
	public Parser(String url, Four_Square u)
	{
		parseUrl(url, u);
	}//parser url constructor
	
	
	//method to encrypt file from input stream and passed in four square object
	public void parse(InputStream input, Four_Square f)
	{
		try
		{
			
			BufferedReader br = new BufferedReader(new InputStreamReader(input));
			
			FileWriter cipherFile = new FileWriter("Cipher.txt");
			
			String line = null;
			
			long encryptPlain = System.currentTimeMillis();
			
			//loop over file input stream and encrypt
			while((line = br.readLine()) != null)
			{
				line = line.toUpperCase().replaceAll("[^A-Z0-9]+", "").replace('J', 'I');
				String encrypt = f.encrypt(line);
				System.out.print(line + "\n");
				cipherFile.write(encrypt + "\n");
			}//while
			
			System.out.println("\nFile Contents Before Encryption Printed above!");
			
			System.out.println("\nFile Encrypted Check Folder!");
			
			System.out.println("Encrypt File Running Time (ms): " + (System.currentTimeMillis() - encryptPlain));
			
			//close file and flush buffer
			br.close();
			cipherFile.flush();
			cipherFile.close();
			
			System.out.print("\n\nWould you like to create decrypted version of file? Enter 'Y' or 'N': ");
			confirm = confirm();
			
			//option to decrypt file
			switch(confirm)
			{
			case 'y':
			case 'Y':
				//decrypt file and test methods run time
				long decryptPlain = System.currentTimeMillis();
				decryptFile(cipher, f);
				System.out.println("\nPlease check Folder for Encrypted and Decrypted Versions of File!");
				
				System.out.println("\nDecrypt File Running Time (ms): " + (System.currentTimeMillis() - decryptPlain));
				break;
				
			case 'n':
			case 'N':
				System.out.println("\nPlease check Folder for Encrypted Version of File!");
				break;
				
				default:
					break;
			}//switch
			
			
			System.out.print("\n\nWould you like to print the created Four-Square? Enter 'Y' or 'N': ");
			confirmPrint = confirm();
			
			//option to print created four square
			switch(confirmPrint)
			{
			case 'y':
			case 'Y':
				long print = System.currentTimeMillis();
				f.print();
				System.out.println("\nYour Created Four-Square!");
				
				System.out.println("\nPrint Running Time (ms): " + (System.currentTimeMillis() - print));
				break;
				
			case 'n':
			case 'N':
				System.out.println("\nYour Four-Square Cipher Remains a Secret!");
				break;
				
				default:
					break;
			}//switch
			
			
		}
		catch(Exception e)
		{
			System.out.println("No input something went wrong!");
			e.printStackTrace();
		}//try/catch
		
	
	}//encrypt file
	
	//method decrypts file based on passed in four square object
	public void decryptFile(File in, Four_Square f)throws FileNotFoundException
	{
		try
		{
			
			BufferedReader b = new BufferedReader(new FileReader(in));
			
			FileWriter plainFile = new FileWriter("Plain.txt");
			
			String line = null;
			
			//loop over file and decrypt 
			while((line = b.readLine()) != null)
			{
				line = line.toUpperCase();
				String decrypted = f.decrypt(line);
				System.out.print(line + "\n");
				plainFile.write(decrypted + "\n");
			}
			
			System.out.println("\nFile contents before Decryption Printed above!");
			
			//close file and flush buffer
			b.close();
			plainFile.flush();
			plainFile.close();
		
		}
		catch(Exception e)
		{
			System.out.println("No input something went wrong!");
			e.printStackTrace();
		}
		
	}//decryptFile
	
	//method to encrypt chosen url and created four square object
	private void parseUrl(String url, Four_Square f)
	{
		try
		{
			URL input = new URL(url);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(input.openStream()));
			
			FileWriter urlFile = new FileWriter("CipherUrl.txt");
			
			String urlText = null;
			
			long urlTime = System.currentTimeMillis();
			
			//loop until end of url and encrypt based from passed in four square object
			while((urlText = br.readLine()) != null)
			{
				urlText = urlText.toUpperCase().replaceAll("[^A-Z0-9]+", "").replace('J', 'I');
				String encrypt = f.encrypt(urlText);
				System.out.print(urlText + "\n");
				urlFile.write(encrypt + "\n");
			}//while
			
			System.out.println("\nURL Contents Before Encryption Printed above!");
			
			System.out.println("\nURL Encrypted Check Folder!");
			
			System.out.println("\nEncrypt URL Running Time (ms): " + (System.currentTimeMillis() - urlTime));
			
			//close file and flush buffer
			br.close();
			urlFile.flush();
			urlFile.close();
			
			System.out.println("\nHit Enter to continue\n");
			
			System.out.print("\nWould you like to create decrypted version of file? Enter 'Y' or 'N': ");
			confirmDecrypt = confirm();
			
			//option to decrypt your encrypted file
			switch(confirmDecrypt)
			{
			case 'y':
			case 'Y':
				//call decrypt method and test run time of method
				long decryptUrl = System.currentTimeMillis();
				decryptUrl(decryptUrlFile, f);
				System.out.println("\nPlease check Folder for Encrypted and Decrypted Versions of File!");
				
				System.out.println("\nDecrypt File Running Time (ms): " + (System.currentTimeMillis() - decryptUrl));
				break;
				
			case 'n':
			case 'N':
				System.out.println("\nPlease check Folder for Encrypted Version of File!");
				break;
				
				default:
					break;
			}//switch
			
			System.out.println("\nHit Enter to continue\n");
			
			System.out.print("\n\nWould you like to print the created Four-Square? Enter 'Y' or 'N': ");
			confirmPrintSquare = confirm();
			
			//option to print your four square cipher 2D arrays
			switch(confirmPrintSquare)
			{
			case 'y':
			case 'Y':
				long print = System.currentTimeMillis();
				f.print();
				System.out.println("\nYour Created Four-Square!");
				
				System.out.println("\nPrint Running Time (ms): " + (System.currentTimeMillis() - print));
				break;
				
			case 'n':
			case 'N':
				System.out.println("\nYour Four-Square Cipher Remains a Secret!");
				break;
				
				default:
					break;
			}//switch
			
		}
		catch(Exception e)
		{
			System.out.println("\nNo input something went wrong!");
			e.printStackTrace();
		}
		
	}//encryptUrl
	
	//decrypt url method takes encrypted file and four square object
	public void decryptUrl(File in, Four_Square f)throws FileNotFoundException
	{
		try
		{
			
			BufferedReader b = new BufferedReader(new FileReader(in));
			
			FileWriter plainFile = new FileWriter("PlainUrl.txt");
			
			String line = null;
			
			while((line = b.readLine()) != null)
			{
				line = line.toUpperCase();
				String decrypted = f.decrypt(line);
				System.out.print(line + "\n");
				plainFile.write(decrypted + "\n");
			}
			
			System.out.println("\nFile contents before Decryption Printed above!");
			
			//close files and flush buffer
			b.close();
			plainFile.flush();
			plainFile.close();
			
		}
		catch(Exception e)
		{
			System.out.println("\nNo input something went wrong!");
			e.printStackTrace();
			
		}//try/catch
		
	}//decryptUrl
	
	//method to confirm choice
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

}//Parser
