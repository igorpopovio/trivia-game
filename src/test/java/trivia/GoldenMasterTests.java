package trivia;

import org.junit.Ignore;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;

public class GoldenMasterTests {
    @Test
    @Ignore("Should be run only when generating the golden master")
    public void generateGoldenMaster() throws Exception {
        for (int seed = 0; seed < 100; seed++)
            saveGoldenMasterForSeed(seed);
    }

    public void saveGoldenMasterForSeed(int seed) throws FileNotFoundException {
        System.setOut(new PrintStream(getFilenameForSeed(seed)));
        runGameForSeed(seed);
    }

    public String getFilenameForSeed(int seed) {
        return String.format("golden-master/seed-%d.txt", seed);
    }

    public void runGameForSeed(int seed) {
        GameRunner.rand = new Random(seed);
        GameRunner.main(null);
    }
}
