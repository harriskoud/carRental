/*package com.carRental.carRentalApp.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.carRental.carRentalApp.repositories")
@ComponentScan(basePackages = { "com.carRental.carRentalApp.resources" })
public class Config {

	
	 * @Bean public ElasticsearchOperations elasticsearchTemplate() {
	 * 
	 * String path = "C:\\Users\\koch\\Desktop\\elasticsearch-6.3.0";
	 * 
	 * Settings elasticsearchSettings = Settings.builder() .put("http.enabled",
	 * "true") .put("index.number_of_shards", "1") .put("path.data", path+"\\data")
	 * .put("path.logs", path+"\\logs") .put("path.work", path+"\\work")
	 * .put("path.home", path) .build();
	 * 
	 * Node node= new Node(elasticsearchSettings);
	 * 
	 * return new ElasticsearchTemplate(node.client()); }
	 

	Settings indexSettings = Settings.builder().put("number_of_shards", 1).build();
	//CreateIndexRequest indexRequest = new CreateIndexRequest("cars", indexSettings);
	// client.admin().indices().create(indexRequest).actionGet();

	@Bean
	public ElasticsearchTemplate elasticsearchTemplate(Client client) {

		//IndexResponse response = client.prepareIndex("cars", "car").setSource(putJsonDocument(1, "test")).execute().actionGet();

		return new ElasticsearchTemplate(client);
	}

	public static Map<String, Object> putJsonDocument(int id, String brandName) {

		Map<String, Object> jsonDocument = new HashMap<String, Object>();

		jsonDocument.put("id", id);
		jsonDocument.put("brandName", brandName);
		return jsonDocument;
	}

}*/