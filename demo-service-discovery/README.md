## Service Discovery Problem
- Now-a-days we run multiple instances of an application for availability, but it introduces complexity.
- Each instance is identified by a network endpoint (IP and port). 
- Traditionally, virtual or physical machines hosted these endpoints, but containers are now more prevalent. 
- Clients should know IP address and port before accessing the service. 
- This task becomes more challenging due to the dynamic nature of instances— new ones appear, and existing ones disappear due to failures, scaling, or maintenance. 
- This scenario gives rise to what is known as a service discovery problem.

<hr>

## Discovery Mechanisms
### DNS (Domain Name System) Service Discovery:
- Services register their information with a DNS server, typically using DNS records like A (Address) or SRV (Service) records. Clients can query the DNS to resolve the domain name of a service to its corresponding IP address.
- Two extensions to the DNS protocol are multicast DNS (mDNS) and DNS Service Discovery (DNS-SD).
- Multicast DNS (mDNS) provides a naming service system that is easy to set up and maintain, for computers on a local link. All participating network devices on the same local link perform standard DNS functions, using multicast DNS rather than unicast, and do not need a unicast DNS server.
- DNS Service Discovery (DNS-SD) extends traditional DNS to include information about services. Clients can use DNS-SD to discover services within a specific domain, obtaining details such as service type, port, and other metadata.
- For example, a service might be registered with a DNS server under a specific domain (e.g., service.example.com), and clients can query DNS to obtain the IP address and other information associated with that service.
- Pros:
  - Simple, widely adopted, Decentralized
- Cons:
  - May have higher latency due to DNS caching at various levels,
  - Limited dynamism for rapidly changing environments.

### Embedded Service Lists:
- In this approach, clients are provided with an embedded list of services or a configuration file that contains information about available services. 
- This list is typically provided during the deployment or configuration of the client application.
- The embedded service list can include details such as service names, IP addresses, ports, and any other necessary metadata. Clients use this information to locate and communicate with the required services.
- This method is often used in scenarios where the set of services is known in advance or doesn’t change frequently.
- Pros:
  - Simple setup.
  - Full control over the configuration.
- Cons:
  - Prone to errors and manual oversight. 
  - Not scalable for large or dynamic clusters. 

### Dynamic Discovery (Service Registry):
- Nodes register themselves with a centralized service registry or discovery service. Other nodes query the registry to discover available nodes dynamically.
- Pros:
  - Dynamic adaptation to node changes.
  - Suitable for dynamic environments.
- Cons:
  - Requires an additional service registry.
  - Potential single point of failure & bottleneck.

### Multicast & Broadcast:
- Multicast: Nodes can use multicast messages to announce their presence and services to a group of interested parties. Other nodes subscribe to these multicast groups to discover available services.
- Broadcast: Similar to multicast, but messages are sent to the entire network. Broadcast messages can be used for service announcements and discovery.
- Pros:
  - Efficient for small to medium-sized clusters.
  - Minimal configuration is required.
- Cons:
  - Limited scalability in large networks.
  - May face challenges in certain network environments.

### Gossip Protocols:
- Nodes periodically exchange information about themselves with a few randomly selected peers. Over time, this information propagates throughout the network, allowing nodes to discover each other.
- Pros:
  - Decentralized and adaptive.
  - Efficient for large clusters.
- Cons:
  - Eventual consistency challenges.
  - Configuration and tuning required.

### Zeroconf protocols,
such as Bonjour (used in Apple networks) and Avahi (used in Linux), enable automatic service discovery without a central registry. Devices broadcast information about services they offer, and others on the network can discover and connect to these services.

<hr>

## Service Discovery
Service discovery is a mechanism enabling dynamic detection and location of services in a network, facilitating effective communication between clients and servers. 
It involves techniques and protocols for systems to automatically locate and connect to available services, streamlining the management of distributed architectures like microservices. 
The primary goals of service discovery include:
- **Dynamic Node Registration**: Nodes (services or instances) register themselves with a central authority or a distributed system upon startup.
- **Node Discovery**: Clients or other nodes dynamically discover available nodes or services, enabling them to establish connections for communication.
- **Health Monitoring**: Service discovery often includes health checks to assess the liveliness and status of registered nodes, allowing the system to adapt to changes dynamically.


### Significance of Service Discovery
- **Scalability**:
  - In a distributed system, the number of nodes can change dynamically due to auto-scaling, failures, or updates. Service discovery ensures that new nodes can be seamlessly integrated into the system without manual intervention.
- **Load Balancing**:
  - Service discovery facilitates load balancing by distributing incoming requests across available nodes. This ensures optimal resource utilization and prevents any single node from becoming a bottleneck.
- **Fault Tolerance**:
  - Service discovery enhances fault tolerance by enabling systems to adapt to node failures. When a node becomes unhealthy or fails, the service discovery mechanism can route traffic to healthy nodes, minimizing service disruption.
- **Dynamic Environments**:
  - In cloud-native environments, where infrastructure is dynamic and elastic, service discovery becomes essential for maintaining connectivity and ensuring the responsiveness of applications.
- **Microservices Communication**:
  - Microservices often need to communicate with each other to fulfill business processes. Service discovery simplifies this communication by providing a dynamic and efficient way for microservices to locate and connect to each other.

### Challenges in Service Discovery
While service discovery offers numerous benefits, it also introduces challenges that must be addressed for a robust implementation:

- Consistency:
  - Ensuring a consistent view of the system’s state across all nodes is challenging, especially when nodes are frequently added or removed.
