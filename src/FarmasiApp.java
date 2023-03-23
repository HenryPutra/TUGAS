import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FarmasiApp {
    public static void main(String[] args) throws Exception {
        TM1 koneksisaya = new TM1();
        String urlString = "https://farmasi.mimoapps.xyz/mimoqss2auyqD1EAlkgZCOhiffSsFl6QqAEIGtM";
        URL url = koneksisaya.buildURL(urlString);

        String response = koneksisaya.getResponseFromHttpUrl(url);

        JSONObject jsonObject = new JSONObject(response);
        JSONArray itemsArray = jsonObject.getJSONArray("items");

        List<String> matchingItems = new ArrayList<>();

        for (int i = 0; i < itemsArray.length(); i++) {
            JSONObject item = itemsArray.getJSONObject(i);
            String itemName = item.getString("name");
            if (itemName.toUpperCase().startsWith("S")) {
                matchingItems.add(itemName);
            }
        }

        System.out.println("Items starting with S:");
        for (String itemName : matchingItems) {
            System.out.println(itemName);
        }
    }
}
