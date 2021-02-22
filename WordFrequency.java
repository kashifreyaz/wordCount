package com.cognitree.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class WordFrequency {

	public static void main(String[] args) {

		HashMap<String, Integer> occurenceMap = new HashMap<String, Integer>();
		Scanner input = new Scanner(System.in);
		System.out.println("Please provide full path of file: ");
		String path = input.nextLine();
		int count=0;
		String line=null;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(path));
			System.out.println("Please provide number of output needed: ");
			if(input.hasNextInt()) {
				count=input.nextInt();
			}
			if(count<=0) {
				System.out.println("Provided number is not valid");
			}
			input.close();
		} catch (FileNotFoundException e) {
			System.out.println("Provided path is not valid");
			input.close();
		}
		if(reader!=null&&count>0) {
			try {
				while (reader!=null&&(line=reader.readLine()) != null) {
					String[] WordArray = line.toLowerCase().split("[\\s\\n.,()?”“â€œ–-]");
					for (String singleWord : WordArray) {
						if (!singleWord.equals("")) {
							if (occurenceMap.containsKey(singleWord)) {
								occurenceMap.put(singleWord, occurenceMap.get(singleWord) + 1);
							}
							else {
								occurenceMap.put(singleWord, 1);
							}
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(occurenceMap.size()>count) {
			TreeMap<Integer, TreeSet<String>> finalMap =sortByKeyAndValue(occurenceMap);
			List<String> sortedListByKeyAndValueOfMap = new ArrayList<String>();
			for (Entry<Integer, TreeSet<String>> entry : finalMap.entrySet()) {
				sortedListByKeyAndValueOfMap.addAll(entry.getValue());
			}
				System.out.println("Most frequent");
				for(int i=sortedListByKeyAndValueOfMap.size()-1;i>sortedListByKeyAndValueOfMap.size()-(count+1);i--) {
					if(i==sortedListByKeyAndValueOfMap.size()-count) {
						System.out.print("("+sortedListByKeyAndValueOfMap.get(i)+":"+occurenceMap.get(sortedListByKeyAndValueOfMap.get(i))+")");
						System.out.println("");
					}else {
						System.out.print("("+sortedListByKeyAndValueOfMap.get(i)+":"+occurenceMap.get(sortedListByKeyAndValueOfMap.get(i))+"), ");
					}
				}
				
				System.out.println("Least frequent");
				for(int i=0;i<count;i++) {
					if(i==count-1) {
						System.out.print("("+sortedListByKeyAndValueOfMap.get(i)+":"+occurenceMap.get(sortedListByKeyAndValueOfMap.get(i))+")");
					}else {
						System.out.print("("+sortedListByKeyAndValueOfMap.get(i)+":"+occurenceMap.get(sortedListByKeyAndValueOfMap.get(i))+"), ");
					}
				}
			}else {
				System.out.println("Output count is more than repeated word");
			}
		}
	}
	
	public static TreeMap<Integer, TreeSet<String>> sortByKeyAndValue(HashMap<String, Integer> occurenceMap) 
	{
		TreeMap<Integer, TreeSet<String>> finalTemp = new TreeMap<Integer, TreeSet<String>>();
		for (Map.Entry<String, Integer> occuranceOfSingleWord : occurenceMap.entrySet()) {
			TreeSet<String> set= new TreeSet<String>();
			if(finalTemp.containsKey(occuranceOfSingleWord.getValue())) {
				set =finalTemp.get(occuranceOfSingleWord.getValue());
				set.add(occuranceOfSingleWord.getKey());
				finalTemp.put(occuranceOfSingleWord.getValue(), set);
			}else {
				set.add(occuranceOfSingleWord.getKey());
				finalTemp.put(occuranceOfSingleWord.getValue(), set);
			}
		}
		return finalTemp; 
	} 
}


