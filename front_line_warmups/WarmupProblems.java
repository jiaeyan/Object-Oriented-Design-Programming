package edu.brandeis.cs12b.pa1;
import java.util.*;
public class WarmupProblems {
	/**
	 * Returns the number of items that have duplicates in an array. For example,
	 * in the array [3,1,5,5,3], countRepeats should return 2, because there are
	 * two numbers that are repeated -- three and five. 
	 * 
	 * @param items - an array of integers
	 * @return the number of integers in items array that are repeated more than once.
	 */
	public static int countRepeats(int[] items) {
		Arrays.sort(items);
		int counter=0;
		int check_num=items[1];   //used to keep track of current number;
		if (items[0]==items[1]){
			counter++;
		}
		for (int i=2;i<items.length;i++){
			if (check_num==items[i] && items[i]!=items[i-2]){ 
				//if current item is the same as previous one but not the one before the previous, 
				//it repeats for the 1st time;
				counter++;
			}
			check_num=items[i];
		}
		return counter;
	}
	
	
	/**
	 * Return true if there are three integers in the items array that sum up to
	 * zero. Return false otherwise.
	 * 
	 * Examples: 
	 * [5, 4, 2, -7] -> TRUE, because (5 + 2 + -7 = 0) 
	 * [3, 4, 1, 52] -> FALSE, because there are no 3 integers in the array that sum up to 0.
	 * 
	 * Try to solve this problem in O(n^2) time -- in other words, try to only
	 * nest your for loops two levels deep (and not 3). It can be done!
	 * 
	 * @param items - an array of integers
	 * @return true - if there exist three integers in items array that add up to zero, 
	 *         otherwise, return false.
	 */
	public static boolean sum3(int[] items) {
		Arrays.sort(items);
		for (int i=0;i<(items.length-2);i++){
			int j=i+1;
			int k=items.length-1;
			//when 1st item fixed, sum its next with the last of the string,
			//if the sum is bigger than (0-1st),the last moves backwards to decrease;
			//if the sum is smaller than (0-1st), the next moves forwards to increment;
			//or the sum equals with (0-1st), meaning that current three numbers sum to 0.
			while (j<k){
				int sum2=items[j]+items[k];
				if (sum2>-items[i]){
					k--;
				}else if (sum2<-items[i]){
					j++;
				}else {
					return true;
				}
			}
		}
		return false;
	}
	
	
	/**
	 * Returns true if a string of parenthesis is balanced.
	 * 
	 * Examples: ()  -> TRUE (balanced)
	 *           (() -> FALSE (unmatched "(" )
	 *           
   	 * A string with any character beside "(" or ")" is invalid, and therefore not balanced.
	 *           
	 * @param str - an input string
	 * @return true - if str is a balanced string, 
	 *         otherwise, return false.
	 */
	public static boolean isBalancedParens(String str) {
		int flag=0;  //pointer to move forwards along the str;
		while (flag<str.length()){
			if ((str.length()-flag)%2!=0 || str.charAt(flag)==')'){
				return false;
			}else{
				int counter=0;
				for (int i=flag;i<str.length();i++){
					if (str.charAt(i)=='('){
						counter++;
						flag++;
					}else if (str.charAt(i)==')'){
						break;
					}else{
						return false;
					}
				}
				int stop=flag;
				for (int j=flag;j<stop+counter;j++){
					if (str.charAt(j)!=')'){
						return false;
					}else{
						flag++;
					}
				}
			}
		}
		return true;
	}


	/**
	 * Similar to isBalancedParens, but this time we consider 4 types of brackets:
	 * ( ) , { } , [ ] and < >.
	 * 
	 * A string with any character beside the brackets is invalid, and therefore not balanced.
	 * 
	 * @param str - an input string
	 * @return true - if str is a balanced string, 
	 *         otherwise, return false.
	 */
	public static boolean isBalancedBrackets(String str) {
		int flag=0;
		while (flag<str.length()){
			char init=str.charAt(flag);
			if ((str.length()-flag)%2!=0 || init==')' || init==']' || init=='}' || init=='>'){
				return false;
			}else{
				int counter=0;
				char[] left_pattern=new char[str.length()];
				for (int i=flag;i<str.length();i++){
					char pare=str.charAt(i);
					if (pare=='(' || pare=='[' || pare=='{' || pare=='<'){
						counter++;
						flag++;
						left_pattern[counter-1]=pare; 
					}else if (pare==')' || pare==']' || pare=='}' || pare=='>'){
						break;
					}else{
						return false;
					}
				}
				char[] right_pattern=new char[counter];
				//copy the left-side pattern and create an array with its right-side pattern 
				for (int j=0;j<counter;j++){
					if (left_pattern[j]=='('){
						right_pattern[counter-j-1]=')';
					}else if (left_pattern[j]=='['){
						right_pattern[counter-j-1]=']';
					}else if (left_pattern[j]=='{'){
						right_pattern[counter-j-1]='}';
					}else if (left_pattern[j]=='<'){
						right_pattern[counter-j-1]='>';
					}
				}
				int stop=flag;
				for (int k=flag;k<(stop+counter);k++){
					//check the rest of the str to see if the series of chars
					//is the same as the right-side pattern;
					if (str.charAt(k)!=right_pattern[k-stop]){
						return false;
					}else{
						flag++;
					}
				}
			}
		}
		return true;
	}
}

