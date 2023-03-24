import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class FarmasiApp extends JFrame {
    private JButton submitButton;
    private JTextArea resultArea;

    public FarmasiApp() {
        initComponents();
    }

    private void initComponents() {
        // Membuat label dan tombol
        JLabel label = new JLabel("Klik tombol untuk mencari data");
        submitButton = new JButton("Submit");
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);

        // Menambahkan action listener untuk tombol submit
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Mendapatkan data dari URL
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
                        resModel.setStk(Integer.parseInt(myJSONObject.getString("id")));
                        responseModel.add(resModel);

                    }

                    // Menampilkan hasil
                    StringBuilder result = new StringBuilder();
                    result.append("\n           Respon adalah \n");
                    result.append("Nama barang dan obat yang berawalan huruf S  \n");
                    result.append("-------------------------------------------\n");

                    for (int i = 0; i < responseModel.size(); i++) {
                        if (responseModel.get(i).getBrg().startsWith("S") && Integer.parseInt(responseModel.get(i).getHrg()) < 7000){
                            result.append("Nama  Barang = " + responseModel.get(i).getBrg() + "\n");
                            result.append("Harga Barang = " + responseModel.get(i).getHrg() + "\n");
                            result.append("Stok Barang  = " + responseModel.get(i).getStk() + "\n");
                        }
                    }

                    resultArea.setText(result.toString());

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Menambahkan komponen ke frame
        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(submitButton);
        panel.add(resultArea);
        getContentPane().add(panel);

        // Mengatur properti frame
        setTitle("ConnectGUI");
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        FarmasiApp gui = new FarmasiApp();
        gui.setVisible(true);
    }
}
