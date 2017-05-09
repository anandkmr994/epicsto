package com.epicsto.controllers;

import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.ClientPolicy;
import com.datastax.driver.core.Session;
import com.epicsto.aerospike.AerospikeInstance;
import com.epicsto.cassandra.CassandraClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	@RequestMapping("/hello")
	public ModelAndView mymethod(){

		return new ModelAndView("hellopage","msg","Hello First Spring");
	}

	@RequestMapping("/aerospike/put")
	public void putInAerospike(){
		ClientPolicy policy = AerospikeInstance.getAerospikeClientPolicy();
		Key key = new Key("test","experiment","name");
		Bin bin = new Bin("name","anand");
		AerospikeInstance.getClientInstance().add(policy.writePolicyDefault, key, bin);
	}

	@RequestMapping("/aerospike/get")
	public ModelAndView getFromAerospike(){
		ClientPolicy policy = AerospikeInstance.getAerospikeClientPolicy();
		Key key = new Key("test","experiment","name");
		Record record = AerospikeInstance.getClientInstance().get(policy.readPolicyDefault, key);
		return new ModelAndView("hellopage","msg",record.toString());
	}

	@RequestMapping("/cassandra")
	public ModelAndView connectCassandra(){
		CassandraClient cassandraClient = new CassandraClient();
		Session session = cassandraClient.getClientSession();
		return new ModelAndView("hellopage","msg","cassandra cluster name is : " +
				session.getCluster().getMetadata().getClusterName());
	}
}
