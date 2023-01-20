# Spell Checking 

> A spell-checking program that stores its dictionary in a hash table checks whether a given word is spelled correctly and provides a method to suggest possible alternatives in the event of a misspelled word.

<a name="toc"/></a>
## Table of Contents

1. [Overview](#overview)

2. [Technologies](#technologies)

3. [Launch](#launch)

4. [Source](#source)

<a name="overview"/></a>
## 1. Overview
[Back to ToC](#toc)

This project reads in and stores word spellings from a dictionary file, provides a method to check whether a given word is spelled correctly, and provides a method to suggest possible alternatives in the event of a misspelled word.

It operates in two possible modes. In command-line mode, it will provide explicit feedback on the spelling of specific words. In file scanning mode, it will take input from a file and print messages only for words that are not spelled correctly.

For words that are not spelled correctly, it will return an ArrayList of correctly spelled words that are exactly one edit away from a given incorrect word spelling. It will do this by constructing all possible near misses, checking them against the dictionary, and returning any that are real words, without duplicates. It considers the following types of near misses:

Deletions: Delete one letter from the word. (n possibilities for a word of length n) E.g.: catttle -> cattle

Insertions: Insert one letter into the word at any point. (26*(n+1) possibilities for a word of length n) E.g.: catle -> cattle

Substitutions: Replace one character with another. (25*n possibilities for a word of length n) E.g.: caxtle -> cattle

Transpositions: Swap two adjacent characters. (n-1 possibilities for a word of length n) E.g.: cattel -> cattle

Splits: Divide the word into two legal words. (n-1 possibilities for a word of length n) -- for this kind of near miss, the pair of words together should be recorded as a single entry, with a space between them. E.g.: cattell -> cat tell

<a name="technologies"/></a>
## 2. Technologies
[Back to ToC](#toc)

java version "1.8.0_181"<br />
Java(TM) SE Runtime Environment (build 1.8.0_181-b13)<br />
Java HotSpot(TM) 64-Bit Server VM (build 25.181-b13, mixed mode)<br />

<a name="launch"/></a>
## 3. Launch
[Back to ToC](#toc)
```bash
javac -classpath .:target/dependency/* -d . $(find . -type f -name '*.java')

Command-line mode:
java SpellChecker <list of words to check>
For example: java SpellChecker qest questt quest

File scanning mode:
java SpellChecker <file name>
For example: java SpellChecker < sonnet.txt
```
<a name="source"/></a>
## 4. Source
[Back to ToC](#toc)

The concept for this project is due to [**David Eck**](https://math.hws.edu/eck/).