import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Card class test
 */
public class CardTest {

    @Test
    public void shouldCreateCardObject() {
        //Given
        String name = "Kowalski";
        String surname = "Janusz";
        String phone = "500500500";

        Card expectedCard = new Card("Kowalski", "Janusz", "500500500");

        //When
        Card createdCard = new Card(name, surname, phone);

        //Then
        assertThat(createdCard)
                .as("Created card should be exactly the same as expected")
                .isEqualToComparingFieldByField(expectedCard);

    }

}
