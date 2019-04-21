# Ultimate Tic Tac Toe

## Lancement du jeu
Pour le lancement du jeu, l'outil d'automatisation de la compilation ```ant``` est utilisé et doit être installé sur la machine de l'utilisateur.

### Démarrer le serveur
```
// Commandes fonctionnelles sur Linux
export DERBY_HOME=/opt/derby
export JAVA_HOME=/usr/j2se
export PATH="$DERBY_HOME/bin:$PATH"
startNetworkServer
```
[Documentation détailée d'Apache](http://db.apache.org/derby/docs/10.7/adminguide/tadmincbdjhhfd.html)

### Compilation et exécution du jeu
```
cd <chemin vers le dossier d'installation>/UltimateTicTacToe

// compilation du projet
ant jfx-build

// exécution du projet
ant jfx-project-run
```
## Description de l'interface graphique
### Menu de jeu
Le menu permet à l'utilisateur de joindre/ quitter une partie et de naviguer entre les différentes fenêtres de l'application.

#### Options principales
- ***Join*** : demande à l'utilisateur un pseudonyme alphanumérique unique de 8 à 20 caractères. Si l'utilisateur s'est déjà enregistré lors d'une session précédente, ses données sont chargées. Sinon une nouvelle entrée est créee dans la base de données. Ce bouton devient indisponible lorsque deux utilisateurs ont joint la partie.
- ***New Game*** : commence une nouvelle partie avec les utilisateurs qui ont joint le jeu et affiche la *fenêtre de jeu*. Ce bouton ne devient disponible que lorsque deux utilisateurs ont joint la session.
- ***Statistics*** : affiche la *fenêtre des statistiques*.
- ***Quit*** : termine le programme sans confirmation.

#### Quitter la session
Sur la droite des joueurs prêts à jouer un bouton rouge permet aux utilisateurs de quitter la session courante.

### Fenêtre de jeu
C'est la fenêtre principle du jeu, c'est ici que les joueurs jouent à *Ultimate Tic Tac Toe*.

#### Barre de menu
La barre de menu de la fenêtre de jeu contient un unique menu qui est contient quatre options:

- ***Withdraw*** : indique que le joueur courant abandonne et concède la victoire à son adversaire.
- ***New Game*** : commence une nouvelle partie avec les mêmes utilisateurs après avoir demander une confirmation aux utilisateurs.
- ***Go to menu*** : retourne au *menu de jeu* en demandant confirmation aux utilisateurs.
- ***Quit*** : termine le programme en demandant une confirmation aux utilisateurs.

#### Plateau
Pour marquer une case il suffit de clicker sur celle désirée. Aussi le *mini tic tac toe* coloré correspond à celui sur lequel le joueur courant doit jouer.

### Fenêtre des statistiques
La fenêtre reprend sous forme de tableau l'ensemble des statistiques qui concernent tous les utilisateurs du jeu.
Elle présente pour chaque joueur son nombre de victoires, nombre d'ex aequos et nombre de défaites.

La barre de menu contient un menu qui permet à l'utilisateur de retourner vers le menu principal ou de quitter le jeu.

## Structure générale du projet
- ```atlg4.ultimate.g47923``` : package de travail du projet.
    - ```view``` : classes qui représentent les différentes fenêtres et dialogues de l'interface graphique.
    - ```model``` : classes de modèlisation des données et dynamiques de l'application.
    - ```controller```: controleurs des différentes fenêtres et dialogues de l'application.
    - ```exceptions``` : exceptions propre à l'application.
    - ```dto``` : classes des objets de transfert de données.
    - ```persistence``` : classes relatives à la persistance des données de l'application.
        - ```business``` : classes qui implémentent la logique métier.
        - ```db``` : classes d'accès et de gestion de la base de données.
        - ```seldto``` : classes de sélections.
