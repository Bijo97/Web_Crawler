import org.jsoup.Jsoup;
import org.junit.Test;
import org.mockito.internal.util.collections.ListUtil;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

public class ConverterTest {
    @Test
    public void shouldReturnEmptyJSONStringWhenMediaObjectIsNull(){
        // ARRANGE
        Media mockedMedia = mock(Media.class);

        // Create SUT
        Converter SUT = new Converter();

        // ACT
        String result = SUT.convert(mockedMedia);

        // ASSERT
        assertEquals("Should return empty JSON string when Media object is null", "", result);
    }

}
