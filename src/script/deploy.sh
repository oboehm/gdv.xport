#! /bin/sh
#
# This script deploys the artifacts to https://oss.sonatype.org/
# see https://docs.sonatype.org/display/Repository/Sonatype+OSS+Maven+Repository+Usage+Guide#SonatypeOSSMavenRepositoryUsageGuide-7b.StageExistingArtifacts
#
# (c)reated: 16-Jul-2012 by boehm@javatux.de
#

# set up some constants
URL=https://oss.sonatype.org/service/local/staging/deploy/maven2/
VERSION=3.1.0
options="gpg:sign-and-deploy-file -Durl=$URL -DrepositoryId=sonatype-nexus-staging"

# passphrase is needed for signing
echo "passphrase for GPG: "
stty_orig=`stty -g`
stty -echo
read passphrase
stty $stty_orig

options="gpg:sign-and-deploy-file -Durl=$URL -DrepositoryId=sonatype-nexus-staging -Dgpg.passphrase=$passphrase"

deploy_pom_for() {
	module=$1
	echo deploying $module...
	mvn -N $options -DpomFile=target/$module-$VERSION.pom -Dfile=target/$module-$VERSION.pom
    echo
}

deploy_jar_for() {
    subdir=$1
	module=$2
	pushd $subdir
	echo deploying $module in $subdir...
    mvn $options -DpomFile=target/$module-$VERSION.pom -Dfile=target/$module-$VERSION.jar
    mvn $options -DpomFile=target/$module-$VERSION.pom -Dfile=target/$module-$VERSION-uberjar.jar -Dclassifier=uberjar
    mvn $options -DpomFile=target/$module-$VERSION.pom -Dfile=target/$module-$VERSION-sources.jar -Dclassifier=sources
    mvn $options -DpomFile=target/$module-$VERSION.pom -Dfile=target/$module-$VERSION-javadoc.jar -Dclassifier=javadoc
    popd
    echo
}

deploy_war_for() {
    subdir=$1
	module=$2
	echo deploying $module in $subdir...
	pushd $subdir
    mvn $options -DpomFile=target/$module-$VERSION.pom -Dfile=target/$module-$VERSION.war
    mvn $options -DpomFile=target/$module-$VERSION.pom -Dfile=target/$module-$VERSION-sources.jar -Dclassifier=sources
    mvn $options -DpomFile=target/$module-$VERSION.pom -Dfile=target/$module-$VERSION-javadoc.jar -Dclassifier=javadoc
    popd
    echo
}

# start deployment
deploy_pom_for gdv-xport
deploy_jar_for lib gdv-xport-lib
deploy_war_for service gdv-xport-service
