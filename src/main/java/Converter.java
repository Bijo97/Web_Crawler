import com.owlike.genson.Genson;

public class Converter{

        public String convert(Media m) {
            Genson genson = new Genson();

            String json = genson.serialize(m);
//            genson.deserialize(json, String[].class);

            return json;
        }
}
