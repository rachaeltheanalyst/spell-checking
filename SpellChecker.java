import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/** 
 * Class that operates in two possible modes. In command- line mode 
 * it will provide explicit feedback on the spelling of specific words. 
 * In file scanning mode it will take input from a file and print messages 
 * only for words that are not spelled correctly.
 */
public class SpellChecker {
  public static void main(String[] args) throws Exception {

    /** a SpellDictionary that reads in file **/
    SpellDictionary fileWordArray = new SpellDictionary("words.txt");

    if (args.length == 0) {
      processFromFile(fileWordArray);
    } else {
      processFromInput(args, fileWordArray);
    }
  }

  /**
   * Method that reads words from command line, check whether the words are correct or not,
   * and makes suggestions for incorrect words
   *
   * @param args          Array of String that contains words from input
   * @param fileWordArray An SpellDictionary object that contains words from dictionary file
   */
  public static void processFromInput(String[] args, SpellDictionary fileWordArray) {
    for (int i = 0; i < args.length; i++) {
      String wordInFile = args[i];
      if (fileWordArray.containsWord(wordInFile)) {
        System.out.println(" ");
        System.out.println("'" + wordInFile + "'" + " is spelled correctly.");
      } else {
        System.out.println(" ");
        System.out.println("Not found: " + wordInFile);
        System.out.println("Suggestions: ");
        ArrayList<String> nearMisses = fileWordArray.nearMisses(wordInFile);
        for (int j = 0; j < nearMisses.size(); j++) {
          System.out.printf(nearMisses.get(j) + " ");
        }
      }
    }
  }

  /** 
   * Method that reads contents from a file, check whether the words are correct or
   * not, and makes suggestions for incorrect words
   *
   * @param fileWordArray A SpellDictionary object contains words from
   *                      dictionary file
   */
  public static void processFromFile(SpellDictionary fileWordArray) {
    ArrayList<String> sonnetWordsArrayList = new ArrayList<String>();
    try {
      Scanner input = new Scanner(System.in);

      while (input.hasNextLine()) {
        String line = input.nextLine();

        // convert to lower case
        line = line.toLowerCase();

        // skip over punctuations
        String result = line.replaceAll("\\p{Punct}", "");
        String[] sonnetWords = result.split("\\s");
        Collections.addAll(sonnetWordsArrayList, sonnetWords);
      }

      input.close();

      // System.out.println(sonnetWordsArrayList);

    } catch (Exception ex) {
      ex.printStackTrace();
    }

    // remove duplicates
    Set<String> uniqueWords = new HashSet<>(sonnetWordsArrayList);
    sonnetWordsArrayList.clear();
    sonnetWordsArrayList.addAll(uniqueWords);

    ArrayList<String> nearMisses = new ArrayList<String>();
    for (int i = 0; i < sonnetWordsArrayList.size(); i++) {
      if (!fileWordArray.containsWord(sonnetWordsArrayList.get(i))) {
        System.out.println("Not found: " + sonnetWordsArrayList.get(i));
        nearMisses = fileWordArray.nearMisses(sonnetWordsArrayList.get(i));
        if (nearMisses.size() == 0) {
          System.out.println("No suggestions");
        } else {
          System.out.println("Suggestions: ");
          for (int j = 0; j < nearMisses.size(); j++) {
            System.out.printf(nearMisses.get(j) + " ");
          }
          System.out.println();
        }
        System.out.println();
      }
    }
  }
}
