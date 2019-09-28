import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Ein (sehr) einfacher WebServer (WebServer.java) 
 * Siehe auch:
 * www.java2s.com/Code/Java/Network-Protocol/ASimpleWebServer.htm
 * 
 * @author Jeff Heaton modifiziert für BlueJ von Werner Winzerling
 * @version 1.0
 */

public class WebServer
{
    // Instanzvariablen
    ServerSocket s;
    
    /**
     * Konstruktor
     */
    public WebServer()
    {
         // Webserver erzeugen und Bindung an Port 80
        System.out.println("WebServer erzeugt");                
        try {
            s = new ServerSocket(80);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return;
        }
    } 
    
    /**
    * Ausführen des WebServers
     */
    public void start()
    {
        // Warten auf Verbindungswünsche
        System.out.println("WebServer wartet auf Verbindungswunsch");
        while (true) {
            try {
                // Warten auf Verbindungswunsch
                Socket remote = s.accept();
                // remote = Socket des Clients (für Antwort)
                System.out.println("Verbindung o.k., HTML-Ausgabe");
                // Vom Client gesendete Daten werden (hier) ignoriert.
                // Solange Zeilen einlesen, bis Lehrzeile folgt
                BufferedReader in = new BufferedReader(new InputStreamReader(remote.getInputStream()));
                while (!in.readLine().equals("")) {};
                
                // Ausgabe des Headers
                PrintWriter out =
                    new PrintWriter(remote.getOutputStream());
                out.println("HTTP/1.0 200 OK");
                out.println("Content-Type: text/html");
                // Leerzeile trennt Header vom Body
                out.println("");
                // Ausgabe der HTML-Seite
                out.println("<H1>Das ist eine &Uuml;berschrift</H1>");
                out.flush();  // Gepufferten Daten sofort ausgegeben
                remote.close();
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
    }
}