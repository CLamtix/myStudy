package xy20170607;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.DefaultValueProcessor;

public class JsonTest {
	public static void main(String[] args) {
		JsonConfig conf = new JsonConfig();
		conf.registerDefaultValueProcessor(Double.class, new DefaultValueProcessor(){

			@Override
			public Object getDefaultValue(Class clazz) {
				return new Double(4.22);
			}
		});
		Map<String,Double> map = new HashMap<String,Double>();
		map.put("1", new Double(4.00));
		JSONObject jo = JSONObject.fromObject(map,conf);
		System.out.println(jo.toString());
		System.out.println(Double.valueOf(jo.getDouble("1")));
	}
}	
