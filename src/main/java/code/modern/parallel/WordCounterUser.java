package code.modern.parallel;

import static java.util.stream.StreamSupport.stream;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WordCounterUser {
    private static final String SENTENCE = " Nel mezzo del cammin di nostra vita " +
            "mi ritrovai in una selva oscura" +
            " che la dritta via era smarrita ";

    public static void main(final String[] args) {
        final Stream<Character> stream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
        System.out.println("Found " + countWords(stream) + " words");

        final Stream<Character> parallelStream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
        System.out.println("Found " + countWords(parallelStream.parallel()) + " words. This is wrong.");

        final Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        final Stream<Character> spliteratorStream = stream(spliterator, true);
        System.out.println("Found " + countWords(spliteratorStream) + " words");
    }

    private static int countWords(final Stream<Character> stream) {
        final WordCounter wordCounter = stream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
        return wordCounter.getCounter();
    }
}
