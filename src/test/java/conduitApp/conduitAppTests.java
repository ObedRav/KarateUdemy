package conduitApp;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import net.masterthought.cucumber.ReportBuilder;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;
import java.io.File;
import net.masterthought.cucumber.Configuration;
import org.apache.commons.io.FileUtils;

class conduitAppTests {

    /**
     * The testParallel function runs Cucumber tests in parallel with 5 threads and generates a report,
     * asserting that there are no failures.
     */
    @Test
    void testParallel() {
        Results results = Runner.path("classpath:conduitApp")
                .outputCucumberJson(true)
                .parallel(5);
        generateReport(results.getReportDir());
        assertTrue(results.getFailCount() == 0, results.getErrorMessages());
    }

    /**
     * The function generates a report using JSON files from a specified directory and saves it in the
     * target directory with the name "conduitApp".
     * 
     * @param karateOutputPath The karateOutputPath parameter is the path to the directory where the
     * Karate test execution results in JSON format are stored.
     */
    public static void generateReport(String karateOutputPath) {
        Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[] {"json"}, true);
        List<String> jsonPaths = new ArrayList<>(jsonFiles.size());
        jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
        Configuration config = new Configuration(new File("target"), "conduitApp");
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();
    }

}
