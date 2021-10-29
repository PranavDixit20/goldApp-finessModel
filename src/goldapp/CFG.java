/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldapp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author vikra
 */
class CFG {
 
    // Method 1
    // To check whether number is valid or not
    public static boolean isValidMobile(String s)
    {
 
        // The given argument to compile() method
        // is regular expression. With the help of
        // regular expression we can validate mobile
        // number.
        // The number should be of 10 digits.
 
        // Creating a Pattern class object
        Pattern p = Pattern.compile("^\\d{10}$");
 
        // Pattern class contains matcher() method
        // to find matching between given number
        // and regular expression for which
        // object of Matcher class is created
        Matcher m = p.matcher(s);
 
        // Returning bollean value
        return (m.matches());
    }
    
    public static boolean isValidDecimal(String s){
        Pattern p = Pattern.compile("^\\d+\\.\\d+");
        Matcher m = p.matcher(s);
        return (m.matches());
    }
    
    // A function that return given number in words
    static String convert_to_words(char[] num)
    {
        // Get number of digits in given number
        int len = num.length;
		String finalWord = "";
        // Base cases
        if (len == 0) {
            //System.out.println("empty string");
            return "empty string";
        }
        if (len > 4) {
            //System.out.println("Length more than 4 is not supported");
            return "Length more than 4 is not supported";
        }
 
        /* The first string is not used, it is to make
            array indexing simple */
        String[] single_digits = new String[] {
            "zero", "one", "two",   "three", "four",
            "five", "six", "seven", "eight", "nine"
        };
 
        /* The first string is not used, it is to make
            array indexing simple */
        String[] two_digits = new String[] {
            "",          "ten",      "eleven",  "twelve",
            "thirteen",  "fourteen", "fifteen", "sixteen",
            "seventeen", "eighteen", "nineteen"
        };
 
        /* The first two string are not used, they are to
            make array indexing simple*/
        String[] tens_multiple = new String[] {
            "",      "",      "twenty",  "thirty", "forty",
            "fifty", "sixty", "seventy", "eighty", "ninety"
        };
 
        String[] tens_power
            = new String[] { "hundred", "thousand" };
 
        /* Used for debugging purpose only */
        //System.out.print(String.valueOf(num) + ": ");
 
        /* For single digit number */
        if (len == 1) {
            //System.out.println(single_digits[num[0] - '0']);
            return single_digits[num[0] - '0'];
        }
 
        /* Iterate while num
            is not '\0' */
        int x = 0;
        while (x < num.length) {
 
            /* Code path for first 2 digits */
            if (len >= 3) {
                if (num[x] - '0' != 0) {
                    //System.out.print(single_digits[num[x] - '0'] + " ");
                    //System.out.print(tens_power[len - 3] + " ");
                    // here len can be 3 or 4
					finalWord = finalWord + single_digits[num[x] - '0'] + " " + tens_power[len - 3] + " ";
                }
                --len;
            }
 
            /* Code path for last 2 digits */
            else {
                /* Need to explicitly handle
                10-19. Sum of the two digits
                is used as index of "two_digits"
                array of strings */
                if (num[x] - '0' == 1) {
                    int sum
                        = num[x] - '0' + num[x + 1] - '0';
                    //System.out.println(two_digits[sum]);
					finalWord = finalWord + two_digits[sum];
                    return finalWord;
                }
 
                /* Need to explicitely handle 20 */
                else if (num[x] - '0' == 2
                         && num[x + 1] - '0' == 0) {
                    //System.out.println("twenty");
					finalWord = "twenty";
                    return finalWord;
                }
 
                /* Rest of the two digit
                numbers i.e., 21 to 99 */
                else {
                    int i = (num[x] - '0');
                    if (i > 0){
						//System.out.print(tens_multiple[i] + " ");
						finalWord = finalWord + tens_multiple[i] + " ";
					}  
                    else{
						//System.out.print("");
						finalWord = finalWord + "";
					}
                        
                    ++x;
                    if (num[x] - '0' != 0)
                        //System.out.println(single_digits[num[x] - '0']);
						finalWord = finalWord + single_digits[num[x] - '0'];
                }
            }
            ++x;
        }
		return finalWord;
    }
}
 
