package tec.monster.Jsonconf;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

public class Json {
    private static ObjectMapper objectMapper = getDefaultObjectMapper();

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
    //ejemplo: Cards card = Json.fromJson(node.get("elemento en archivo"),Cards.class);
    public static <A> A fromJson(JsonNode node, Class<A> aClass) throws JsonProcessingException{
        return objectMapper.treeToValue(node,aClass);
    }
    public static JsonNode toJson(Object a){
        return objectMapper.valueToTree(a);
    }

    public static String stringify(JsonNode node) throws JsonProcessingException {
        return generateString(node,false);
    }
    public static String prettyPrint(JsonNode node) throws JsonProcessingException{
        return generateString(node,true);
    }
    //ppara m'as atributos
    private static String generateString(JsonNode node, boolean pretty) throws JsonProcessingException {
        ObjectWriter objectWriter = objectMapper.writer();

        if (pretty)
            objectWriter = objectMapper.writer(SerializationFeature.INDENT_OUTPUT);
        return objectWriter.writeValueAsString(node);
        //solo para un atributo
    }
}
