/** 
 *  Class that tests SpellDictionary class
 */
class Main {
  public static void main(String[] args) {
    testSpellDictionary();
  }

  /**
   * Method to test the SpellDictionary class
   */
  public static void testSpellDictionary() {
    /* construct an object */
    SpellDictionary dict = new SpellDictionary("words.txt");
    
    TestCode.runTest("containsWord", dict.containsWord("Albany"));
    TestCode.runTest("containsWord2", dict.containsWord("cesium"));
    
    TestCode.runTest("nearMissesDelete", dict.nearMisses("alfford").contains("alford"));
    TestCode.runTest("nearMissesDeleteF", dict.nearMisses("aaberdeen").contains("aberdeen"));
    TestCode.runTest("nearMissesDeleteE", dict.nearMisses("bantuss").contains("bantus"));
    TestCode.runTest("nearMissesSplits", dict.nearMisses("understandingunending").contains("understanding unending"));
    TestCode.runTest("nearMissesSplitsF", dict.nearMisses("abrain").contains("a brain"));
    TestCode.runTest("nearMissesSplitsE", dict.nearMisses("baathb").contains("baath b"));
    TestCode.runTest("nearMissesInsertion", dict.nearMisses("unese").contains("unease"));
    TestCode.runTest("nearMissesInsertionF", dict.nearMisses("arbie").contains("barbie"));
    TestCode.runTest("nearMissesInsertionE", dict.nearMisses("bismar").contains("bismark"));
    TestCode.runTest("nearMissesSubstitution", dict.nearMisses("uxease").contains("unease"));
    TestCode.runTest("nearMissesSubstitutionF", dict.nearMisses("sarole").contains("carole"));
    TestCode.runTest("nearMissesSubstitutionE", dict.nearMisses("chaz").contains("chan"));
    TestCode.runTest("nearMissesTransposition", dict.nearMisses("uenase").contains("unease"));
    TestCode.runTest("nearMissesTranspositionF", dict.nearMisses("edbra").contains("debra"));
    TestCode.runTest("nearMissesTranspositionE", dict.nearMisses("fauts").contains("faust"));

  }
}
