# Heme Biotech — Analyse des symptômes

Projet 2 de la formation Java OpenClassrooms : correction et refonte d'une application qui analyse les symptômes recensés dans un fichier texte.

## Objectif

L'application lit `symptoms.txt`, compte le nombre d'occurrences de **chaque libellé de symptôme** et génère `result.out` avec les résultats **triés par nom de symptôme**.

Chaque ligne du fichier d'entrée représente un symptôme. Les doublons sont conservés pour le comptage ; les libellés différents sont comptés séparément (par exemple `dialated pupils` et `constricted pupils`).

Extrait du fichier de sortie :

```text
constricted pupils: 3
dialated pupils: 4
headache: 3
rash: 4
```

## Prérequis

- Un JDK installé et configuré dans IntelliJ IDEA, ou disponible dans le terminal (`java` et `javac`).
- IntelliJ IDEA est recommandé pour ouvrir et exécuter ce projet Java simple. Aucun outil de construction Maven ou Gradle n'est nécessaire pour la version fournie.

La compilation et l'exécution ont été vérifiées avec **Java 21**.

## Lancer l'application avec IntelliJ IDEA

1. Ouvrir le dossier `Project02Eclipse` dans IntelliJ IDEA.
2. Configurer un JDK dans les paramètres du projet si nécessaire.
3. Vérifier que `src` est marqué comme dossier source (*Sources Root*).
4. Ouvrir `src/com/hemebiotech/analytics/Main.java` et lancer `Main.main()`.
5. Dans la configuration d'exécution, définir le *Working directory* sur le dossier **`Project02Eclipse`**, qui contient `symptoms.txt`.

Après l'exécution, consulter le fichier **`Project02Eclipse/result.out`**. Il est recréé à chaque lancement réussi de l'écriture.

> Les chemins `symptoms.txt` et `result.out` sont relatifs au *Working directory*, et non à l'emplacement de `Main.java`.

## Lancer depuis PowerShell (Windows)

Ouvrir PowerShell dans le dossier `Project02Eclipse`, puis exécuter :

```powershell
java -version
javac -version

New-Item -ItemType Directory -Force out | Out-Null
$sources = Get-ChildItem -Path "src/com" -Recurse -Filter "*.java" | ForEach-Object { $_.FullName }
javac -d out $sources
java -cp out com.hemebiotech.analytics.Main
```

Le fichier `result.out` apparaît dans le dossier courant (`Project02Eclipse`). Les fichiers `.class` dans `out/` sont des fichiers générés : il n'est pas nécessaire de les modifier ni de les versionner.

## Organisation du code

```text
Project02Eclipse/
├── README.md
├── symptoms.txt                 # Données d'entrée
├── result.out                   # Résultats générés
└── src/
    └── com/hemebiotech/analytics/
        ├── Main.java            # Point d'entrée
        └── service/
            ├── SymptomAnalytics.java   # Orchestration
            ├── reader/
            │   ├── ISymptomReader.java
            │   └── ReadSymptomDataFromFile.java
            ├── counter/
            │   ├── ISymptomCount.java
            │   └── SymptomCounter.java
            └── writer/
                ├── ISymptomsWriter.java
                └── SymptomsWriter.java
```

`Main` démarre l'analyse. `SymptomAnalytics` enchaîne les trois opérations :

1. **Lecture :** `ReadSymptomDataFromFile` retourne une `List<String>` depuis `symptoms.txt`.
2. **Comptage :** `SymptomCounter` produit une `Map<String, Integer>` ; sa `TreeMap` classe les clés (noms des symptômes) par ordre naturel.
3. **Écriture :** `SymptomsWriter` parcourt cette Map et écrit une ligne `symptôme: nombre` dans `result.out`. Le fichier de sortie est fermé à l'aide d'un *try-with-resources*.

Les interfaces définissent les contrats de lecture, de comptage et d'écriture. Les traitements sont répartis entre des classes à responsabilités distinctes.

## Vérifications utiles

- **Données normales :** lancer `Main` et vérifier que chaque symptôme distinct apparaît une seule fois dans `result.out`, avec son nombre d'occurrences.
- **Tri :** vérifier que les lignes sont ordonnées alphabétiquement selon le nom du symptôme, **pas** selon le compteur.
- **Fichier vide :** vérifier que l'application produit un fichier de sortie vide.
- **Chemin d'exécution :** si le fichier d'entrée est introuvable, vérifier le *Working directory*.

**Limite actuelle :** le lecteur de départ intercepte lui-même les erreurs de lecture et peut retourner une liste vide ou partielle. En cas d'échec de lecture, un `result.out` vide ou incomplet peut donc être généré. Le Writer, lui, transmet ses erreurs d'écriture à l'orchestrateur.

## Versionnement

Le travail est réalisé sur des branches de développement, puis intégré à `main` pour la livraison. Les fichiers générés (`out/`, `*.class`, `result.out`) et les fichiers locaux de l'IDE n'ont pas vocation à être versionnés.
