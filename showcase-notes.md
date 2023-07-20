# modify VM settings
multipass set local.test-vm2.cpus=4
multipass set local.test-vm2.disk=25G
multipass set local.test-vm2.memory=8G

# start VM
multipass start test-vm2

# Connect to VM via CLI
ssh vscdev@192.168.64.5

# create new integration project based on a template (archetype)


mvn archetype:generate                                   \
  -DarchetypeGroupId=com.esentri.fuse.quarkus.archetype  \
  -DarchetypeArtifactId=esentri-fuse-quarkus-project     \
  -DarchetypeVersion=1.4.0                               \
  -DgroupId=com.daimler.integration                      \
  -DartifactId=integration-csv-to-api                         \
  -Dversion=1.0.0


# access dev UI

http://192.168.64.5:8080/

# Activate interface

cp -f $HOME/mitglieder-test-1.csv $HOME/projects/integration-csv-to-api/src/test/resources


