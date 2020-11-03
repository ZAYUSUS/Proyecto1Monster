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

    public static <A> A fromJson(JsonNode node, Class<A> aClass) throws JsonProcessingException{
        return objectMapper.treeToValue(node,aClass);
    }
    public static JsonNode toJson(Object a){
        return objectMapper.valueToTree(a);
    }
}
