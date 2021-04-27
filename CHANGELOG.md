# Changelog

Hier sind alle wichtigen Änderungen dieses Projekts aufgeführt.
Es ersetzt die ehemaligen **Release Notes** und
[Changes-Report](http://www.aosd.de/gdv.xport/changes-report.html)
von gdv.xport und orientiert sich an 
[Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
genauso wie an [Semantic Versioning](https://semver.org/spec/v2.0.0.html).
Aus Gründen der Übersichtlichkeit sind bei älteren Versionen die einzelnen Patch-Versionen nicht extra aufgeführt, sondern in der Minor-Version.


## [Planned]

- Performance-Optimierung und Verringerung des Speicher-Abdrucks (Footprint).
- ByteAdresse durchgängiger verwenden


## [Unreleased]

### Added

- statt "VUVM2018.xml" kann jetzt über '-Dgdv.XML-Resource=VUVM2015.xml' die 2015er-Version vorgegeben werden (oder andere Versionen)
- XmlSatzFactory erlaubt die Unterstützung mehrerer GDV-Versionen gleichzeitig
- GdvXmlFormatter formattiert (exportiert) jetzt Datensätze in das XML-Format von GDV-Online
- Versionen im Vorsatz können jetzt mit der aktuellen Version aus der XML-Beschreibung vorbelegt werden
- Nachsatz besitzt eine add-Methode
- numerische Felder akzeptieren BigDecimal und können auch nach BigDecimal konvertiert werden
- ByteAdresse ist als neuer Datentyp hinzugekommen und wird bei Teildatensatz.getFeld(..) als Parameter akzeptiert


### Changed

- es werden viel mehr Satzarten direkt auf der XML-Beschreibung in VUVM2018.xml abgeleitet
- Widersprüche in VUVM2018.xml wurden manuell bereinigt
- Enums zur Beschreibung von Datensätzen sind jetzt @deprecated  
- SatzTyp wurde ausgebaut und um of(..)-Methode erweitert
- gdv-xport-service auf OpenAPI / Swagger 3.0 aktualisiert
- _fixed_: Formatierung in Javaoc wird jetzt korrekt mit einem 't' geschrieben
  ([Issue #57](https://github.com/oboehm/gdv.xport/issues/57))



## [4.2.0] - 2020-09-17

### Added

- Vorsatz.setVersion(..) eingeführt

### Changed

- Versionen im Vorsatz werden nicht mehr vorbelegt


## [4.1.0] - 2020-06-08

### Added
- rudimentäre Docker-Unterstützung
  ([Issue #46](https://github.com/oboehm/gdv.xport/issues/46))
- Dokumentation um Bau-Anleitung ergänzt
  ([Issue #42](https://github.com/oboehm/gdv.xport/issues/42))

### Changed
- _fixed_: format-Parameter in `api/v1/Datenpaket*` wird jetzt beachtet
  ([Issue #48](https://github.com/oboehm/gdv.xport/issues/48))
- _fixed_: Fehler in der Swagger-API-Dokumentation behoben
  ([Issue #45](https://github.com/oboehm/gdv.xport/issues/45))


## [4.0.4] - 2020-02-14

### Added
- zusätzlicher Konstruktor für Wagnisart in Satzart 0220.010 und 0221.010
  (behebt [Issue #39](https://github.com/oboehm/gdv.xport/issues/39))
  
### Changed
- _fixed_: Security-Problem mit jackson-databind behoben
  ([Issue #38](https://github.com/oboehm/gdv.xport/issues/38))


## [4.0.3] - 2019-02-16

### Changed
- _fixed_: Umlaut-Problem und fehlerhafter XML-Header
  ([Issue #37](https://github.com/oboehm/gdv.xport/issues/37))


## [4.0.2] - 2019-02-14

### Changed
- _fixed_: Start-Probleme mit Uberjar
  ([Issue #36](https://github.com/oboehm/gdv.xport/issues/36))


## [4.0.1] - 2019-02-02

### Changed
- _fixed_: Problem beim CSV-Export mit Sonderzeichen (Strichpunkt) behoben
  ([Issue #35](https://github.com/oboehm/gdv.xport/issues/35))


### [4.0.0] - 2019-01-20

### Added
- es gibt ein neues Modul 'gdv-xport-deprecated', in der Klassen und Enumerationen verschoben wurden, die 'deprecated' sind.
  Dieses Abhängigkeit wird nur dann benötigt, wenn man noch einer dieser Klassen verwendet

### Changed
- Aktualisierung auf die aktuelle XML-Version von 2018.
- Bezeichner-Klasse wurde aufgeräumt und falsche bzw. nicht benötigte Konstanten entfernt.
  Ebenso wurden manche Konstanten in den Feld-Beschreibungen korrigiert.
- Komplexität reduziert
- Umstellung auf Java 8
- Umstellung REST-Services auf Spring-Boot 2


### [0.0.1 bis 3.2.2] - ab 2009

Die Versionen 3.x und früher sind auf der Maven-Projekt-Seite unter [changes](http://www.aosd.de/gdv.xport/changes-report.html) aufgelistet.
