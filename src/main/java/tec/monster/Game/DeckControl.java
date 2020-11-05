package tec.monster.Game;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import tec.monster.DeckStructure.Deck;
import tec.monster.HandStructure.Hand;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class DeckControl {
    private Deck deck;
    private ArrayList<Cards> cartas;
    private static ObjectMapper objectMapper = getDefaultObjectMapper();

    private static ObjectMapper getDefaultObjectMapper(){
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        return defaultObjectMapper;
    }

    public DeckControl(){
        this.deck = new Deck();
        try {
            this.cartas = objectMapper.readValue(new File("Cards.json"),
                    objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Cards.class));
        }catch (IOException e) {
            System.out.println("Ocurrió un error al cargar el archivo json");
        }
    }

    public Deck GenerateDeck() {
        Collections.shuffle(this.cartas);

        for (Cards carta:this.cartas) {
            if (this.deck.getSize()>15){
                break;
            }
            this.deck.Push(carta);
        }
        return deck;
    }


    public ArrayList<Cards> getCartas() {
        return cartas;
    }

    public static void main(String[] args) {
        DeckControl d =new DeckControl();
        Deck dd = d.GenerateDeck();
        dd.ShowId();
        System.out.println("----------------------------");

        Hand mano = new Handcontrol().GenerateHand(dd);

    }
}
