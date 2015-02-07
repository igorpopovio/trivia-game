package trivia;

import org.junit.Ignore;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Random;

public class GoldenMasterTests {
    @Test
    @Ignore("Should be run only when generating the golden master")
    public void generateGoldenMaster() throws Exception {
        for (int seed = 0; seed < 100; seed++) {
            String filename = String.format("golden-master/seed-%d.txt", seed);
            System.setOut(new PrintStream(filename));
            runGameForSeed(seed);
        }
    }

    public void runGameForSeed(int seed) {
        GameRunner.rand = new Random(seed);
        GameRunner.main(null);
    }
}
