# Keyword Counter

## Overview
The program implements the Keyword counter program detailed in the project description.

## Running
To execute, enter:
```
>> java keywordcounter <file name>
```
where,
- *file name* - the name of the input file following the format described in the project description.

After the program terminates, **output.txt** is generated. The output file is overwriten each time the program is executed.

## Project Details

### Problem Statement
Create a system to count the most popular keywords used in a text file. The *n* most popular keywords must can be requested at any time. Keywords are given together with their frequencies. The following data structures are required for implementation:
- Max Fibonacci heap: to keep track of the frequencies of keywords
- Hash Table: keywords should be used as keys for the hash table and value is the pointer to the corresponding node in the Fibonacci heap.

The increase key operation is performaed as keywords appear in the input keywords steam. 

### Input format
The input file will have three types of lines:
1) keyword - The keyword begines with a *$* sign. Additionally, an integer will appear after the keyword, which represents the count (frequency) of the keyword (there is a space between this and the integer). 
2) query - A query is an integer number (n) without a *$* sign in the beginning. The top most *n* keywords are appended to the output file. 
3) stop - When *stop* (without a *$* sign) appears in the input stream, the program should terminate.

#### Example
```
$wikipedia 5
$youtube 3
$wikipedia 10
$amazon 2
$tesla 4
$weather 2
$wikipedia 6
$youtube 8
$tesla 2
$news 2
$wikipedia 12
$youtube 11
$amazon 6
5
$wikipedia 12
$amazon 2
$stop 3
$playing 4
$gmail 15
$drawing 3
$ebay 12
$netflix 6
$cnn 5
4
stop
```

