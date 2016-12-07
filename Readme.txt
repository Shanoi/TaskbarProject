Projet BrainFuck de la Team Taskbar en SI3

Brainf*ck :
La construction et l'éxécution du logiciel se fait grâce à la commande ./bfck

   #!/bin/bash                                                                                                                                                                       
   mvn clean package
   mvn compile
   rm -f bfck.jar
   cp target/brainfuck_Taskbar-1.0-SNAPSHOT.jar bfck.jar
   java -cp bfck.jar brfk.BRFK $@
   rm -f bfck.jar

Par exemple:
./bfck --check [lien_vers_le_code_source_brainf*ck]


