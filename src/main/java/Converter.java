import au.com.bytecode.opencsv.CSVReader;
import com.thoughtworks.xstream.XStream;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Converter {

    /**
     * Converts Cards objects into XML files
     * @throws IOException
     */
    public void convert() throws IOException{
        List<Card> cardsToConvert = readCardFromCsvFile();
        String xml = convertToXML(cardsToConvert);

        /**
         * Out to console
         */
        System.out.println(xml);

        /**
         * Out to xml file
         */
        PrintWriter out = new PrintWriter("xmlCard.xml");
        out.println(xml);
        out.close();
    }

    /**
     * Reads from CSV file
     * @return ArrayList of Cards objects
     * @throws IOException
     * @throws RuntimeException
     */
    private static List<Card> readCardFromCsvFile() throws IOException{
        List<Card> cards = new ArrayList<>();

        CSVReader reader = new CSVReader(new FileReader("cards.csv"), ',', '"', 0);

        /**
         * Read CSV line by line and use the string array to create card
         */
        String[] nextLine;

        int column = 0;

        while ((nextLine = reader.readNext()) != null) {
            column++;
            int rows = 0;
            for (String row: nextLine) {
                rows++;
                if (row.isBlank()) {
                    throw new RuntimeException("CSV file not filled correctly. " +
                            "Fill data in column " + column + " row " + rows);
                }
            }
            if (nextLine != null) {
                Card card = createCard(nextLine);
                cards.add(card);
            }
        }
        return cards;
    }

    /**
     * Creates Card object
     * @param data
     * @return
     */
    public static Card createCard(String[] data) {
        String name = data[0];
        String surname = data[1];
        String phone = data[2];

        /**
         * Create and return card from data
         */
        return new Card(name, surname, phone);
    }

    /**
     * Returns xml representation of object data via XStream api.
     * @param file object to convert
     * @return xml representation
     * @throws FileNotFoundException
     */
    public static String convertToXML(Object file) throws FileNotFoundException {
        XStream xstream = new XStream();
        xstream.alias("card", Card.class);
        String xml = xstream.toXML(file);
        return xml;
    }
}
