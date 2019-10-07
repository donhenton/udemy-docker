# DOCKER NETWORK

Experimenting with docker networking

## Creating the docker network

<https://docker-curriculum.com/#docker-network>
<https://runnable.com/docker/docker-compose-networking>

* list docker networks: ```docker network ls```
* inspect network details: ```docker network inspect <networkname>```
* create our own special network ```docker network create bonzo-net```

## Running docker compose

Shell into test2 and ping manny:

```bash
64 bytes from test1.docker-network_bonzo-net (172.27.0.3): icmp_seq=3 ttl=64 time=0.157 ms
64 bytes from test1.docker-network_bonzo-net (172.27.0.3): icmp_seq=4 ttl=64 time=0.167 ms
```

### Ping manny.docker-network_legacy

```bash
64 bytes from test1.docker-network_legacy (172.28.0.3): icmp_seq=1 ttl=64 time=0.122 ms
64 bytes from test1.docker-network_legacy (172.28.0.3): icmp_seq=2 ttl=64 time=0.160 ms
```

### Ping test2bonzo.docker-network_bonzo-net

```bash
64 bytes from 5f1288ce2150 (172.27.0.4): icmp_seq=1 ttl=64 time=0.046 ms
64 bytes from 5f1288ce2150 (172.27.0.4): icmp_seq=2 ttl=64 time=0.091 ms
```

### Ping test2bonzo.docker-network_legacy

nothing found

### Run sepmachine.sh to create a running instance that is on the network

ping test1 works
ping manny doesn't work -- manny only works when you shell into test2
ping test3bonzo works

