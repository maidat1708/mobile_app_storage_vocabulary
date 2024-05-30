package storage.vocabulary.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Translator {
    // TODO: Nếu bạn có thông tin tài khoản Premium của riêng bạn, hãy điền vào đây:
    // private static final String CLIENT_ID = "FREE_TRIAL_ACCOUNT";
    // private static final String CLIENT_SECRET = "PUBLIC_SECRET";
    // private static final String ENDPOINT = "http://api.whatsmate.net/v1/translation/translate";
    // private static final String API_KEY = "AIzaSyCzQxURMYOOaqPGt2MS83oaqZJRLHkRnzg";
    @Id
    private int id;
    private String fromLang,toLang,text,translatedText;
    
  
    public Translator() {
    }


    // public Translator translate() throws Exception {
    //     Translate translate = TranslateOptions.newBuilder().setApiKey(API_KEY).build().getService();
    //     Translation translation = translate.translate(this.text, Translate.TranslateOption.targetLanguage(toLang),Translate.TranslateOption.sourceLanguage(fromLang));
    //     System.out.println(translation.getTranslatedText());
    //     this.text =  translation.getTranslatedText();
    //     return this;
        //   TODO: Nên sử dụng một thư viện bên thứ 3 để tạo chuỗi JSON từ một đối tượng
    //   String jsonPayload = new StringBuilder()
    //     .append("{")
    //     .append("\"fromLang\":\"")
    //     .append(this.fromLang)
    //     .append("\",")
    //     .append("\"toLang\":\"")
    //     .append(this.toLang)
    //     .append("\",")
    //     .append("\"text\":\"")
    //     .append(this.text)
    //     .append("\"")
    //     .append("}")
    //     .toString();
  
    //   URL url = new URL(ENDPOINT);
    //   HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    //   conn.setDoOutput(true);
    //   conn.setRequestMethod("POST");
    //   conn.setRequestProperty("X-WM-CLIENT-ID", CLIENT_ID);
    //   conn.setRequestProperty("X-WM-CLIENT-SECRET", CLIENT_SECRET);
    //   conn.setRequestProperty("Content-Type", "application/json");
  
    //   // Sử dụng OutputStreamWriter để đảm bảo mã hóa UTF-8
    //   OutputStreamWriter os = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
    //   os.write(jsonPayload);
    //   os.flush();
    //   os.close();
  
    //   int statusCode = conn.getResponseCode();
    //   BufferedReader br = new BufferedReader(new InputStreamReader(
    //       (statusCode == 200) ? conn.getInputStream() : conn.getErrorStream(), "UTF-8"
    //     ));
    //   String output;
    //   StringBuilder res = new StringBuilder();
    //   while ((output = br.readLine()) != null) {
    //       res.append(output);
    //   }
    //   conn.disconnect();
    //   return res.toString();
    // }


    public Translator(int id, String fromLang, String toLang, String text, String translatedText) {
        this.id = id;
        this.fromLang = fromLang;
        this.toLang = toLang;
        this.text = text;
        this.translatedText = translatedText;
    }




    public String getFromLang() {
        return fromLang;
    }


    public void setFromLang(String fromLang) {
        this.fromLang = fromLang;
    }


    public String getToLang() {
        return toLang;
    }


    public void setToLang(String toLang) {
        this.toLang = toLang;
    }


    public String getText() {
        return text;
    }


    public void setText(String text) {
        this.text = text;
    }



    // public static String getApiKey() {
    //     return API_KEY;
    // }



    public int getId() {
        return id;
    }



    public void setId(int id) {
        this.id = id;
    }




    public String getTranslatedText() {
        return translatedText;
    }




    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }
}