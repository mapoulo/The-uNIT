package com.example.demo.JsonFiles;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

import org.json.JSONObject;


public class JsonFilesToObjects {

	public JSONObject read() {
		String file = "src/main/java/com/example/demo/JsonFiles/response.json";
		String content = "";
		try {
			content = new String(Files.readAllBytes(Paths.get(file)));
		} catch (Exception e) {
e.printStackTrace();
		}
		return new JSONObject(content);
	}
}
