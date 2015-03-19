/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextmingPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipFile;

/**
 *
 * @author Thunderkerrigan
 */
public class TextMiningParser {
    String[] skillsArray;
    String[] jobsArray;
    String[] SchoolsArray;
    
    HashMap<String, String[]> wordsMap;
    
    public TextMiningParser(){
        skillsArray = new String[]{"java", "objective-c", "uml", "microsoft", "sql"};
        jobsArray = new String[]{"apple", "sfr", "readbooks", "altran"};
        SchoolsArray = new String[]{"supinfo", "chartreux", "bts" };
    }
    
    public void startHashingTextFile(File f){
        
        try {
 
            Scanner scanner = new Scanner(f);
            
            while (scanner.hasNextLine()) {
                this
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    private boolean containsSkills(String s){
        for (int i = 0; i < skillsArray.length; i++) {
            if (s.contains(skillsArray[i])) {
                wordsMap.
            }
        }
    };
}
