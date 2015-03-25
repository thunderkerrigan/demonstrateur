/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextminingPackage;

import GraphicsElements.WordCloudContainer;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipFile;

/**
 *
 * @author Thunderkerrigan
 */
public class TextMiningParser {
    String contentOfOfile;
    String[] skillsArray;
    String[] jobsArray;
    String[] SchoolsArray;
    String[] namesArray;
    
    HashMap<String, ArrayList<String>> wordsMap;

    /**
     *
     * @return
     */
    public String getContentOfOfile() {
        return contentOfOfile;
    }
    
    /**
     *
     */
    public TextMiningParser(){
        skillsArray = new String[]{"java", "objective-c", "uml", "microsoft", "sql", "ios", "coredata", "c"};
        jobsArray = new String[]{"apple", "sfr", "readbooks", "altran"};
        SchoolsArray = new String[]{"supinfo", "chartreux", "bts" };
        namesArray = new String[]{"Joseph", "Pasqualini"};
        wordsMap = new HashMap<>();
        wordsMap.put("schools", new ArrayList<>());
        wordsMap.put("jobs", new ArrayList<>());
        wordsMap.put("skills", new ArrayList<>());
        wordsMap.put("names", new ArrayList<>());
    }
    
    /**
     *
     * @param f
     */
    public void startHashingTextFile(File f){
        
        try {
 
            Scanner scanner = new Scanner(f);
            scanner.useDelimiter("\\Z");
            contentOfOfile = scanner.next();
            scanner = new Scanner(contentOfOfile);
            System.out.println("scanner:"+scanner.delimiter());
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                this.containsJobs(line);
                this.containsSkills(line);
                this.containsSchools(line);
                this.containsNames(line);
                System.out.println("next line is :"+scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    private boolean containsSkills(String s){
        System.out.println("string to match:"+s);
        ArrayList<String> skills = wordsMap.get("skills");
        Boolean didcontainsSkills = false;
        for (String skill : skillsArray) {
            System.out.println("try to match: " + skill);
            if (!skills.contains(skill) && s.matches("(?i:.*" + skill + ".*)")) {
                System.out.println("success match!!!");
                skills.add(skill);
                didcontainsSkills = true;
            }
        }
        wordsMap.replace("skills", skills);
        return didcontainsSkills;
    }
    
    private boolean containsSchools(String s){
        System.out.println("string to match:"+s);
        ArrayList<String> schools = wordsMap.get("schools");
        Boolean didcontainsSchools = false;
        for (String school : SchoolsArray) {
            System.out.println("try to match: " + school);
            if (!schools.contains(school) && s.matches("(?i:.*" + school + ".*)")) {
                System.out.println("success match!!!");
                schools.add(school);
                didcontainsSchools = true;
            }
        }
        wordsMap.replace("schools", schools);
        return didcontainsSchools;
    }
       
    private boolean containsJobs(String s){
        System.out.println("string to match:"+s);
        ArrayList<String> jobs = wordsMap.get("jobs");
        Boolean didcontainsJobs = false;
        for (String job : jobsArray) {
            System.out.println("try to match: " + job);
            if (!jobs.contains(job) && s.matches("(?i:.*" + job + ".*)")) {
                System.out.println("success match!!!");
                jobs.add(job);
                didcontainsJobs = true;
            }
        }
        wordsMap.replace("jobs", jobs);
        return didcontainsJobs;
    }
    
    private boolean containsNames(String s){
        System.out.println("string to match:"+s);
        ArrayList<String> names = wordsMap.get("names");
        Boolean didcontainsJobs = false;
        for (String name : namesArray) {
            System.out.println("try to match: " + name);
            if (!names.contains(name) && s.matches("(?i:.*" + name + ".*)")) {
                System.out.println("success match!!!");
                names.add(name);
                didcontainsJobs = true;
            }
        }
        wordsMap.replace("names", names);
        return didcontainsJobs;
    }
    
    /**
     *
     * @return
     */
    public List<WordCloudContainer> GetWordCloud(){
        List<WordCloudContainer> wordCloud = new ArrayList<>();
        wordsMap.entrySet().stream().forEach((entrySet) -> {
            String key = entrySet.getKey();
            ArrayList<String> value = entrySet.getValue();
            value.stream().forEach((v) -> {
                System.out.println("value:"+v);
            });
            wordCloud.add(new WordCloudContainer(Color.red, key));
            value.stream().forEach((v) -> {
                wordCloud.add(new WordCloudContainer(Color.blue, v));
            });
        });
        int r = 0;
        int c = 0;
        int w = 250;
        int h = 60;
        for (WordCloudContainer wc : wordCloud) {
            if (c<3) {
                wc.setBounds(c*w, r*h, w, h);
                c++;
            }
            else{
                c = 0;
                r++;
            }
        }
            return wordCloud;
    }
}
