import com.owlike.genson.Genson;

public class Converter{

        public String convertMedia(Media m) {
            Genson genson = new Genson();

            String json = genson.serialize(m);

            return json;
        }

        public String convertMetadata(String _strategy,int _numberOfPage,int _timeElapsed,int _searchDepth) {

            String json = "{\"numberOfPage\":\""+_numberOfPage+
                    "\",\"searchDepth\":\""+_searchDepth+
                    "\",\"strategy\":\""+_strategy+
                    "\",\"timeElapsed\":\""+_timeElapsed+"\"}";;

            return json;
        }
}
