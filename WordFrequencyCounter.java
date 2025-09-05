import java.util.*;

public class WordFrequencyCounter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        System.out.println("Paste paragraph text (press Enter on an empty line to finish):");
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.trim().isEmpty()) break;
            sb.append(line).append(' ');
        }

        String text = sb.toString().toLowerCase().replaceAll("[^a-z0-9']+", " ");
        String[] words = text.trim().isEmpty() ? new String[0] : text.trim().split("\\s+");

        Map<String,Integer> freq = new HashMap<>();
        for (String w : words) {
            if (!w.isEmpty()) freq.put(w, freq.getOrDefault(w, 0) + 1);
        }

        if (freq.isEmpty()) {
            System.out.println("No words found.");
            return;
        }

        String best = null;
        int bestCount = 0;
        for (Map.Entry<String,Integer> e : freq.entrySet()) {
            String w = e.getKey();
            int c = e.getValue();
            if (c > bestCount || (c == bestCount && (best == null || w.compareTo(best) < 0))) {
                best = w; bestCount = c;
            }
        }

        System.out.println("Most frequent word: \"" + best + "\" (" + bestCount + " times)");
        sc.close();
    }
}
