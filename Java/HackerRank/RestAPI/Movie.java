package HackerRank.RestAPI;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Movie {

    private static final String URL = "https://jsonmock.hackerrank.com/api/movies/search/?Title=";

    public static void main(String[] args) throws IOException, ParseException {
        Scanner sc = new Scanner(System.in);
        String title = sc.next();
        sc.close();

        List<String> titles = getMovieTitles(title);
        Collections.sort(titles);
        titles.forEach(System.out::println);
    }

    private static List<String> getMovieTitles(String subtitle) throws IOException, ParseException {
        List<String> titles = new ArrayList<>();
        int page = 1;
        int totalPage = 1;
        String response;

        while (page <= totalPage) {
            URL obj = new URL(URL + subtitle + "&page=" + page);

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            while ((response = in.readLine()) != null) {
                System.out.print(response);

                JSONObject jsonResponse = new JSONObject();
                JSONParser parser = new JSONParser();
                jsonResponse = (JSONObject) parser.parse(response);
                totalPage = Integer.parseInt(jsonResponse.get("total_pages").toString());
                JSONArray data = (JSONArray) jsonResponse.get("data");

                for (Object e : data) {
                    JSONObject object = (JSONObject)e;
                    String title = object.get("Title").toString();
                    titles.add(title);
                }

            }

            page++;
        }
        return titles;
    }
}
