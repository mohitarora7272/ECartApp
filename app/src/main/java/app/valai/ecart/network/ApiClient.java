package app.valai.ecart.network;

/**
 * @author by Mohit Arora on 23/8/18.
 * @projectname ECartApp
 */
public class ApiClient {
//    private Retrofit retrofit = null;
//
//    public ApiClient() {
//    }
//
//    public RestClient getClient() {
//        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").setLenient().create();
//
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        // set your desired log level
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        OkHttpClient httpClient = new OkHttpClient.Builder()
//                .connectTimeout(10, TimeUnit.MINUTES)
//                .addInterceptor(logging)
//                .readTimeout(10, TimeUnit.MINUTES).build();
//
//        if (retrofit == null) {
//            retrofit = new Retrofit.Builder().baseUrl("")
//                    .client(httpClient)
//                    .addConverterFactory(GsonConverterFactory.create(gson))
//                    .build();
//        }
//        return retrofit.create(RestClient.class);
//    }
//
//    public Retrofit getRetrofit() {
//        return retrofit;
//    }
}