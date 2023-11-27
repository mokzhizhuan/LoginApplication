package com.loginapp;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class restapiController {

	static String encodedString = ""; //Declare static string for storing encodedString output
    static char[] ars = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
    'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '(', ')', '*', '+',
    ',', '-', '.', '/' };//Given character Table
    static char[] oris = ars.clone(); //Make copy of given table
    static char offChar; //character of Offset i.e B or F or any
    static int newOFF = 0; //To store Offset Index/number
	
    @RequestMapping(method = RequestMethod.POST)
	@PostMapping("/encodedpass")
	public String encodepass(@Param("passwords") String password, @Param("offsetchar") String offsetchar)
	{
		String passuppercase = password.toUpperCase();
		char oddsetchar = offsetchar.charAt(0);
		int offsetii = 0;
		char[] arplain = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
			    'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '(', ')', '*', '+',
			    ',', '-', '.', '/' };
        for (int i = 0; i < 44; i++) 
        { //Store offset character index in offset varible
            if (oddsetchar == arplain[i]) 
            {
                offsetii = i;
            }
        }
        newOFF = offsetii;
        String encodedpassword = oddsetchar + encode(passuppercase);
		return encodedpassword;
	}
	
	public String encode(String plaintext) 
    {//Used to decode plaintext
        char[] res = plaintext.toCharArray(); // convert plaintext to character array
        int c = 0; //varible to store number of spaces
        for (int i = 0; i < res.length; i++) 
        { //Calculate Space in String
            if (res[i] == ' ') 
            {
                c++;
            }
        }
        //To Get Offset Table based on Offset value i.e newOFF
        for (int j = 0; j < newOFF; j++) 
        {
            char temp = ars[ars.length - 1]; //store last element of given array in tem varible
            int l = ars.length; //Calculate length of array

            int last = ars.length - 1;

            for (int i = l - 2; i >= 0; i--) 
            { //Shift every character from begenning to end by one time
                ars[last] = ars[i]; //store ar[i]th element in last location of array
                last--; //decrement last so all element upto 1st will Shifted to last
            }
            ars[0] = temp; //Store last element of array at 1st location

        }
        String t = "";
        int[] position = new int[res.length]; //integer array to store index of resultant array
        int pos = 0; //Index of position array
        int g = 0;

        int f = 0; //Flag to check wheather element of String is present on Table or not
        for (int k = 0; k < res.length; k++) 
        {
            for (int i = 0; i < oris.length; i++) 
            {
                if (res[k] == oris[i]) 
                { // IF element from String present in table then add its position in array
                    f = 1; //Indicate element of String is present in Table
                    position[pos] = i; //Store that element
                    pos++; //Increment pos index of array
                    break;
                }
            }
            if (f == 0 && res[k] != ' ') 
            { //If element from input String not present in table then type cast that character to integer and store in array
                position[pos] = (int) res[k];
                pos++;
            }
            f = 0;
            if (res[k] == ' ') 
            {//If space is present in input String then add 0 to position array
                position[pos] = 0;
                pos++;
            }
        }
        String tr = ""; //resultant encoded String to be return

        for (int i = 0; i < position.length; i++) 
        {//If 0 present in position array it Indicate presence of space so add space to encoded String
            if (position[i] == 0) 
            {
                tr = tr + " ";
            }
            if (position[i] != 0 && position[i] >= 0 && position[i] <= 43) 
            {//To encode element from table
                tr = tr + ars[position[i]];
            }

            if (position[i] >= 97 && position[i] <= 122) 
            { //To encode element of lowerCases
                char single = (char) position[i]; //Typecast integer to character from position array
                tr = tr + single;
            }

            if (position[i] >= 58 && position[i] <= 64) 
            { //To encode element of special character
                char single = (char) position[i]; ////Typecast integer to character from position array
                tr = tr + single;
            }

            if (position[i] >= 91 && position[i] <= 96) 
            {//To encode element of special character
                char single = (char) position[i]; //Typecast integer to character from position array
                tr = tr + single;
            }

            if (position[i] >= 123 && position[i] <= 126)
            {//To encode element of special character
                char single = (char) position[i]; //Typecast integer to character from position array
                tr = tr + single;
            }
        }
        encodedString = encodedString + tr; //Update static encodedString String for decode
        return tr; //return resultant encoded string
    }
}
