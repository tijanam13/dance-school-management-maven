package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class JsonServis {

    public void serijalizujInstruktore(List<Instruktor> instruktori, String putanja) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        FileWriter writer = new FileWriter(putanja);
        writer.write(gson.toJson(instruktori));
        writer.close();
    }

    public List<Instruktor> deserijalizujInstruktore(String putanja) throws IOException {
        Gson gson = new GsonBuilder().create();
        FileReader reader = new FileReader(putanja);
        Type listType = new TypeToken<List<Instruktor>>(){}.getType();
        List<Instruktor> instruktori = gson.fromJson(reader, listType);
        reader.close();
        return instruktori;
    }
    
    public void serijalizujPolaznike(List<Polaznik> polaznici, String putanja) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        FileWriter writer = new FileWriter(putanja);
        writer.write(gson.toJson(polaznici));
        writer.close();
    }

    public List<Polaznik> deserijalizujPolaznike(String putanja) throws IOException {
        Gson gson = new GsonBuilder().create();
        FileReader reader = new FileReader(putanja);
        Type listType = new TypeToken<List<Polaznik>>(){}.getType();
        List<Polaznik> polaznici = gson.fromJson(reader, listType);
        reader.close();
        return polaznici;
    }

    public void serijalizujUpisnice(List<Upisnica> upisnice, String putanja) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        FileWriter writer = new FileWriter(putanja);
        writer.write(gson.toJson(upisnice));
        writer.close();
    }

    public List<Upisnica> deserijalizujUpisnice(String putanja) throws IOException {
        Gson gson = new GsonBuilder().create();
        FileReader reader = new FileReader(putanja);
        Type listType = new TypeToken<List<Upisnica>>(){}.getType();
        List<Upisnica> upisnice = gson.fromJson(reader, listType);
        reader.close();
        return upisnice;
    }
}