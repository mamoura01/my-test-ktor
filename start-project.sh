#!/bin/sh

chmod +x ./gradlew

echo "\n"
echo "*********************************************************"
echo "******  on construit le projet et ses binaires **********"
echo "*********************************************************"
./gradlew clean build installDist

echo "\n"
echo "*********************************************************"
echo "** on nettoie la bdd et on injecte les données d'init ***"
echo "*********************************************************"
mongosh -f ./player.js --quiet

echo "\n"
echo "*********************************************************"
echo "*** on lance le projet a partir du binaire deployable ***"
echo "*********************************************************"
sh build/install/my-test-ktor/bin/my-test-ktor
