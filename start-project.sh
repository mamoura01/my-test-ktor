#!/bin/sh

echo "on construit le projet et ses binaires"
./gradlew clean build installDist

echo "on nettoie la bdd et on injecte les données d'init"
mongosh -f ./player.js --quiet

echo "on lance le projet a partir du binaire deployable"
sh build/install/my-test-ktor/bin/my-test-ktorr
