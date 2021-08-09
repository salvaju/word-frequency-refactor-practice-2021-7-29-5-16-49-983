import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String WHITE_SPACES = "\\s+";
    public static final String NEW_LINE = "\n";
    public static final String BLANK_SPACE = " ";
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String sentence) {

        try {
            List<WordInfo> wordInfoList = calculateWordFrequency(sentence);
            sortWordInfo(wordInfoList);
            return mergeWordInfos(wordInfoList);
        } catch (Exception e) {
            return CALCULATE_ERROR;
        }
    }

    private List<WordInfo> calculateWordFrequency(String sentence) {
        List<String> words = Arrays.asList(sentence.split(WHITE_SPACES));
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

    private void sortWordInfo(List<WordInfo> wordInfoList) {
        wordInfoList.sort(Comparator.comparingInt(WordInfo::getWordCount).reversed());
    }

    private String mergeWordInfos(List<WordInfo> wordInfoList) {
        return wordInfoList.stream()
                .map(this::generateWordWithWordCount)
                .collect(Collectors.joining(NEW_LINE));
    }

    private String generateWordWithWordCount(WordInfo wordInfo) {
        return wordInfo.getWord() + BLANK_SPACE + wordInfo.getWordCount();
    }
}
