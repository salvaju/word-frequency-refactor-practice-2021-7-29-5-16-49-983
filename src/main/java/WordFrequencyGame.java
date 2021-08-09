import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String BLANK_SPACE = "\\s+";
    public static final String NEW_LINE = "\n";

    public String getResult(String sentence) {


        if (isSingleWord(sentence)) {
            return sentence + " 1";
        } else {

            try {
                List<WordInfo> wordInfoList = calculateWordFrequency(sentence);
                sortWordInfo(wordInfoList);

                return mergeWordInfos(wordInfoList);
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }

    private String mergeWordInfos(List<WordInfo> wordInfoList) {
        return wordInfoList.stream()
                .map(this::generateWordWithWordCount)
                .collect(Collectors.joining(NEW_LINE));
    }

    private String generateWordWithWordCount(WordInfo wordInfo) {
        return wordInfo.getWord() + " " + wordInfo.getWordCount();
    }

    private void sortWordInfo(List<WordInfo> wordInfoList) {
        wordInfoList.sort((firstWordInfo, secondWordInfo) -> secondWordInfo.getWordCount() - firstWordInfo.getWordCount());
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
