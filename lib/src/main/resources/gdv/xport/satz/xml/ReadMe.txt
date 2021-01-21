2018er-Version: Satz-Versionen in 0220.010.X:
entgegen den GDV-Richtlinien haben die 0220er/0221er-Satzarten unterschiedliche Versionen:

0220.010.0: 2.1

0220.010.2.1: 2.2
0220.010.2.6: 2.1
0220.010.2.7: 2.1
0220.010.2.8: 2.1
0220.010.2.9: 2.1

0220.010.5.1: 2.2
0220.010.5.6: 2.1
0220.010.5.8: 2.1
0220.010.5.9: 2.1

0220.010.6.1: 2.2
0220.010.6.6: 2.1
0220.010.6.8: 2.1
0220.010.6.9: 2.1

0220.010.7.1: 2.2
0220.010.7.6: 2.1
0220.010.7.8: 2.1
0220.010.7.9: 2.1

0220.010.9.1: 2.2
0220.010.9.6: 2.1
0220.010.9.7: 2.1
0220.010.9.8: 2.1
0220.010.9.9: 2.1

0220.010.2.1: 2.2
0220.010.2.6: 2.1
0220.010.2.7: 2.1
0220.010.2.8: 2.1
0220.010.2.9: 2.1

0220.010.2.1: 2.2
0220.010.2.6: 2.1
0220.010.2.7: 2.1
0220.010.2.8: 2.1
0220.010.2.9: 2.1

0220.010.13.1: 2.2
0220.010.13.6: 2.1
0220.010.13.8: 2.1
0220.010.13.9: 2.1

0220.010.48.1: 2.2
0220.010.48.6: 2.1
0220.010.48.7: 2.1
0220.010.48.8: 2.1
0220.010.48.9: 2.1

Die GDV-Richlinien findest du hier:
http://www.gdv-online.de/vuvm/bestand/faq.html

unter dem Abschnitt: "Wie erfolgt die Versionierung in der Sparte Leben":

"In Leben müssen bei einer Änderung in einem oder mehreren 0220 bzw. 0221 Satzarten 
 immer zwingend alle 0220 bzw. 0221 Satzarten als "versionsrelevante Änderung" hochgezählt 
 werden (auch wenn es in diesen Satzarten keine direkten Änderungen bzw. neuen Datenfelder 
 gab), da dies sonst im Vorsatz nicht abbildbar ist."
---------------------------

Mindestens seit der 2009er-Version gibt es 7 Teildatensätze, bei denen die Feld-Nr des letzten Feldes
größer ist als die Anzahl der Felder. Diese Fehler wurden (und werden vermutlich!) niemals korrigiert:

2018er-Version: in SA0100, TD1: es gibt kein Feld-Nr 27! Die SatzNr ist Feld 26 !!!
2018er-Version: in SA0210.050, TD1: es gibt kein Feld-Nr 35! Die SatzNr ist Feld 34 !!!
2018er-Version: in SA0220.010.13.1, TD1: es gibt kein Feld-Nr 46! Die Satznummer ist Feld 45 !!!
2018er-Version: in SA0600, TD2: es gibt kein Feld-Nr 13! Die Satznummer ist Feld 12 !!!
2018er-Version: in SA0600, TD3: es gibt kein Feld-Nr 14! Die Satznummer ist Feld 13 !!!
2018er-Version: in SA9950, TD1: es gibt kein Feld-Nr 11! Die Satznummer ist Feld 10 !!!
2018er-Version: in SA9951, TD1: es gibt kein Feld-Nr 11! Die Satznummer ist Feld 10 !!!

2015er-Version: in SA0100, TD1: es gibt kein Feld-Nr 27! Die SatzNr ist Feld 26 !!!
2015er-Version: in SA0210.050, TD1: es gibt kein Feld-Nr 35! Die SatzNr ist Feld 34 !!!
2015er-Version: in SA0220.010.13.1, TD1: es gibt kein Feld-Nr 46! Die Satznummer ist Feld 45 !!!
2015er-Version: in SA0600, TD2: es gibt kein Feld-Nr 13! Die Satznummer ist Feld 12 !!!
2015er-Version: in SA0600, TD3: es gibt kein Feld-Nr 14! Die Satznummer ist Feld 13 !!!
2015er-Version: in SA9950, TD1: es gibt kein Feld-Nr 11! Die Satznummer ist Feld 10 !!!
2015er-Version: in SA9951, TD1: es gibt kein Feld-Nr 11! Die Satznummer ist Feld 10 !!!

2013er-Version: in SA0100, TD1: es gibt kein Feld-Nr 27! Die SatzNr ist Feld 26 !!!
2013er-Version: in SA0210.050, TD1: es gibt kein Feld-Nr 35! Die SatzNr ist Feld 34 !!!
2013er-Version: in SA0220.010.13.1, TD1: es gibt kein Feld-Nr 46! Die Satznummer ist Feld 45 !!!
2013er-Version: in SA0600, TD2: es gibt kein Feld-Nr 13! Die Satznummer ist Feld 12 !!!
2013er-Version: in SA0600, TD3: es gibt kein Feld-Nr 14! Die Satznummer ist Feld 13 !!!
2013er-Version: in SA9950, TD1: es gibt kein Feld-Nr 11! Die Satznummer ist Feld 10 !!!
2013er-Version: in SA9951, TD1: es gibt kein Feld-Nr 11! Die Satznummer ist Feld 10 !!!

2009er-Version: in SA0100, TD1: es gibt kein Feld-Nr 27! Die SatzNr ist Feld 26 !!!
2009er-Version: in SA0210.050, TD1: es gibt kein Feld-Nr 35! Die SatzNr ist Feld 34 !!!
2009er-Version: in SA0220.010.13.1, TD1: es gibt kein Feld-Nr 46! Die Satznummer ist Feld 45 !!!
2009er-Version: in SA9950, TD1: es gibt kein Feld-Nr 11! Die Satznummer ist Feld 10 !!!

Deshalb musste ich auch die Methode "Teildatensatz.getFeld(int)" anpassen:
In den o.g. Situationen wird der übergebene Feld-Index um 1 erniedrigt.


 