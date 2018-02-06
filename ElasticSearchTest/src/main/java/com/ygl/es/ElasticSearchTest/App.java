package com.ygl.es.ElasticSearchTest;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
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
		// index api
//		createIndex(client, json);

		// get api
//		getIndex(client);

		// update api
//		updateIndex(client);

		// delete api
//		deleteIndex(client);

		// search api
		searchIndex(client);
	}

	private static void searchIndex(TransportClient client) {
		SearchResponse response = client.prepareSearch("gyang").setTypes("employee")
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setQuery(QueryBuilders.termQuery("user", "kimchy")) // Query
				//				.setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18)) // Filter
				.setFrom(0).setSize(60).setExplain(true).get();
		SearchHits hits = response.getHits();
		logger.info("the search result length: " + hits.getHits().length);
		SearchHit[] hits1 = hits.getHits();
		for (SearchHit hit : hits1) {
			Map<String, Object> source = hit.getSourceAsMap();
			for (Map.Entry<String, Object> field : source.entrySet()) {
				String key = field.getKey();
				String value = field.getValue().toString();
				logger.info("search reuslt -->  key:" + key + " || " + "value: " + value);
			}
		}
	}

	private static void deleteIndex(TransportClient client) {
		DeleteResponse response = client.prepareDelete("gyang", "employee", "1").get();
	}

	private static void updateIndex(TransportClient client)
			throws IOException, InterruptedException, ExecutionException {
		UpdateRequest updateRequest = new UpdateRequest();
		updateRequest.index("gyang");
		updateRequest.type("employee");
		updateRequest.id("3");
		updateRequest.doc(jsonBuilder().startObject().field("user", "updated kimchy").endObject());
		client.update(updateRequest).get();
		getIndex(client);
	}

	private static void getIndex(TransportClient client) {
		GetResponse response1 = client.prepareGet("gyang", "employee", "3").get();
		Map<String, Object> fields = response1.getSource();
		for (Map.Entry<String, Object> field : fields.entrySet()) {
			String key = field.getKey();
			String value = field.getValue().toString();
			logger.info("key:" + key + " || " + "value: " + value);
		}
	}

	private static void createIndex(TransportClient client, String json) {
		IndexResponse response = client.prepareIndex("gyang", "employee", "3").setSource(json, XContentType.JSON).get();
	}
}
