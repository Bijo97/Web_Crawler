import com.owlike.genson.Genson;
import org.hamcrest.core.IsNull;

public class Converter{

        public String convertMedia(Media m) {
            if (m.getFormat() != null && m.getGenre() != null && m.getName() != null && m.getYear() != null){
                Genson genson = new Genson();
                String json = genson.serialize(m);
                return json;
            }else{
                return "";
            }
        }

        public String convertMetadata(String _strategy,int _numberOfPage,int _timeElapsed,int _searchDepth) {

            if (_strategy != null) {
                String json = "{\"numberOfPage\":\"" + _numberOfPage +
                        "\",\"searchDepth\":\"" + _searchDepth +
                        "\",\"strategy\":\"" + _strategy +
                        "\",\"timeElapsed\":\"" + _timeElapsed + "\"}";
                ;

                return json;
            }else{
                return "";
            }
        }
}
