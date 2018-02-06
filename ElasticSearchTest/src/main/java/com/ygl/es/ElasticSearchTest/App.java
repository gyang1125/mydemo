package com.ygl.es.ElasticSearchTest;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.net.InetAddress;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;

/**
 * Hello world!
 *
 */
public class App {
	public static Logger logger = LogManager.getRootLogger();

	public static void main(String[] args) throws Exception {
		logger.info("start...");

		TransportClient client = new PreBuiltXPackTransportClient(
				Settings.builder().put("cluster.name", "ygl").put("xpack.security.user", "elastic:elastic").build())
						.addTransportAddress(new TransportAddress(InetAddress.getByName("slave1"), 9300));

		XContentBuilder builder = jsonBuilder().startObject().field("user", "kimchy").field("postDate", new Date())
				.field("message", "trying out Elasticsearch").endObject();
		String json = builder.string();
		//IndexResponse response = client.prepareIndex("gyang", "employee", "3").setSource(json, XContentType.JSON).get();

		// get api
		GetResponse response1 = client.prepareGet("gyang", "employee", "1").get();
		Map<String, Object> fields = response1.getSource();
		for (Entry<String, Object> field : fields.entrySet()) {
			String key = field.getKey();
			String value = field.getValue().toString();
			logger.info("key:" + key + " || " + "value: " + value);
		}
	}
}
