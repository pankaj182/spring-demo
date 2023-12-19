## Concepts

<hr>

## Setup
- Download [kafka binary](https://kafka.apache.org/downloads)
- Download [zookeeper binary](https://zookeeper.apache.org/releases.html)
- Extract both the zips.
- java version >= 8 required.

```jql
$ tar -xzf kafka.tgz
$ cd kafka_2.13-3.6.1
```

- Start the ZooKeeper service
```jql
$ bin/zookeeper-server-start.sh config/zookeeper.properties
```

- Start the Kafka broker service
```jql
$ bin/kafka-server-start.sh config/server.properties
```
