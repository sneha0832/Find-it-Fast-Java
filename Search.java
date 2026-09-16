/*
 * Sneha Patel
 * Program 1: Find it fast
 * 5 October, 2024
 * Main Program
 */

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Search {
    private static void Time(String name,long time){
        System.out.println(name + " took: " + time + " ns");
    }

    private static List<Integer> BruteForce(LinkedList<Character> list, String find) { 
        long startTime = System.nanoTime();
        int n = list.size();
        int m = find.length();
        List<Integer> result = new ArrayList<>(); 
        // Iterate through the list
        for (int i = 0; i <= n - m; i++) { // Iterate only where a full substring could fit
            boolean match = true; // Flag to check if we found a match
            for (int j = 0; j < m; j++) { // Check if the substring matches
                if (!list.get(i + j).equals(find.charAt(j))) {
                    match = false; // Set flag to false if there's a mismatch
                    break; // Break if any character doesn't match
                }
            }
            if (match) { // If match is true, we found the substring
                result.add(i); // Return the starting index of the substring
            }
        }
        long endtime = System.nanoTime();
        long total = endtime - startTime;
        Time("Brute Force",total);
        return result; // Not found
   
    }
    private static void FaliureFunction(String pattern, int [] ff_result){
        int length = 0;// keeps track of longest prefix suffix
        ff_result[0] = 0;
        int i = 1; //start from the second index
        while(i< pattern.length()){//iterate through the pattern
            if (pattern.charAt(i) == pattern.charAt(length)){
                //if there is a match increase the length of prefix suffix
                length++;
                ff_result[i] = length;
                i++;
            }else{//mismatch case
                if(length != 0){//if length is not 0, we can find the next longest prefix suffix
                    length = ff_result[length-1];//set length to the last longest prefix sufffix
                }else{// If len is 0, it means there's no prefix suffix found
                    ff_result[i] = 0;
                    i++;
                }
            }
        }
    }

    private static List<Integer> KMPSearch(LinkedList<Character> text, String pattern){
        long startTime = System.nanoTime();
        int txt_length = text.size();
        int pat_length = pattern.length();
        int[] ff = new int[pat_length]; //holds the faliure function
        List<Integer> result = new ArrayList<>(); 
        FaliureFunction(pattern, ff);
        int t = 0;// for text
        int p = 0;//for pattern
        while(t<txt_length){
        //while (txt_length - t >= pat_length - p){ // loop through the ll
            char current = text.get(t);//current index
            //System.out.println("Comparing pattern[" + p + "]=" + pattern.charAt(p) + " with text[" + t + "]=" + current);
            //if (pattern.charAt(p) == current){//if the current char is the same as the current pattern
            while ( p < pat_length && t< txt_length){
                if (pattern.charAt(p) == text.get(t)){
                    p++;//go next in pattern
                    t++;//go next in text
                }
                else break;
            }

            if ( p == pat_length){// if we found the complete match
                //t - p will give us the starting index of the pattern
                result.add(t - p );
                //System.out.println("Pattern found at index: " + (t - p));
                p = ff[p-1];
                if (t >= txt_length){
                    break;
                }else{
                    current = text.get(t+1);
                }
            }else if(t < txt_length && p< pat_length && pattern.charAt(p) != text.get(t)){//if we are still within the bounds and the current chars of text and pattern does not match
                if(p != 0){
                    //if p is not 0 we can go back to longest prefix suffix value
                    p = ff[p-1];
                }else{
                    //if j is 0 that means there are no match at all so we move to the next character in the text
                    t++;
                }
            }
        }
        long endtime = System.nanoTime();
        long total = endtime - startTime;
        Time("KMP",total);
        return result;
    }
    /* */
    private static  List<Integer> BMSearch(LinkedList<Character> text,String pattern){
        long startTime = System.nanoTime();
        int pat_length = pattern.length();
        int text_length = text.size();
        List<Integer> result = new ArrayList<>(); 
        //using a hashmap to build bad character table
        //loops through pattern and 
        //the table will store the right most occurence of each character in the pattern
        HashMap<Character, Integer> BadChar_table = new HashMap<>();
        for (int i = 0; i< pat_length; i++ ){
            BadChar_table.put(pattern.charAt(i), i);
        }
        int skip; //variable to store how many character to skip after a mismatch
        for(int i = 0; i <= text_length - pat_length; i+= skip){
            skip=0;
            //extract a sublist from the linked list for comparison with pattern
            LinkedList<Character> subList = new LinkedList<>(text.subList(i,i+pat_length));
            //Traverse the pattern form right to left
            for(int j = pat_length -1; j>= 0; j--){
                char textchar = subList.get(j);//get character from text
                char patchar = pattern.charAt(j);//get character from pattern
                //if there is a mismatch
                if (textchar != patchar){
                    //calculate the skip value using the bad character table
                    //if the character is not in the pattern , we skip by the whole patern
                    skip = Math.max(1, j - BadChar_table.getOrDefault(textchar, -1)); //this will get the last occurence of textchar from bad character table and if not found will return -1
                    break;//exit because we found a mismatch
                }
            
            }
            if(skip==0){
                result.add(i);
                //System.out.println("Found at index"+ i);
                skip = pat_length;
            }
        }
        long endtime = System.nanoTime();
        long total = endtime - startTime;
        Time("Boyer-Moore",total);
        //if skip is still 0 it means that patten has already been found
        return result;
    }
    


    public static void main(String[] args){
        //Search search = new Search(); // Create an instance of the Search class
       
        LinkedList<Character> myList = new LinkedList<>();
        try{
            //read the file
            File file = new File("prog1input1.txt");
            Scanner sc = new Scanner(file);
            //populate the ll
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                for (char ch : line.toCharArray()) {
                    myList.add(ch);
                }
            }
            sc.close();
        }catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return; // Exit the program if the file is not found
        }

        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Enter the substring to search for:");
        String find = inputScanner.nextLine();
        inputScanner.close();

        // Perform the search
        //long result = search.BruteForce(myList, find); 
        List<Integer> result = Search.BruteForce(myList,find);
        List<Integer>  result2 = Search.KMPSearch(myList,find);
        List<Integer> result3 = Search.BMSearch(myList,find);
        if (result.size() != 0) {
            System.out.println("Substring found at index:" + result);
            System.out.println("Substring found at index: " + result2);
            System.out.println("Substring found at index: " + result3);
        } else {
            System.out.println("Substring not found.");
        }
    }
}
/*
 * Brute Force performed consistently. It is ideal to use for smaller inputs and where pattern and texts are small.
 * KMP seemed to be the fastest in all the tests but took longer time for the worst case. This search algorithm should be used in scenarios where the pattern contains repeated substrings or overlaps, significantly reducing the number of character comparisons. 
 * BM semmed pretty consistent and also faster for larger inputs. This algorithm is best for searching large patterns as there will be larger skips making it more faster.
 * overall the average performance was BM > Brute Force > KMP   
 */