import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String BLANK_SPACE = "\\s+";

    public String getResult(String sentence) {


        if (isSingleWord(sentence)) {
            return sentence + " 1";
        } else {

            try {
                //split the input string with 1 to n pieces of spaces
                List<WordInfo> wordInfoList = calculateWordFrequency(sentence);

                //get the map for the next step of sizing the same word
                sortWordInfo(wordInfoList);

                StringJoiner joiner = new StringJoiner("\n");
                for (WordInfo w : wordInfoList) {
                    String s = w.getWord() + " " + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }

    private void sortWordInfo(List<WordInfo> wordInfoList) {
        wordInfoList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());
    }

    private List<WordInfo> calculateWordFrequency (String sentence) {
        List<String> words = Arrays.asList(sentence.split(BLANK_SPACE));
        List<String> distinctWords = words.stream().distinct().collect(Collectors.toList());

        List<WordInfo> wordInfos = new ArrayList<>();

        distinctWords.forEach(distinctWord -> {
            int count = (int) words.stream()
                    .filter(word -> word.equals(distinctWord))
                    .count();

            wordInfos.add(new WordInfo(distinctWord, count));
        });

        return wordInfos;
    }

    private boolean isSingleWord(String sentence) {
        return 1 == sentence.split(BLANK_SPACE).length;
    }
}
