package HackerRank.RestAPI;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


class Result {
    public static int getTotalGoals(String team, int year) {
        String basic = "https://jsonmock.hackerrank.com/api/football_matches?year="+year;
        String home_url = basic+"&team1="+team;
        String visiting_url = basic+"&team2="+team;
        try {
            int homeGoals = getGoals(home_url, "team1", 1, 0);
            int visitingGoals = getGoals(visiting_url, "team2", 1, 0);
            return homeGoals + visitingGoals;
        } catch (Exception e) {
            return 0;
        }
    }

    public static int getGoals(String teamUrl, String teamtype, int page, int totalGoals) throws Exception {
        String response = getResponse(teamUrl, page);
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(response);
        JSONObject jsonResponse = (JSONObject) obj;
        int totalPages = Integer.parseInt(jsonResponse.get("total_pages").toString());
        JSONArray data = (JSONArray) jsonResponse.get("data");
        for (Object e : data) {
            JSONObject object = (JSONObject) e;
            totalGoals += Integer.parseInt(object.get(teamtype+"goals").toString());
        }
        return totalPages==page? totalGoals : getGoals(teamUrl, teamtype, page+1, totalGoals);

    }

    public static String getResponse(String endpoint, int page) throws MalformedURLException, IOException, ProtocolException {
        URL url = new URL(endpoint+"&page="+page);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.addRequestProperty("Content-Type", "application/json");
        int status = con.getResponseCode();
        if(status<200 || status>=300) {
            throw new IOException("Error in reading data with status:"+status);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String response;
        StringBuilder sb = new StringBuilder();
        while((response = br.readLine())!=null) {
            sb.append(response);
        }

        br.close();
        con.disconnect();

        return sb.toString();
    }
}

public class FootballMatches {
    public static void main(String[] args) throws Exception {
        int year = 2014;
        String team = "No Team"; //Chelsea
        System.out.println(Result.getTotalGoals(team, year));
    }

}
