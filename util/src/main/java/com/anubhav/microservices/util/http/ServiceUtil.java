package com.anubhav.microservices.util.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;


/*
*
* The main purpose of ServiceUtil.java is to find out the hostname, IP address, and port used by the microservice.
* The class exposes a method, getServiceAddress(),
* that can be used by the microservices to find their hostname, IP address, and port.
*
* */

@Component
public class ServiceUtil {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceUtil.class);

    private final String port;

    private String serviceAddress = null;

    @Autowired
    public ServiceUtil(
            @Value("${server.port}") String port) {

        this.port = port;
    }

    public String getServiceAddress() {
        if (serviceAddress == null) {
            serviceAddress = findMyHostname() + "/" + findMyIpAddress() + ":" + port;
        }
        return serviceAddress;
    }

    private String findMyHostname() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "unknown host name";
        }
    }

    private String findMyIpAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "unknown IP address";
        }
    }
}
