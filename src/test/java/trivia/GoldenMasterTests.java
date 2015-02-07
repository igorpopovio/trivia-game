package trivia;

import junitx.framework.FileAssert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.PrintStream;
import java.util.Random;

import static java.lang.String.format;

public class GoldenMasterTests {
    @Test
    @Ignore("Should be run only when generating the golden master")
    public void generateGoldenMaster() throws Exception {
        for (int seed = 0; seed < 100; seed++) {
            File goldenMasterOutput = new File(format("golden-master/seed-%d.txt", seed));
            System.setOut(new PrintStream(goldenMasterOutput));
            runGameForSeed(seed);
        }
    }

    public void runGameForSeed(int seed) {
        GameRunner.rand = new Random(seed);
        GameRunner.main(null);
    }

    @Test
    public void isCurrentOutputSameAsGoldenMaster() throws Exception {
        for (int seed = 0; seed < 100; seed++) {
            File currentOutput = new File(format("current-output/seed-%d.txt", seed));
            File goldenMasterOutput = new File(format("golden-master/seed-%d.txt", seed));
            System.setOut(new PrintStream(currentOutput));
            runGameForSeed(seed);

            FileAssert.assertEquals(goldenMasterOutput, currentOutput);
        }
    }
}
