package iphone;

import com.apple.dnssd.*;

public class ServiceAnnouncer implements RegisterListener {

    static final String serviceName = "oop-zuul2014";
    static final String serviceType = "witap";
    static final String serviceProtocol = "tcp";
    static final String registrationType = "_" + serviceType + "._" + serviceProtocol;
    // Instance variables
    private DNSSDRegistration serviceRecord;
    private boolean registered;
    private int listeningPort;

    public boolean isRegistered() {
        return registered;
    }

    public void registerService(int port) {
        try {
            listeningPort = port;
            serviceRecord = DNSSD.register(serviceName, registrationType, listeningPort, this);
            System.out.println("-> Chosen port is " + listeningPort);
        } catch (DNSSDException e) {
            System.out.println("Unable to register the service: " + e.getMessage());
        }
    }

    public void unregisterService() {
        serviceRecord.stop();
        registered = false;
    }

    public void serviceRegistered(DNSSDRegistration registration, int flags, String serviceName, String regType, String domain) {
        if (!registered) {
            registered = true;
            System.out.println("-> Service " + regType + " on " + serviceName + " registered in domain " + domain);
            synchronized (this) {
                this.notifyAll();
            }
        }
    }

    public void operationFailed(DNSSDService registration, int error) {
        System.out.println("-> Service registration failed");
    }
}
