package edu.brandeis.cs12b.pa3;
import java.io.*;
import java.util.*;


public class CaesarCipher {
	// necessary fields for encoding and decoding methods
	char[] lowerCaseLetter={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	char[] upperCaseLetter={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	char[] digitNum={'0','1','2','3','4','5','6','7','8','9'};
    Set<String> dict=new HashSet<String>();
    
    public CaesarCipher(){
    	    File f = new File("dictionary/google10000.txt");
    	    try{
    	    	  Scanner input = new Scanner (f);
    	      while (input.hasNextLine()){
    	    	      String word = input.nextLine();
    	    	      this.dict.add(word);
    	      }
    	      input.close();
    	    }catch (FileNotFoundException e){
    	    	  e.printStackTrace();
    	    }
    }
    
    public static void main(String[] args) throws FileNotFoundException{
		CaesarCipher cipher=new CaesarCipher();
		String s=cipher.encode("Mary Jane is the most beautiful woman I have seen", 141);
		System.out.println(s);
		System.out.println(cipher.decode(s, 141));
		String s1="Xlcj Ulyp td esp xzde mplfetqfw hzxly T slgp dppy";
		System.out.println(cipher.decode(s1));
	} 
    
	public String encode(String s, int n){
		StringBuilder sb=new StringBuilder();
		// check the validity of input
		if (s!=null && !s.isEmpty()){    
			if (n==0){  // here we assume a bad encrypt as one itself still counts as valid encoding
				return s;
			}else{
				for (int i=0;i<s.length();i++){  // process each char of input
					if(n>0){
						sb.append(encodeCharPos(s.charAt(i),n));
					}else if(n<0){
						sb.append(encodeCharNeg(s.charAt(i),n));
				    }
					//System.out.println(sb);
				}
				return sb.toString();
			}
		}
		return null;
	}
	public char encodeCharPos(char c,int n){
		// the index of after-forward-moving pointer will be
		// (current index + moving steps) % 26 for letters and 
		// (current index + moving steps) % 10 for digits
		
		if (Character.isLowerCase(c)){
			return lowerCaseLetter[(indexOf(c,lowerCaseLetter)+n)%26];
		}else if (Character.isUpperCase(c)){
			return upperCaseLetter[(indexOf(c,upperCaseLetter)+n)%26];
		}else if (Character.isDigit(c)){
			return digitNum[(indexOf(c,digitNum)+n)%10];
		}else{
			return c;
		}
	}
	
	public char encodeCharNeg(char c, int n){
		// the index of after-backward-moving pointer will be
		// (current index + 26 + moving steps % 26) % 26 for letters and 
		// (current index + 10 + moving steps % 10) % 10 for digits
		
		if (Character.isLowerCase(c)){
			return lowerCaseLetter[(indexOf(c,lowerCaseLetter)+26+n%26)%26];
		}else if(Character.isUpperCase(c)){
			return upperCaseLetter[(indexOf(c,upperCaseLetter)+26+n%26)%26];
		}else if(Character.isDigit(c)){
			return digitNum[(indexOf(c,digitNum)+10+n%10)%10];
		}else{
			return c;
		}
	}
	
	public int indexOf(char c, char[] list){
		// this help function finds the index of certain char in certain field
		for (int i=0;i<list.length;i++){
			if (c==list[i]){
				return i;
			}
		}
		return -1;
	}
	
	public String decode(String s, int n){
		StringBuilder sb=new StringBuilder();
		if (s!=null && !s.isEmpty()){
			if (n==0){
				return s;
			}else{
				for (int i=0;i<s.length();i++){
					if(n>0){  // decoding is the opposite act of encoding, including the orientation of steps
						sb.append(encodeCharNeg(s.charAt(i),-n));
					}else if(n<0){
						sb.append(encodeCharPos(s.charAt(i),-n));
				    }
					//System.out.println(sb);
				}
				return sb.toString();
			}
		}
		return null;
	}
	
	public String decode(String s){
		// for this method we assume the output includes the uncommon words
		// that are decoded from the input, even though they may not be real Engilsh words,
		// if at least 90% of the words in dict
		
		if (legalText(s)){
			String[] wordChain = s.split("[ ]");
			if (wordChain.length>4){
				for (int i=0;i<26;i++){ // all different possible moves for a letter is 25
					int count=0;        // but here we assume a bad encrypt as one itself still counts as valid input
					String output="";
					for (int j=0;j<wordChain.length;j++){
						if (dict.contains(encode(wordChain[j],i).toLowerCase())){
							count+=1;
						}
						output=output+" "+encode(wordChain[j],i);
					}
					if (count >= Math.floor(0.9*wordChain.length)){
						return output.substring(1);
					}
				}
			}
			return null;
		}
		return null;
	}
	
	public boolean legalText(String s){
		// this help function checks the validity of input
		if (s==null || s.isEmpty()){
			return false;
		}else{
			for (int i=0;i<s.length();i++){
				if (!Character.isLowerCase(s.charAt(i)) && !Character.isUpperCase(s.charAt(i)) && s.charAt(i)!=' '){
					return false;
				}
			}
		}
		return true;
	}
}
