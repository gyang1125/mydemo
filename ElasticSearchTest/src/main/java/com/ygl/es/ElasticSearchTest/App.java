package com.ygl.es.ElasticSearchTest;

import static org.elasticsearch.common.xcontent.XContentFactory.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
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
	public static void main(String[] args) throws Exception {

		TransportClient client = new PreBuiltXPackTransportClient(Settings.builder()
				.put("cluster.name", "ygl")
				.put("xpack.security.user", "elastic:elastic").build())
						.addTransportAddress(new TransportAddress(InetAddress.getByName("slave1"), 9300));

		XContentBuilder builder = jsonBuilder().startObject().field("user", "kimchy").field("postDate", new Date())
				.field("message", "trying out Elasticsearch").endObject();
		String json = builder.string();
		IndexResponse response = client.prepareIndex("gyang", "employee", "3").setSource(json, XContentType.JSON).get();
	}
}
