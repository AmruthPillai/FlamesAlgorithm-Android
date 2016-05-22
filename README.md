# Flames Algorithm
#### Implementation in Java/Android

> Check out the implementation in Python here: [AmruthPillai/FlamesAlgorithm-Python](https://github.com/AmruthPillai/FlamesAlgorithm-Python)  
> Check out the implementation in C here: [AmruthPillai/FlamesAlgorithm-C](https://github.com/AmruthPillai/FlamesAlgorithm-C)  

---

### Logo
<img src="https://cloud.githubusercontent.com/assets/1134738/15452569/8447ae9e-2011-11e6-8b4e-0f72ed407891.png" width="256">

---

### Description
The game of FLAMES is apparently a popular game played by teenagers as a way to deal with the unpredictability of their romantic relationships. This belongs to the vast class of prediction devices such as the counting of petals, numerological tricks, horoscopes and the like. However, unlike many of these other methods, the FLAMES game is based on a transparent deterministic algorithm.

---

### How To Play

The rules of the FLAMES game can be described as follows. Take the names of two people, usually of opposite genders, and cross out all the common letters in the two names. Count the total number of letters that remain in both names after this procedure. Let this number of mismatches be m.  

Now write FLAMES on a piece of paper. Count through the letters of this string starting from F to S and cycling back through F till m letters have been counted. At this point, cross out the letter in FLAMES at which the count ends (say A). Restart counting from the next letter (here M) through the string FLMES. Repeat this till ﬁve of the letters of FLAMES have been crossed out and only one letter remains.  

The final letter that remains is the predicted nature of the relationship between the two people whose names were initially chosen, where F = “Friend”, L = “Love”, A = “Affection”, M = “Marriage”, E = “Enemy”, S = “Sibling”. The rationale (or the lack of it) for these interpretations is beyond the scope of this repository.

---

### Algorithm

**Step 1: ReadName Procedure**  
  1. Read two strings from the user and store it in `yourName` & `partnerName`, respectively  
  2. Remove spaces from both strings, if any  
  3. Convert both strings to lowercase (or uppercase)  
  4. Calculate the length of both strings and store in `yourNameLength` & `partnerNameLength` respectively  
	
**Step 2: EliminateCommonCharacters Procedure**  
  1. `FOR` `i` in every character in `yourName` till `yourNameLength`  
    1. `FOR` `j` in every character in `partnerName` till `partnerNameLength`  
      1. `IF` a character `yourName[i]` is equal to a character in `partnerName[j]` `THEN`  
        1. Remove the character from the strings `yourName` and `partnerName`  
        2. Break the inner loop as soon as a match is found, otherwise all instances of that character will be deleted  
      2. `END IF`
    2. `END FOR`  
  2. `END FOR`  
  3. Concatenate both strings and store it in `completeName` and calculate the length of it to be stored in `completeNameLength`  
	
**Step 3: FlamesCalculation Procedure**  
  1. Declare a string "FLAMES" with the variable `FLAMES` and store it's length of 6 in a variable `flamesLength`  
  2. Declare a variable `index` to store the index of the character to be striked out  
  3. WHILE length of `FLAMES` is not equal to 1, do the following:  
    1. `index <- completeNameLength % flamesLength`  
    2. `IF` (index == 0) `THEN`  
      3. Remove the last character of `FLAMES`  
    4. `ELSE`  
      5. Remove the character that `index` is currently pointing toward in `FLAMES`  
      6. Extract a substring of `FLAMES` from `(index) to end`, and another substring from `start to (index)`. Concatenate the two substrings and overwrite `FLAMES`  
    5. `END IF`  
    6. Decrement `flamesLength` by 1  
  4. `END WHILE`  
  5. At the end of the WHILE loop, the prediction will be the last remaining character of the `FLAMES`, which we will store in `flamesResult`  
	
**Step 4: DisplayResult Procedure**  
  1. `IF` `flamesResult` is  
    1. `F`, `THEN`  
      display "`yourName` & `partnerName` are friends!"  
    2. `L`, `THEN`  
      display "`yourName` & `partnerName` are lovers!"  
    2. `A`, `THEN`  
      display "`yourName` & `partnerName` are affectionate to each other!"  
    2. `M`, `THEN`  
      display "`yourName` & `partnerName` are or will be married!"  
    2. `E`, `THEN`  
      display "`yourName` & `partnerName` are enemies!"  
    2. `S`, `THEN`  
    display "`yourName` & `partnerName` are or are like siblings!"  
	2. `END IF`  

---

### Authors
> **Amruth A. Pillai** - [Facebook Profile](https://www.facebook.com/AmruthPillai)  
> *Email:* im.amruth(at)gmail.com  

> **Prabhu B.** - [Facebook Profile](https://www.facebook.com/prabhu.bond.5)  
> *Email:* im.prabhu(at)gmail.com  
