package tec.monster.Jsonconf;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import tec.monster.Connections.Paquete;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;

/**
 * Clase utilizada para serializar y deserializar clases en strings y volverlos
 * a objetos de Jva
 */

public class Json implements Serializable {
    private static ObjectMapper objectMapper = getDefaultObjectMapper();
    private static StringWriter escritor = new StringWriter();

    private static ObjectMapper getDefaultObjectMapper(){
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        return defaultObjectMapper;
    }
    /***
     * Encargada de convertir un string a una clase.
     *
     * @param jsonSource
     * @return
     * @throws JsonProcessingException
     */
    public static JsonNode parse(String jsonSource) throws JsonProcessingException{
        return objectMapper.readTree(jsonSource);
    }

    /**
     * Convierte un Jsonnode a un tipo de Clase compatible
     * @param node objeto parseado tipo Jsonnode
     * @param aClass clase a la que se desea convertir
     * @param <A> clase generica
     * @return una clase según se le haya indicado
     * @throws JsonProcessingException
     */
    public static Paquete fromJson(String nodo) throws JsonProcessingException{
        return objectMapper.readValue(nodo,Paquete.class);
    }
    public static String toJson(Paquete a) throws IOException {
        String paqt = objectMapper.writeValueAsString(a);
        return paqt;
    }

}
