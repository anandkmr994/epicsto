package com.example.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;
import com.example.utils.PropertiesContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on 11/4/17.
 */
public class CassandraClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(CassandraClient.class);

    /** Cassandra Cluster. */
    private Cluster cluster;
    /** Cassandra Session. */
    private Session session;
    /**
     * Connect to Cassandra Cluster specified by provided node IP
     * address and port number.
     *
     * @param node Cluster node IP address.
     * @param port Port of cluster host.
     */

    public Session getClientSession(){
        String hostNode = PropertiesContext.getInstance().getConfigProperty("cassandra.cluster.host");
        int port = Integer.parseInt(PropertiesContext.getInstance().getConfigProperty("cassandra.port"));

        return connect(hostNode,port);
    }
    public Session connect(final String node, final int port)
    {
        this.cluster = Cluster.builder().addContactPoint(node).withPort(port).build();
        final Metadata metadata = cluster.getMetadata();
        LOGGER.info("Connected to cluster: " + metadata.getClusterName());
        for (final Host host : metadata.getAllHosts())
        {
            LOGGER.info("Datacenter: " + host.getDatacenter() + " Host : " + host.getAddress()
                    + " rack : " + host.getRack());
        }
        session = cluster.connect();
        return session ;
    }
    /**
     * Provide my Session.
     *
     * @return My session.
     */
    public Session getSession()
    {
        return this.session;
    }
    /** Close cluster. */
    public void close()
    {
        cluster.close();
    }
}
