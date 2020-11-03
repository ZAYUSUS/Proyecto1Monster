package tec.monster.Game;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;

public class DeckControl {
    private Deck deck;
    private ArrayList<Cards> cartas;
    private static ObjectMapper objectMapper = getDefaultObjectMapper();

    private static ObjectMapper getDefaultObjectMapper(){
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        return defaultObjectMapper;
    }

    public Deck GenerateDeck() {
        deck = new Deck();
        try {
            cartas = objectMapper.readValue(new File("Cards.json"),
                    objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Cards.class));
        }catch (IOException e) {
            System.out.println("Ocurrió un error al cargar el archivo json");
        }

        for (Cards carta:cartas){
            deck.Push(carta);
        }
        deck.ShowId();
        return deck;
    }

    public static void main(String[] args) {
        DeckControl d =new DeckControl();
        Deck dd = d.GenerateDeck();
        dd.ShowId();

    }
}
