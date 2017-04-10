
package com.javatpoint;


import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Host;
import com.aerospike.client.policy.ClientPolicy;
import com.javatpoint.PropertiesContext;

import java.util.ArrayList;
import java.util.List;

public class AerospikeInstance {
    private static AerospikeClient aerospikeClient;
    private static ClientPolicy policy;
    public final  static AerospikeClient getClientInstance(){
        if (aerospikeClient == null){
            List<Host> hostList = new ArrayList<Host>();
            Host[] hosts = new Host[]{};
            for(String aerspikeHost: PropertiesContext.getInstance().getConfigProperty("aerospike.host").split(",")){
                hostList.add(new Host(aerspikeHost, 3000));
            }
            aerospikeClient = new AerospikeClient(getAerospikeClientPolicy(), hostList.toArray(hosts));
        }

        return aerospikeClient;
    }

    public static ClientPolicy getAerospikeClientPolicy(){
        if(policy==null){
            policy = new ClientPolicy();
            policy.readPolicyDefault.timeout = 500;
            policy.readPolicyDefault.maxRetries = 2;
            policy.readPolicyDefault.sleepBetweenRetries = 10;
            policy.failIfNotConnected = true;
        }
        return policy;
    }

}
