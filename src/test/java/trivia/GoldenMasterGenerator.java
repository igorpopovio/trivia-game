package trivia;

import org.junit.Ignore;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;

public class GoldenMasterGenerator {
    @Test
    @Ignore("Should be run only when generating the golden master")
    public void generateGoldenMaster() throws Exception {
        for (int seed = 0; seed < 100; seed++)
            saveGoldenMasterForSeed(seed);
    }

    public void saveGoldenMasterForSeed(int seed) throws FileNotFoundException {
        String filename = String.format("golden-master/seed-%d.txt", seed);
        System.setOut(new PrintStream(filename));
        GameRunner.rand = new Random(seed);
        GameRunner.main(null);
    }
}
