package edu.ntnu.iir.bidata;
import java.util.ArrayList;

public class Diary {
    private String diaryName;
    private ArrayList<DiaryEntry> diaryEntries; 

    public Diary (String diaryName){
        this.diaryName = diaryName;
        this.diaryEntries = new ArrayList<>();
    }
    // Getter
    public String getDiaryName (){
        return diaryName;
    }
    public ArrayList<DiaryEntry> getEntries (){
        return diaryEntries;
    }
    // Setters/main
    public void addEntry (DiaryEntry entry){
        diaryEntries.add(entry);
    }
    // ToString
    @Override
    public String toString(){
        System.out.println("---------------------------");
        diaryEntries.forEach(entry -> {
            System.out.println("#-------#");
            System.out.println(entry.getTitle());
            System.out.println(entry.getReleaseDate());
            System.out.println("");
            System.out.println(entry.getContent());
            System.out.println("#-------#");

            try {
                Thread.sleep(1000/diaryEntries.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("---------------------------");
        return "Antall innlegg: " + diaryEntries.size();
    }

}
