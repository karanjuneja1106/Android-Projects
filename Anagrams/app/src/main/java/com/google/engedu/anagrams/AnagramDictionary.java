package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.SortedSet;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private int level=0;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    private ArrayList<String> wordList;
    private HashSet<String> wordSet;
    private HashMap<String,ArrayList<String>> lettersToWord;
    private HashMap<Integer,ArrayList<String>> sizeToWords;

    private String sortString(String str){
        char[] charArray=str.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }
    public AnagramDictionary(InputStream wordListStream) throws IOException {
        wordList=new ArrayList<>();
        wordSet=new HashSet<>();
        lettersToWord=new HashMap<>();
        sizeToWords=new HashMap<>();
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        String line;

        while((line = in.readLine()) != null) {
            String word = line.trim();
            wordList.add(word);
            wordSet.add(word);
            String key=sortString(word);
            if(!lettersToWord.containsKey(key))
            {
                ArrayList<String> list=new ArrayList<>();
                list.add(word);
                lettersToWord.put(key,list);
            }
            else {
                lettersToWord.get(key).add(word);
            }
            if(!sizeToWords.containsKey(word.length())){
                ArrayList<String> list=new ArrayList<>();
                list.add(word);
                sizeToWords.put(word.length(),list);
            }
            else
            {
                sizeToWords.get(word.length()).add(word);
            }
        }
    }

    public boolean isGoodWord(String word, String base) {
        return wordSet.contains(word)&& !word.contains(base);
    }

    public ArrayList<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<String> list;
        for(char c='a';c<='z';c++)
        {
            String newWord=sortString(word+c);
            if(lettersToWord.containsKey(newWord)){
                list=lettersToWord.get(newWord);
                for(String s:list){
                    if(isGoodWord(s,word))
                        result.add(s);
                }
            }
        }
        return result;
    }

    public String pickGoodStarterWord() {
        ArrayList<String> list=sizeToWords.get(DEFAULT_WORD_LENGTH+level);
        String word=list.get(random.nextInt(list.size()));
        while(getAnagramsWithOneMoreLetter(word).size()<MIN_NUM_ANAGRAMS)
        {
            word=list.get(random.nextInt(list.size()));
        }
        if(level+DEFAULT_WORD_LENGTH<MAX_WORD_LENGTH)
            level++;
            return word;
    }
}
