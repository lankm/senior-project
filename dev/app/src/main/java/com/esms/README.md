# Formatting tips

composable functions start with a capital with the rest as camelcase.

non-composable functions start with a lowercase with the rest as camelcase.

# general structure

./models/ are data transfer objects. they sometime include functions to represent the data a different way.

./services/ are functions used to retrieve or send information.
./services/engines/ this is where all the cryptography engines are stored. They are instantiated by the Cryptography Engine Generator service

./theme/ is where the shared colors for the views are held. 

./views/ this is where all the composables are and acts like controllers at times.
