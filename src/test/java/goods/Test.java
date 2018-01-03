package goods;

import net.sf.json.JSONObject;

public class Test {
	
	
	 public static void main( String[] args )
	 {
		 qq qw = new qq("qwe", "asdadsadsasdadasd");
		 System.out.println(JSONObject.fromObject(qw).toString());
		 
	 }

}
