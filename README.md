# 💬 JavaChatSystem 
Englische Version hier: [English](README.md)<br>
JChat ist ein Chat Programm welches mit Java entwickelt wurde. Es benutzt eine Websocket Library um mit den Clients zu kommunizieren.

### ✨ Features
* MySQL / SQLite Datenbanken für die Datenspeicherung
* Konfigurationsdatei (siehe [config.properties](#-Die-Konfigurationsdatei))
* Nicht blockierendes Paket Management

### 🚧 Geplante Features
* [x] PreparedStatements benutzen um SQL Injections besser vorzubeugen
* [x] Wartezeit für eine neue Registrierung hinzufügen
* [ ] Hochladen von Dateien
* [ ] Hochladen von Bildern
<br/>

### 🔨 Selber kompilieren
1. Diese Repository mit `git clone https://github.com/myyxl/jchat-server` klonen
2. Das Gradle Projekt in eine IDE importieren
3. Den Gradle Task `shadowJar` ausführen, welcher sich in `shadow` befindet
4. Die kompilierte Datei befindet sich in `build/libs/`

### 🚀 Ausführen
1. Kompiliere den Code oder lade die neuste Version herunter
2. Starte den Server wie folgt `java -jar <server-datei>`
3. Der Server wird eine `config.properties` erstellen (siehe [config.properties](#-Die-Konfigurationsdatei))
4. Starte den Server erneut, nachdem die Konfigurationsdatei überprüft wurde<br/><br/><br/>


## 📄 Die Konfigurationsdatei
Die [config.properties](config.properties) enthält wichtige Informationen, z.B. den Benutzernamen und das Passwort für den MySQL Server.  
| Name | Beschreibung | Standardwert |
|:-------------:|:-----------:|:------------:|
| database.table   | Der Name der Tabelle in der die Benutzer gespeichert werden | users |
| database.database| Der Name der Datenbank welche benutzt werden soll | jchat |
| database.password| Das Passwort für den MySQL Server | root |
| database.username| Der Benutzername für den MySQL Server | root |
| database.host| Die Adresse des MySQL Servers | 127.0.0.1 |
| database.port| Der Port des MySQL Servers | 3306 |
| server.ip | Die Adresse an der sich der Server binden soll | 0.0.0.0 |
| server.port | Der Port des Websocket Servers | 5333 | 
| server.logging | Boolean Wert ob der Server Nachrichten aufzeichnen soll | false |
| server.auto-discovery | Boolean Wert ob der Server den Clients im selben Netzwerk automatisch die IP senden soll | false |
| storage.default | Der Standardwert für die Datenspeicherung (Möglich sind: `mysql` oder `sqlite`)| sqlite |
