package servis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import model.Instruktor;
import model.InstruktorKvalifikacija;
import model.Polaznik;
import model.Upisnica;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Servisna klasa koja pruza funkcionalnosti za rad sa JSON formatom.
 * Omogucava serijalizaciju i deserijalizaciju domenskih objekata u JSON fajlove,
 * rucno kreiranje JSON objekata, kao i pozivanje eksternog web servisa
 * za konverziju valuta.
 *
 * @author Tijana
 * @version 1.0
 * @see Instruktor
 * @see Polaznik
 * @see Upisnica
 */
public class JsonServis {

    /**
     * Serijalizuje listu instruktora u JSON fajl u pretty print formatu.
     * Null vrednosti atributa se takodje upisuju u fajl.
     *
     * @param instruktori lista instruktora koja se serijalizuje
     * @param putanja putanja do JSON fajla u koji se upisuju podaci
     * @throws IOException ako dodje do greske pri radu sa fajlom
     */
    public void serijalizujInstruktore(List<Instruktor> instruktori, String putanja) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(putanja), StandardCharsets.UTF_8)) {
            writer.write(gson.toJson(instruktori));
        }
    }

    /**
     * Deserijalizuje listu instruktora iz JSON fajla.
     *
     * @param putanja putanja do JSON fajla iz kojeg se ucitavaju podaci
     * @return lista instruktora ucitana iz fajla
     * @throws IOException ako dodje do greske pri radu sa fajlom
     */
    public List<Instruktor> deserijalizujInstruktore(String putanja) throws IOException {
        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<List<Instruktor>>(){}.getType();
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(putanja), StandardCharsets.UTF_8)) {
            return gson.fromJson(reader, listType);
        }
    }

    /**
     * Serijalizuje listu polaznika u JSON fajl u pretty print formatu.
     * Null vrednosti atributa se takodje upisuju u fajl.
     *
     * @param polaznici lista polaznika koja se serijalizuje
     * @param putanja putanja do JSON fajla u koji se upisuju podaci
     * @throws IOException ako dodje do greske pri radu sa fajlom
     */
    public void serijalizujPolaznike(List<Polaznik> polaznici, String putanja) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(putanja), StandardCharsets.UTF_8)) {
            writer.write(gson.toJson(polaznici));
        }
    }

    /**
     * Deserijalizuje listu polaznika iz JSON fajla.
     *
     * @param putanja putanja do JSON fajla iz kojeg se ucitavaju podaci
     * @return lista polaznika ucitana iz fajla
     * @throws IOException ako dodje do greske pri radu sa fajlom
     */
    public List<Polaznik> deserijalizujPolaznike(String putanja) throws IOException {
        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<List<Polaznik>>(){}.getType();
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(putanja), StandardCharsets.UTF_8)) {
            return gson.fromJson(reader, listType);
        }
    }

    /**
     * Serijalizuje listu upisnica u JSON fajl u pretty print formatu.
     * Null vrednosti atributa se takodje upisuju u fajl.
     *
     * @param upisnice lista upisnica koja se serijalizuje
     * @param putanja putanja do JSON fajla u koji se upisuju podaci
     * @throws IOException ako dodje do greske pri radu sa fajlom
     */
    public void serijalizujUpisnice(List<Upisnica> upisnice, String putanja) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(putanja), StandardCharsets.UTF_8)) {
            writer.write(gson.toJson(upisnice));
        }
    }

    /**
     * Deserijalizuje listu upisnica iz JSON fajla.
     *
     * @param putanja putanja do JSON fajla iz kojeg se ucitavaju podaci
     * @return lista upisnica ucitana iz fajla
     * @throws IOException ako dodje do greske pri radu sa fajlom
     */
    public List<Upisnica> deserijalizujUpisnice(String putanja) throws IOException {
        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<List<Upisnica>>(){}.getType();
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(putanja), StandardCharsets.UTF_8)) {
            return gson.fromJson(reader, listType);
        }
    }

    /**
     * Serijalizuje listu instruktora u JSON fajl rucnim kreiranjem JSON objekata,
     * bez automatskog mapiranja ka klasi. Svaki instruktor se upisuje sa imenom,
     * prezimenom, emailom i listom kvalifikacija.
     * Lista instruktora i lista kvalifikacija svakog instruktora se transformisu
     * u JSON objekte preko Stream API-ja (stream, map, lambda izrazi) umesto
     * ugnjezdenih for petlji.
     *
     * @param instruktori lista instruktora koja se serijalizuje
     * @param putanja putanja do JSON fajla u koji se upisuju podaci
     * @throws IOException ako dodje do greske pri radu sa fajlom
     */
    public void serijalizujInstruktoreRucno(List<Instruktor> instruktori, String putanja) throws IOException {
        JsonArray niz = new JsonArray();

        instruktori.stream()
                .map(this::instruktorUJson)
                .forEach(niz::add);

        try (Writer writer = new OutputStreamWriter(new FileOutputStream(putanja), StandardCharsets.UTF_8)) {
            writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(niz));
        }
    }

    /**
     * Pomocna metoda koja od jednog instruktora pravi odgovarajuci JsonObject,
     * ukljucujuci i niz njegovih kvalifikacija (dobijen preko Stream API-ja).
     *
     * @param i instruktor koji se pretvara u JSON objekat
     * @return JsonObject koji predstavlja instruktora
     */
    private JsonObject instruktorUJson(Instruktor i) {
        JsonObject obj = new JsonObject();
        obj.addProperty("ime", i.getIme());
        obj.addProperty("prezime", i.getPrezime());
        obj.addProperty("email", i.getEmail());

        JsonArray kvalifikacije = new JsonArray();
        if (i.getInstruktorKvalifikacije() != null) {
            i.getInstruktorKvalifikacije().stream()
                    .map(this::kvalifikacijaUJson)
                    .forEach(kvalifikacije::add);
        }
        obj.add("kvalifikacije", kvalifikacije);
        return obj;
    }

    /**
     * Pomocna metoda koja od jedne instruktorske kvalifikacije pravi
     * odgovarajuci JsonObject sa tipom, organizacijom i nivoom.
     *
     * @param ik instruktorska kvalifikacija koja se pretvara u JSON objekat
     * @return JsonObject koji predstavlja kvalifikaciju
     */
    private JsonObject kvalifikacijaUJson(InstruktorKvalifikacija ik) {
        JsonObject kvalObj = new JsonObject();
        kvalObj.addProperty("tip", ik.getKvalifikacija().getTip());
        kvalObj.addProperty("organizacija", ik.getKvalifikacija().getOrganizacija());
        kvalObj.addProperty("nivo", ik.getNivo().toString());
        return kvalObj;
    }

    /**
     * Poziva eksterni web servis za kurseve valuta i vraca kurs dinara prema evru.
     * Koristi ExchangeRate API na adresi https://open.er-api.com/v6/latest/RSD.
     *
     * @return kurs RSD prema EUR kao decimalni broj
     * @throws Exception ako dodje do greske pri pozivu web servisa ili parsiranju odgovora
     * @see <a href="https://open.er-api.com">ExchangeRate API</a>
     */
    public double vratiKursRsdEur() throws Exception {
        URL url = new URL("https://open.er-api.com/v6/latest/RSD");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JsonObject jsonObject = JsonParser.parseString(response.toString()).getAsJsonObject();
        JsonObject rates = jsonObject.getAsJsonObject("rates");
        return rates.get("EUR").getAsDouble();
    }

    /**
     * Konvertuje iznos iz dinara u evre koristeci trenutni kurs sa eksternog web servisa.
     *
     * @param cenaRsd iznos u dinarima koji se konvertuje
     * @return iznos konvertovan u evre
     * @throws Exception ako dodje do greske pri pozivu web servisa
     * @see #vratiKursRsdEur()
     */
    public double konvertujUEvre(double cenaRsd) throws Exception {
        double kurs = vratiKursRsdEur();
        return cenaRsd * kurs;
    }
}
