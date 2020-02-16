import java.io.*;

public class Main{
    public static void main (String [] args){
        File code1 = new File("test_program1.java");
        String[] first = new String[1000];
        String[] second = new String[1000];
        String[] words1 = new String[1000];
        String[] words2 = new String[1000];
        int i = 0;
        int y = 0;

        System.out.println("\nCODE 1");
        try (BufferedReader br = new BufferedReader(new FileReader(code1))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                first[i] = line;
                i++;
                String[] words = line.split("\\s+");
                for (int x = 0; x < words.length; x++) {
                    words1[y] = words[x];
                    y++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nCODE 2");
        y = 0;
        File code2 = new File("test_program2.java");
        try (BufferedReader br = new BufferedReader(new FileReader(code2))) {
            String line;
            int j = 0;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                second[j] = line;
                j++;
                String[] words = line.split("\\s+");
                for (int x = 0; x < words.length; x++) {
                    words2[y] = words[x];
                    y++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        float x = 0;
        for (int z = 0; z < i; z++) {
            if (first[z].equals(second[z])) {
                x++;
            }
        }

        float a = 0;
        for (int z = 0; z < i; z++) {
            if (words1[z].equals(words2[z])) {
                a++;
            }
        }

        //float perWord = (a/y) * 100;
        float perLine = (x/i) * 100;
        System.out.println("\nSimilarity Percentage (by line): " + perLine + "%");
        //System.out.println("Similarity Percentage (by word): " + perWord + "%");
    }
}
