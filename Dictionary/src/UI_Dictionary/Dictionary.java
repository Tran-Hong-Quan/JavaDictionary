/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package UI_Dictionary;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Quan
 */
public class Dictionary {

    public static Dictionary instance;

    private List<Word> engToViet = new ArrayList<Word>();
    private List<Word> vietToEng = new ArrayList<Word>();

    public Dictionary() {

    }

    private Connection connect() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            //File temp = new File("dict_hh.db");
            //String url = "jdbc:sqlite:/" + temp.getAbsolutePath().replace("\\","\\\\");
            String url = "jdbc:sqlite:dict_hh.db";
            System.out.println(url);
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    private boolean readDatabase() {

        String selectEngData = "SELECT * FROM av";
        String selectVietData = "SELECT * FROM va";

        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(selectEngData);

            // loop through the result set  
            while (rs.next()) {
                int id = rs.getInt("id");
                String word = rs.getString("word");
                String pronounce = rs.getString("pronounce");
                String description = rs.getString("description");
                String html = rs.getString("html");
                engToViet.add(new Word(id, word, html, pronounce, description));
            }

            stmt = conn.createStatement();
            rs = stmt.executeQuery(selectVietData);

            // loop through the result set  
            while (rs.next()) {
                int id = rs.getInt("id");
                String word = rs.getString("word");
                String pronounce = rs.getString("pronounce");
                String description = rs.getString("description");
                String html = rs.getString("html");
                vietToEng.add(new Word(id, word, html, pronounce, description));
            }

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Word[] findAllEngWordContain(String find) {
        List<Word> words = new ArrayList<Word>();

        for (Word w : engToViet) {
            if (w.getWord().startsWith(find)) {
                words.add(w);
            }
        }

        Collections.sort(words, new Comparator<Word>() {
            @Override
            public int compare(Word s1, Word s2) {
                return s1.getWord().compareTo(s2.getWord());
            }
        });

        return words.toArray(new Word[0]);
    }

    public Word findEngWord(String find) {
        for (Word w : engToViet) {
            if (w.getWord().equals(find)) {
                return w;
            }
        }
        return null;
    }

    public Word findVietWord(String find) {
        for (Word w : vietToEng) {
            if (w.getWord().equals(find)) {
                return w;
            }
        }
        return null;
    }

    public Word[] findAllVietWordContain(String find) {
        List<Word> words = new ArrayList<Word>();

        for (Word w : vietToEng) {
            if (w.getWord().startsWith(find)) {
                words.add(w);
            }
        }

        Collections.sort(words, new Comparator<Word>() {
            @Override
            public int compare(Word s1, Word s2) {
                return s1.getWord().compareTo(s2.getWord());
            }
        });

        return words.toArray(new Word[0]);
    }

    public static void main(String[] args) {
        var dic = new Dictionary();
        dic.readDatabase();
        Dictionary.instance = dic;
        DictionaryFrame dicFrame = new DictionaryFrame();
        dicFrame.setVisible(true);
    }
}
