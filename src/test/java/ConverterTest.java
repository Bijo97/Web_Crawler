import org.jsoup.Jsoup;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.mockito.internal.util.collections.ListUtil;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

public class ConverterTest {

    @Rule
    public final TestName testName = new TestName();

    @Before
    public void setup() throws Exception {
        System.out.println("Setup for test '" + testName.getMethodName() + "'");
    }

    @Test
    public void shouldReturnEmptyJSONStringWhenMediaObjectIsNull(){
        // ARRANGE
        // Mocked media as dummy object
        Media mockedMedia = mock(Media.class);

        // Create SUT
        Converter SUT = new Converter();

        // ACT
        String result = SUT.convertMedia(mockedMedia);

        // ASSERT
        assertEquals("Should return empty JSON string when Media object is null", "", result);
    }

    @Test
    public void shouldReturnJSONStringOfTheMediaObject() throws WrongFormatException, NoDataItemsException {
        // ARRANGE
        String expected = "{\"format\":\"Bluray\",\"genre\":\"Drama\",\"name\":\"Pride and Prejudice\",\"year\":\"1963\"}";
        Media m = new Media("Drama","Bluray","1963","Pride and Prejudice");

        // Create SUT
        Converter SUT = new Converter();

        // ACT
        String result = SUT.convertMedia(m);

        // ASSERT
        assertEquals("Should return correct JSON string", expected, result);
    }

    @Test
    public void shouldReturnJSONStringOfMetadataParameters() throws WrongFormatException, NoDataItemsException {
        // ARRANGE
        String _strategy = "DFS";
        int _numberOfPage = 5;
        int _timeElapsed = 600;
        int _searchDepth = 3;

        String expected = "{\"numberOfPage\":\"5\",\"searchDepth\":\"3\",\"strategy\":\"DFS\",\"timeElapsed\":\"600\"}";

        // Create SUT
        Converter SUT = new Converter();

        // ACT
        String result = SUT.convertMetadata(_strategy,_numberOfPage,_timeElapsed,_searchDepth);

        // ASSERT
        assertEquals("Should return correct JSON string", expected, result);
    }
}
