import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Connect {
    public static void main(String[] args) throws IOException {
        TM1 koneksisaya = new TM1();
        URL myAddress = koneksisaya.buildURL
                ("https://farmasi.mimoapps.xyz/mimoqss2auyqD1EAlkgZCOhiffSsFl6QqAEIGtM");
        String response = koneksisaya.getResponseFromHttpUrl(myAddress);

        assert response != null;
        JSONArray responseJSON = new JSONArray(response);
        ArrayList<ResponModel> responseModel = new ArrayList<>();
        for (int i = 0; i < responseJSON.length(); i++) {
            ResponModel resModel = new ResponModel();
            JSONObject myJSONObject = responseJSON.getJSONObject(i);
            resModel.setBrg(myJSONObject.getString("i_name"));
            resModel.setHrg(String.valueOf(Integer.parseInt(myJSONObject.getString("i_sell"))));

            responseModel.add(resModel);

        }

        System.out.println("\n           Respon adalah ");
        System.out.println("Nama barang dan obat yang berawalan huruf S  \n");
        System.out.println("-------------------------------------------");

        for (int i = 0; i < responseModel.size(); i++) {
            if (responseModel.get(i).getBrg().startsWith("S") && Integer.parseInt(responseModel.get(i).getHrg()) < 7000) {
                System.out.println(responseModel.get(i).getBrg());
                System.out.println(responseModel.get(i).getHrg());
            }
        }

    }
}



