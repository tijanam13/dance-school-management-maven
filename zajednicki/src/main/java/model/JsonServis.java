package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class JsonServis {

    public void serijalizujInstruktore(List<Instruktor> instruktori, String putanja) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        try (FileWriter writer = new FileWriter(putanja)) {
            writer.write(gson.toJson(instruktori));
        }
    }

    public List<Instruktor> deserijalizujInstruktore(String putanja) throws IOException {
        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<List<Instruktor>>(){}.getType();
        try (FileReader reader = new FileReader(putanja)) {
            return gson.fromJson(reader, listType);
        }
    }

    public void serijalizujPolaznike(List<Polaznik> polaznici, String putanja) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        try (FileWriter writer = new FileWriter(putanja)) {
            writer.write(gson.toJson(polaznici));
        }
    }

    public List<Polaznik> deserijalizujPolaznike(String putanja) throws IOException {
        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<List<Polaznik>>(){}.getType();
        try (FileReader reader = new FileReader(putanja)) {
            return gson.fromJson(reader, listType);
        }
    }

    public void serijalizujUpisnice(List<Upisnica> upisnice, String putanja) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        try (FileWriter writer = new FileWriter(putanja)) {
            writer.write(gson.toJson(upisnice));
        }
    }

    public List<Upisnica> deserijalizujUpisnice(String putanja) throws IOException {
        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<List<Upisnica>>(){}.getType();
        try (FileReader reader = new FileReader(putanja)) {
            return gson.fromJson(reader, listType);
        }
    }

    public void serijalizujInstruktoreRucno(List<Instruktor> instruktori, String putanja) throws IOException {
        JsonArray niz = new JsonArray();

        for (Instruktor i : instruktori) {
            JsonObject obj = new JsonObject();
            obj.addProperty("ime", i.getIme());
            obj.addProperty("prezime", i.getPrezime());
            obj.addProperty("email", i.getEmail());

            JsonArray kvalifikacije = new JsonArray();
            if (i.getInstruktorKvalifikacije() != null) {
                for (InstruktorKvalifikacija ik : i.getInstruktorKvalifikacije()) {
                    JsonObject kvalObj = new JsonObject();
                    kvalObj.addProperty("tip", ik.getKvalifikacija().getTip());
                    kvalObj.addProperty("organizacija", ik.getKvalifikacija().getOrganizacija());
                    kvalObj.addProperty("nivo", ik.getNivo().toString());
                    kvalifikacije.add(kvalObj);
                }
            }
            obj.add("kvalifikacije", kvalifikacije);
            niz.add(obj);
        }

        try (FileWriter writer = new FileWriter(putanja)) {
            writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(niz));
        }
    }
}