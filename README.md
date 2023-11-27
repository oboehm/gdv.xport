[![Maven Central](https://img.shields.io/maven-central/v/com.github.oboehm/gdv-xport.svg)](https://central.sonatype.com/artifact/com.github.oboehm/gdv-xport)
[![Issues](https://img.shields.io/github/issues/oboehm/gdv.xport.svg)](https://github.com/oboehm/gdv.xport/issues)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)
[![Umfrage](https://img.shields.io/badge/zur%20Umfrage-FFCC00)](https://nuudel.digitalcourage.de/CqLHF4HX5SfKZ24I)

# Was ist gdv.xport?

gdv.xport ist als Java-Bibliothek gestartet, die den Umgang mit dem GDV-Format erleichtert. 
Das "x" in port steht fur "im"- und "ex"-port. Die Bibliothek ist Open Source und steht unter der Apache License. 

Ab Version 3.0 gibt es neben dieser Bibliothek auch REST-Services fuer Dateien im GDV-Format.
Die einzelnen Module sind: 

* [**gdv-xport-lib**](lib/): gdv.xport als Java-Bibliothek
* [**gdv-xport-service**](service/): gdv.xport-Services (Spring-Boot-Anwendung)
* [gdv-xport-deprecated](deprecated/): gdv.xport-Deprecated (aussortierte Altlasten, mit 4.0 eingeführt)

Damit aendern sich die Maven-Koordinaten für der Bibliothek in:

```
<dependency>
    <groupId>com.github.oboehm</groupId>
    <artifactId>gdv-xport-lib</artifactId>
</dependency>
```


## Aehnliche Projekte

Fuer Ruby gibt es [OpenGDV](https://github.com/vendis/opengdv/), einer Open-Source-Bibliothek, die unter der MIT-License steht.
Allerdings wurde diese Bibliothek schon seit laengerer Zeit nicht mehr aktualisiert.



## ![Docker](https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/Docker_%28container_engine%29_logo.svg/320px-Docker_%28container_engine%29_logo.svg.png)

Ab Version 4.1 gibt es jetzt dank der Mithilfe von [janjagusch](https://github.com/janjagusch) ein erstes [Dockerfile](Dockerfile), mit dem sowohl die Anwendung gebaut, als auch im Container gestartet werden kann.
Unter [doc/build.adoc](doc/build.adoc) ist der Aufruf beschrieben.
Auch der manuelle Bau der Anwendung ist dort beschrieben.

Inzwischen gibt es unter https://hub.docker.com/r/oboehm/gdv.xport ein dockerhub-Repository, in dem das Docker-Image über

```
docker pull oboehm/gdv.xport
```

abgeholt werden kann.


## GIT Branching-Modell

Entwickelt wird nach [A successful Git branching model](http://nvie.com/posts/a-successful-git-branching-model/) von Vincent Driessen.
Die Weiterentwicklung der aktuellen Version findet auf dem [develop](https://github.com/oboehm/gdv.xport/tree/develop)-Branch statt, waehrend das letzte Release im Release-Zweig (derzeit: [release/5.x](https://github.com/oboehm/gdv.xport/tree/release/5.x)) zu finden ist.



## Weitere Infos

Unter [doc](doc/) ist das Wichtigste für Entwickler zusammengefasst.
Weitere Infos wie z.B. die [JavaDocs](http://www.aosd.de/gdv.xport/apidocs/index.html) finden Sie auch auf der [Maven Projekt-Seite](http://www.aosd.de/gdv.xport/) .

* [Release Notes](CHANGELOG.md)
* Projekt-Seite: http://www.aosd.de/gdv.xport/
* [Architektur-Doku](src/asciidoc/README.adoc) mit [Glossar](src/asciidoc/de/12_glossary.adoc)
* die eigentliche [gdv-xport-lib](lib/README.adoc)-Bibliothek
* [Hello-World](doc/manual/hello.adoc) und andere [Anleitungen](doc/manual/README.adoc)
* [GDV Online-Handbuch](http://www.gdv-online.de/vuvm/bestand/rel2023/samenue.html) zu den verschiedene Satzarten


### Umfragen

* Umfrage zu [Bezeichner-Konstanten](https://nuudel.digitalcourage.de/CqLHF4HX5SfKZ24I)