import com.thoughtworks.xstream.XStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Converter class test
 */
public class ConverterTest {

    private Card card;

    @BeforeEach
    void beforeEach() {
        card = new Card("Janusz", "Kowalski", "500500500");
        System.out.println("New Card object has been created");
    }

    @AfterEach
    void afterEach() {
        card = null;
        System.out.println("Card object has been deleted");
    }

    /**
     * Uses createCard method in Converter to create Card object from given array of strings
     */
    @Test
    void shouldConverterCreateCardObjectFromGivenArray() {
        //Given
        String[] arrayOfStrings = {"Janusz", "Kowalski", "500500500"};
        Card expectedCard = card;

        //When
        Card createdCard = Converter.createCard(arrayOfStrings);

        // Then
        assertThat(createdCard)
                .as("Two objects should have the same pools")
                .isEqualToComparingFieldByField(expectedCard);

    }

    /**
     * Tests converting Card object into XML
     */
    @Test
    void SimpleXStreamTest() {

        XStream xStream = new XStream();
        xStream.alias("card", Card.class);

        String xml = xStream.toXML(card);

        System.out.println(xml);
    }
}