- Latency:
  - The time taken to discover and establish connections with nodes can impact application latency. Minimizing discovery latency is crucial for maintaining optimal system performance.
- Security:
  - Service discovery mechanisms must incorporate security measures to protect against unauthorized access and ensure secure communication between nodes.
- Scalability:
  - As the number of nodes grows, the service discovery mechanism must scale efficiently to handle the increased load without introducing performance bottlenecks.

### Strategies for Service Discovery

#### Server-Side Service Discovery:
- A central registry or service discovery server maintains information about available nodes.
- Clients query the registry to discover nodes, and the central authority may handle load balancing and failover decisions.
- Pros:
    - Clients are simpler and less aware of the underlying discovery mechanism.
    - Centralized control allows for easier management and monitoring.
- Cons:
    - Increased load on the central registry.
    - `Single point of failure` if the central registry becomes unavailable.
    - Potential Bottleneck
    - load balancer may need to be aware of all the communication protocols used between services and clients
    - there will be always an extra network hop on the request path.

#### Client-Side Service Discovery:
- Clients are responsible for discovering and selecting available services. 
- Services register with a central registry, and clients query the registry to discover nodes.
- After fetching the full list of IP addresses constituting the service fleet, a client could pick up an instance based on the load balancing strategy at its disposal.
- Pros:
  - No single point of failure, no bottleneck, no extra hop
  - Clients have control over service selection. 
  - Load balancing and failover decisions can be made on the client side.
- Cons:
  - Clients need to be aware of the service discovery mechanism.
  - Potential inconsistency in the client's view of the system.
  
<hr>

## The Service Registry
The service registry is like a database that keeps track of where different services are located on the network. It’s crucial for service discovery. This registry needs to be always available and up-to-date. Clients, which are the users of these services, can remember where the services are, but over time, this information can become outdated. To ensure consistency, the service registry is made up of multiple servers that work together using a replication protocol.

Netflix Eureka is a service registry that helps services find each other. It works through a simple web interface called REST API. When a service wants to join, it sends a message (POST request) saying where it is. Every 30 seconds, it says it’s still there (PUT request). If a service leaves, it can either send a message (DELETE request) or just let its registration expire. To see all the services, you can ask for the list (GET request).

Netflix keeps Eureka always available by running it on multiple servers in different areas of Amazon EC2. Each server has its special address, and they use DNS to keep track of each other. When a new Eureka server starts, it checks DNS to find its friends and joins the group.

Services and their clients also use DNS to find Eureka servers. They like to use a server close to them, but if that’s not possible, they use one from a different area. This way, even if some servers are not available, the services can still find each other.

Other examples of service registries include etcd, which is used by projects like Kubernetes and Cloud Foundry, consul, a tool for discovering and configuring services, and Apache Zookeeper, a widely used coordination service for distributed applications.

<hr>

## Service Registration Options
Let’s look at how service instances are registered with the service registry.

As mentioned earlier, service instances need to be both registered with and deregistered from the service registry. Two approaches exist for managing this registration process. In the self-registration pattern, service instances handle their own registration. On the other hand, in the third-party registration pattern, another system component takes care of registering and deregistering service instances.

### Self Registration Pattern
A service instance is responsible for registering and deregistering itself with the service registry.
Also, if required, a service instance sends heartbeat requests to prevent its registration from expiring.
A good example of this approach is the Netflix OSS Eureka client. The Eureka client handles all aspects of service instance registration and deregistration.
Pros: it is relatively simple and doesn’t require any other system components
Cons: it couples the service instances to the service registry

### Third-Party Registration Pattern
service instances aren’t responsible for registering themselves with the service registry
Instead, another system component known as the service registrar handles the registration.
The service registrar tracks changes to the set of running instances by either polling the deployment environment or subscribing to events
An example of a service registrar is NetflixOSS Prana. it is a sidecar application that runs side by side with a service instance. Prana registers and deregisters the service instance with Netflix Eureka.

<hr>

## Load Balancing Vs Service Discovery
While load balancers focus on traffic distribution and ensuring system reliability, service discovery is about dynamic and automated location awareness of services. Together, they contribute to the scalability, availability, and flexibility of distributed systems, especially in microservices architectures.

<hr>

## An Example with Netflix Eureka
1. **Setup Eureka Server**
```xml
    <properties>
        <spring-cloud.version>2023.0.0</spring-cloud.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>
    </dependencies>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
```

```java
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
```

```yaml
server:
  port: 8761

# register-with-eureka: false and fetch-registry: false indicate that the Eureka server does not register itself with itself.
eureka:
  client:
    register-with-eureka: false
    fetch-registry: false
```

2. **Setup Eureka Client**
```xml
    <dependencies>
        <dependency>
            <groupId>com.neatcode</groupId>
            <artifactId>services-rest</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
    </dependencies>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
```

```java
@SpringBootApplication
@EnableDiscoveryClient
public class MyMicroserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyMicroserviceApplication.class, args);
    }
}
```

```yaml
eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/ # URL of the Eureka server
  instance:
    hostname: localhost

spring:
  application:
    name: SAMPLE-APP # the logical name of the microservice.
```

3. **Service Registration**
  - start the eureka server
  - start your client app. When a microservice starts, the Eureka client automatically registers itself with the Eureka server.

4. **invoking an API**
```java
@Service
public class MyService {
    @Autowired
    private RestTemplate restTemplate;

    public String callOtherService() {
        String otherServiceUrl = "http://SAMPLE-APP/users";
        return restTemplate.getForObject(otherServiceUrl, String.class);
    }
}

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
```
