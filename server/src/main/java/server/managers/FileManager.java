package server.managers;

import common.collections.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import server.utility.Deserializer;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.NoSuchElementException;
import common.utility.CustomConsole;

/**
 * Класс, управляющий работой с внешними файлами
 */
public class FileManager {
    private final String fileName="data/JSON.json";
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new Deserializer())
            .setPrettyPrinting()
            .serializeNulls()
            .create();
    public FileManager(String fileName){

    }

    /**
     * Перезаписывает коллекцию в файл с исходными данными
     * @param collection коллекция, которую нужно записать в файл
     */
    public void writeCollection(Collection<Person> collection) {
        try (BufferedWriter collectionPrintWriter = new BufferedWriter(new FileWriter(fileName))) {
            collectionPrintWriter.write(gson.toJson(collection));
            CustomConsole.printLn("Коллекция успешна сохранена в файл!");
        } catch (IOException exception) {
            CustomConsole.printError("Загрузочный файл не может быть открыт!");
        }
    }

    /**
     * Считывает данные из файла для создания коллекции
     * @return Созданная из исходных данных коллекция
     */
    public ArrayDeque<Person> readCollection() {
        if (fileName != null && !fileName.isEmpty()) {
            try (var fileReader = new FileReader(fileName)) {
                var collectionType = new TypeToken<ArrayDeque<Person>>() {}.getType();
                var reader = new BufferedReader(fileReader);

                var jsonString = new StringBuilder();

                String line;
                while((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (!line.equals("")) {
                        jsonString.append(line);
                    }
                }

                if (jsonString.length() == 0) {
                    jsonString = new StringBuilder("[]");
                    throw new NoSuchElementException();
                }
                ArrayDeque<Person> collection = gson.fromJson(jsonString.toString(), collectionType);

                System.out.println("Коллекция успешна загружена!");
                return collection;

            } catch (FileNotFoundException exception) {
                CustomConsole.printError("Загрузочный файл не найден!");
            } catch (NoSuchElementException exception) {
                CustomConsole.printError("Загрузочный файл пуст!");
            } catch (JsonParseException exception) {
                CustomConsole.printError("В загрузочном файле не обнаружена необходимая коллекция!");
            } catch (IllegalStateException | IOException exception) {
                CustomConsole.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        else{
            CustomConsole.printError("Некорректные данные в файле!");}

        return new ArrayDeque<>();
}
  }

