import java.util.HashSet;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Set;

/**
 * Class that reads in and store word spellings from a dictionary 
 * file, check whether a given word is spelled correctly and 
 * provide a method to suggest possible alternatives in the event 
 * of a misspelt word.
 */
public class SpellDictionary {
  /** Stores the dictionary words */
  private HashSet<String> words;
  
  /** 
   * Constructor to read in and store word spellings from a 
   * dictionary file
   * @param filename The name of the dictionary file
   */
  public SpellDictionary(String filename) {
    words = new HashSet<String>();
    try {
      Scanner input = new Scanner(new File(filename));
      while (input.hasNextLine()) {
        String line = input.nextLine();
        line = line.toLowerCase();
        String result = line.replaceAll("\\p{Punct}", "");
        //System.out.println(result);
        words.add(result);
      }
    } catch (FileNotFoundException e) {
        
    }
  }

  /** 
   *  Method to check if a word is spelled correctly
   *  @param spelling  the word to check
   *  @return true if the word is in the dictionary and false otherwise 
   */
  public boolean containsWord(String spelling) {
    return words.contains(spelling.toLowerCase());
  }

  /** 
   *  Method to suggest possible alternatives for the misspelt word
   *  @param mispelledWord the misspelt word
   *  @return an ArrayList of String that contains the possible alternatives  
   */
  public ArrayList<String> nearMisses(String mispelledWord) {
     
    ArrayList<String> correctWords = new ArrayList<String>();
    
    //Deletions
    for (int i = 0; i < mispelledWord.length(); i++) {
      String str1 = mispelledWord.substring(0,i);
      String str2 = mispelledWord.substring(i+1,mispelledWord.length());
      if (containsWord(str1+str2)) {
        correctWords.add(str1+str2);
      }
    }
    //Insertions
    for(char alphabet = 'a'; alphabet <='z'; alphabet++)
    {
      for (int i = 0; i <= mispelledWord.length(); i++) {
        String insertLetter = mispelledWord.substring(0, i) + alphabet + mispelledWord.substring(i, mispelledWord.length());
        //System.out.println(insertionWord);
        if (containsWord(insertLetter)) {
          correctWords.add(insertLetter);
        }
      }
    }

    //Substitutions
    for(char alphabet = 'a'; alphabet <='z'; alphabet++) {     
      for (int i = 0; i < mispelledWord.length(); i++) {
        if (alphabet!=mispelledWord.charAt(i)) {
          String substituteLetter = mispelledWord.substring(0, i) + alphabet + mispelledWord.substring(i+1, mispelledWord.length());
          //System.out.println(substituteLetter);
          if (containsWord(substituteLetter)) {
            correctWords.add(substituteLetter);
          }
        }
      }
    }

    //Transpositions
    for (int i = 0; i < mispelledWord.length()-1; i++) {
      char char1 = mispelledWord.charAt(i);
      char char2 = mispelledWord.charAt(i+1);
      String swapWord = mispelledWord.substring(0,i) + char2 + char1 + mispelledWord.substring(i+2, mispelledWord.length());
      //System.out.println(swapWord);
      if (containsWord(swapWord)) {
        //System.out.println(str1+str2);
        correctWords.add(swapWord);
      }
    }
    
    //Splits
    for (int i = 1; i < mispelledWord.length(); i++) {
      String str1 = mispelledWord.substring(0,i);
      String str2 = mispelledWord.substring(i, mispelledWord.length());
      //System.out.println(str1 + " " + str2);
      if (containsWord(str1) && containsWord(str2)) {
        //System.out.println(str1 + " " + str2);
        correctWords.add(str1 + " " + str2);
      }
    }

    // Remove duplicates
    Set<String> uniqueCorrectSet = new HashSet<>(correctWords);
    correctWords.clear();
    correctWords.addAll(uniqueCorrectSet);
    
    return correctWords;
  }
}
