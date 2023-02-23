#!/bin/sh

# on construit le projet et ses binaires
./gradlew clean build installDist

# on nettoie la bdd et on injecte les données d'init
mongosh -f ./player.js --quiet

# on lance le projet a partir du binaire deployable
bash -x ./build/install/my-test-ktor/my-test-ktor