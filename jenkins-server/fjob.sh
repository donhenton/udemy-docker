docker run -d --name nexus -v /Users/dhh003/docker_series/nexus_repo:/nexus-data \
-e NEXUS_DATA=/nexus-data \
-e INSTALL4J_ADD_VM_PARAMS="-Xms1200m -Xmx1200m -XX:MaxDirectMemorySize=2g -Djavax.net.ssl.trustStore=/nexus-data/certs/cacerts.jks -Djavax.net.ssl.trustStorePassword=changeit" \
-p 9081:8081 \
sonatype/nexus3


 