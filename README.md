# MultiCommands
Essentials custom de la série Multi

MultiCommands contient la plupart des commandes essentielles proposées par Essentials.

Le but de ce plugin n'est absolument pas de l'égaler / le dépasser. 

Cependant, il contient des ajouts non présents dans Essentials :

![Capture](https://user-images.githubusercontent.com/33187305/183760792-14713d3f-ab4a-491f-b08a-08cff53d21fd.PNG)

La commande /list permet d'afficher les joueurs connectés, mais affiche également les "nicknames" de ces joueurs.
Cette commande permet aussi aux administrateurs (joueurs ayant la permission "multicommands.all_perms", "multicommands.teleport.self" ou "multicommands.teleport.*) de se téléporter à ces-dit joueurs
en cliquant simplement sur leurs pseudonymes.


Il est évident que Multicommands ne contient pas uniquement cette commande, ce serait trop inutile pour un serveur.

Ce plugin faisant partie d'une série de projets, il est possible en ajoutant [MultiMaintenance](https://github.com/CapitaineCat0/MultiMaintenance) / [MultiFun](https://github.com/CapitaineCat0/MultiFun) / [MultiAnnounce](https://github.com/CapitaineCat0/MultiAnnounce), d'afficher et de gérer d'autres
paramètres / features de votre serveur.

*Ce plugin est en cours de développement, il est donc possible que des bugs soient présents. Si vous en trouvez, merci de les signaler dans l'onglet "[Issues](https://github.com/CapitaineCat0/MultiCommands/issues)" de ce projet.*

## Installation
Pour installer ce plugin, il vous suffit de télécharger la dernière version disponible [ici](https://github.com/CapitaineCat0/MultiCommands/tree/main/build/libs)
et de la glisser dans le dossier "plugins" de votre serveur.

## Configuration
La configuration de ce plugin est très simple, il vous suffit de modifier les valeurs des différents paramètres présents dans le fichier "config.yml" généré par MultiCommands.

```yaml #Configuration file of MultiCommands
#Changer la langue du plugin :
lang: en

#Les messages peuvent être envoyés dans différents canaux :
# - ACTIONBAR | actionbar
# - CHAT | chat
# - TITLE | title
# - BOSSBAR | bossbar
send-message-on: ACTIONBAR

#Gérer les effets sonores :
#Liste des sons :
#https://www.digminecraft.com/lists/sound_list_pc.php

enable-command-sounds: true

#Activer ou non les messages de démarrage:
console-setup: true

#Sons d'erreurs:
no-perm-sound: "BLOCK_ANVIL_PLACE"

#Sons de succès:
cmd-done-sound: "ENTITY_EXPERIENCE_ORB_PICKUP"

#Sons custom:
feed-heal-sounds: true

#Activer ou désactiver le HelpGUI :
#(désactivez ce paramêtre si vous n'utilisez pas de Build paperSpigot)
enable-help-gui: true

#Valeures max des coordonées X et Z pour la commande /rtp :
random-teleporter-max-X: 25000
random-teleporter-max-Z: 25000

#/!\ NE PAS TOUCHER /!\
#Paramêtre vide par défaut, 
#se remplis grâce à la commande /setspawn
#(si vous modifiez ces paramères,
#la commande pourrait ne pas fonctionner)
spawn:
```
